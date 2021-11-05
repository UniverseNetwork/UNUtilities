package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Effects;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.events.bukkit.SkriptStartEvent;
import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.SlimefunHook;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

@ch.njol.skript.doc.Name("Slimefun - Create Item")
@ch.njol.skript.doc.Description("Creates Slimefun item - Categories: https://pastebin.com/HF63gBBj | Recipe Types: https://pastebin.com/G5thupbG")
@ch.njol.skript.doc.Examples({"set {_recipe::*} to dirt, dirt, dirt, dirt, dirt, dirt, dirt, dirt, and dirt\n" + "create slimefun item dirt named \"Cool Dirt\" with lore \"Not your ordinary dirt!\" with id \"COOL_DIRT\" in category \"RESOURCES\" with recipe {_recipe::*} with recipe type \"ENHANCED_CRAFTING_TABLE\""})
public class EffCreateItem extends ch.njol.skript.lang.Effect {
    static {
        Skript.registerEffect(EffCreateItem.class, "create [a] [new] [Slimefun] item %itemstack% with id %string% in category %string% with recipe %itemstacks% with recipe type %string%");
    }

    Expression<ItemStack> item;
    Expression<String> id;
    Expression<String> category;
    Expression<ItemStack> recipe;
    Expression<String> recipeType;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        if (!ScriptLoader.isCurrentEvent(SkriptStartEvent.class)) {
            Skript.error("You can not use Slimefun create item effect in any event but on skript load.");
            return false;
        }
        item = (Expression<ItemStack>) e[0];
        id = (Expression<String>) e[1];
        category = (Expression<String>) e[2];
        recipe = (Expression<ItemStack>) e[3];
        recipeType = (Expression<String>) e[4];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "create Slimefun item " + item.toString(e, b) + " with id " + id.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (item.getSingle(e) == null || id.getSingle(e) == null || category.getSingle(e) == null || recipe.getSingle(e) == null || recipeType.getSingle(e) == null)
            return;
        RecipeType slimefunRecipeType;
        try {
            slimefunRecipeType = (RecipeType) RecipeType.class.getField(recipeType.getSingle(e)).get(null);
        } catch (NoSuchFieldException | IllegalAccessException exc) {
            slimefunRecipeType = RecipeType.ENHANCED_CRAFTING_TABLE;
        }
        ItemStack[] recipeItems = recipe.getArray(e);
        for (int i = 0; i < recipeItems.length; i++)
            if (recipeItems[i].getType() == org.bukkit.Material.AIR) recipeItems[i] = null;
        new io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem(SlimefunHook.getItemGroup(category.getSingle(e).toLowerCase()), new io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack(id.getSingle(e), item.getSingle(e)), slimefunRecipeType, recipeItems).register(SlimefunHook.ADDON);
    }
}