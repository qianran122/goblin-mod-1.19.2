package top.qianran.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
public class NbtCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        //注册nbt命令,使用权限等级为4
        dispatcher.register(literal("nbt").requires(c -> c.hasPermissionLevel(4))
                .then(argument("slot", IntegerArgumentType.integer())).requires(c -> c.hasPermissionLevel(4))
                .executes(c -> getNbt(IntegerArgumentType.getInteger(c, "slot"), c.getSource().getPlayer())));
    }
    private static int getNbt(int slot, PlayerEntity player){
        if(slot>=0 && slot<player.getInventory().size()){
            ItemStack stack = player.getInventory().getStack(slot);
            if (stack.hasNbt()) {
                assert stack.getNbt() != null;
                String s = stack.getNbt().toString();
                player.sendMessage(Text.literal(s), false);
                return Command.SINGLE_SUCCESS;
            } else {
                player.sendMessage(Text.literal("物品没有NBT数据"), false);
            }
            return 0;
        } else {
            player.sendMessage(Text.literal("物品栏没有这个格子"), false);
        }
        return 0;
    }
}
