package work.lclpnet.mmoquark.module;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import work.lclpnet.mmoquark.MMOQuark;
import work.lclpnet.mmoquark.block.EnderWatcherBlock;
import work.lclpnet.mmoquark.block.MMOBlockRegistrar;
import work.lclpnet.mmoquark.blockentity.EnderWatcherBlockEntity;

public class EnderWatcherModule implements IModule {

    public static BlockEntityType<EnderWatcherBlockEntity> enderWatcherType;

    @Override
    public void register() {
        Block enderWatcher = new EnderWatcherBlock();
        new MMOBlockRegistrar(enderWatcher)
                .register("ender_watcher", ItemGroup.REDSTONE);

        enderWatcherType = Registry.register(Registry.BLOCK_ENTITY_TYPE, MMOQuark.identifier("ender_watcher"),
                BlockEntityType.Builder.create(EnderWatcherBlockEntity::new, enderWatcher).build(null));
    }
}