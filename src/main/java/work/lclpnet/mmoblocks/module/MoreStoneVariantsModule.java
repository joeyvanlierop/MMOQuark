package work.lclpnet.mmoblocks.module;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import work.lclpnet.mmoblocks.block.MMOBlockRegistrar;
import work.lclpnet.mmoblocks.block.MyaliteBlock;
import work.lclpnet.mmoblocks.block.MyalitePillarBlock;
import work.lclpnet.mmoblocks.block.ext.MMOBlock;
import work.lclpnet.mmoblocks.block.ext.MMOPillarBlock;

import java.util.function.Function;

public class MoreStoneVariantsModule implements IModule {

    @Override
    public void register() {
        add("granite", MaterialColor.DIRT);
        add("diorite", MaterialColor.QUARTZ);
        add("andesite", MaterialColor.STONE);

        add("marble", MaterialColor.QUARTZ);
        add("limestone", MaterialColor.STONE);
        add("jasper", MaterialColor.RED_TERRACOTTA);
        add("slate", MaterialColor.ICE);
        add("basalt", MaterialColor.BLACK);

        add("myalite", MaterialColor.PURPLE, MyaliteBlock::new, MyalitePillarBlock::new);
    }

    private void add(String name, MaterialColor color) {
        add(name, color, MMOBlock::new, MMOPillarBlock::new);
    }

    private void add(String name, MaterialColor color, Function<AbstractBlock.Settings, MMOBlock> constr, Function<AbstractBlock.Settings, MMOPillarBlock> pillarConstr) {
        AbstractBlock.Settings settings = FabricBlockSettings.of(Material.STONE, color)
                .requiresTool()
                .breakByTool(FabricToolTags.PICKAXES)
                .strength(1.5F, 6.0F);

        MMOBlock bricks = constr.apply(settings);
        new MMOBlockRegistrar(bricks)
                .withStairs().withSlab().withVerticalSlab().withWall()
                .register(String.format("%s_bricks", name));

        new MMOBlockRegistrar(constr.apply(settings))
                .register(String.format("chiseled_%s_bricks", name));

        new MMOBlockRegistrar(constr.apply(settings))
                .register(String.format("%s_pavement", name));

        new MMOBlockRegistrar(pillarConstr.apply(settings))
                .register(String.format("%s_pillar", name));
    }
}
