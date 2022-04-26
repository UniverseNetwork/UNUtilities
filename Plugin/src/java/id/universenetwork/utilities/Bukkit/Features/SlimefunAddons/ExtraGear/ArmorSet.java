package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraGear;

import org.bukkit.Material;

enum ArmorSet {
    LEATHER(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS),
    IRON(Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS),
    GOLD(Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS),
    DIAMOND(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS);

    final Material helmet;
    final Material chestplate;
    final Material leggings;
    final Material boots;

    ArmorSet(Material helmet, Material chestplate, Material leggings, Material boots) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public Material getHelmet() {
        return helmet;
    }

    public Material getChestplate() {
        return chestplate;
    }

    public Material getLeggings() {
        return leggings;
    }

    public Material getBoots() {
        return boots;
    }
}