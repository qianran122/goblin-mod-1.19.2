package top.qianran.entity.cube;


import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.include.com.google.common.collect.ImmutableList;
import top.qianran.CubeEntity;

public class CubeEntityModel extends EntityModel<CubeEntity> {

    //标准的模型在类的顶部提供并在构造方法中初始化“部位（parts）”，即 ModelPart 对象，
    // 在构造方法中实例化，在 getTexturedModelData 方法中获得数据，然后在 render 方法中渲染它们。
    // 注意 setAngles 和 render 是 EntityModel 类的抽象方法，必须重写。
    private final ModelPart base;
    public CubeEntityModel(ModelPart modelPart) {
        this.base = modelPart.getChild(EntityModelPartNames.CUBE);
    }

    //在创建一个部位之后，我们需要添加一个形状（shape）。
    // 为此，我们必须为根部添加一个子形状。
    // 新部分的纹理位置在 .uv 中，其偏移位于 .cuboid 的前三个数字中，
    // 尺寸则为 .cuboid 的后三个数字。注意，模型的原点从拐角处开始，所以你需要一些偏移让它居中：
    // 你可以使用 BlockBench，制作你的模型并为你的实体模型导出以得到这个方法。
      public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 12F, 12F, 12F), ModelTransform.pivot(0F, 0F, 0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    //我们的实体模型现在有了一个 12x12x12 的方块（大约一个方块的75%），并以0, 0, 0为中心。
    // setAngles 用于模型的动画，但是目前我们留空。
    // render 用来告诉游戏我们的实体出现在哪。
    // 注意标准的原版模型通常看起来比它们的碰撞体积更大，
    // 因此，我们在这里把模型变小。
    @Override
    public void setAngles(CubeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.base).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
    }
    //要完成我们的模型，我们还需要一个纹理文件。
    // 默认的纹理大小是64宽、32高的；
    // 你可以通过在 texturedModelData 添加返回值来改变。

}