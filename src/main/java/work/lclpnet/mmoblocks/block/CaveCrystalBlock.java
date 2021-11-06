package work.lclpnet.mmoblocks.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import work.lclpnet.mmoblocks.block.ext.MMOGlassBlock;
import work.lclpnet.mmoblocks.util.Env;
import work.lclpnet.mmoblocks.util.MMORenderLayers;

import java.util.Random;

public class CaveCrystalBlock extends MMOGlassBlock {

    public final float[] colorComponents;
    public final Vec3d colorVector;

    public CaveCrystalBlock(MaterialColor materialColor, int color) {
        super(FabricBlockSettings.of(Material.GLASS, materialColor)
                .strength(0.3F, 0F)
                .sounds(BlockSoundGroup.GLASS)
                .luminance(b -> 11)
                .breakByTool(FabricToolTags.PICKAXES, 0)
                .requiresTool()
                .nonOpaque());

        float r = ((color >> 16) & 0xff) / 255f;
        float g = ((color >> 8) & 0xff) / 255f;
        float b = (color & 0xff) / 255f;
        colorComponents = new float[]{r, g, b};
        colorVector = new Vec3d(r, g, b);

        if (Env.isClient()) registerRenderLayer();
    }

    @Environment(EnvType.CLIENT)
    private void registerRenderLayer() {
        MMORenderLayers.setBlockRenderType(this, RenderLayer.getTranslucent());
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        for(int i = 0; i < 4; i++) {
            double range = 5;

            double ox = random.nextDouble() * range - (range / 2);
            double oy = random.nextDouble() * range - (range / 2);
            double oz = random.nextDouble() * range - (range / 2);

            double x = (double)pos.getX() + 0.5 + ox;
            double y = (double)pos.getY() + 0.5 + oy;
            double z = (double)pos.getZ() + 0.5 + oz;

            float size = 0.4F + random.nextFloat() * 0.5F;

            if(random.nextDouble() < 0.1) {
                double ol = ((ox * ox) + (oy * oy) + (oz * oz)) * -2;
                if(ol == 0)
                    ol = 0.0001;
                world.addParticle(ParticleTypes.END_ROD, x, y, z, ox / ol, oy / ol, oz / ol);
            }

            world.addParticle(new DustParticleEffect(colorComponents[0], colorComponents[1], colorComponents[2], size), x, y, z, 0, 0, 0);
        }
    }
}
