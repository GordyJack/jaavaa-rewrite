package net.gordyjack.jaavaa.screen;

import com.mojang.blaze3d.systems.*;
import net.gordyjack.jaavaa.*;
import net.minecraft.client.gl.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.client.render.*;
import net.minecraft.entity.player.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

import java.util.function.*;

public class AlloyFurnaceBlockEntityScreen extends HandledScreen<AlloyFurnaceBlockEntityScreenHandler> {
    private static final int GUI_WIDTH = 176;
    private static final int GUI_HEIGHT = 181;
    private static final int GUI_TEXTURE_SIZE = 256;
    private static final int FLAME_SIZE = 18;
    private static final Identifier GUI_TEXTURE = JAAVAA.id("textures/gui/container/alloy_furnace.png");
    private static final Identifier FLAME_TEXTURE = Identifier.of("textures/gui/sprites/container/blast_furnace/lit_progress.png");
    
    public AlloyFurnaceBlockEntityScreen(AlloyFurnaceBlockEntityScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - GUI_WIDTH) / 2;
        int y = (height - GUI_HEIGHT) / 2;
        
        context.drawTexture(RenderLayer::getGuiTextured,
                GUI_TEXTURE,
                x, y,
                0f, 0f,
                GUI_WIDTH, GUI_HEIGHT,
                GUI_WIDTH, GUI_HEIGHT,
                GUI_TEXTURE_SIZE, GUI_TEXTURE_SIZE);
        
        if (handler.isLit()) {
            context.drawGuiTexture(RenderLayer::getGuiTextured,
                    FLAME_TEXTURE,
                    0,0,
                    FLAME_SIZE, FLAME_SIZE);
        }
    }
    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        // Draw the title of the screen
        context.drawText(textRenderer, title, (GUI_WIDTH - textRenderer.getWidth(title)) / 2, -2, 0x404040, false);
        // Draw the "Inventory" label for the player inventory
        context.drawText(textRenderer, playerInventoryTitle, 8, GUI_HEIGHT - 108, 0x404040, false);
    }
}
