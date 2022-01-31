package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public final class MachineRecipeType extends RecipeType {
    @Getter
    final Map<ItemStack[], ItemStack> recipes = new LinkedHashMap<>();
    final List<BiConsumer<ItemStack[], ItemStack>> callbacks = new ArrayList<>();

    public MachineRecipeType(String key, ItemStack item) {
        super(new NamespacedKey(plugin, key), item);
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