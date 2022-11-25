package com.reviksedy.foodmania.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.reviksedy.foodmania.Foodmania;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BrickOvenScreen extends AbstractContainerScreen<BrickOvenMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Foodmania.MOD_ID, "textures/gui/brick_oven_gui.png");

    public BrickOvenScreen(BrickOvenMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        if(menu.isCrafting()) {
            blit(pPoseStack, x + 76, y + 34, 176, 0, menu.getScaledProgress(), 17);
        }
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 113, y + 58, 176, 17, 14, menu.getScaledBurnTime());
        }
        if(!menu.isCrafting()) {
            blit(pPoseStack, x + 113, y + 58, 176, 17, 14, 14);
        }

    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}