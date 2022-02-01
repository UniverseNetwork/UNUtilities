package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Transfer;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Optional;

import static me.mrCookieSlime.Slimefun.api.BlockStorage.*;

public class WirelessEnergyPoint extends SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider {
    static final NamespacedKey WIRELESS_LOCATION_KEY = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "wireless-location");
    final int capacity;
    final int energyRate;

    @javax.annotation.ParametersAreNonnullByDefault
    public WirelessEnergyPoint(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, int capacity, int energyRate, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.capacity = capacity;
        this.energyRate = energyRate;
        addItemHandler(onRightClick(), onBlockPlace(), onBlockBreak());
    }

    @Override
    public int getGeneratedOutput(Location l, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
        String wirelessBankLocation = getLocationInfo(l, "wireless-location");
        int chargedNeeded = getCapacity() - getCharge(l);
        if (chargedNeeded != 0 && wirelessBankLocation != null) {
            Location wirelessEnergyBank = StringToLocation(wirelessBankLocation);
            // Note: You should probably also see if the Future from getChunkAtAsync is finished here.
            // You don't really want to possibly trigger the chunk to load in another thread twice.
            if (!wirelessEnergyBank.getWorld().isChunkLoaded(wirelessEnergyBank.getBlockX() >> 4, wirelessEnergyBank.getBlockZ() >> 4)) {
                java.util.concurrent.CompletableFuture<org.bukkit.Chunk> chunkLoad = io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib.getChunkAtAsync(wirelessEnergyBank);
                if (!chunkLoad.isDone()) return 0;
            }
            if (wirelessEnergyBank != null && checkID(wirelessEnergyBank) != null && checkID(wirelessEnergyBank).equals(DynaTechItems.WIRELESS_ENERGY_BANK.getItemId())) {
                int BankCharge = getCharge(wirelessEnergyBank);
                if (BankCharge > chargedNeeded) {
                    if (chargedNeeded > getEnergyRate()) {
                        removeCharge(wirelessEnergyBank, getEnergyRate());
                        return getEnergyRate();
                    }
                    removeCharge(wirelessEnergyBank, chargedNeeded);
                    return chargedNeeded;
                }
            }
        }
        return 0;
    }

    ItemHandler onRightClick() {
        return (io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler) event -> {
            Optional<org.bukkit.block.Block> blockClicked = event.getClickedBlock();
            Optional<SlimefunItem> sfBlockClicked = event.getSlimefunBlock();
            if (blockClicked.isPresent() && sfBlockClicked.isPresent()) {
                Location blockLoc = blockClicked.get().getLocation();
                SlimefunItem sfBlock = sfBlockClicked.get();
                ItemStack item = event.getItem();
                if (sfBlock != null && io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(event.getPlayer(), blockLoc, io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK) && sfBlock.getId().equals(DynaTechItems.WIRELESS_ENERGY_BANK.getItemId()) && blockLoc != null) {
                    event.cancel();
                    ItemMeta im = item.getItemMeta();
                    String locationString = LocationToString(blockLoc);
                    PersistentDataAPI.setString(im, WIRELESS_LOCATION_KEY, locationString);
                    item.setItemMeta(im);
                    setItemLore(item, blockLoc);
                }
            }
        };
    }

    ItemHandler onBlockPlace() {
        return new io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(org.bukkit.event.block.BlockPlaceEvent event) {
                Location blockLoc = event.getBlockPlaced().getLocation();
                ItemStack item = event.getItemInHand();
                String locationString = PersistentDataAPI.getString(item.getItemMeta(), WIRELESS_LOCATION_KEY);
                if (item != null && item.getType() == DynaTechItems.WIRELESS_ENERGY_POINT.getType() && item.hasItemMeta() && locationString != null)
                    addBlockInfo(blockLoc, "wireless-location", locationString);
            }
        };
    }

    ItemHandler onBlockBreak() {
        return new io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(org.bukkit.event.block.BlockBreakEvent event, ItemStack block, List<ItemStack> drops) {
                clearBlockInfo(event.getBlock().getLocation());
            }
        };
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public int getEnergyRate() {
        return energyRate;
    }

    void setItemLore(ItemStack item, Location l) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();
        for (int i = 0; i < lore.size(); i++) if (lore.get(i).contains("Location: ")) lore.remove(i);
        lore.add(org.bukkit.ChatColor.WHITE + "Location: " + l.getWorld().getName() + " " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ());
        im.setLore(lore);
        item.setItemMeta(im);
    }

    String LocationToString(Location l) {
        return l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ();
    }

    Location StringToLocation(String str) {
        String[] locComponents = str.split(":");
        return new Location(org.bukkit.Bukkit.getWorld(locComponents[0]), Double.parseDouble(locComponents[1]), Double.parseDouble(locComponents[2]), Double.parseDouble(locComponents[3]));
    }
}