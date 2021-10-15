package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.EcoPower.Generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.inventory.ItemStack;

import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.MULTIBLOCK;
import static org.bukkit.Material.MAGMA_BLOCK;
import static org.bukkit.Material.WATER_BUCKET;

public class SteamTurbineMultiblock extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {
    public SteamTurbineMultiblock(ItemGroup itemGroup, SlimefunItemStack item, SteamTurbine turbine) {
        super(itemGroup, item, MULTIBLOCK, new ItemStack[]{null, turbine.getItem(), null, null, new CustomItemStack(WATER_BUCKET, "&fWater (Bubble Column)"), null, null, new ItemStack(MAGMA_BLOCK), null});
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