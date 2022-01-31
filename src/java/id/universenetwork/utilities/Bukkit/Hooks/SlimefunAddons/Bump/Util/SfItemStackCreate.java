package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Util;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.enchantments.Enchantment.getByName;

public class SfItemStackCreate extends SlimefunItemStack {
    public SfItemStackCreate(String id, Material material, String displayName, String[] lore, String... enchants) {
        super(id, material, displayName, lore);
        ItemMeta meta = getItemMeta();
        if (enchants != null) {
            for (String ench : enchants) {
                String[] strs = ench.split("[-]");
                meta.addEnchant(getByName(ench.substring(0, ench.indexOf("-"))), Integer.parseInt(strs[1]), true);
            }
            setItemMeta(meta);
        }
    }
}