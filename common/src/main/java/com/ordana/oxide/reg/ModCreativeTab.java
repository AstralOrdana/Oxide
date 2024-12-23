package com.ordana.oxide.reg;

import com.ordana.oxide.Oxide;
import com.ordana.oxide.configs.CommonConfigs;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ModCreativeTab {

    public static final RegSupplier<CreativeModeTab> MOD_TAB = !CommonConfigs.CREATIVE_TAB.get() ? null :
        RegHelper.registerCreativeModeTab(Oxide.res("oxide"),
            (c) -> c.title(Component.translatable("itemGroup.oxide.oxide"))
                .icon(() -> ModBlocks.RUSTED_CUT_IRON.get().asItem().getDefaultInstance()));

    public static void init(){
        RegHelper.addItemsToTabsRegistration(ModCreativeTab::addItems);
    }

    public static void addItems(RegHelper.ItemToTabEvent e) {


        after(e, Items.IRON_BLOCK, CreativeModeTabs.BUILDING_BLOCKS,
            ModBlocks.PLATE_IRON, ModBlocks.PLATE_IRON_STAIRS, ModBlocks.PLATE_IRON_SLAB,
            ModBlocks.CUT_IRON, ModBlocks.CUT_IRON_STAIRS, ModBlocks.CUT_IRON_SLAB,
            ModBlocks.WEATHERED_PLATE_IRON, ModBlocks.WEATHERED_PLATE_IRON_STAIRS, ModBlocks.WEATHERED_PLATE_IRON_SLAB,
            ModBlocks.WEATHERED_CUT_IRON, ModBlocks.WEATHERED_CUT_IRON_STAIRS, ModBlocks.WEATHERED_CUT_IRON_SLAB,
            ModBlocks.RUSTED_PLATE_IRON, ModBlocks.RUSTED_PLATE_IRON_STAIRS, ModBlocks.RUSTED_PLATE_IRON_SLAB,
            ModBlocks.RUSTED_CUT_IRON, ModBlocks.RUSTED_CUT_IRON_STAIRS, ModBlocks.RUSTED_CUT_IRON_SLAB,
            ModBlocks.WAXED_PLATE_IRON, ModBlocks.WAXED_PLATE_IRON_STAIRS, ModBlocks.WAXED_PLATE_IRON_SLAB,
            ModBlocks.WAXED_CUT_IRON, ModBlocks.WAXED_CUT_IRON_STAIRS, ModBlocks.WAXED_CUT_IRON_SLAB,
            ModBlocks.WAXED_WEATHERED_PLATE_IRON, ModBlocks.WAXED_WEATHERED_PLATE_IRON_STAIRS, ModBlocks.WAXED_WEATHERED_PLATE_IRON_SLAB,
            ModBlocks.WAXED_WEATHERED_CUT_IRON, ModBlocks.WAXED_WEATHERED_CUT_IRON_STAIRS, ModBlocks.WAXED_WEATHERED_CUT_IRON_SLAB,
            ModBlocks.WAXED_RUSTED_PLATE_IRON, ModBlocks.WAXED_RUSTED_PLATE_IRON_STAIRS, ModBlocks.WAXED_RUSTED_PLATE_IRON_SLAB,
            ModBlocks.WAXED_RUSTED_CUT_IRON, ModBlocks.WAXED_RUSTED_CUT_IRON_STAIRS, ModBlocks.WAXED_RUSTED_CUT_IRON_SLAB
        );

        after(e, Items.IRON_BARS, CreativeModeTabs.BUILDING_BLOCKS,
            ModBlocks.WEATHERED_IRON_BARS, ModBlocks.RUSTED_IRON_BARS,
            ModBlocks.WAXED_IRON_BARS, ModBlocks.WAXED_WEATHERED_IRON_BARS, ModBlocks.WAXED_RUSTED_IRON_BARS
        );

        after(e, Items.IRON_DOOR, CreativeModeTabs.BUILDING_BLOCKS,
            ModBlocks.WEATHERED_IRON_DOOR, ModBlocks.RUSTED_IRON_DOOR,
            ModBlocks.WAXED_IRON_DOOR, ModBlocks.WAXED_WEATHERED_IRON_DOOR, ModBlocks.WAXED_RUSTED_IRON_DOOR
        );

        after(e, Items.IRON_TRAPDOOR, CreativeModeTabs.BUILDING_BLOCKS,
            ModBlocks.WEATHERED_IRON_TRAPDOOR, ModBlocks.RUSTED_IRON_TRAPDOOR,
            ModBlocks.WAXED_IRON_TRAPDOOR, ModBlocks.WAXED_WEATHERED_IRON_TRAPDOOR, ModBlocks.WAXED_RUSTED_IRON_TRAPDOOR
        );
    }

    private static void after(RegHelper.ItemToTabEvent event, Item target,
                              ResourceKey<CreativeModeTab> tab, Supplier<?>... items) {
        after(event, i -> i.is(target), tab, items);
    }

    private static void after(RegHelper.ItemToTabEvent event, Predicate<ItemStack> targetPred,
                              ResourceKey<CreativeModeTab> tab, Supplier<?>... items) {
        //if (CommonConfigs.isEnabled(key)) {
        ItemLike[] entries = Arrays.stream(items).map((s -> (ItemLike) (s.get()))).toArray(ItemLike[]::new);
        if(MOD_TAB != null){
            tab = MOD_TAB.getKey();
        }
        event.addAfter(tab, targetPred, entries);
    }

    private static void before(RegHelper.ItemToTabEvent event, Item target,
                               ResourceKey<CreativeModeTab> tab, Supplier<?>... items) {
        before(event, i -> i.is(target), tab, items);
    }

    private static void before(RegHelper.ItemToTabEvent event, Predicate<ItemStack> targetPred,
                               ResourceKey<CreativeModeTab> tab, Supplier<?>... items) {
        //if (CommonConfigs.isEnabled(key)) {
        ItemLike[] entries = Arrays.stream(items).map(s -> (ItemLike) s.get()).toArray(ItemLike[]::new);
        if(MOD_TAB != null){
            tab = MOD_TAB.getKey();
        }
        event.addBefore(tab, targetPred, entries);
        //}
    }
}
