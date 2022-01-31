package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WindTurbineMultiblock extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {
    public WindTurbineMultiblock(ItemGroup itemGroup, SlimefunItemStack item, WindTurbine turbine) {
        super(itemGroup, item, RecipeType.MULTIBLOCK, new ItemStack[]{null, turbine.getItem(), null, null, new ItemStack(Material.OAK_FENCE), null, null, new ItemStack(Material.OAK_FENCE), null});
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            e.getPlayer().sendMessage("Psst, this Item is just a dummy. You need to place the actual structure down.");
        };
    }
}