package top.qianran.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import java.util.UUID;

public class GoblinArmorItem extends ArmorItem {
    private static final UUID[] MODIFIERS = new UUID[]{
            UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    private final Multimap<EntityAttribute, EntityAttributeModifier> newAttributeModifiers;



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        double i = user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        user.sendMessage(Text.literal("Attack Damage: " + i), false);
        return super.use(world, user, hand);
    }

    public GoblinArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        UUID uUID = MODIFIERS[slot.getEntitySlotId()];
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uUID, "Armor modifier", (double) this.getProtection(), EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uUID, "Armor toughness", (double) this.getToughness(), EntityAttributeModifier.Operation.ADDITION));

        if (slot == EquipmentSlot.CHEST){
            UUID speedModifierUUID = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");
            builder.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(speedModifierUUID, "Armor speed boost", 0.05, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

            UUID attackDamageModifierUUID = UUID.fromString("7b0363d1-7817-43e3-a1d1-3b5c5f1e6ed2");
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(attackDamageModifierUUID, "Armor attack boost", 5.0, EntityAttributeModifier.Operation.ADDITION));

            UUID healthModifierUUID = UUID.fromString("3dd6e6a2-7dec-4288-8d70-bc3b3a10b9a6");
            builder.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(healthModifierUUID, "Armor health boost", -0.3, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

        }
        if (slot == EquipmentSlot.LEGS) {

            UUID speedModifierUUID = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");
            builder.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(speedModifierUUID, "Armor speed boost", 0.2, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));


        }
        if(slot == EquipmentSlot.FEET){
            UUID speedModifierUUID = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");
            builder.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(speedModifierUUID, "Armor speed boost", 0.05, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        newAttributeModifiers = builder.build();
    }

    private double originalHealth;
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == this) {
                // When the armor is equipped, save the player's current health
                //player.sendMessage(Text.of("You are wearing the chestplate"), false);
                this.originalHealth = player.getHealth();
            } else if (this.originalHealth != 0) {
                // When the armor is unequipped, restore the player's health
                //player.sendMessage(Text.of("You are not wearing the chestplate"), false);
                player.setHealth((float) this.originalHealth);
                this.originalHealth = 0;
            }
        }
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == this.slot) {
            return newAttributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }

}
