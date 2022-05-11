package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.DyedBackpacks;

import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SfAddon;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;

import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;

/**
 * This is the main class of the {@link DyedBackpacks} addon.
 * Here we initialize the different instances of {@link DyedBackpack}.
 *
 * @author TheBusyBiscuit
 * @author ARVIN3108 ID
 * @see DyedBackpack
 */
public class DyedBackpacks extends SfAddon {
    @Override
    public void Load() {
        Research research = new Research(UNUtilities.createKey("dyed_backpacks"), 17200, "Dyed Backpacks", 24);
        ItemGroup itemGroup = new ItemGroup(UNUtilities.createKey("dyed_backpacks"), new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead.getItemStack(io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin.fromHashCode(BackpackColor.RED.getTexture())), "&4Dyed Backpacks"), 2);
        if (UNUtilities.cfg.getBoolean(configPath + "backpacks.small"))
            createBackpacks(itemGroup, research, BACKPACK_SMALL, 9);
        if (UNUtilities.cfg.getBoolean(configPath + "backpacks.normal"))
            createBackpacks(itemGroup, research, BACKPACK_MEDIUM, 18);
        if (UNUtilities.cfg.getBoolean(configPath + "backpacks.large"))
            createBackpacks(itemGroup, research, BACKPACK_LARGE, 27);
        if (UNUtilities.cfg.getBoolean(configPath + "backpacks.woven"))
            createBackpacks(itemGroup, research, WOVEN_BACKPACK, 36);
        if (UNUtilities.cfg.getBoolean(configPath + "backpacks.gilded"))
            createBackpacks(itemGroup, research, GILDED_BACKPACK, 45);
        if (UNUtilities.cfg.getBoolean(configPath + "backpacks.radiant"))
            createBackpacks(itemGroup, research, RADIANT_BACKPACK, 54);
        research.register();
    }

    void createBackpacks(ItemGroup itemGroup, Research research, SlimefunItemStack backpack, int size) {
        for (BackpackColor color : BackpackColor.values()) {
            SlimefunItemStack item = new SlimefunItemStack("DYED_" + backpack.getItemId() + "_" + color.name(), color.getTexture(), backpack.getItemMeta().getDisplayName() + " &7(" + color.getName() + "&7)", backpack.getItemMeta().getLore().toArray(new String[0]));
            DyedBackpack dyed = new DyedBackpack(size, itemGroup, item, backpack, color);
            research.addItems(dyed);
            dyed.register(this);
        }
    }
}