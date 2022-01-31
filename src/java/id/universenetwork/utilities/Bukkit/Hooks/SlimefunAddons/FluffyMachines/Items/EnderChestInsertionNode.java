package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Utils.send;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.addBlockInfo;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.getLocationInfo;

public class EnderChestInsertionNode extends SlimefunItem {
    static final Material material = Material.ENDER_CHEST;

    public EnderChestInsertionNode(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onPlace());
        addItemHandler(onInteract());
    }

    @Override
    public void preRegister() {
        this.addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                EnderChestInsertionNode.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    void tick(@NotNull Block b) {
        ItemStack transferItemStack;
        BlockFace face;
        if (b.getRelative(BlockFace.NORTH).getType() == material) face = BlockFace.SOUTH;
        else if (b.getRelative(BlockFace.SOUTH).getType() == material) face = BlockFace.NORTH;
        else if (b.getRelative(BlockFace.EAST).getType() == material) face = BlockFace.WEST;
        else if (b.getRelative(BlockFace.WEST).getType() == material) face = BlockFace.EAST;
        else return;
        BlockState state = PaperLib.getBlockState(b.getRelative(face), false).getState();
        if (b.getRelative(face).getState() instanceof InventoryHolder) {
            Player p = Bukkit.getOfflinePlayer(UUID.fromString(getLocationInfo(b.getLocation(), "owner"))).getPlayer();

            // Ender chest null check necessary because Bukkit yes.
            if (p != null) {
                boolean enderValid = false;
                boolean containerValid = false;
                int enderIndex = -1;
                int containerIndex = -1;
                Inventory containerInv = ((InventoryHolder) state).getInventory();
                for (int i = 0; i < containerInv.getSize(); i++)
                    if (containerInv.getItem(i) != null) {
                        containerIndex = i;
                        containerValid = true;
                        break;
                    }
                Inventory enderInv = p.getEnderChest();
                for (int i = 0; i < enderInv.getSize(); i++)
                    if (enderInv.getItem(i) == null) {
                        enderIndex = i;
                        enderValid = true;
                        break;
                    }
                if (enderValid && containerValid) {
                    transferItemStack = containerInv.getItem(containerIndex);
                    containerInv.setItem(containerIndex, null);
                    enderInv.setItem(enderIndex, transferItemStack);
                }
            }
        }
    }

    BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                Player p = e.getPlayer();
                Block b = e.getBlock();
                if (!e.isCancelled()) {
                    addBlockInfo(b, "owner", p.getUniqueId().toString());
                    addBlockInfo(b, "playername", p.getDisplayName());
                    send(p, "&aEnder Chest Insertion Node registered to " + p.getDisplayName() + " &7(UUID: " + p.getUniqueId() + ")");
                }
            }
        };
    }

    BlockUseHandler onInteract() {
        return e -> {
            Player p = e.getPlayer();
            Block b = e.getClickedBlock().get();
            send(p, "&eThis Ender Chest Insertion Node belongs to " + getLocationInfo(b.getLocation(), "playername") + " &7(UUID: " + getLocationInfo(b.getLocation(), "owner") + ")");
        };
    }
}