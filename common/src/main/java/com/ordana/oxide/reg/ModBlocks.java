package com.ordana.oxide.reg;

import com.ordana.oxide.Oxide;
import com.ordana.oxide.blocks.rusty.*;
import net.mehvahdjukaar.moonlight.api.block.ModStairBlock;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.function.Supplier;

public class ModBlocks {

    public static void init() {
    }

    public static <T extends Block> Supplier<T> regBlock(String name, Supplier<T> block) {
        return RegHelper.registerBlock(Oxide.res(name), block);
    }

    public static Supplier<BlockItem> regBlockItem(String name, Supplier<? extends Block> blockSup, Item.Properties properties) {
        return RegHelper.registerItem(Oxide.res(name), () -> new BlockItem(blockSup.get(), properties));
    }

    public static <T extends Block> Supplier<T> regWithItem(String name, Supplier<T> blockFactory) {
        Supplier<T> block = regBlock(name, blockFactory);
        regBlockItem(name, block, new Item.Properties());
        return block;
    }


    //plate iron
    public static final Supplier<Block> PLATE_IRON = regWithItem("plate_iron", () ->
            new RustableBlock(Rustable.RustLevel.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WEATHERED_PLATE_IRON = regWithItem("weathered_plate_iron", () ->
            new RustableBlock(Rustable.RustLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> RUSTED_PLATE_IRON = regWithItem("rusted_plate_iron", () ->
            new RustableBlock(Rustable.RustLevel.RUSTED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> PLATE_IRON_STAIRS = regWithItem("plate_iron_stairs", () ->
            new RustableStairsBlock(Rustable.RustLevel.UNAFFECTED, PLATE_IRON, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WEATHERED_PLATE_IRON_STAIRS = regWithItem("weathered_plate_iron_stairs", () ->
            new RustableStairsBlock(Rustable.RustLevel.WEATHERED, PLATE_IRON, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> RUSTED_PLATE_IRON_STAIRS = regWithItem("rusted_plate_iron_stairs", () ->
            new RustableStairsBlock(Rustable.RustLevel.RUSTED, PLATE_IRON, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> PLATE_IRON_SLAB = regWithItem("plate_iron_slab", () ->
            new RustableSlabBlock(Rustable.RustLevel.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WEATHERED_PLATE_IRON_SLAB = regWithItem("weathered_plate_iron_slab", () ->
            new RustableSlabBlock(Rustable.RustLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> RUSTED_PLATE_IRON_SLAB = regWithItem("rusted_plate_iron_slab", () ->
            new RustableSlabBlock(Rustable.RustLevel.RUSTED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    //waxed
    public static final Supplier<Block> WAXED_PLATE_IRON = regWithItem("waxed_plate_iron", () ->
            new Block(noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_WEATHERED_PLATE_IRON = regWithItem("waxed_weathered_plate_iron", () ->
            new Block(noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_RUSTED_PLATE_IRON = regWithItem("waxed_rusted_plate_iron", () ->
            new Block(noTick(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> WAXED_PLATE_IRON_STAIRS = regWithItem("waxed_plate_iron_stairs", () ->
            new ModStairBlock(WAXED_PLATE_IRON, noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_WEATHERED_PLATE_IRON_STAIRS = regWithItem("waxed_weathered_plate_iron_stairs", () ->
            new ModStairBlock(WAXED_WEATHERED_PLATE_IRON, noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_RUSTED_PLATE_IRON_STAIRS = regWithItem("waxed_rusted_plate_iron_stairs", () ->
            new ModStairBlock(WAXED_RUSTED_PLATE_IRON, noTick(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> WAXED_PLATE_IRON_SLAB = regWithItem("waxed_plate_iron_slab", () ->
            new SlabBlock(noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_WEATHERED_PLATE_IRON_SLAB = regWithItem("waxed_weathered_plate_iron_slab", () ->
            new SlabBlock(noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_RUSTED_PLATE_IRON_SLAB = regWithItem("waxed_rusted_plate_iron_slab", () ->
            new SlabBlock(noTick(Blocks.IRON_BLOCK)));


    //cut iron
    public static final Supplier<Block> CUT_IRON = regWithItem("cut_iron", () ->
            new RustableBlock(Rustable.RustLevel.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WEATHERED_CUT_IRON = regWithItem("weathered_cut_iron", () ->
            new RustableBlock(Rustable.RustLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> RUSTED_CUT_IRON = regWithItem("rusted_cut_iron", () ->
            new RustableBlock(Rustable.RustLevel.RUSTED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> CUT_IRON_STAIRS = regWithItem("cut_iron_stairs", () ->
            new RustableStairsBlock(Rustable.RustLevel.UNAFFECTED, CUT_IRON, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WEATHERED_CUT_IRON_STAIRS = regWithItem("weathered_cut_iron_stairs", () ->
            new RustableStairsBlock(Rustable.RustLevel.WEATHERED, CUT_IRON, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> RUSTED_CUT_IRON_STAIRS = regWithItem("rusted_cut_iron_stairs", () ->
            new RustableStairsBlock(Rustable.RustLevel.RUSTED, CUT_IRON, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> CUT_IRON_SLAB = regWithItem("cut_iron_slab", () ->
            new RustableSlabBlock(Rustable.RustLevel.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WEATHERED_CUT_IRON_SLAB = regWithItem("weathered_cut_iron_slab", () ->
            new RustableSlabBlock(Rustable.RustLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> RUSTED_CUT_IRON_SLAB = regWithItem("rusted_cut_iron_slab", () ->
            new RustableSlabBlock(Rustable.RustLevel.RUSTED, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    //waxed
    public static final Supplier<Block> WAXED_CUT_IRON = regWithItem("waxed_cut_iron", () ->
            new Block(noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_WEATHERED_CUT_IRON = regWithItem("waxed_weathered_cut_iron", () ->
            new Block(noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_RUSTED_CUT_IRON = regWithItem("waxed_rusted_cut_iron", () ->
            new Block(noTick(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> WAXED_CUT_IRON_STAIRS = regWithItem("waxed_cut_iron_stairs", () ->
            new ModStairBlock(WAXED_CUT_IRON, noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_WEATHERED_CUT_IRON_STAIRS = regWithItem("waxed_weathered_cut_iron_stairs", () ->
            new ModStairBlock(WAXED_WEATHERED_CUT_IRON, noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_RUSTED_CUT_IRON_STAIRS = regWithItem("waxed_rusted_cut_iron_stairs", () ->
            new ModStairBlock(WAXED_RUSTED_CUT_IRON, noTick(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> WAXED_CUT_IRON_SLAB = regWithItem("waxed_cut_iron_slab", () ->
            new SlabBlock(noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_WEATHERED_CUT_IRON_SLAB = regWithItem("waxed_weathered_cut_iron_slab", () ->
            new SlabBlock(noTick(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> WAXED_RUSTED_CUT_IRON_SLAB = regWithItem("waxed_rusted_cut_iron_slab", () ->
            new SlabBlock(noTick(Blocks.IRON_BLOCK)));


    public static final Supplier<Block> IRON_SCAFFOLD = regWithItem("iron_scaffold", () ->
            new RustableScaffoldBlock(Rustable.RustLevel.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final Supplier<Block> WEATHERED_IRON_SCAFFOLD = regWithItem("weathered_iron_scaffold", () ->
            new RustableScaffoldBlock(Rustable.RustLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final Supplier<Block> RUSTED_IRON_SCAFFOLD = regWithItem("rusted_iron_scaffold", () ->
            new RustableScaffoldBlock(Rustable.RustLevel.RUSTED, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final Supplier<Block> IRON_SCAFFOLD_STAIRS = regWithItem("iron_scaffold_stairs", () ->
            new RustableScaffoldStairsBlock(Rustable.RustLevel.UNAFFECTED, IRON_SCAFFOLD, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final Supplier<Block> WEATHERED_IRON_SCAFFOLD_STAIRS = regWithItem("weathered_iron_scaffold_stairs", () ->
            new RustableScaffoldStairsBlock(Rustable.RustLevel.WEATHERED, IRON_SCAFFOLD, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final Supplier<Block> RUSTED_IRON_SCAFFOLD_STAIRS = regWithItem("rusted_iron_scaffold_stairs", () ->
            new RustableScaffoldStairsBlock(Rustable.RustLevel.RUSTED, IRON_SCAFFOLD, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final Supplier<Block> IRON_SCAFFOLD_SLAB = regWithItem("iron_scaffold_slab", () ->
            new RustableScaffoldSlabBlock(Rustable.RustLevel.UNAFFECTED, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final Supplier<Block> WEATHERED_IRON_SCAFFOLD_SLAB = regWithItem("weathered_iron_scaffold_slab", () ->
            new RustableScaffoldSlabBlock(Rustable.RustLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final Supplier<Block> RUSTED_IRON_SCAFFOLD_SLAB = regWithItem("rusted_iron_scaffold_slab", () ->
            new RustableScaffoldSlabBlock(Rustable.RustLevel.RUSTED, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));



    //iron decor

    public static final Supplier<Block> WEATHERED_IRON_DOOR = regWithItem("weathered_iron_door", () ->
            new RustableDoorBlock(Rustable.RustLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)));
    public static final Supplier<Block> RUSTED_IRON_DOOR = regWithItem("rusted_iron_door", () ->
            new RustableDoorBlock(Rustable.RustLevel.RUSTED, BlockBehaviour.Properties.copy(Blocks.IRON_DOOR)));

    public static final Supplier<Block> WEATHERED_IRON_TRAPDOOR = regWithItem("weathered_iron_trapdoor", () ->
            new RustableTrapdoorBlock(Rustable.RustLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)));
    public static final Supplier<Block> RUSTED_IRON_TRAPDOOR = regWithItem("rusted_iron_trapdoor", () ->
            new RustableTrapdoorBlock(Rustable.RustLevel.RUSTED, BlockBehaviour.Properties.copy(Blocks.IRON_TRAPDOOR)));

    public static final Supplier<Block> WEATHERED_IRON_BARS = regWithItem("weathered_iron_bars", () ->
            new RustableBarsBlock(Rustable.RustLevel.WEATHERED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)));
    public static final Supplier<Block> RUSTED_IRON_BARS = regWithItem("rusted_iron_bars", () ->
            new RustableBarsBlock(Rustable.RustLevel.RUSTED, BlockBehaviour.Properties.copy(Blocks.IRON_BARS)));

    //waxed
    public static final Supplier<Block> WAXED_IRON_DOOR = regWithItem("waxed_iron_door", () ->
            new RustAffectedDoorBlock(Rustable.RustLevel.UNAFFECTED, noTick(Blocks.IRON_DOOR)));
    public static final Supplier<Block> WAXED_WEATHERED_IRON_DOOR = regWithItem("waxed_weathered_iron_door", () ->
            new RustAffectedDoorBlock(Rustable.RustLevel.WEATHERED, noTick(Blocks.IRON_DOOR)));
    public static final Supplier<Block> WAXED_RUSTED_IRON_DOOR = regWithItem("waxed_rusted_iron_door", () ->
            new RustAffectedDoorBlock(Rustable.RustLevel.RUSTED, noTick(Blocks.IRON_DOOR)));

    public static final Supplier<Block> WAXED_IRON_TRAPDOOR = regWithItem("waxed_iron_trapdoor", () ->
            new RustAffectedTrapdoorBlock(Rustable.RustLevel.UNAFFECTED, noTick(Blocks.IRON_TRAPDOOR), BlockSetType.IRON));
    public static final Supplier<Block> WAXED_WEATHERED_IRON_TRAPDOOR = regWithItem("waxed_weathered_iron_trapdoor", () ->
            new RustAffectedTrapdoorBlock(Rustable.RustLevel.WEATHERED, noTick(Blocks.IRON_TRAPDOOR), BlockSetType.IRON));
    public static final Supplier<Block> WAXED_RUSTED_IRON_TRAPDOOR = regWithItem("waxed_rusted_iron_trapdoor", () ->
            new RustAffectedTrapdoorBlock(Rustable.RustLevel.RUSTED, noTick(Blocks.IRON_TRAPDOOR), BlockSetType.IRON));

    public static final Supplier<Block> WAXED_IRON_BARS = regWithItem("waxed_iron_bars", () ->
            new IronBarsBlock(noTick(Blocks.IRON_BARS)) {});
    public static final Supplier<Block> WAXED_WEATHERED_IRON_BARS = regWithItem("waxed_weathered_iron_bars", () ->
            new IronBarsBlock(noTick(Blocks.IRON_BARS)) {});
    public static final Supplier<Block> WAXED_RUSTED_IRON_BARS = regWithItem("waxed_rusted_iron_bars", () ->
            new IronBarsBlock(noTick(Blocks.IRON_BARS)) {});


    private static BlockBehaviour.Properties noTick(Block copyFrom){
        var p = BlockBehaviour.Properties.copy(copyFrom);
        p.isRandomlyTicking = false;
        return p;
    }
}
