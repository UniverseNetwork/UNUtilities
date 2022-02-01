package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.Items;

import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Crook extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ToolUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable, io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem {
    static final int CHANCE = 25;

    @javax.annotation.ParametersAreNonnullByDefault
    public Crook(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onRightClick());
    }

    @org.jetbrains.annotations.NotNull
    io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler onRightClick() {
        return io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent::cancel;
    }

    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            damageItem(e.getPlayer(), tool);
            if (org.bukkit.Tag.LEAVES.isTagged(e.getBlock().getType()) && java.util.concurrent.ThreadLocalRandom.current().nextInt(100) < CHANCE) {
                ItemStack sapling = new ItemStack(Material.getMaterial(e.getBlock().getType().toString().replace("LEAVES", "SAPLING")));
                drops.add(sapling);
            }
        };
    }

    @Override
    public boolean isDamageable() {
        return true;
    }
}