package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.MultiBlocks.Alchemic.AlchemicRecipe;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.MultiBlocks.Alchemic.AlchemicStation;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.NamespacedKey;

import java.util.Arrays;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class PotionRecipeType {
    public static final RecipeType ALCHEMIC_STATION_RECIPE = new RecipeType(new NamespacedKey(plugin, "alchemic_station"), PotionItems.ALCHEMIC_STATION, (recipe, output) -> {
        AlchemicRecipe alchemicRecipe = new AlchemicRecipe(Arrays.asList(recipe), output);
        AlchemicStation station = (AlchemicStation) PotionItems.ALCHEMIC_STATION.getItem();
        station.getAlchemicRecipes().add(alchemicRecipe);
    }, "", "&a&oCraft it in an Alchemic station");
}