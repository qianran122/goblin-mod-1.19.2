package top.qianran.entity.shit;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.example.client.renderer.entity.layer.GeoExampleLayer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import top.qianran.entity.custom.AAALEEntity;

public class AAALERenderer extends GeoEntityRenderer<AAALEEntity> {
	public AAALERenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new AAALEModel());

		this.shadowRadius = 0.25f;

        addLayer(new GeoExampleLayer(this));
    }
}
