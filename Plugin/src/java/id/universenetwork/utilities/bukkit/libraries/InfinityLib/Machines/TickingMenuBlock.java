package id.universenetwork.utilities.bukkit.libraries.InfinityLib.Machines;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public abstract class TickingMenuBlock extends MenuBlock {
    public TickingMenuBlock(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(new me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return synchronous();
            }

            @Override
            public void tick(Block b, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem item, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                BlockMenu menu = me.mrCookieSlime.Slimefun.api.BlockStorage.getInventory(b);
                if (menu != null) TickingMenuBlock.this.tick(b, menu);
            }
        });
    }

    protected abstract void tick(Block b, BlockMenu menu);

    protected boolean synchronous() {
        return false;
    }
}