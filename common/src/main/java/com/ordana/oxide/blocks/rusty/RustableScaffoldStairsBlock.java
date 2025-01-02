package com.ordana.oxide.blocks.rusty;

import com.ordana.oxide.reg.ModTags;
import net.mehvahdjukaar.moonlight.api.block.ModStairBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class RustableScaffoldStairsBlock extends ModStairBlock implements Rustable {

    private final RustLevel rustLevel;

    public RustableScaffoldStairsBlock(RustLevel rustLevel, Supplier<Block> baseBlockState, Properties settings) {
        super(baseBlockState, Rustable.setRandomTicking(settings, rustLevel));
        this.rustLevel = rustLevel;
    }

    @Override
    public RustLevel getAge() {
        return this.rustLevel;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        this.tryWeather(state, serverLevel, pos, random);
    }


    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    public float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return (adjacentState.is(ModTags.SCAFFOLDS) && direction == Direction.UP) || super.skipRendering(state, adjacentState, direction);
    }
}
