package com.blakebr0.pickletweaks.feature.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class NightVisionGogglesModel extends EntityModel<LivingEntity> {
    private static final String MAIN = "main";
    private static final String LENS = "lens";
    private static final String LENS_LEFT = "lens_left";
    private static final String LENS_RIGHT = "lens_right";
    private static final String FRAME = "frame";

    private final ModelPart main;
    private final ModelPart lens;
    private final ModelPart frame;

    public NightVisionGogglesModel(ModelPart part) {
        super(RenderType::entityTranslucent);
        this.main = part.getChild(MAIN);
        this.lens = part.getChild(LENS);
        this.frame = part.getChild(FRAME);
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer buffer, int light, int overlay, float r, float g, float b, float a) {
        this.main.render(stack, buffer, light, overlay, r, g, b, a);
        this.lens.render(stack, buffer, light, overlay, r, g, b, a);
        this.frame.render(stack, buffer, light, overlay, r, g, b, a);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild(MAIN, CubeListBuilder.create()
                .texOffs(0, 7)
                .addBox(-8.0F, -7.0F, 1.0F, 1, 1, 6)
                .addBox(7.0F, -7.0F, 1.0F, 1, 1, 6)
                .addBox(-7.0F, -7.0F, 7.0F, 14, 1, 1)
                .addBox(-8.0F, -7.0F, -6.0F, 1, 2, 7)
                .addBox(7.0F, -7.0F, -6.0F, 1, 2, 7),
                PartPose.ZERO
        );

        var lens = root.addOrReplaceChild(LENS, CubeListBuilder.create(), PartPose.ZERO);

        lens.addOrReplaceChild(LENS_LEFT, CubeListBuilder.create()
                .texOffs(0, 6)
                .addBox(-6.0F, -9.0F, -7.0F, 4, 5, 1)
                .addBox(-2.0F, -8.0F, -7.0F, 1, 3, 1)
                .addBox(-7.0F, -8.0F, -7.0F, 1, 4, 1)
                .addBox(-6.0F, -4.0F, -7.0F, 3, 1, 1),
                PartPose.ZERO
        );

        lens.addOrReplaceChild(LENS_RIGHT, CubeListBuilder.create()
                .texOffs(6, 0)
                .addBox(6.0F, -8.0F, -7.0F, 1, 4, 1)
                .addBox(1.0F, -8.0F, -7.0F, 1, 3, 1)
                .addBox(2.0F, -9.0F, -7.0F, 4, 5, 1)
                .addBox(3.0F, -4.0F, -7.0F, 3, 1, 1),
                PartPose.ZERO
        );

        root.addOrReplaceChild(FRAME, CubeListBuilder.create()
                .texOffs(0, 13)
                .addBox(-1.0F, -8.0F, -8.0F, 2, 3, 2)
                .addBox(-2.0F, -9.0F, -8.0F, 4, 1, 2)
                .addBox(-2.0F, -5.0F, -8.0F, 4, 1, 2)
                .addBox(2.0F, -10.0F, -8.0F, 4, 1, 2)
                .addBox(3.0F, -3.0F, -8.0F, 3, 1, 2)
                .addBox(-6.0F, -10.0F, -8.0F, 4, 1, 2)
                .addBox(-6.0F, -3.0F, -8.0F, 3, 1, 2)
                .addBox(-7.0F, -9.0F, -8.0F, 1, 1, 2)
                .addBox(6.0F, -9.0F, -8.0F, 1, 1, 2)
                .addBox(-8.0F, -8.0F, -8.0F, 1, 4, 2)
                .addBox(7.0F, -8.0F, -8.0F, 1, 4, 2)
                .addBox(6.0F, -4.0F, -8.0F, 1, 1, 2)
                .addBox(-7.0F, -4.0F, -8.0F, 1, 1, 2)
                .addBox(-3.0F, -4.0F, -8.0F, 1, 1, 2)
                .addBox(2.0F, -4.0F, -8.0F, 1, 1, 2),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) { }
}