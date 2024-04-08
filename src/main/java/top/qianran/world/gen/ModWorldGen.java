package top.qianran.world.gen;

public class ModWorldGen {
    public static void gen() {
        //先矿石生成，再树木生成，最后花朵生成
        ModOreGeneration.ore();
        ModTreeGeneration.tree();
        ModFlowerGeneration.flower();
    }
}
