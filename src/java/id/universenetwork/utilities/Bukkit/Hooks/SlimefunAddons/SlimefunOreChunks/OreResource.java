package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SlimefunOreChunks;

import org.bukkit.NamespacedKey;
import org.bukkit.World.Environment;

public class OreResource implements io.github.thebusybiscuit.slimefun4.api.geo.GEOResource {
    final NamespacedKey key;
    final OreChunk oreChunk;

    public OreResource(NamespacedKey key, OreChunk item) {
        this.key = key;
        oreChunk = item;
    }

    @Override
    public String getName() {
        return oreChunk.getName();
    }

    @Override
    public int getDefaultSupply(Environment environment, org.bukkit.block.Biome biome) {
        if (environment == Environment.NORMAL) {
            int amplifier = oreChunk.getAmplifier();
            return java.util.concurrent.ThreadLocalRandom.current().nextInt(amplifier * 2, 18 + amplifier * 4);
        }
        return 0;
    }

    @Override
    public int getMaxDeviation() {
        return 6;
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public org.bukkit.inventory.ItemStack getItem() {
        return oreChunk.getItem();
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }
}