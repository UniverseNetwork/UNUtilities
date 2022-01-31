package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SMG.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.MULTIBLOCK;
import static org.bukkit.Material.BEDROCK;
import static org.bukkit.Material.CHEST;

public class GeneratorMultiblock extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {
    public GeneratorMultiblock(ItemGroup itemGroup, SlimefunItemStack item) {
        super(itemGroup, item, MULTIBLOCK, new ItemStack[]{null, null, null, null, new ItemStack(CHEST), null, null, new CustomItemStack(BEDROCK, "Any SMG generator"), null});
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            e.getPlayer().sendMessage("Psst, this Item is just a dummy. You need to place the actual generator down.");
        };
    }
}