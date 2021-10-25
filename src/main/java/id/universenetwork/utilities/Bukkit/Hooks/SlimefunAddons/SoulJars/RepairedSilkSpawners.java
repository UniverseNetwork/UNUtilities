package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoulJars;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import static de.dustplanet.util.SilkUtil.hookIntoSilkSpanwers;
import static id.universenetwork.utilities.Bukkit.Manager.Color.Translator;
import static io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize;

public class RepairedSilkSpawners {
    public static ItemStack get(EntityType type) {
        return hookIntoSilkSpanwers().newSpawnerItem(type.name(), Translator("&bReinforced Spawner &7(" + humanize(type.name()) + ")"), 1, false);
    }
}