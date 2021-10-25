package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Util;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

public class Register {
    public Register(SlimefunItem... slimefunItems) {
        for (SlimefunItem slimefunItem : slimefunItems)
            slimefunItem.register(id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon);
    }
}