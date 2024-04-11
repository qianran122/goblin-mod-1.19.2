package top.qianran.mixin;

import com.google.common.collect.Multimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ArmorItem.class)
public interface ArmorItemAccessor {
    @Accessor("attributeModifiers")
    Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers();

    @Accessor("attributeModifiers")
    void setAttributeModifiers(Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers);
}