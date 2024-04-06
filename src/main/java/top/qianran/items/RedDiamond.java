package top.qianran.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import top.qianran.util.Registries;
import top.qianran.util.TestGroup;

import java.util.List;

public class
RedDiamond extends Item {



    public RedDiamond(Settings settings) {
        super(settings);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(hand == Hand.MAIN_HAND){
            user.playSound(BlockSoundGroup.ANVIL.getPlaceSound(), 1.0F, 1.0F);
            user.setHealth(5.0f);
            world.createExplosion(user, user.getX(), user.getY(), user.getZ(), 10.0f, true, Explosion.DestructionType.DESTROY);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }


    //物品提示
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.goblin-mod.red_diamond.tooltip"));
    }
}
