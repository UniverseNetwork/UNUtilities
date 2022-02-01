package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.Items;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

import static org.bukkit.Material.*;
import static org.bukkit.block.BlockFace.UP;

public class GrassSeeds extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> {
    @javax.annotation.ParametersAreNonnullByDefault
    public GrassSeeds(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (e.getClickedBlock().isPresent()) {
                org.bukkit.block.Block b = e.getClickedBlock().get();
                if (b.getType() == DIRT) {
                    if (e.getPlayer().getGameMode() != org.bukkit.GameMode.CREATIVE)
                        io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils.consumeItem(e.getItem(), false);
                    b.setType(GRASS_BLOCK);
                    if (b.getRelative(UP).getType() == AIR)
                        b.getRelative(UP).setType(GRASS);
                    b.getWorld().playEffect(b.getLocation(), org.bukkit.Effect.STEP_SOUND, GRASS);
                }
            }
        };
    }
}