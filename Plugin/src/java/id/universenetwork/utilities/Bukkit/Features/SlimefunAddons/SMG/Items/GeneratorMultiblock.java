package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SMG.Items;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GeneratorMultiblock extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    public GeneratorMultiblock(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item) {
        super(itemGroup, item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.MULTIBLOCK, new ItemStack[]{null, null, null, null, new ItemStack(Material.CHEST), null, null, new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(Material.BEDROCK, "Any SMG generator"), null});
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            e.getPlayer().sendMessage("Psst, this Item is just a dummy. You need to place the actual generator down.");
        };
    }
}