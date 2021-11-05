package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Utils;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

/**
 * A {@link io.github.thebusybiscuit.slimefun4.implementation.items.medical.Bandage} that consumes experience
 * instead of the item
 *
 * @author NCBPFluffyBear
 */
public class InfinityBandage extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Objects.FPNotPlaceable, io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public static final double HEALTH_PER_CONSUME = 1;
    public static final int EXP_PER_CONSUME = 10;

    public InfinityBandage(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            org.bukkit.entity.Player p = e.getPlayer();
            int exp = Utils.getTotalExperience(p);
            double health = p.getHealth();
            double maxHealth = p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).getValue();

            // Check if player has enough exp
            if (exp < EXP_PER_CONSUME) {
                Utils.send(p, "&cYou can not afford this! Needed exp points: " + EXP_PER_CONSUME);
                return;
            }

            // Check if player needs healing
            if (health >= maxHealth) {
                Utils.send(p, "&cYour health is already full!");
                return;
            }

            // Consume exp and heal player
            p.giveExp(-EXP_PER_CONSUME);
            double newHealth = health + HEALTH_PER_CONSUME;
            if (newHealth > maxHealth) newHealth = maxHealth;
            p.setHealth(newHealth);
            p.playSound(p.getLocation(), org.bukkit.Sound.ENTITY_CAT_HISS, 1, 1);
        };
    }

    @Override
    public boolean isDisenchantable() {
        return false;
    }
}