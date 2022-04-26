package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SMG.Items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class MaterialGenerator extends SlimefunItem {
    static final java.util.Map<BlockPosition, Integer> generatorProgress = new java.util.HashMap<>();
    final io.github.thebusybiscuit.slimefun4.api.items.ItemSetting<Integer> rate;
    ItemStack item;

    public MaterialGenerator(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe, int defaultRate) {
        super(itemGroup, item, recipeType, recipe);
        rate = new io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting(this, "rate", 2, defaultRate, 1000);
        addItemSetting(rate);
    }

    @Override
    public void preRegister() {
        addItemHandler(new me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                MaterialGenerator.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    public void tick(Block b) {
        Block targetBlock = b.getRelative(org.bukkit.block.BlockFace.UP);
        if (targetBlock.getType() == Material.CHEST) {
            org.bukkit.block.BlockState state = io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib.getBlockState(targetBlock, false).getState();
            if (state instanceof InventoryHolder) {
                org.bukkit.inventory.Inventory inv = ((InventoryHolder) state).getInventory();
                if (inv.firstEmpty() != -1) {
                    BlockPosition pos = new BlockPosition(b);
                    int progress = generatorProgress.getOrDefault(pos, 0);
                    if (progress >= rate.getValue()) {
                        progress = 0;
                        inv.addItem(item);
                    } else progress++;
                    generatorProgress.put(pos, progress);
                }
            }
        }
    }

    public final MaterialGenerator setItem(Material material) {
        item = new ItemStack(material);
        return this;
    }
}