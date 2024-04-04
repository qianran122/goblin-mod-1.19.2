package top.qianran.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoblinItem extends Item {
    private String tooltip;

    public GoblinItem(Settings settings) {
        super(settings);
    }

    public GoblinItem(Settings settings, String tooltip) {
        super(settings);
        this.toolTip = tooltip;
    }

    private String toolTip = null;
    public void setTooltip(String tooltip){
        this.toolTip = tooltip;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (this.toolTip != null) {
            tooltip.add(Text.of(this.toolTip));
        }
    }
}
