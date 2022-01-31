package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AdvancedChargingBench extends AContainer {
    public static final int CAPACITY = 128;
    public static final int ENERGY_CONSUMPTION = 10;
    public static final int CHARGE = 5;
    int tier = 0;

    public AdvancedChargingBench(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onPlace());
    }

    BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(true) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                Block b = e.getBlockPlaced();
                BlockStorage.addBlockInfo(b.getLocation(), "tier", "0");
            }
        };
    }

    @Override
    protected void tick(Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) return;
        BlockMenu inv = BlockStorage.getInventory(b);
        tier = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "tier"));
        for (int slot : getInputSlots()) {
            ItemStack item = inv.getItemInSlot(slot);
            if (charge(b, inv, slot, item, tier)) return;
        }
    }

    boolean charge(Block b, BlockMenu inv, int slot, ItemStack item, int tier) {
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem instanceof Rechargeable) {
            float charge = CHARGE + CHARGE * tier;
            if (((Rechargeable) sfItem).addItemCharge(item, charge))
                removeCharge(b.getLocation(), getEnergyConsumption());
            else if (inv.fits(item, getOutputSlots())) {
                inv.pushItem(item, getOutputSlots());
                inv.replaceExistingItem(slot, null);
            }
            return true;
        } else if (sfItem != null && inv.fits(item, getOutputSlots())) {
            inv.pushItem(item, getOutputSlots());
            inv.replaceExistingItem(slot, null);
        }
        return false;
    }

    @Override
    public ItemStack getProgressBar() {
        return null;
    }

    @Override
    public int getCapacity() {
        return CAPACITY + CAPACITY * tier;
    }

    @Override
    public int getEnergyConsumption() {
        return ENERGY_CONSUMPTION + ENERGY_CONSUMPTION * tier;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @NotNull
    @Override
    public String getMachineIdentifier() {
        return "ADVANCED_CHARGING_BENCH";
    }
}