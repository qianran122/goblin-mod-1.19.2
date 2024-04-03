package top.qianran.entity.shita;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import top.qianran.entity.custom.ShitAEntity;
import top.qianran.entity.custom.ShitEntity;

public class ShitAEntityModel extends AnimatedGeoModel<ShitAEntity> {
    @Override
    public Identifier getModelResource(ShitAEntity object) {
        return new Identifier("goblin-mod","geo/shita.geo.json");
    }

    @Override
    public Identifier getTextureResource(ShitAEntity object) {
        return new Identifier("goblin-mod","textures/entity/shita.png");
    }

    @Override
    public Identifier getAnimationResource(ShitAEntity animatable) {
        return new Identifier("goblin-mod","animations/shita.animation.json");
    }
}
