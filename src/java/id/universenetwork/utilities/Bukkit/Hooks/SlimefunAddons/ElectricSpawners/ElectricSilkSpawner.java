package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ElectricSpawners;

import de.dustplanet.util.SilkUtil;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.RepairedSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Utils.Color.Translate;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.REPAIRED_SPAWNER;
import static io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize;

public class ElectricSilkSpawner {
    public static ItemStack get(EntityType mob, boolean SilkSpawnersSupport) {
        if (SilkSpawnersSupport)
            return SilkUtil.hookIntoSilkSpanwers().newSpawnerItem(mob.name(), Translate("&bReinforced Spawner &7(" + humanize(mob.name()) + ")"), 1, false);
        else return REPAIRED_SPAWNER.getItem(RepairedSpawner.class).getItemForEntityType(mob);
    }
}
