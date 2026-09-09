package com.ordana.oxide;

import com.ordana.oxide.entities.DrippingLiquidParticle;
import com.ordana.oxide.entities.FallingLiquidParticle;
import com.ordana.oxide.entities.RustyNailRenderer;
import com.ordana.oxide.reg.ModEntities;
import com.ordana.oxide.reg.ModItems;
import com.ordana.oxide.reg.ModParticles;
import net.mehvahdjukaar.moonlight.api.fluids.SoftFluidStack;
import net.mehvahdjukaar.moonlight.api.fluids.client.SoftFluidColors;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SplashParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.entity.NoopRenderer;
//import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class OxideClient {

    public static final ModelLayerLocation RUSTY_NAIL =  new ModelLayerLocation(Oxide.res("rusty_nail"), "rusty_nail");

    public static void init() {
        ClientHelper.addClientSetup(OxideClient::setup);
        ClientHelper.addEntityRenderersRegistration(OxideClient::registerEntityRenderers);
        //ClientHelper.registerOptionalTexturePack(Oxide.res("visual_waxed_iron_items"));
        ClientHelper.addParticleRegistration(OxideClient::registerParticles);
    }

    public static void setup() {

//        ItemProperties.register(ModItems.VARNISH_SPRAYER.get(), Oxide.res("primed"),
//                (itemStack, clientLevel, livingEntity, i) -> livingEntity != null && (livingEntity.getUseItemRemainingTicks() != 0) && livingEntity.getUseItem() == itemStack && (livingEntity.getUseItemRemainingTicks() % 20 <= 1) ? 1 : 0);

    }

    public static int getParticleColor(SoftFluidStack fluid, Level level, @Nullable BlockPos pos) {
        //TODO: try still, particle and flowing color. idk which might work best
        return SoftFluidColors.getParticleColor(fluid, (ClientLevel) level, pos);
    }

    private static void registerEntityRenderers(ClientHelper.EntityRendererEvent event) {
        event.register(ModEntities.RUSTY_NAIL.get(), RustyNailRenderer::new);
        event.register(ModEntities.SPRAY_ENTITY.get(), NoopRenderer::new);
    }

    private static void registerParticles(ClientHelper.ParticleEvent event) {
        event.register(ModParticles.SCRAPE_RUST.get(), ScrapeRustFactory::new);
        event.register(ModParticles.DRIPPING_LIQUID.get(), DrippingLiquidParticle.Factory::new);
        event.register(ModParticles.FALLING_LIQUID.get(), FallingLiquidParticle.Factory::new);
        event.register(ModParticles.SPLASHING_LIQUID.get(), ColoredSplashingParticle::new);
    }

    public static Level getClientLevel() {
        return Minecraft.getInstance().level;
    }


    public static class ColoredSplashingParticle extends SplashParticle.Provider {
        public ColoredSplashingParticle(SpriteSet sprites) {
            super(sprites);
        }

        @Override
        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            var p = (SplashParticle) super.createParticle(options, level, x, y, z, 0, 0, 0, random);
            p.setColor((float) xAux, (float) yAux, (float) zAux);
            return p;
        }
    }

    private static class ScrapeRustFactory extends GlowParticle.ScrapeProvider {

        public ScrapeRustFactory(SpriteSet spriteSet) {
            super(spriteSet);
        }

        @Override
        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            GlowParticle p = (GlowParticle) super.createParticle(options, level, x, y, z, xAux, yAux, zAux, random);
            if (p != null) {
                if (level.getRandom().nextBoolean()) {
                    p.setColor(196 / 255f, 118 / 255f, 73 / 255f);
                } else {
                    p.setColor(176 / 255f, 63 / 255f, 40 / 255f);
                }
            }
            return p;
        }
    }
}