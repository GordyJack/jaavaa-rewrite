package net.gordyjack.jaavaa.screen;

import net.gordyjack.jaavaa.*;
import net.minecraft.client.gl.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.entity.player.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

public class RecyclingScreen extends HandledScreen<RecyclingEntityScreenHandler> {
    //Constants
    public static final Identifier GUI_TEXTURE = JAAVAA.id("textures/gui/container/recycling_table.png");
    private static final int PROGRESS_ARROW_WIDTH = 51;
    private static final int PROGRESS_ARROW_HEIGHT = 20;
    //Constructor
    public RecyclingScreen(RecyclingEntityScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    //Methods
    //Custom Methods
    private int xD2() {
        return (width-backgroundWidth)/2;
    }
    private int yD2() {
        return (height-backgroundHeight)/2;
    }
    //Inherited Methods
    @Override
    protected void drawBackground(DrawContext drawContext, float delta, int mouseX, int mouseY) {
        drawContext.drawTexture(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                this.xD2(), this.yD2(),
                0, 0,
                backgroundWidth, backgroundHeight,
                256, 256);
        if (handler.isCrafting()) {
            int scaledProgress = handler.getScaledArrowProgress();
            drawContext.drawTexture(RenderPipelines.GUI_TEXTURED,
                    GUI_TEXTURE,
                    xD2() + 57, yD2() + 32,
                    176, 21,
                    scaledProgress, PROGRESS_ARROW_HEIGHT,
                    scaledProgress, PROGRESS_ARROW_HEIGHT,
                    256, 256);
        }
    }
    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth-textRenderer.getWidth(title))/2;
    }
    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        renderBackground(drawContext, mouseX, mouseY, delta);
        super.render(drawContext, mouseX, mouseY, delta);
        drawMouseoverTooltip(drawContext, mouseX, mouseY);
    }
}
