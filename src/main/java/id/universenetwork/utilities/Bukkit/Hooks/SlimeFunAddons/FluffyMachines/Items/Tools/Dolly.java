package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Items.Tools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerBackpack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getBackpackListener;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.BREAK_BLOCK;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.hasBlockInfo;
import static org.bukkit.Material.*;

public class Dolly extends SimpleSlimefunItem<ItemUseHandler> {
    static final ItemStack lockItem = Utils.buildNonInteractable(DIRT, "&4&lDolly empty", "&cHow did you get in here?");

    public Dolly(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Player p = e.getPlayer();
            ItemStack dolly = e.getItem();
            if (!e.getClickedBlock().isPresent()) return;
            Block b = e.getClickedBlock().get();
            if (!getProtectionManager().hasPermission(e.getPlayer(), b.getLocation(), BREAK_BLOCK))
                return;
            Block relative = b.getRelative(e.getClickedFace());
            if (b.getType() == CHEST && !hasBlockInfo(b)) {
                ItemMeta dollyMeta = dolly.getItemMeta();
                for (String line : dollyMeta.getLore())
                    if (line.contains("ID: <ID>")) {
                        PlayerProfile.get(p, profile -> {
                            int backpackId = profile.createBackpack(27).getId();
                            getBackpackListener().setBackpackId(p, dolly, 3, backpackId);
                            PlayerProfile.getBackpack(dolly, backpack -> backpack.getInventory().setItem(0, lockItem));
                        });
                    }
                Inventory chest = ((InventoryHolder) b.getState()).getInventory();
                if (chest.getSize() > 27) {
                    Utils.send(p, "&cYou can only pick up single chests!");
                    return;
                }
                ItemStack[] contents = chest.getContents();
                AtomicBoolean exists = new AtomicBoolean(false);
                PlayerProfile.getBackpack(dolly, backpack -> {
                    if (backpack != null && backpack.getInventory().getItem(0) != null && Utils.checkNonInteractable(backpack.getInventory().getItem(0))) {
                        backpack.getInventory().setStorageContents(contents);
                        chest.clear();
                        PlayerProfile.getBackpack(dolly, PlayerBackpack::markDirty);
                        exists.set(true);
                        dolly.setType(CHEST_MINECART);
                    } else Utils.send(p, "&cThis dolly is already carrying a chest!");
                });

                // Deals with async problems
                if (exists.get()) {
                    b.setType(AIR);
                    Utils.send(p, "&aYou have picked up this chest");
                }
            } else if (relative.getType() == AIR) {
                PlayerProfile.getBackpack(dolly, backpack -> {
                    if (backpack != null && (backpack.getInventory().getItem(0) == null || !Utils.checkNonInteractable(backpack.getInventory().getItem(0)))) {
                        ItemStack[] bpcontents = backpack.getInventory().getContents();
                        backpack.getInventory().clear();
                        backpack.getInventory().setItem(0, lockItem);
                        relative.setType(CHEST);
                        ((InventoryHolder) relative.getState()).getInventory().setStorageContents(bpcontents);
                        dolly.setType(MINECART);
                        Utils.send(p, "&aChest has been placed");
                    } else Utils.send(p, "&cYou must pick up a chest first!");
                });
            }
        };
    }
}