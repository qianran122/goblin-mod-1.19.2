package top.qianran;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import top.qianran.entity.CubeEntity;

/*
 * 一个用来提供模型、阴影大小和纹理的渲染器
 * 阴影大小是 0.5f
 */
public class CubeEntityRenderer extends MobEntityRenderer<CubeEntity, CubeEntityModel> {


    public CubeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CubeEntityModel(context.getPart(GoblinModClient.MODEL_CUBE_LAYER)), 0.5f);
    }
    @Override
    public Identifier getTexture(CubeEntity entity) {
        return new Identifier("goblin-mod", "textures/entity/cube/cube.png");
    }
}