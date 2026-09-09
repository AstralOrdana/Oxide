//? fabric {
package com.ordana.oxide.fabric.data.provider;


import com.ordana.oxide.Oxide;
import com.ordana.oxide.blocks.rusty.RustableBarsBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.*;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

	public BlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		var bars = valueLookupBuilder(BlockTags.BARS);
		var doors = valueLookupBuilder(BlockTags.DOORS);
		var trapdoors = valueLookupBuilder(BlockTags.TRAPDOORS);
		var slabs = valueLookupBuilder(BlockTags.SLABS);
		var stairs = valueLookupBuilder(BlockTags.STAIRS);
		var chains = valueLookupBuilder(BlockTags.CHAINS);
		BuiltInRegistries.BLOCK.entrySet().forEach(entry -> {
			var block = entry.getValue();
			var id = entry.getKey().identifier();
			if (id.getNamespace().equals(Oxide.MOD_ID)) {
				if (block instanceof RustableBarsBlock) {
					bars.add(block);
				}
				if (block instanceof DoorBlock) {
					doors.add(block);
				}
				if (block instanceof TrapDoorBlock) {
					trapdoors.add(block);
				}
				if (block instanceof SlabBlock) {
					slabs.add(block);
				}
				if (block instanceof StairBlock) {
					stairs.add(block);
				}
				if (block instanceof ChainBlock) {
					chains.add(block);
				}
			}
		});
	}
}
//?}