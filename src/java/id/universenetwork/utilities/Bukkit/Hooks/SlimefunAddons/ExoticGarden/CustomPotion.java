package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden;

import org.bukkit.inventory.meta.PotionMeta;

import static id.universenetwork.utilities.Bukkit.Utils.Color.Translate;

public final class CustomPotion extends org.bukkit.inventory.ItemStack {
    @javax.annotation.ParametersAreNonnullByDefault
    public CustomPotion(String name, org.bukkit.Color color, org.bukkit.potion.PotionEffect effect, String... lore) {
        super(org.bukkit.Material.POTION);
        PotionMeta meta = (PotionMeta) getItemMeta();
        java.util.List<String> list = new java.util.ArrayList<>();
        for (String line : lore) list.add(Translate(line));
        meta.setDisplayName(Translate(name));
        meta.setLore(list);
        meta.setColor(color);
        meta.addCustomEffect(effect, true);
        setItemMeta(meta);
    }
}