package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DyedBackpacks;

import id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import org.bukkit.NamespacedKey;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class DyedBackpacks extends Addons {
    public DyedBackpacks() {
        if (Enabled("DyedBackpacks")) {
            Research research = new Research(new NamespacedKey(plugin, "dyed_backpacks"), 17200, "Dyed Backpacks", 24);
            ItemGroup itemGroup = new ItemGroup(new NamespacedKey(plugin, "dyed_backpacks"), new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode(BackpackColor.RED.getTexture())), "&4Dyed Backpacks"), 2);
            if (backpacks("Small")) createBackpacks(itemGroup, research, SlimefunItems.BACKPACK_SMALL, 9);
            if (backpacks("Medium")) createBackpacks(itemGroup, research, SlimefunItems.BACKPACK_MEDIUM, 18);
            if (backpacks("Large")) createBackpacks(itemGroup, research, SlimefunItems.BACKPACK_LARGE, 27);
            if (backpacks("Woven")) createBackpacks(itemGroup, research, SlimefunItems.WOVEN_BACKPACK, 36);
            if (backpacks("Gilded")) createBackpacks(itemGroup, research, SlimefunItems.GILDED_BACKPACK, 45);
            if (backpacks("Radiant")) createBackpacks(itemGroup, research, SlimefunItems.RADIANT_BACKPACK, 54);
            research.register();
            System.out.println(prefix + " §bSuccessfully Registered §dDyedBackpacks §bAddon");
        }
    }

    @ParametersAreNonnullByDefault
    void createBackpacks(ItemGroup itemGroup, Research research, SlimefunItemStack backpack, int size) {
        BackpackColor[] v5 = BackpackColor.values();
        for (BackpackColor color : v5) {
            SlimefunItemStack item = new SlimefunItemStack("DYED_" + backpack.getItemId() + "_" + color.name(), color.getTexture(), backpack.getItemMeta().getDisplayName() + " &7(" + color.getName() + "&7)", Objects.requireNonNull(backpack.getItemMeta().getLore()).toArray(new String[0]));
            DyedBackpack dyed = new DyedBackpack(size, itemGroup, item, backpack, color);
            research.addItems(dyed);
            dyed.register(addon);
        }
    }

    boolean backpacks(String Name) {
        return Config.get().getBoolean(SlimeFunAddons.ADDONSSETTINGS.getConfigPath() + "DyedBackpacks.Backpacks." + Name);
    }
}
