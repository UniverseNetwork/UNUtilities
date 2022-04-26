package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraTools.Implementation.Machines;

import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraTools.Implementation.Interfaces.ETInventoryBlock;
import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraTools.Lists.ETItems;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.getEmptyClickHandler;
import static org.bukkit.Material.*;

public class CobblestoneGenerator extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<BlockTicker> implements ETInventoryBlock, io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent {
    static final int ENERGY_CONSUMPTION = 32;
    final int[] border = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 18, 19, 20, 21, 22, 27, 28, 29, 30, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44, 22};
    final int[] inputBorder = {};
    final int[] outputBorder = {14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    int decrement = 2;

    public CobblestoneGenerator() {
        super(ETItems.extra_tools, ETItems.COBBLESTONE_GENERATOR, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{PROGRAMMABLE_ANDROID_MINER, MAGNESIUM_INGOT, PROGRAMMABLE_ANDROID_MINER, new ItemStack(WATER_BUCKET), BLISTERING_INGOT_3, new ItemStack(LAVA_BUCKET), PROGRAMMABLE_ANDROID_MINER, BIG_CAPACITOR, PROGRAMMABLE_ANDROID_MINER});
        createPreset(this, this::constructMenu);
        addItemHandler(onBreak());
    }

    void constructMenu(me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset preset) {
        for (int i : border)
            preset.addItem(i, new CustomItemStack(new ItemStack(GRAY_STAINED_GLASS_PANE), " "), getEmptyClickHandler());
        for (int i : inputBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(CYAN_STAINED_GLASS_PANE), " "), getEmptyClickHandler());
        for (int i : outputBorder)
            preset.addItem(i, new CustomItemStack(new ItemStack(ORANGE_STAINED_GLASS_PANE), " "), getEmptyClickHandler());
        for (int i : getOutputSlots())
            preset.addMenuClickHandler(i, new me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler() {
                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(org.bukkit.event.inventory.InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor == null || cursor.getType() == AIR;
                }
            });
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{24, 25};
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return 512;
    }

    public BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(org.bukkit.event.block.BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Block b = e.getBlock();
                BlockMenu inv = BlockStorage.getInventory(b);
                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
                }
            }
        };
    }

    @Override
    public BlockTicker getItemHandler() {
        return new BlockTicker() {
            @Override
            // Fires first!! The method tick() fires after this
            public void uniqueTick() {
                // Needed to keep track of all cobble gens at once,
                // All it does is set back to max (for now 2, will be customizable)
                // when it reaches the lowest possible (AKA 1)
                if (decrement == 1) {
                    decrement = 2;
                    return;
                }
                decrement--;
            }

            @Override
            public void tick(Block b, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem sf, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                // We only act once per decrement cycle, when decrement got to
                // lowest and has been reset
                if (decrement != 2) return;
                ItemStack output = new ItemStack(COBBLESTONE);
                if (getCharge(b.getLocation()) >= ENERGY_CONSUMPTION) {
                    BlockMenu menu = BlockStorage.getInventory(b);
                    if (!menu.fits(output, getOutputSlots())) return;
                    removeCharge(b.getLocation(), ENERGY_CONSUMPTION);
                    menu.pushItem(output, getOutputSlots());
                }
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        };
    }
}