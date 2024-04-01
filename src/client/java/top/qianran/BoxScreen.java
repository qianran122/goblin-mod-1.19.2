package top.qianran;


import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class BoxScreen extends HandledScreen<ScreenHandler> {
    // GUI 纹理的路径，本例中使用发射器中的纹理
    private static final Identifier TEXTURE = new Identifier("goblin-mod", "textures/gui/container/box.png");

    /*public BoxScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }*/
    public BoxScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, getPositionText(handler).orElse(title));
        //我们尝试获取方块位置以将其用作我们的标题，如果由于某种原因失败，我们将使用默认标题
    }
    //此方法将尝试从 ScreenHandler 获取位置，因为 ScreenRendering 仅发生在客户端上，
    //我们在此处获取具有正确 BlockPos 的 ScreenHandler 实例！
    private static Optional<Text> getPositionText(ScreenHandler handler) {
        if (handler instanceof BoxScreenHandler) {
            BlockPos pos = ((BoxScreenHandler) handler).getPos();
            return pos != null ? Optional.of(Text.of("(" + pos.toShortString() + ")")) : Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(2.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        //1.20或者以上的版本，这个方法在DrawContext类里面。
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // 将标题居中
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}