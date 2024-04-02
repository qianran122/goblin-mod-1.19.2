package top.qianran.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import top.qianran.entity.ModEntity;
import top.qianran.entity.custom.ShitEntity;

public class Registries {
    public static void init(){
        registerAttributes();
    }

    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntity.SHIT_ENTITY, ShitEntity.setAttributes());
    }
}
