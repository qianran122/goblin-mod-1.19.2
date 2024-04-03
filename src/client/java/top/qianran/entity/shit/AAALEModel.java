package top.qianran.entity.shit;

import net.minecraft.util.Identifier;
import software.bernie.example.client.EntityResources;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import top.qianran.entity.custom.AAALEEntity;

public class AAALEModel extends AnimatedTickingGeoModel<AAALEEntity> {

	@Override
	public Identifier getModelResource(AAALEEntity object) {
		return EntityResources.LAYER_EXAMPLE_MODEL;
	}

	@Override
	public Identifier getTextureResource(AAALEEntity object) {
		return EntityResources.LAYER_EXAMPLE_TEXTURE;
	}

	@Override
	public Identifier getAnimationResource(AAALEEntity animatable) {
		return EntityResources.LAYER_EXAMPLE_ANIMATIONS;
	}

}