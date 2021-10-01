package work.lclpnet.mmoblocks.util;

import net.minecraft.block.AbstractBlock;
import work.lclpnet.mmoblocks.block.MMOBlock;

public class States {

    public static AbstractBlock.Settings copyState(MMOBlock parent) {
        AbstractBlock.Settings props = AbstractBlock.Settings.copy(parent);
        if(parent instanceof IVariantsShouldBeEmissive) props = props.emissiveLighting((s, r, p) -> true);
        return props;
    }

    public interface IVariantsShouldBeEmissive {}
}