package com.ordana.oxide.blocks.rusty;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.ordana.oxide.configs.CommonConfigs;
import com.ordana.oxide.reg.ModBlocks;
import com.ordana.oxide.reg.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import java.util.Optional;
import java.util.function.Supplier;

public interface Rustable extends ChangeOverTimeBlock<Rustable.RustLevel> {
    Supplier<BiMap<Block, Block>> RUST_LEVEL_INCREASES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(ModBlocks.CUT_IRON.get(),ModBlocks.WEATHERED_CUT_IRON.get())
            .put(ModBlocks.WEATHERED_CUT_IRON.get(), ModBlocks.RUSTED_CUT_IRON.get())
            .put(ModBlocks.CUT_IRON_SLAB.get(), ModBlocks.WEATHERED_CUT_IRON_SLAB.get())
            .put(ModBlocks.WEATHERED_CUT_IRON_SLAB.get(), ModBlocks.RUSTED_CUT_IRON_SLAB.get())
            .put(ModBlocks.CUT_IRON_STAIRS.get(), ModBlocks.WEATHERED_CUT_IRON_STAIRS.get())
            .put(ModBlocks.WEATHERED_CUT_IRON_STAIRS.get(), ModBlocks.RUSTED_CUT_IRON_STAIRS.get())
            .put(ModBlocks.PLATE_IRON.get(), ModBlocks.WEATHERED_PLATE_IRON.get())
            .put(ModBlocks.WEATHERED_PLATE_IRON.get(), ModBlocks.RUSTED_PLATE_IRON.get())
            .put(ModBlocks.PLATE_IRON_SLAB.get(), ModBlocks.WEATHERED_PLATE_IRON_SLAB.get())
            .put(ModBlocks.WEATHERED_PLATE_IRON_SLAB.get(), ModBlocks.RUSTED_PLATE_IRON_SLAB.get())
            .put(ModBlocks.PLATE_IRON_STAIRS.get(), ModBlocks.WEATHERED_PLATE_IRON_STAIRS.get())
            .put(ModBlocks.WEATHERED_PLATE_IRON_STAIRS.get(), ModBlocks.RUSTED_PLATE_IRON_STAIRS.get())

            .put(Blocks.IRON_DOOR, ModBlocks.WEATHERED_IRON_DOOR.get())
            .put(ModBlocks.WEATHERED_IRON_DOOR.get(), ModBlocks.RUSTED_IRON_DOOR.get())
            .put(Blocks.IRON_TRAPDOOR, ModBlocks.WEATHERED_IRON_TRAPDOOR.get())
            .put(ModBlocks.WEATHERED_IRON_TRAPDOOR.get(), ModBlocks.RUSTED_IRON_TRAPDOOR.get())
            .put(Blocks.IRON_BARS, ModBlocks.WEATHERED_IRON_BARS.get())
            .put(ModBlocks.WEATHERED_IRON_BARS.get(), ModBlocks.RUSTED_IRON_BARS.get())

            .put(ModBlocks.WAXED_IRON_DOOR.get(), ModBlocks.WAXED_WEATHERED_IRON_DOOR.get())
            .put(ModBlocks.WAXED_WEATHERED_IRON_DOOR.get(), ModBlocks.WAXED_RUSTED_IRON_DOOR.get())
            .put(ModBlocks.WAXED_IRON_TRAPDOOR.get(), ModBlocks.WAXED_WEATHERED_IRON_TRAPDOOR.get())
            .put(ModBlocks.WAXED_WEATHERED_IRON_TRAPDOOR.get(), ModBlocks.WAXED_RUSTED_IRON_TRAPDOOR.get())
            .put(ModBlocks.WAXED_IRON_BARS.get(), ModBlocks.WAXED_WEATHERED_IRON_BARS.get())
            .put(ModBlocks.WAXED_WEATHERED_IRON_BARS.get(), ModBlocks.WAXED_RUSTED_IRON_BARS.get())

            .build());

    Supplier<BiMap<Block, Block>> RUST_LEVEL_DECREASES = Suppliers.memoize(() -> RUST_LEVEL_INCREASES.get().inverse());

    static Optional<Block> getDecreasedRustBlock(Block block) {
        return Optional.ofNullable(RUST_LEVEL_DECREASES.get().get(block));
    }

    static Block getUnaffectedRustBlock(Block block) {
        Block block2 = block;
        Block block3 = RUST_LEVEL_DECREASES.get().get(block2);
        while (block3 != null) {
            block2 = block3;
            block3 = RUST_LEVEL_DECREASES.get().get(block2);
        }
        return block2;
    }

    default Optional<BlockState> getPrevious(BlockState state) {
        return Rustable.getDecreasedRustBlock(state.getBlock()).map(block -> block.withPropertiesOf(state));
    }

    static Optional<Block> getIncreasedRustBlock(Block block) {
        return Optional.ofNullable(RUST_LEVEL_INCREASES.get().get(block));
    }

    static BlockState getUnaffectedRustState(BlockState state) {
        return Rustable.getUnaffectedRustBlock(state.getBlock()).withPropertiesOf(state);
    }

    @Override
    default Optional<BlockState> getNext(BlockState state) {
        return Rustable.getIncreasedRustBlock(state.getBlock()).map(block -> block.withPropertiesOf(state));
    }

    default float getChanceModifier() {
        if (this.getAge() == RustLevel.UNAFFECTED) {
            return 0.75f;
        }
        return 1.0f;
    }

    enum RustLevel {
        UNAFFECTED,
        WEATHERED,
        RUSTED;

        public boolean canScrape() {
            return this != RUSTED;
        }
    }

    default int getInfluenceRadius() {
        return CommonConfigs.RUSTING_INFLUENCE_RADIUS.get();
    }

    //same as the base one but has configurable radius
    @Override
    default void applyChangeOverTime(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {

        int airCheck = 0;
        int wetness = 0;
        for (Direction dir : Direction.values()) {
            var dirPos = pos.relative(dir);
            var dirState = level.getBlockState(dirPos);
            if (dirState.isAir()) airCheck += 1;

            if (level.isRainingAt(dirPos)) {
                if (dir == Direction.UP) wetness += 5;
                wetness += 1;
            }
            if (level.getFluidState(dirPos).is(Fluids.WATER)) wetness += 5;
            if (dirState.is(Blocks.BUBBLE_COLUMN)) wetness += 10;
            if (dirState.getBlock() instanceof Rustable rusty) {
                if (rusty.getNext(dirState).isEmpty()) wetness += 100;
            }
        }
        if (airCheck == 0) return;

        for (int i = 0; i < wetness; i++) {
            if (level.random.nextInt(50) == 1) {
                this.getNext(state).ifPresent(s -> level.setBlockAndUpdate(pos, s));
                return;
            }
        }

        /*
        int age = this.getAge().ordinal();
        int j = 0;
        int k = 0;
        int affectingDistance = this.getInfluenceRadius();
        for (BlockPos blockpos : BlockPos.withinManhattan(pos, affectingDistance, affectingDistance, affectingDistance)) {
            int distance = blockpos.distManhattan(pos);
            if (distance > affectingDistance) {
                break;
            }

            if (!blockpos.equals(pos)) {
                BlockState blockstate = serverLevel.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (block instanceof ChangeOverTimeBlock<?> changeOverTimeBlock) {
                    Enum<?> ageEnum = changeOverTimeBlock.getAge();
                    //checks if they are of same age class
                    if (this.getAge().getClass() == ageEnum.getClass()) {
                        int neighbourAge = ageEnum.ordinal();
                        if (neighbourAge < age) {
                            return;
                        }

                        if (neighbourAge > age) {
                            ++k;
                        } else {
                            ++j;
                        }
                    }
                }
            }
        }

        float f = (float) (k + 1) / (float) (k + j + 1);
        float f1 = f * f * this.getChanceModifier();
        if (randomSource.nextFloat() < f1) {
            this.getNext(state).ifPresent(s -> serverLevel.setBlockAndUpdate(pos, s));
        }

         */

    }

    default void tryWeather(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        applyChangeOverTime(state, level, pos, random);

        /*
        if (!state.is(ModTags.RUSTED_IRON)) {
            var canWeather = false;
            for (Direction direction : Direction.values()) {
                var targetPos = pos.relative(direction);
                BlockState neighborState = level.getBlockState(targetPos);
                if (neighborState.is(Blocks.BUBBLE_COLUMN) && random.nextFloat() < CommonConfigs.RUSTING_RATE.get()) {
                    canWeather = true;
                } else if (neighborState.getFluidState().is(FluidTags.WATER) && random.nextFloat() < CommonConfigs.RUSTING_RATE.get() / 1.25) {
                    canWeather = true;
                } else if (state.is(ModTags.CLEAN_IRON)) {
                    if (neighborState.is(Blocks.AIR) && random.nextFloat() < CommonConfigs.RUSTING_RATE.get() / 5) {
                        canWeather = true;
                    }
                } else if (state.is(ModTags.EXPOSED_IRON) || state.is(ModTags.CLEAN_IRON) && level.isRaining() && random.nextFloat() < CommonConfigs.RUSTING_RATE.get() / 2) {
                    if (level.isRainingAt(pos.above())) {
                        canWeather = true;
                    } else if (level.isRainingAt(targetPos) && level.getBlockState(pos.above()).is(ModTags.WEATHERED_IRON) && random.nextFloat() < CommonConfigs.RUSTING_RATE.get() / 3) {
                        if (BlockPos.withinManhattanStream(pos, 2, 2, 2)
                                .map(level::getBlockState)
                                .filter(b -> b.is(ModTags.WEATHERED_IRON))
                                .toList().size() <= 9) {
                            canWeather = true;
                        }
                    }
                }
            }
            if (canWeather) {
                applyChangeOverTime(state, level, pos, random);
            }
        }

         */
    }

    static BlockBehaviour.Properties setRandomTicking(BlockBehaviour.Properties properties, RustLevel rustLevel) {
        properties.isRandomlyTicking = rustLevel != RustLevel.RUSTED;
        return properties;
    }
}
