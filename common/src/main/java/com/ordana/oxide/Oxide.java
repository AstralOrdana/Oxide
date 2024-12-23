package com.ordana.oxide;

import com.ordana.oxide.configs.ClientConfigs;
import com.ordana.oxide.configs.CommonConfigs;
import com.ordana.oxide.reg.ModBlocks;
import com.ordana.oxide.reg.ModCreativeTab;
import com.ordana.oxide.reg.ModParticles;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Oxide {

    public static final String MOD_ID = "oxide";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void commonInit() {

        CommonConfigs.init();

        if (PlatHelper.getPhysicalSide().isClient()) {
            ClientConfigs.init();
            OxideClient.init();
        }

        PlatHelper.addCommonSetup(Oxide::setup);

        ModParticles.init();
        ModCreativeTab.init();
        ModBlocks.init();
    }

    public static void setup() {
        //ModCompostable.register();
    }
}
