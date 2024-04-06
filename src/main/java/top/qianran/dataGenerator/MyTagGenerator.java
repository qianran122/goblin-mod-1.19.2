package top.qianran.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MyTagGenerator extends FabricTagProvider {
    /**
     * Construct a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided. For example @see BlockTagProvider
     *
     * @param dataGenerator The data generator instance
     * @param registry      The backing registry for the Tag type.
     */
    public MyTagGenerator(FabricDataGenerator dataGenerator, Registry registry) {
        super(dataGenerator, registry);
    }

    private static final TagKey<Item> SMELLY_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier("goblin-mod", "smelly_items"));

    @Override
    protected void generateTags() {

    }
}
