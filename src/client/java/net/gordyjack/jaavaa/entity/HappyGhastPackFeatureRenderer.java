package net.gordyjack.jaavaa.entity;

import net.gordyjack.jaavaa.*;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.equipment.*;
import net.minecraft.client.render.entity.feature.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.state.*;
import net.minecraft.client.util.math.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class HappyGhastPackFeatureRenderer<S extends BipedEntityRenderState, M extends EntityModel<S>> extends FeatureRenderer<S, M> {
    private static final Identifier TEXTURE = JAAVAA.id("textures/entity/happy_ghast_pack.png");
    private final HappyGhastPackEntityModel MODEL;
    private final HappyGhastPackEntityModel BABY_MODEL;
    private final EquipmentRenderer EQUIPMENT_RENDERER;

    public HappyGhastPackFeatureRenderer(FeatureRendererContext<S, M> context, LoadedEntityModels loader, EquipmentRenderer equipmentRenderer) {
        super(context);

        this.MODEL = new HappyGhastPackEntityModel(loader.getModelPart(EntityModelLayers.ELYTRA));
        this.BABY_MODEL = new HappyGhastPackEntityModel(loader.getModelPart(EntityModelLayers.ELYTRA_BABY));
        this.EQUIPMENT_RENDERER = equipmentRenderer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light, S bipedEntityRenderState, float limbAngle, float limbDistance) {
        ItemStack itemStack = bipedEntityRenderState.equippedChestStack;
        EquippableComponent equippableComponent = itemStack.get(DataComponentTypes.EQUIPPABLE);
        if (equippableComponent != null && !equippableComponent.assetId().isEmpty()) {
            HappyGhastPackEntityModel model = bipedEntityRenderState.baby ? this.BABY_MODEL : this.MODEL;
            matrices.push();
            matrices.translate(0.0F, 0.0F, 0.125F);
            model.setAngles(bipedEntityRenderState);
            this.EQUIPMENT_RENDERER
                    .render(
                            EquipmentModel.LayerType.WINGS,
                            equippableComponent.assetId().get(),
                            model,
                            itemStack,
                            matrices,
                            vertexConsumerProvider,
                            light,
                            TEXTURE
                    );
            matrices.pop();
        }
    }
}
