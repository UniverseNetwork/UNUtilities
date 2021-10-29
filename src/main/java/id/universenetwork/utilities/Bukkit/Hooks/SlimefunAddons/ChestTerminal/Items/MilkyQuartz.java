package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.Items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static java.util.concurrent.ThreadLocalRandom.current;

public class MilkyQuartz implements io.github.thebusybiscuit.slimefun4.api.geo.GEOResource {
    final NamespacedKey key;
    final ItemStack item;

    public MilkyQuartz(ItemStack item) {
        this.key = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "milky_quartz");
        this.item = item;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public int getDefaultSupply(org.bukkit.World.Environment environment, @NotNull org.bukkit.block.Biome biome) {
        switch (environment) {
            case THE_END:
                return 0;
            case NETHER:
                return current().nextInt(12);
            default:
                return current().nextInt(8);
        }
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return item;
    }

    @Override
    public int getMaxDeviation() {
        return 5;
    }

    @Override
    @NotNull
    public String getName() {
        return "Milky Quartz";
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }
}