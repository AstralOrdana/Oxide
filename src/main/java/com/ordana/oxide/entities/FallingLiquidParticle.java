package com.ordana.oxide.entities;

import com.ordana.oxide.reg.ModParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.FluidState;
import org.jspecify.annotations.Nullable;


public class FallingLiquidParticle extends SingleQuadParticle {

    private FallingLiquidParticle(ClientLevel world, double x, double y, double z, TextureAtlasSprite sprite) {
        super(world, x, y, z, sprite);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.lifetime = (int) (64.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    protected Layer getLayer() {
        return Layer.OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.ageParticle();
        if (!this.removed) {
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.updateMotion();
            if (!this.removed) {
                this.xd *= 0.98F;
                this.yd *= 0.98F;
                this.zd *= 0.98F;
                BlockPos blockpos = BlockPos.containing(this.x, this.y, this.z);
                FluidState fluidstate = this.level.getFluidState(blockpos);
                if (!fluidstate.isEmpty() && this.y < (blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
                    this.remove();
                }
            }
        }
    }

    protected void ageParticle() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }
    }

    protected void updateMotion() {
        if (this.onGround) {
            this.remove();
            this.level.addParticle(ModParticles.SPLASHING_LIQUID.get(), this.x, this.y, this.z, this.rCol, this.gCol, this.bCol);
        }
    }


    public static class Factory implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            TextureAtlasSprite icon = this.spriteSet.get(random);
            FallingLiquidParticle fallingparticle = new FallingLiquidParticle(level, x, y, z, icon);
            fallingparticle.setColor((float) xAux, (float) yAux, (float) zAux);
            fallingparticle.setSprite(icon);
            return fallingparticle;
        }
    }

}