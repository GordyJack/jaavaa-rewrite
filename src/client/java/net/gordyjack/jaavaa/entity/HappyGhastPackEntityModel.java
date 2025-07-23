package net.gordyjack.jaavaa.entity;

import net.gordyjack.jaavaa.*;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.state.*;

public class HappyGhastPackEntityModel extends EntityModel<BipedEntityRenderState> {
    public static final EntityModelLayer HAPPY_GHAST_PACK = new EntityModelLayer(JAAVAA.id("happy_ghast_pack"), "main");
    public static final ModelTransformer BABY_TRANSFORMER = ModelTransformer.scaling(0.5F);
    private final ModelPart belt;
    private final ModelPart shoulderL;
    private final ModelPart shoulderR;
    private final ModelPart back;


    protected HappyGhastPackEntityModel(ModelPart root) {
        super(root);

        this.belt = root.getChild("belt");
        this.shoulderL = root.getChild("shoulder_l");
        this.shoulderR = root.getChild("shoulder_r");
        this.back = root.getChild("back");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation = new Dilation(1.0F);
        modelPartData.addChild(
                "belt",
                ModelPartBuilder.create()
                        .uv(22, 0)
                        .cuboid(-15.0F, -10.0F, -10.0F, 20.0F, 4.0F, 20.0F, dilation),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );
        modelPartData.addChild(
                "shoulder_l",
                ModelPartBuilder.create()
                        .uv(22, 0).mirrored()
                        .cuboid(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, dilation),
                ModelTransform.of(-5.0F, 0.0F, 0.0F, (float) (Math.PI / 12), 0.0F, (float) (Math.PI / 12))
        );
        return TexturedModelData.of(modelData, 64, 32);
    }
}
