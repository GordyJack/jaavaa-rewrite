package net.gordyjack.jaavaa;

import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.gordyjack.jaavaa.block.*;
import net.gordyjack.jaavaa.data.*;
import net.gordyjack.jaavaa.item.custom.*;
import net.gordyjack.jaavaa.screen.*;
import net.gordyjack.jaavaa.utils.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.client.network.*;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;

import java.util.*;

public class JAAVAAClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		JAAVAA.log("Initializing JAAVAA client");
		HandledScreens.register(JAAVAAScreenHandlers.ALLOY_FURNACE_SCREEN_HANDLER, AlloyFurnaceBlockEntityScreen::new);
		HandledScreens.register(JAAVAAScreenHandlers.RECYCLING_SCREEN_HANDLER, RecyclingScreen::new);

		// RenderTranslucentLayers
		BlockRenderLayerMap.putBlock(JAAVAABlocks.STARSTEEL_GLASS, BlockRenderLayer.TRANSLUCENT);
		BlockRenderLayerMap.putBlock(JAAVAABlocks.STARSTEEL_GLASS_PANE, BlockRenderLayer.TRANSLUCENT);

		this.modifyRenderEvents();

//		if (FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
//			JAAVAA.log("REI detected, adding plugin");
//			new JAAVAAREIPlugin();
//		}
	}
	private void modifyRenderEvents() {
		WorldRenderEvents.BLOCK_OUTLINE.register((ctx, outlineCtx) -> {
			MinecraftClient mc = MinecraftClient.getInstance();
			ClientPlayerEntity player = mc.player;
			if (player == null) return true;

			ItemStack stack = player.getMainHandStack();
			BlockPos hitPos = outlineCtx.blockPos();
			if (!(stack.getItem() instanceof HammerItem hammer)) return true;
			if (!JAAVAAUtils.isToolCorrectForBlock(stack, ctx.world().getBlockState(hitPos))) return true;

			int range = hammer.getComponents().contains(JAAVAAComponents.Types.HAMMER_RANGE)
					? hammer.getComponents().get(JAAVAAComponents.Types.HAMMER_RANGE) : 0;
			List<BlockPos> toHighlight = HammerItem.getBlocksToBeDestroyed(range, hitPos, player);
			VoxelShape combined = VoxelShapes.empty();
			for (BlockPos pos : toHighlight) {
				// use getOutlineShape so it respects partial-block shapes (e.g. slabs, fences)
				if (!JAAVAAUtils.isToolCorrectForBlock(stack, ctx.world().getBlockState(pos))) {
					continue;
				}
				VoxelShape blockShape =
						mc.world.getBlockState(pos)
								.getOutlineShape(mc.world, pos)
								.offset(pos.getX(), pos.getY(), pos.getZ());
				combined = VoxelShapes.union(combined, blockShape);
			}
			// Prepare render context and camera offset
			Vec3d cam = ctx.camera().getPos();
			MatrixStack matrices = ctx.matrixStack();
			if (Objects.equals(combined.offset(hitPos.multiply(-1)).toString(), VoxelShapes.fullCube().toString())) return true;
			VertexRendering.drawOutline(
					matrices, ctx.consumers().getBuffer(RenderLayer.getSecondaryBlockOutline()),
					combined,
					-cam.x, -cam.y, -cam.z,
					0xFF00FFFF
			);
			return false;
		});
	}
}