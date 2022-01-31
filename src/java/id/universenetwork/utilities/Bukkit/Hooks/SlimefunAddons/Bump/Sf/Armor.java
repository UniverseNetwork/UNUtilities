package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Util.SfItemStackCreate;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Material.DIAMOND_HELMET;

public class Armor {
    public static SlimefunItemStack randomHelmet_ = new SfItemStackCreate("RANDOM_HELMET", DIAMOND_HELMET, "&6Random Helmet", new String[]{"", "&b&k|&b- &7&oHow is my luck...", "&b&k|&b- &e&oThe appraiser &7&orandomly gives this attribute...", "§b§k|§b- §7§oThe armor has not been verified..."});
    public ItemGroup Armor;

    public Armor() {
        Armor = new ItemGroup(new NamespacedKey(plugin, "Armor"), new CustomItemStack(DIAMOND_HELMET, "&bBump-Armor", "", "&b&k|&b- Click to open >", "", "&7Armor, armor!", "They may contain unknown power..."));
        Armor.register(addon);
        SlimefunItem randomHelmet = new SlimefunItem(Armor, randomHelmet_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{Stuff.oldCoin_, Stuff.oldCoin_, Stuff.oldCoin_, Stuff.make_, Stuff.oldCoin_, Stuff.oldCoin_, Stuff.oldCoin_, Stuff.oldCoin_});
        randomHelmet.register(addon);
    }
}