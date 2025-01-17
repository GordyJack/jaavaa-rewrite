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

public class RecyclingScreen extends HandledScreen<RecyclingScreenHandler> {
    //Constructor
    public RecyclingScreen(RecyclingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    //Fields
    public static final Identifier TEXTURE = JAAVAA.id("textures/gui/container/recycling_table.png");

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
        RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        drawContext.drawTexture(RenderLayer::getGuiTextured, TEXTURE,
                this.xD2(), this.yD2(),
                0, 0,
                backgroundWidth, backgroundHeight,
                256, 256);
//        if (!this.handler.isEmpty() && !this.handler.hasRecipe()) {
//            drawContext.drawTexture(RenderLayer::getGuiTextured, TEXTURE, this.xD2() + 92, this.yD2() + 31, this.backgroundWidth, 0, 28, 21, 256, 256);
//        }
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
