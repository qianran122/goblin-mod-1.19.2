package top.qianran.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import top.qianran.blocks.uiBlock.UIBlockScreenHandler;

public class UIBlockScreen extends HandledScreen<UIBlockScreenHandler> {

    private static final Identifier TEXTURE = new Identifier("goblin-mod", "textures/gui/container/ui_block.png");
    /**
     * x:目标x坐标
     * y:目标y坐标
     * u:替代x坐标
     * v:替代y坐标
     * w:替代的高度
     * h:替代的宽度
     */
    public UIBlockScreen(UIBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.passEvents = false;
        this.backgroundHeight = 167;//背景图片高度
        this.playerInventoryTitleY = this.backgroundHeight - 94;//玩家背包标题的Y坐标
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = this.x;
        int y = this.y;
        this.drawTexture(matrices, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
        //绘制进度条
        int tick =  (int)this.handler.getTick()/2;
        this.drawTexture(matrices, x + 68, y + 41, 176, 4, tick , 4);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
