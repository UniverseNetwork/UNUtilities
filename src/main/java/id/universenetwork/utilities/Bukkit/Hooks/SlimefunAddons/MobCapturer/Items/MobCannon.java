package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MobCapturer.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class MobCannon extends SimpleSlimefunItem<ItemUseHandler> {
    final MobPellet pellet;

    public MobCannon(ItemGroup itemGroup, SlimefunItemStack item, MobPellet pellet, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.pellet = pellet;
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (consumeAmmo(e.getPlayer(), pellet))
                e.getPlayer().launchProjectile(Snowball.class).setMetadata("mob_capturing_cannon", new FixedMetadataValue(plugin, e.getPlayer().getUniqueId()));
        };
    }

    private boolean consumeAmmo(Player p, MobPellet pellet) {
        if (p.getGameMode() == GameMode.CREATIVE) return true;
        for (ItemStack item : p.getInventory()) {
            if (pellet.isItem(item)) {
                ItemUtils.consumeItem(item, false);
                return true;
            }
        }
        return false;
    }
}