package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SMG.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GeneratorMultiblock extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {
    public GeneratorMultiblock(ItemGroup itemGroup, SlimefunItemStack item) {
        super(itemGroup, item, RecipeType.MULTIBLOCK, new ItemStack[]{
                null, null, null,
                null, new ItemStack(Material.CHEST), null,
                null, new CustomItemStack(Material.BEDROCK, "Any SMG generator"), null});
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            e.getPlayer().sendMessage("Psst, this Item is just a dummy. You need to place the actual generator down.");
        };
    }
}