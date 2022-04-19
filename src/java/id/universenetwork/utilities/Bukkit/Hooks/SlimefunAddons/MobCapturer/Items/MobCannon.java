package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Items;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

public class MobCannon extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> {
    final MobPellet pellet;

    public MobCannon(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, MobPellet pellet, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.pellet = pellet;
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (consumeAmmo(e.getPlayer(), pellet)) {
                Snowball projectile = e.getPlayer().launchProjectile(Snowball.class);
                projectile.setMetadata("mob_capturing_cannon", new org.bukkit.metadata.FixedMetadataValue(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, e.getPlayer().getUniqueId()));
            }
        };
    }

    boolean consumeAmmo(org.bukkit.entity.Player p, MobPellet pellet) {
        if (p.getGameMode() == org.bukkit.GameMode.CREATIVE) return true;
        for (ItemStack item : p.getInventory())
            if (pellet.isItem(item)) {
                io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils.consumeItem(item, false);
                return true;
            }
        return false;
    }
}