package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SteamTurbineMultiblock extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public SteamTurbineMultiblock(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, SteamTurbine turbine) {
        super(itemGroup, item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.MULTIBLOCK, new ItemStack[]{null, turbine.getItem(), null, null, new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(Material.WATER_BUCKET, "&fWater (Bubble Column)"), null, null, new ItemStack(Material.MAGMA_BLOCK), null});
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            e.getPlayer().sendMessage("Psst, this Item is just a dummy. You need to place the actual structure down.");
        };
    }
}