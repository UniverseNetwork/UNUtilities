package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.ChatColor.WHITE;

public class ItemBand extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    public static final NamespacedKey KEY = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "item_band");
    final PotionEffect[] potionEffects;

    public ItemBand(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe, PotionEffect[] potionEffects) {
        super(itemGroup, item, recipeType, recipe);
        this.potionEffects = potionEffects;
    }

    public PotionEffect[] getPotionEffects() {
        return potionEffects;
    }

    public static boolean containsItemBand(ItemStack item) {
        if (item != null && item.getType() != Material.AIR && item.hasItemMeta())
            return PersistentDataAPI.getString(item.getItemMeta(), KEY) != null;
        else return false;
    }

    @Nullable
    public ItemStack applyToItem(@Nullable ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            ItemMeta im = item.getItemMeta();
            List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<>();
            lore.add(WHITE + "Bandaid: " + getPotionEffects()[0].getType().getName());
            PersistentDataAPI.setString(im, KEY, this.getId());
            im.setLore(lore);
            item.setItemMeta(im);
            return item;
        }
        return null;
    }

    @Nullable
    public static ItemStack removeFromItem(@Nullable ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            ItemMeta im = item.getItemMeta();
            List<String> lore = im.getLore();
            im.getPersistentDataContainer().remove(KEY);
            lore.removeIf(line -> line.contains(WHITE + "Bandaid: "));
            im.setLore(lore);
            item.setItemMeta(im);
            return item;
        }
        return null;
    }
}