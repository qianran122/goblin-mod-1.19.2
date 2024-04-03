package top.qianran.entity.goblin;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import top.qianran.entity.custom.*;

public class GoblinEntityModel extends AnimatedGeoModel<GoblinEntity> {
    @Override
    public Identifier getModelResource(GoblinEntity object) {
        return new Identifier("goblin-mod","geo/goblin.geo.json");
    }

    @Override
    public Identifier getTextureResource(GoblinEntity object) {
        return new Identifier("goblin-mod","textures/entity/goblin.png");
    }

    @Override
    public Identifier getAnimationResource(GoblinEntity animatable) {
        return new Identifier("goblin-mod","animations/goblin.animation.json");
    }
}
