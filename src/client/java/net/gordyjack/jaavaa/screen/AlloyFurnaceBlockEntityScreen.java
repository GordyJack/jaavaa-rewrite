package net.gordyjack.jaavaa.screen;

import net.gordyjack.jaavaa.*;
import net.minecraft.client.gl.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.entity.player.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

public class AlloyFurnaceBlockEntityScreen extends HandledScreen<AlloyFurnaceBlockEntityScreenHandler> {
    private static final int GUI_WIDTH = 176;
    private static final int GUI_HEIGHT = 181;
    private static final int GUI_TEXTURE_SIZE = 256;
    private static final int FLAME_SIZE = 14;
    private static final int PROGRESS_ARROW_WIDTH = 58;
    private static final int PROGRESS_ARROW_HEIGHT = 35;
    private static final Identifier GUI_TEXTURE = JAAVAA.id("textures/gui/container/alloy_furnace.png");
    private static final Identifier FLAME_TEXTURE = JAAVAA.id("container/alloy_furnace/lit_progress");
    private static final Identifier PROGRESS_ARROW_TEXTURE = JAAVAA.id("textures/gui/sprites/container/alloy_furnace/progress_arrow.png");

    public AlloyFurnaceBlockEntityScreen(AlloyFurnaceBlockEntityScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(RenderPipelines.GUI_TEXTURED,
                GUI_TEXTURE,
                xD2(), yD2(),
                0f, 0f,
                GUI_WIDTH, GUI_HEIGHT,
                GUI_WIDTH, GUI_HEIGHT,
                GUI_TEXTURE_SIZE, GUI_TEXTURE_SIZE);
        if (handler.isLit()) {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED,
                    FLAME_TEXTURE,
                    xD2() + 81, yD2() + 28,
                    FLAME_SIZE, FLAME_SIZE);
        }
        if (handler.isCrafting()) {
            int scaledProgress = handler.getScaledArrowProgress();
            context.drawTexture(RenderPipelines.GUI_TEXTURED,
                    PROGRESS_ARROW_TEXTURE,
                    xD2() + 59, yD2() + 35,
                    0, 0,
                    PROGRESS_ARROW_WIDTH, scaledProgress,
                    PROGRESS_ARROW_WIDTH, PROGRESS_ARROW_HEIGHT);
        }
    }
    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        renderBackground(drawContext, mouseX, mouseY, delta);
        super.render(drawContext, mouseX, mouseY, delta);
        drawMouseoverTooltip(drawContext, mouseX, mouseY);
    }
    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth-textRenderer.getWidth(title))/2;
    }
    //Custom Methods
    private int xD2() {
        return (width-backgroundWidth)/2;
    }
    private int yD2() {
        return (height-backgroundHeight)/2;
    }
}
