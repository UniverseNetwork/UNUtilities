package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LightningReceptor extends SlimefunItem implements EnergyNetProvider {
    static final long MIN_DELAY = TimeUnit.MINUTES.toMillis(8);
    static final int CHANCE = 8;
    final Map<Location, Long> lastLightningStrike = new HashMap<>();
    final int minPower;
    final int maxPower;

    public LightningReceptor(ItemGroup itemGroup, SlimefunItemStack item, int min, int max, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.minPower = min;
        this.maxPower = max;
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    @Override
    public int getGeneratedOutput(Location l, @Nonnull Config config) {
        if (!l.getWorld().isThundering()) return 0;
        Long previousLightningStrike = lastLightningStrike.get(l);
        if (previousLightningStrike != null && System.currentTimeMillis() - previousLightningStrike < MIN_DELAY)
            return 0;
        if (ThreadLocalRandom.current().nextInt(100) < CHANCE) {
            lastLightningStrike.put(l, System.currentTimeMillis());
            JavaPlugin plugin = getAddon().getJavaPlugin();
            Bukkit.getScheduler().runTask(plugin, () -> l.getWorld().strikeLightningEffect(l));
            return ThreadLocalRandom.current().nextInt(minPower, maxPower);
        }
        return 0;
    }

    @Override
    public int getCapacity() {
        return 0;
    }
}