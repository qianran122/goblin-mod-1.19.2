package top.qianran.entity.shit;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import top.qianran.entity.custom.ShitEntity;

public class ShitEntityModel extends AnimatedGeoModel<ShitEntity> {
    @Override
    public Identifier getModelResource(ShitEntity object) {
        return new Identifier("goblin-mod","geo/shit.geo.json");
    }

    @Override
    public Identifier getTextureResource(ShitEntity object) {
        return new Identifier("goblin-mod","textures/entity/shit/shit.png");
    }

    @Override
    public Identifier getAnimationResource(ShitEntity animatable) {
        return new Identifier("goblin-mod","animations/shit.animation.json");
    }
}
