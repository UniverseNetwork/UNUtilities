package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import org.bukkit.Location;

import static java.util.concurrent.ThreadLocalRandom.current;

public class LightningReceptor extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider {
    static final long MIN_DELAY = java.util.concurrent.TimeUnit.MINUTES.toMillis(8);
    static final int CHANCE = 8;
    final java.util.Map<Location, Long> lastLightningStrike = new java.util.HashMap<>();
    final int minPower;
    final int maxPower;

    public LightningReceptor(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, int min, int max, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.minPower = min;
        this.maxPower = max;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    @Override
    public int getGeneratedOutput(Location l, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config config) {
        if (!l.getWorld().isThundering()) return 0;
        Long previousLightningStrike = lastLightningStrike.get(l);
        if (previousLightningStrike != null && System.currentTimeMillis() - previousLightningStrike < MIN_DELAY)
            return 0;
        if (current().nextInt(100) < CHANCE) {
            lastLightningStrike.put(l, System.currentTimeMillis());
            org.bukkit.Bukkit.getScheduler().runTask(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, () -> l.getWorld().strikeLightningEffect(l));
            return current().nextInt(minPower, maxPower);
        }
        return 0;
    }

    @Override
    public int getCapacity() {
        return 0;
    }
}