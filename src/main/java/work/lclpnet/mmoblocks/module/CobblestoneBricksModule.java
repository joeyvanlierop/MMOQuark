package work.lclpnet.mmoblocks.module;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import work.lclpnet.mmoblocks.block.MMOBlockRegistrar;

import static net.minecraft.block.AbstractBlock.Settings.copy;

public class CobblestoneBricksModule implements IModule {

    @Override
    public void register() {
        addBricks("cobblestone_bricks", Blocks.MOSSY_COBBLESTONE);
        addBricks("mossy_cobblestone_bricks", Blocks.MOSSY_COBBLESTONE);

        addBricks("dirt_bricks", Blocks.DIRT);
        addBricks("netherrack_bricks", Blocks.NETHERRACK);
        addBricks("vanilla_basalt_bricks", Blocks.BASALT);
    }

    private void addBricks(String name, Block block) {
        new MMOBlockRegistrar(copy(block))
                .withSlab().withWall().withStairs().withVerticalSlab()
                .register(name);
    }
}