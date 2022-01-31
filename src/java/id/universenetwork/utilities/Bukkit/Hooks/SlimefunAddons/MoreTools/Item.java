package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.Enums.SlimefunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;

public class Item {
    // TOOLS
    public static final SlimefunItemStack CRESCENT_HAMMER = new SlimefunItemStack("CRESCENT_HAMMER", Material.IRON_HOE, "&bCrescent Hammer", "&7&oActually this is a wrench, really.", "", "&eLeft Click &7> Dismantles the machine.");

    static {
        ItemMeta meta = CRESCENT_HAMMER.getItemMeta();
        List<String> lore = meta.getLore();
        assert lore != null;
        if (getConfig("Enable-Rotation"))
            lore.add(3, ChatColor.YELLOW + "Right Click" + ChatColor.GRAY + " > Rotates the block, if it's rotatable.");
        if (getConfig("Enable-Channel-Change")) {
            lore.add(4, ChatColor.YELLOW + "Shift + Left Click" + ChatColor.GRAY + " > Increases the channel of a cargo node.");
            lore.add(5, ChatColor.YELLOW + "Shift + Right Click" + ChatColor.GRAY + " > Decreases the channel of a cargo node.");
        }
        meta.setLore(lore);
        CRESCENT_HAMMER.setItemMeta(meta);
    }

    static boolean getConfig(String path) {
        return get().getBoolean(ADDONSSETTINGS.getConfigPath() + "MoreTools.Item-Settings.Crescent-Hammer.Features." + path);
    }
}