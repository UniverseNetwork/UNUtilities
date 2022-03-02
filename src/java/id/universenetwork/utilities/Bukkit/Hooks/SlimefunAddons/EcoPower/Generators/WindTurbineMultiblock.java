package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.OAK_FENCE;

public class WindTurbineMultiblock extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public WindTurbineMultiblock(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, WindTurbine turbine) {
        super(itemGroup, item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.MULTIBLOCK, new ItemStack[]{null, turbine.getItem(), null, null, new ItemStack(OAK_FENCE), null, null, new ItemStack(OAK_FENCE), null});
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            e.getPlayer().sendMessage("Psst, this Item is just a dummy. You need to place the actual structure down.");
        };
    }
}