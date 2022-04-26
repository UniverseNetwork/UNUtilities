package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.BiConsumer;

public final class MachineRecipeType extends io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType {
    @lombok.Getter
    final java.util.Map<ItemStack[], ItemStack> recipes = new java.util.LinkedHashMap<>();
    final List<BiConsumer<ItemStack[], ItemStack>> callbacks = new java.util.ArrayList<>();

    public MachineRecipeType(String key, ItemStack item) {
        super(id.universenetwork.utilities.Bukkit.UNUtilities.createKey(key), item);
    }

    @Override
    public void register(ItemStack[] recipe, ItemStack result) {
        callbacks.forEach(c -> c.accept(recipe, result));
        recipes.put(recipe, result);
    }

    public void sendRecipesTo(BiConsumer<ItemStack[], ItemStack> callback) {
        recipes.forEach(callback);
        callbacks.add(callback);
    }
}