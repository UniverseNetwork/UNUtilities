package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools.Items.CrescentHammer;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;

public class MoreTools {
    ItemGroup moreToolsCategory;

    public MoreTools() {
        if (Enabled("MoreTools")) {
            moreToolsCategory = new ItemGroup(new NamespacedKey(plugin, "more_tools_category"), new CustomItemStack(Item.CRESCENT_HAMMER, "&3More Tools"), 4);
            new CrescentHammer(moreToolsCategory, Item.CRESCENT_HAMMER, ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.TIN_INGOT, null, SlimefunItems.TIN_INGOT, null, SlimefunItems.COPPER_INGOT, null, null, SlimefunItems.TIN_INGOT, null}).register(addon);
            registerResearch(Item.CRESCENT_HAMMER);
            Bukkit.getLogger().warning(prefix + " §cThe MoreTools addon has some bugs and is not recommended to be activated. Do not use this addon on public servers!!!");
            System.out.println(prefix + " §bSuccessfully Registered §dMoreTools §bAddon");
        }
    }

    void registerResearch(ItemStack... items) {
        Research research = new Research(new NamespacedKey(plugin, "crescent_hammer"), 7501, "Not A Hammer", 15);
        for (ItemStack item : items) {
            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            if (sfItem != null) research.addItems(sfItem);
        }
        research.register();
    }
}