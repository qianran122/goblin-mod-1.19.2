package top.qianran;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class DemoBlockEntity extends BlockEntity {
    public DemoBlockEntity(BlockPos pos, BlockState state){
        super(GoblinMod.DEMO_BLOCK_ENTITY, pos, state);
    }
}
