//? fabric {
package com.ordana.oxide.fabric;

import com.ordana.oxide.Oxide;
import com.ordana.oxide.reg.ModSetup;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import org.apache.logging.log4j.core.jmx.Server;


public class OxideFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        Oxide.commonInit();

        PlatHelper.addCommonSetup(ModSetup::setup);
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            Oxide.server = server;
        });
    }
}
//?}
