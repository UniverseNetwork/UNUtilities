package id.universenetwork.utilities.bukkit.libraries.InfinityLib.Machines;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import static io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.*;
import static org.bukkit.Material.*;

public abstract class MenuBlock extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    public static final ItemStack PROCESSING_ITEM = new CustomItemStack(LIME_STAINED_GLASS_PANE, "&aProcessing...");
    public static final ItemStack NO_ENERGY_ITEM = new CustomItemStack(RED_STAINED_GLASS_PANE, "&cNot enough energy!");
    public static final ItemStack IDLE_ITEM = new CustomItemStack(BLACK_STAINED_GLASS_PANE, "&8Idle");
    public static final ItemStack NO_ROOM_ITEM = new CustomItemStack(ORANGE_STAINED_GLASS_PANE, "&6Not enough room!");
    public static final ItemStack OUTPUT_BORDER = new CustomItemStack(getOutputSlotTexture(), "&6Output");
    public static final ItemStack INPUT_BORDER = new CustomItemStack(getInputSlotTexture(), "&9Input");
    public static final ItemStack BACKGROUND_ITEM = getBackground();

    public MenuBlock(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(new io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack itemStack, java.util.List<ItemStack> list) {
                BlockMenu menu = me.mrCookieSlime.Slimefun.api.BlockStorage.getInventory(e.getBlock());
                if (menu != null) onBreak(e, menu);
            }
        }, new io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                onPlace(e, e.getBlockPlaced());
            }
        });
    }

    @Override
    public final void postRegister() {
        new MenuBlockPreset(this);
    }

    protected abstract void setup(me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset preset);

    protected final int[] getTransportSlots(DirtyChestMenu menu, me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow flow, ItemStack item) {
        switch (flow) {
            case INSERT:
                return getInputSlots(menu, item);
            case WITHDRAW:
                return getOutputSlots();
            default:
                return new int[0];
        }
    }

    protected int[] getInputSlots(DirtyChestMenu menu, ItemStack item) {
        return getInputSlots();
    }

    protected abstract int[] getInputSlots();

    protected abstract int[] getOutputSlots();

    protected void onNewInstance(BlockMenu menu, Block b) {
    }

    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        org.bukkit.Location l = menu.getLocation();
        menu.dropItems(l, getInputSlots());
        menu.dropItems(l, getOutputSlots());
    }

    protected void onPlace(BlockPlaceEvent e, Block b) {
    }
}