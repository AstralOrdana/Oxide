//? fabric {
package com.ordana.oxide.fabric.data.provider;


import com.ordana.oxide.Oxide;
import com.ordana.oxide.reg.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;

public class ModelProvider extends FabricModelProvider {
	public ModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerators) {
		BuiltInRegistries.ITEM.entrySet().forEach(itemEntry -> {
			var item = itemEntry.getValue();
			var id = itemEntry.getKey().identifier();
			if (id.getNamespace().equals(Oxide.MOD_ID)) {
				var model = id.withPrefix("item/");
				itemModelGenerators.itemModelOutput.accept(item.asItem(), ItemModelUtils.plainModel(model));
			}
		});
	}
}
//?}