//? fabric {
package com.ordana.oxide.fabric.data.provider;


import com.ordana.oxide.Oxide;
import com.ordana.oxide.blocks.rusty.RustableBarsBlock;
import com.ordana.oxide.reg.ModBlocks;
import com.ordana.oxide.reg.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.DoorBlock;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture, @Nullable BlockTagsProvider blockTagsProvider) {
		super(output, registryLookupFuture, blockTagsProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		copy(BlockTags.DOORS, ItemTags.DOORS);
		copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
		copy(BlockTags.BARS, ItemTags.BARS);
		copy(BlockTags.SLABS, ItemTags.SLABS);
		copy(BlockTags.STAIRS, ItemTags.STAIRS);
		copy(BlockTags.CHAINS, ItemTags.CHAINS);
		valueLookupBuilder(ConventionalItemTags.HIDDEN_FROM_RECIPE_VIEWERS).add(ModBlocks.CEMENT_EATER.get().asItem(), ModBlocks.CIRNONAIL.get().asItem(), ModItems.PURE_NAIL.get().asItem());
	}
}
//?}