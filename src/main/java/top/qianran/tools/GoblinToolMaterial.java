package top.qianran.tools;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import top.qianran.util.ModItems;

public class GoblinToolMaterial implements ToolMaterial {
    //耐久
    @Override
    public int getDurability() {
        return 1000;
    }

    //挖掘速度
    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
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
        return 10;
    }

    //修复材料
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.GOBLIN_MEAT);
    }
}
