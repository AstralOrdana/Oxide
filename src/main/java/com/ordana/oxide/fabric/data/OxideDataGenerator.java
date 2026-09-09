//? fabric {
package com.ordana.oxide.fabric.data;

import com.ordana.oxide.fabric.data.provider.BlockTagProvider;
import com.ordana.oxide.fabric.data.provider.ItemTagProvider;
import com.ordana.oxide.fabric.data.provider.ModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class OxideDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelProvider::new);
		var blocks = pack.addProvider(BlockTagProvider::new);
		pack.addProvider((output, providerCompletableFuture)->new ItemTagProvider(output, providerCompletableFuture, blocks));
	}
}
//?}