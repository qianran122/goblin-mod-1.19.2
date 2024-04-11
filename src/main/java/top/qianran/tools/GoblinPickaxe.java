package top.qianran.tools;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoblinPickaxe extends PickaxeItem {
    public GoblinPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.goblin-mod.goblin_pickaxe.tooltip"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.sendMessage(Text.literal("Goblin Pickaxe Used"));
        return super.use(world, user, hand);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        user.sendMessage(Text.literal("Goblin Pickaxe Stopped Using"));
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }


    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {

        BlockHitResult nearestBlockFace = world.raycast(
                new RaycastContext(miner.getEyePos(),
                        new Vec3d(pos.getX(), pos.getY(), pos.getZ()),
                        RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, miner));
        Direction side = nearestBlockFace.getSide();
        miner.sendMessage(Text.literal("Nearest Block Side: " + side));
        if(side == Direction.UP || side == Direction.DOWN){
            tryBreakBlock(world,state,getPos(pos, 1,0,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, -1,0,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,0,1 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,0,-1 ), miner);
            tryBreakBlock(world,state,getPos(pos, 1,0,-1 ), miner);
            tryBreakBlock(world,state,getPos(pos, -1,0,1 ), miner);
            tryBreakBlock(world,state,getPos(pos, 1,0,1 ), miner);
            tryBreakBlock(world,state,getPos(pos, -1,0,-1 ), miner);

        }
        if(side == Direction.NORTH || side == Direction.SOUTH){
            tryBreakBlock(world,state,getPos(pos, 1,0,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, -1,0,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,1,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,-1,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, 1,-1,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, -1,1,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, 1,1,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, -1,-1,0 ), miner);

        }
        if(side == Direction.EAST || side == Direction.WEST){
            tryBreakBlock(world,state,getPos(pos, 0,1,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,-1,0 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,0,1 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,0,-1 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,1,-1 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,-1,1 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,1,1 ), miner);
            tryBreakBlock(world,state,getPos(pos, 0,-1,-1 ), miner);

        }
        return super.postMine(stack, world, state, pos, miner);
    }

    private BlockPos getPos(BlockPos pos, int x, int y, int z ){
        return new BlockPos(pos.getX()+x, pos.getY()+y, pos.getZ()+z);
    }
    private void tryBreakBlock(World world, BlockState state, BlockPos pos, LivingEntity miner){
        boolean b = false;
        String blockName = world.getBlockState(pos).getBlock().getTranslationKey();
        miner.sendMessage(Text.literal("Block name: " + blockName));

        Material material = world.getBlockState(pos).getMaterial();
        miner.sendMessage(Text.literal("Block material: " + material.toString()));

        float hardness = world.getBlockState(pos).getHardness(world, pos);
        miner.sendMessage(Text.literal("Block hardness: " + hardness));
        if(hardness > 0f && hardness <= 3f && material == Material.STONE){
            b = true;
        }
        if(b) world.breakBlock(pos, true, miner);
    }
}
