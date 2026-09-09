package com.ordana.oxide.integrations;

import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.common.builtin.interaction.WorldInteractionClientRecipe;
import cc.cassian.rrv.common.extra.FluidStack;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import com.ordana.oxide.Oxide;
import com.ordana.oxide.blocks.rusty.Rustable;
import com.ordana.oxide.reg.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;

import java.util.Map;

public class RrvIntegration implements ReliableRecipeViewerPlugin {


    @Override
    public void onIntegrationInitialize() {
        ItemView.addClientRecipeProvider((recipeList)->{
            //RUSTING
            Map<Block, Block> rust = Rustable.RUST_LEVEL_INCREASES.get();
            for (Block key : rust.keySet()) {
                Identifier blockId = BuiltInRegistries.ITEM.getKey(key.asItem());
                SlotContent input = SlotContent.of(key);
                SlotContent output = SlotContent.of(rust.get(key));
                Identifier id = Oxide.res("/sponge_rusting/" + blockId.getNamespace() + "/" + blockId.getPath());
                recipeList.add(new WorldInteractionClientRecipe(id, input, SlotContent.of(Items.WET_SPONGE), output, 0));
            }
            recipeList.add(new WorldInteractionClientRecipe(Oxide.res("/cement_watering"), SlotContent.of(ModItems.CEMENT_POWDER_BUCKET.get()), SlotContent.of(new FluidStack(Fluids.WATER)), SlotContent.of(ModItems.CEMENT_BUCKET.get()), 0));
        });

    }
}
