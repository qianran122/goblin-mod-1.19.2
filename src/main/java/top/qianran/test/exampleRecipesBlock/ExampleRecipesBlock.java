package top.qianran.test.exampleRecipesBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class ExampleRecipesBlock extends Block {
    public ExampleRecipesBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult result) {
        // Something that gives the player items should always go through the server.
        // If you need to notify the client in some way, check in the server and then send a packet to the client.
        if (!world.isClient()) {
            // For the sake of simplicity we draw the items off of the player's hands and create an inventory from that.
            ExampleInventory exampleInventory = new ExampleInventory(player.getMainHandStack(), player.getOffHandStack());
            // Or use .getAllMatches if you want all the matches
            Optional<ExampleRecipe> match = world.getRecipeManager()
                    .getFirstMatch(ExampleRecipe.Type.INSTANCE, exampleInventory, world);

            if (match.isPresent()) {
                // Give the player the item and remove from what he has. Make sure to copy the ItemStack to not ruin it!
                player.getInventory().offerOrDrop(match.get().getOutput().copy());
                player.getMainHandStack().decrement(1);
                player.getOffHandStack().decrement(1);
            } else {
                // If it doesn't match we tell the player
                player.sendMessage(Text.literal("No match!"));
            }
        }

        return ActionResult.SUCCESS;
    }
}
