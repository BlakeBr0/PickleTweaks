package com.blakebr0.pickletweaks.feature.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class NightVisionGogglesModel extends HumanoidModel<HumanoidRenderState> {
    private static final String MAIN = "main";
    private static final String LENS_LEFT = "lens_left";
    private static final String LENS_RIGHT = "lens_right";
    private static final String FRAME = "frame";

    public NightVisionGogglesModel(ModelPart part) {
        super(part);
    }

    public static LayerDefinition createArmorLayer() {
        var mesh = HumanoidModel.createMesh(new CubeDeformation(1.0F), 0F);
        var root = mesh.getRoot().clearRecursively();
        var head = root.getChild("head");

        var main = head.addOrReplaceChild(MAIN, CubeListBuilder.create()
                .texOffs(0, 7)
                .addBox(-8.0F, -7.0F, 1.0F, 1, 1, 6)
                .addBox(7.0F, -7.0F, 1.0F, 1, 1, 6)
                .addBox(-7.0F, -7.0F, 7.0F, 14, 1, 1)
                .addBox(-8.0F, -7.0F, -6.0F, 1, 2, 7)
                .addBox(7.0F, -7.0F, -6.0F, 1, 2, 7),
                PartPose.ZERO.scaled(0.6F)
        );

        main.addOrReplaceChild(LENS_LEFT, CubeListBuilder.create()
                .texOffs(6, 0)
                .addBox(-6.0F, -9.0F, -7.0F, 4, 5, 1)
                .addBox(-2.0F, -8.0F, -7.0F, 1, 3, 1)
                .addBox(-7.0F, -8.0F, -7.0F, 1, 4, 1)
                .addBox(-6.0F, -4.0F, -7.0F, 3, 1, 1),
                PartPose.ZERO
        );

        main.addOrReplaceChild(LENS_RIGHT, CubeListBuilder.create()
                .texOffs(6, 0)
                .addBox(6.0F, -8.0F, -7.0F, 1, 4, 1)
                .addBox(1.0F, -8.0F, -7.0F, 1, 3, 1)
                .addBox(2.0F, -9.0F, -7.0F, 4, 5, 1)
                .addBox(3.0F, -4.0F, -7.0F, 3, 1, 1),
                PartPose.ZERO
        );

        main.addOrReplaceChild(FRAME, CubeListBuilder.create()
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
}