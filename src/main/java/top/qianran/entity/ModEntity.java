package top.qianran.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.qianran.entity.custom.ShitAEntity;
import top.qianran.entity.custom.ShitEntity;

public class ModEntity {
    public static final EntityType<ShitEntity> SHIT_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("goblin-mod","shit"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ShitEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f,0.9f))//碰撞箱
                    .build()
    );

    public static final EntityType<ShitAEntity> SHITA_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("goblin-mod","shita"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ShitAEntity::new)
                    .dimensions(EntityDimensions.fixed(0.2f,0.2f))
                    .build()
    );



}
