package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SMG.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class MaterialGenerator extends SlimefunItem {
    private static final Map<BlockPosition, Integer> generatorProgress = new HashMap<>();
    private final ItemSetting<Integer> rate;
    private ItemStack item;


    public MaterialGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int defaultRate) {
        super(itemGroup, item, recipeType, recipe);
        rate = new IntRangeSetting(this, "rate", 2, defaultRate, 1000);
        addItemSetting(rate);
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                MaterialGenerator.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    public void tick(Block b) {
        Block targetBlock = b.getRelative(BlockFace.UP);
        if (targetBlock.getType() == Material.CHEST) {
            BlockState state = PaperLib.getBlockState(targetBlock, false).getState();
            if (state instanceof InventoryHolder) {
                Inventory inv = ((InventoryHolder) state).getInventory();
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