package top.qianran.entity.shit;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import top.qianran.entity.custom.ShitEntity;

public class ShitEntityRenderer extends GeoEntityRenderer<ShitEntity> {
    public ShitEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ShitEntityModel());
    }

    @Override
    public Identifier getTextureLocation(ShitEntity animatable) {
        return new Identifier("goblin-mod","textures/entity/shit.png");
    }

}
