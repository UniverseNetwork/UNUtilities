package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SlimefunOreChunks;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import org.bukkit.NamespacedKey;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

class OreResource implements GEOResource {
    private final NamespacedKey key;
    private final OreChunk oreChunk;

    public OreResource(NamespacedKey key, OreChunk item) {
        this.key = key;
        this.oreChunk = item;
    }

    @Override
    public String getName() {
        return oreChunk.getName();
    }

    @Override
    public int getDefaultSupply(Environment environment, Biome biome) {
        if (environment == Environment.NORMAL) {
            int amplifier = this.oreChunk.getAmplifier();
            return ThreadLocalRandom.current().nextInt(amplifier * 2, 18 + amplifier * 4);
        } else return 0;
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
    public ItemStack getItem() {
        return oreChunk.getItem();
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }
}
