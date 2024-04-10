package top.qianran.armor.material;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import top.qianran.items.group.GoblinGroupThings;

public class GoblinArmorMaterial implements ArmorMaterial {

    //
    private static final int[] PROTECTION = {4, 7, 8, 3};
    //耐久
    @Override
    public int getDurability(EquipmentSlot slot) {
        return 500;
    }

    //护甲值
    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION[slot.getEntitySlotId()];
    }

    //获得优秀附魔概率
    @Override
    public int getEnchantability() {
        return 10;
    }

    //护甲材质对应的装备音效
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    //修复材料
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(GoblinGroupThings.GOBLIN_MEAT);
    }

    @Override
    public String getName() {
        return "goblin";
    }

    //盔甲韧性
    @Override
    public float getToughness() {
        return 1.2f;
    }

    //抗击退
    @Override
    public float getKnockbackResistance() {
        return 1f;
    }
}
