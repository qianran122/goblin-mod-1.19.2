package top.qianran.entity.shita;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import top.qianran.entity.custom.ShitAEntity;

public class ShitAEntityRenderer extends GeoEntityRenderer<ShitAEntity> {
    public ShitAEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ShitAEntityModel());
    }

    @Override
    public Identifier getTextureLocation(ShitAEntity animatable) {
        return new Identifier("goblin-mod","textures/entity/shita.png");
    }


}
