package com.ordana.oxide.entities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.ordana.oxide.Oxide;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class RustyNailRenderer<T extends RustyNailEntity> extends EntityRenderer<T, RustyNailRenderState> {
    private static final Identifier LOCATION = Oxide.res("textures/entity/rusty_nail/rusty_nail.png");

    public RustyNailRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public RustyNailRenderState createRenderState() {
        return new RustyNailRenderState();
    }

    @Override
    public void extractRenderState(T entity, RustyNailRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public void submit(RustyNailRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        //poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        //poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
        //float p = 1 - partialTicks;
        //if (p > 0.0F) {
        //    float q = -Mth.sin(p * 3.0F) * p;
        //    poseStack.mulPose(Axis.ZP.rotationDegrees(q));
        //}

        poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));

        poseStack.scale(0.075F, 0.075F, 0.075F);
        poseStack.translate(8.0F, 0.0F, 0.0F);
        var packedLight = state.lightCoords;
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.entityCutout(LOCATION), (pose, vertexConsumer) -> {
            this.vertex(pose, vertexConsumer, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, packedLight);
            this.vertex(pose, vertexConsumer, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, packedLight);
            this.vertex(pose, vertexConsumer, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, packedLight);
            this.vertex(pose, vertexConsumer, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, packedLight);
            this.vertex(pose, vertexConsumer, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, packedLight);
            this.vertex(pose, vertexConsumer, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, packedLight);
            this.vertex(pose, vertexConsumer, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, packedLight);
            this.vertex(pose, vertexConsumer, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, packedLight);

            for(int r = 0; r < 4; ++r) {
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                this.vertex(pose, vertexConsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, packedLight);
                this.vertex(pose, vertexConsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, packedLight);
                this.vertex(pose, vertexConsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, packedLight);
                this.vertex(pose, vertexConsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, packedLight);
            }
        });



        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    public void vertex(PoseStack.Pose pose, VertexConsumer consumer, int x, int y, int z, float u, float v, int normalX, int normalY, int normalZ, int packedLight) {
        consumer.addVertex(pose, (float)x, (float)y, (float)z).setColor(-1).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, (float)normalX, (float)normalZ, (float)normalY);
    }
}
