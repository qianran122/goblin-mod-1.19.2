package top.qianran.tools;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import top.qianran.items.group.GoblinGroupThings;
//原版数据参阅ToolMaterials类
public class GoblinToolMaterial implements ToolMaterial {
    //耐久
    @Override
    public int getDurability() {
        return 156;
    }

    //挖掘速度
    @Override
    public float getMiningSpeedMultiplier() {
        return 10.5f;
    }

    //攻击伤害
    @Override
    public float getAttackDamage() {
        return -1;
    }

    //挖掘等级
    @Override
    public int getMiningLevel() {
        return 4;
    }

    //获取优秀附魔的概率
    @Override
    public int getEnchantability() {
        return 15;
    }

    //修复材料
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(GoblinGroupThings.GOBLIN_INGOT);
    }
}
