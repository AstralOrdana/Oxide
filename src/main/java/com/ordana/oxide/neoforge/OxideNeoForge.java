//? neoforge {
/*package com.ordana.oxide.neoforge;

import com.ordana.oxide.Oxide;
import com.ordana.oxide.reg.ModSetup;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

import static com.ordana.oxide.Oxide.MOD_ID;

@Mod(MOD_ID)
@EventBusSubscriber(modid = MOD_ID)
public class OxideNeoForge {

    public OxideNeoForge() {
        Oxide.commonInit();
        PlatHelper.addCommonSetup(ModSetup::setup);
    }

}
*///?}
