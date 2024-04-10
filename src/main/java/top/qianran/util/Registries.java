package top.qianran.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import top.qianran.entity.custom.*;
import top.qianran.items.group.GoblinGroupThings;

public class Registries {


    public static void init(){
        registerAttributes();
        flammableBlockRegistry();
        strippableRegistry();
        GoblinGroupThings.setGoblinGroupThings();
    }

    //为实体注册属性
    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.GOBLIN_ENTITY, GoblinEntity.setAttributes());
    }

    //将方块注册为可剥皮
    private static void strippableRegistry() {
        StrippableBlockRegistry.register(GoblinGroupThings.MYSTERIOUS_LOG, GoblinGroupThings.MYSTERIOUS_LOG_STRIPPED);
        StrippableBlockRegistry.register(GoblinGroupThings.MYSTERIOUS_WOOD, GoblinGroupThings.MYSTERIOUS_WOOD_STRIPPED);

    }

    //将方块注册为可燃烧
    private static void flammableBlockRegistry() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(GoblinGroupThings.MYSTERIOUS_LOG, 5, 5);
        registry.add(GoblinGroupThings.MYSTERIOUS_WOOD, 5, 5);
        registry.add(GoblinGroupThings.MYSTERIOUS_LOG_STRIPPED, 5, 5);
        registry.add(GoblinGroupThings.MYSTERIOUS_WOOD_STRIPPED, 5, 5);
        registry.add(GoblinGroupThings.MYSTERIOUS_PLANKS, 5, 20);
        registry.add(GoblinGroupThings.MYSTERIOUS_LEAVES, 30, 60);

    }
}
