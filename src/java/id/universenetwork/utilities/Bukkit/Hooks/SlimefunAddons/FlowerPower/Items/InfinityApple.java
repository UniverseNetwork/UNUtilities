package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Utils;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

/**
 * A "consumable" food that consumes experience
 * instead of the item
 *
 * @author NCBPFluffyBear
 */
public class InfinityApple extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Objects.FPNotPlaceable, io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public static final int FOOD_PER_CONSUME = 1;
    public static final int EXP_PER_CONSUME = 1;

    public InfinityApple(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            org.bukkit.entity.Player p = e.getPlayer();
            int exp = Utils.getTotalExperience(p);
            int foodLevel = p.getFoodLevel();

            // Check if player has enough exp
            if (exp < EXP_PER_CONSUME) {
                Utils.send(p, "&cYou can not afford this! Needed exp points: " + EXP_PER_CONSUME);
                return;
            }

            // Check if player needs food
            if (foodLevel > 20) {
                Utils.send(p, "&cYou are already full!");
                return;
            }

            // Consume exp and feed player
            p.giveExp(-EXP_PER_CONSUME);
            p.setFoodLevel(foodLevel + FOOD_PER_CONSUME);
            p.playSound(p.getLocation(), org.bukkit.Sound.ENTITY_GENERIC_EAT, 1, 1);
        };
    }
}