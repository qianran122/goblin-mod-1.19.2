package top.qianran.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import top.qianran.entity.ModEntity;
import top.qianran.entity.custom.*;

public class Registries {
    public static void init(){
        registerAttributes();
    }

    //为实体注册属性
    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntity.SHIT_ENTITY, ShitEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntity.SHITA_ENTITY, ShitAEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntity.GOBLIN_ENTITY, GoblinEntity.setAttributes());
    }
}
