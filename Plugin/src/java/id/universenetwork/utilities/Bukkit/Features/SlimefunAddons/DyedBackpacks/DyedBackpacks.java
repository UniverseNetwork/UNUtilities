package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.DyedBackpacks;

import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SfAddon;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;

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
        ItemGroup itemGroup = new ItemGroup(UNUtilities.createKey("dyed_backpacks"), new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode(BackpackColor.RED.getTexture())), "&4Dyed Backpacks"), 2);
        if (UNUtilities.cfg.getBoolean(cfgPath + "backpacks.small"))
            createBackpacks(itemGroup, research, SlimefunItems.BACKPACK_SMALL, 9);
        if (UNUtilities.cfg.getBoolean(cfgPath + "backpacks.normal"))
            createBackpacks(itemGroup, research, SlimefunItems.BACKPACK_MEDIUM, 18);
        if (UNUtilities.cfg.getBoolean(cfgPath + "backpacks.large"))
            createBackpacks(itemGroup, research, SlimefunItems.BACKPACK_LARGE, 27);
        if (UNUtilities.cfg.getBoolean(cfgPath + "backpacks.woven"))
            createBackpacks(itemGroup, research, SlimefunItems.WOVEN_BACKPACK, 36);
        if (UNUtilities.cfg.getBoolean(cfgPath + "backpacks.gilded"))
            createBackpacks(itemGroup, research, SlimefunItems.GILDED_BACKPACK, 45);
        if (UNUtilities.cfg.getBoolean(cfgPath + "backpacks.radiant"))
            createBackpacks(itemGroup, research, SlimefunItems.RADIANT_BACKPACK, 54);
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