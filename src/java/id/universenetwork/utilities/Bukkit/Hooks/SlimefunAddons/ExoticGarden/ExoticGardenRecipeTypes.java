package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Material.*;

public final class ExoticGardenRecipeTypes {
    public static final RecipeType KITCHEN = new RecipeType(new NamespacedKey(plugin, "kitchen"), new SlimefunItemStack("KITCHEN", CAULDRON, "&eKitchen"), "", "&rThis item must be made", "&rin a Kitchen");
    public static final RecipeType BREAKING_GRASS = new RecipeType(new NamespacedKey(plugin, "breaking_grass"), new CustomItemStack(GRASS, "&7Breaking Grass"));
    public static final RecipeType HARVEST_TREE = new RecipeType(new NamespacedKey(plugin, "harvest_tree"), new CustomItemStack(OAK_LEAVES, "&aHarvesting a Tree", "", "&rYou can obtain this Item by", "&rharvesting the shown Tree"));
    public static final RecipeType HARVEST_BUSH = new RecipeType(new NamespacedKey(plugin, "harvest_bush"), new CustomItemStack(OAK_LEAVES, "&aHarvesting a Bush", "", "&rYou can obtain this Item by", "&rharvesting the shown Bush"));
}