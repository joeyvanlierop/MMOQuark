package work.lclpnet.mmoquark.block.ext;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemColors;

public interface IItemColorProvider {

    @Environment(EnvType.CLIENT)
    void registerItemColor(ItemColors colors);
}