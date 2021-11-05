package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Utils.send;

/**
 * An item that is used to produce more flowers
 * to help with player progression
 *
 * @author NCBPFluffyBear
 */
public class OvergrowthSeed extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    final ItemSetting<Integer> duplicateAmount = new ItemSetting<>(this, "duplicate-amount", 5);

    public OvergrowthSeed(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(duplicateAmount);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            java.util.Optional<Block> optB = e.getClickedBlock();
            if (!optB.isPresent()) return;
            Block b = optB.get();
            org.bukkit.entity.Player p = e.getPlayer();

            // Check if it is one of the 4 acceptable flowers
            if (!id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Constants.flowers.contains(b.getType())) {
                send(p, "&cYou can not use this on this block");
                return;
            }

            // Consume one overgrowth seed
            ItemStack seed = e.getItem();
            seed.setAmount(seed.getAmount() - 1);

            // Duplicate and drop flowers
            b.getWorld().dropItem(b.getLocation(), new ItemStack(b.getType(), duplicateAmount.getValue()));

            p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_LAVA_POP, 1, 1);
            send(p, "&aThis flower starts to grow quickly...");
        };
    }
}