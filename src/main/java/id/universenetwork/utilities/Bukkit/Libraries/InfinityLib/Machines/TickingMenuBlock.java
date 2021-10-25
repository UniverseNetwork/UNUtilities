package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

import static me.mrCookieSlime.Slimefun.api.BlockStorage.getInventory;

@ParametersAreNonnullByDefault
public abstract class TickingMenuBlock extends MenuBlock {
    public TickingMenuBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return synchronous();
            }

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                BlockMenu menu = getInventory(b);
                if (menu != null) TickingMenuBlock.this.tick(b, menu);
            }
        });
    }

    protected abstract void tick(Block b, BlockMenu menu);

    protected boolean synchronous() {
        return false;
    }
}