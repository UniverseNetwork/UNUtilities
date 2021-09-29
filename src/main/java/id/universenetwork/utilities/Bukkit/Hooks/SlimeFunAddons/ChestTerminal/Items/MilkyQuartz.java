package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.ChestTerminal.Items;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import org.bukkit.NamespacedKey;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class MilkyQuartz implements GEOResource {
    final NamespacedKey key;
    final ItemStack item;

    public MilkyQuartz(ItemStack item) {
        this.key = new NamespacedKey(plugin, "milky_quartz");
        this.item = item;
    }

    @Nonnull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public int getDefaultSupply(Environment environment, @Nonnull Biome biome) {
        switch (environment) {
            case THE_END:
                return 0;
            case NETHER:
                return ThreadLocalRandom.current().nextInt(12);
            default:
                return ThreadLocalRandom.current().nextInt(8);
        }
    }

    @Override
    @Nonnull
    public ItemStack getItem() {
        return item;
    }

    @Override
    public int getMaxDeviation() {
        return 5;
    }

    @Override
    @Nonnull
    public String getName() {
        return "Milky Quartz";
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }
}