package top.qianran.entity.goblin;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import top.qianran.entity.custom.*;
import top.qianran.entity.shit.ShitEntityModel;

public class GoblinEntityRenderer extends GeoEntityRenderer<GoblinEntity> {
    public GoblinEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GoblinEntityModel());
    }

    @Override
    public Identifier getTextureLocation(GoblinEntity animatable) {
        return new Identifier("goblin-mod","textures/entity/goblin.png");
    }

}
