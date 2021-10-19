package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ColoredEnderChests;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class ColoredEnderChests {
    protected final Map<Integer, String> colors = new HashMap<>();
    protected ItemGroup itemGroup;

    public ColoredEnderChests() {
        if (Enabled("ColoredEnderChests")) {
            Research enderChestsResearch = new Research(new NamespacedKey(plugin, "colored_enderchests"), 2610, "Colored Ender Chests", 20);
            Research bigEnderChestsResearch = new Research(new NamespacedKey(plugin, "big_colored_enderchests"), 2611, "Big Colored Ender Chests", 30);
            enderChestsResearch.register();
            bigEnderChestsResearch.register();
            colors.put(0, "&rWhite");
            colors.put(1, "&6Orange");
            colors.put(2, "&dMagenta");
            colors.put(3, "&bLight Blue");
            colors.put(4, "&eYellow");
            colors.put(5, "&aLime");
            colors.put(6, "&dPink");
            colors.put(7, "&8Dark Gray");
            colors.put(8, "&7Light Gray");
            colors.put(9, "&3Cyan");
            colors.put(10, "&5Purple");
            colors.put(11, "&9Blue");
            colors.put(12, "&6Brown");
            colors.put(13, "&2Green");
            colors.put(14, "&4Red");
            colors.put(15, "&8Black");
            itemGroup = new ItemGroup(new NamespacedKey(plugin, "colored_enderchests"), new CustomItemStack(Material.ENDER_CHEST, "&5Colored Ender Chests"), 2);
            for (int c1 = 0; c1 < 16; c1++)
                for (int c2 = 0; c2 < 16; c2++)
                    for (int c3 = 0; c3 < 16; c3++)
                        registerEnderChest(enderChestsResearch, bigEnderChestsResearch, c1, c2, c3);
            System.out.println(prefix + " §bSuccessfully Registered §dColoredEnderChests §bAddon");
        }
    }

    void registerEnderChest(Research smallResearch, Research bigResearch, final int c1, final int c2, final int c3) {
        if (get().getBoolean(ADDONSSETTINGS.getConfigPath() + "ColoredEnderChests.Chests.Small")) {
            ColoredEnderChest item = new ColoredEnderChest(this, 27, c1, c2, c3);
            item.register(addon);
            smallResearch.addItems(item);
        }
        if (get().getBoolean(ADDONSSETTINGS.getConfigPath() + "ColoredEnderChests.Chests.Big")) {
            ColoredEnderChest item = new ColoredEnderChest(this, 54, c1, c2, c3);
            item.register(addon);
            bigResearch.addItems(item);
        }
    }
}