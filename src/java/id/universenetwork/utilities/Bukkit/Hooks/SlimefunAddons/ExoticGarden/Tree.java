package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class Tree {
    final String sapling;
    final String texture;
    final String fruit;
    final java.util.List<Material> soils;
    Schematic schematic;

    public Tree(String fruit, String texture, Material... soil) {
        this.sapling = fruit + "_SAPLING";
        this.texture = texture;
        this.fruit = fruit;
        this.soils = java.util.Arrays.asList(soil);
    }

    public Schematic getSchematic() throws java.io.IOException {
        if (schematic == null)
            schematic = Schematic.loadSchematic(new File(ExoticGarden.getInstance().getSchematicsFolder(), fruit + "_TREE.schematic"));
        return schematic;
    }

    public ItemStack getItem() {
        return SlimefunItem.getById(sapling).getItem();
    }

    public String getTexture() {
        return this.texture;
    }

    public ItemStack getFruit() {
        return SlimefunItem.getById(fruit).getItem();
    }

    public String getFruitID() {
        return fruit;
    }

    public String getSapling() {
        return this.sapling;
    }

    public boolean isSoil(Material material) {
        return soils.contains(material);
    }
}