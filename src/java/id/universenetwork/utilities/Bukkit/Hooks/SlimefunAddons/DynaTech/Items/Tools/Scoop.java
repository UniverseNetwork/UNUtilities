package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import org.bukkit.inventory.ItemStack;

public class Scoop extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable, io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public Scoop(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(getItemHandler());
    }

    public io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler getItemHandler() {
        return (e, item, offhand) -> {
            if (getItemCharge(item) < 8) return;
            org.bukkit.entity.Entity entity = e.getRightClicked();
            if (e.isCancelled() || !io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(e.getPlayer(), entity.getLocation(), io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_ENTITY))
                return;
            org.bukkit.entity.Player p = e.getPlayer();
            if (entity instanceof org.bukkit.entity.Bee) {
                entity.getWorld().dropItemNaturally(entity.getLocation(), id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems.BEE);
                entity.remove();
                removeItemCharge(item, 8);
                p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_ANVIL_FALL, 1, 1);
            }
        };
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        return 512;
    }
}