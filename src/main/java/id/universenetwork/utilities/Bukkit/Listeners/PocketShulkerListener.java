package id.universenetwork.utilities.Bukkit.Listeners;

import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.*;

import static id.universenetwork.utilities.Bukkit.Enums.Features.PocketShulker.*;
import static id.universenetwork.utilities.Bukkit.Manager.Config.*;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.Utils.Color.Translator;
import static org.bukkit.Bukkit.createInventory;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.ChatColor.*;
import static org.bukkit.Material.AIR;
import static org.bukkit.Sound.BLOCK_SHULKER_BOX_CLOSE;
import static org.bukkit.Sound.BLOCK_SHULKER_BOX_OPEN;
import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.inventory.ClickType.RIGHT;

public class PocketShulkerListener implements Listener {
    final Map<Player, ItemStack> openshulkers = new HashMap<>();
    final Map<Player, Boolean> fromhand = new HashMap<>();
    final Map<UUID, Inventory> openinventories = new HashMap<>();
    final Map<Player, Inventory> opencontainer = new HashMap<>();
    final Map<Player, Long> pvp_timer = new HashMap<>();
    String defaultname = DARK_PURPLE + "Shulker Box";

    public PocketShulkerListener() {
        if (PSString(DEFNAME) != null) defaultname = Translator(PSString(DEFNAME));
        getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Player p : openshulkers.keySet()) {
                if (openshulkers.get(p).getType() == AIR) p.closeInventory();
                if (opencontainer.containsKey(p)) if (opencontainer.get(p).getLocation() != null)
                    if (opencontainer.get(p).getLocation() != null && opencontainer.get(p).getLocation().getWorld() == p.getWorld())
                        if (opencontainer.get(p).getLocation().distance(p.getLocation()) > 6)
                            p.closeInventory();
            }
        }, 1L, 1L);
    }

    /*
     * Saves the shulker on inventory drag if its open
     */
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                if (!saveShulker(p, e.getView().getTitle())) e.setCancelled(true);
            }, 1);
        }
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent e) {
        List<Player> closeInventories = new ArrayList<>();
        for (Player p : openshulkers.keySet()) if (openshulkers.get(p).equals(e.getItem())) closeInventories.add(p);
        for (Player p : closeInventories)
            if (e.getInitiator().getLocation() != null && e.getInitiator().getLocation().getWorld() == p.getWorld())
                if (e.getInitiator().getLocation().distance(p.getLocation()) < 6) p.closeInventory();
    }

    /*
     * Opens the shulker if its not in a weird inventory, then saves it
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.isCancelled()) return;
        Player p = (Player) e.getWhoClicked();

        if (openshulkers.containsKey(p)) if (openshulkers.get(p).getType() == AIR) {
            e.setCancelled(true);
            p.closeInventory();
            return;
        }

        // Cancels the event if the player is trying to remove an open shulker
        if (checkIfOpen(e.getCurrentItem())) if (e.getClick() != RIGHT) {
            e.setCancelled(true);
            return;
        }

        if (e.getWhoClicked() instanceof Player && e.getClickedInventory() != null) {
            if (e.getCurrentItem() != null && (openshulkers.containsKey(p) && e.getCurrentItem().equals(openshulkers.get(p)))) {
                e.setCancelled(true);
                return;
            }

            if (e.getClickedInventory() != null && (e.getClickedInventory().getType() == InventoryType.CHEST))
                if (!PSBoolean(CHESTOPEN) || (PSBoolean(CHESTOPEN) && !p.hasPermission("shulkerpacks.open_in_chests")))
                    return;

            InventoryType type = e.getClickedInventory().getType();
            String typeStr = type.toString();
            if (typeStr.equals("WORKBENCH") || typeStr.equals("ANVIL") || typeStr.equals("BEACON") || typeStr.equals("MERCHANT") || typeStr.equals("ENCHANTING") || typeStr.equals("GRINDSTONE") || typeStr.equals("CARTOGRAPHY") || typeStr.equals("LOOM") || typeStr.equals("STONECUTTER"))
                return;

            if (type == InventoryType.CRAFTING && e.getRawSlot() >= 1 && e.getRawSlot() <= 4) return;

            if ((p.getInventory() == e.getClickedInventory()))
                if (!PSBoolean(INVOPEN) || !p.hasPermission("shulkerpacks.open_in_inventory")) return;

            if (e.getSlotType() == InventoryType.SlotType.RESULT) return;

            if (e.getClickedInventory() != null && e.getClickedInventory().getHolder() != null && e.getClickedInventory().getHolder().getClass().toString().endsWith(".CraftBarrel") && !PSBoolean(BARRELOPEN))
                return;

            if (!PSBoolean(ECOPEN) && type == InventoryType.ENDER_CHEST) return;

            for (String str : PSStringList(BLACKLISTINV))
                if (stripColor(p.getOpenInventory().getTitle()).contains(stripColor(translateAlternateColorCodes('&', str))))
                    return;

            if (!PSBoolean(SHIFTOPEN) || e.isShiftClick()) {
                e.setCancelled(true);
                if (e.isRightClick() && openInventoryIfShulker(e.getCurrentItem(), p)) {
                    fromhand.remove(p);
                    return;
                }
                e.setCancelled(false);
            }

            getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                if (!saveShulker(p, e.getView().getTitle())) e.setCancelled(true);
            }, 1);
        }
    }

    // Deals with multiple people opening the same shulker
    boolean checkIfOpen(ItemStack shulker) {
        for (ItemStack i : openshulkers.values()) if (i.equals(shulker)) return true;
        return false;
    }

    /*
     * Saves the shulker if its open, then removes the current open shulker from the player data
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player p = (Player) e.getPlayer();
            if (saveShulker(p, p.getOpenInventory().getTitle()))
                p.playSound(p.getLocation(), BLOCK_SHULKER_BOX_CLOSE, PSFloat(SHULKERVOL), 1);
            openshulkers.remove(p);
        }
    }

    /*
     * Opens the shulker if the air was clicked with one
     */
    @EventHandler
    public void onClickAir(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (PSBoolean(AIROPEN) && (e.getClickedBlock() == null || e.getClickedBlock().getType() == AIR))
            if ((!PSBoolean(SHIFTOPEN) || player.isSneaking())) if (e.getAction() == RIGHT_CLICK_AIR)
                if (PSBoolean(AIROPEN) && player.hasPermission("shulkerpacks.open_in_air")) {
                    ItemStack item = e.getItem();
                    openInventoryIfShulker(item, e.getPlayer());
                    fromhand.put(player, true);
                }
    }

    @EventHandler
    public void onShulkerPlace(BlockPlaceEvent e) {
        if (e.getBlockPlaced().getType().toString().contains("SHULKER_BOX"))
            if (!PSBoolean(SHULKERPLACE)) e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            setPvpTimer((Player) e.getDamager());
            setPvpTimer((Player) e.getEntity());
        }
    }

    @EventHandler
    public void onPlayerShoot(ProjectileHitEvent e) {
        if (e.getHitEntity() instanceof Player && e.getEntity().getShooter() instanceof Player) {
            setPvpTimer((Player) e.getEntity().getShooter());
            setPvpTimer((Player) e.getHitEntity());
        }
    }

    /*
     * Saves the shulker data in the itemmeta
     */
    boolean saveShulker(Player p, String title) {
        try {
            if (openshulkers.containsKey(p)) {
                if (title.equals(defaultname) || (openshulkers.get(p).hasItemMeta() && openshulkers.get(p).getItemMeta().hasDisplayName() && (openshulkers.get(p).getItemMeta().getDisplayName().equals(title)))) {
                    ItemStack item = openshulkers.get(p);
                    if (item != null) {
                        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
                        ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
                        shulker.getInventory().setContents(openinventories.get(p.getUniqueId()).getContents());
                        meta.setBlockState(shulker);
                        item.setItemMeta(meta);
                        openshulkers.put(p, item);
                        updateAllInventories(openshulkers.get(p));
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            openshulkers.remove(p);
            p.closeInventory();
            return false;
        }
        return false;
    }

    void updateAllInventories(ItemStack item) {
        for (Player p : openshulkers.keySet())
            if (openshulkers.get(p).equals(item)) {
                BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
                ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
                p.getOpenInventory().getTopInventory().setContents(shulker.getInventory().getContents());
                p.updateInventory();
            }
    }

    /*
     * Opens the shulker inventory with the contents of the shulker
     */
    boolean openInventoryIfShulker(ItemStack i, Player p) {
        if (p.hasPermission("shulkerpacks.use")) {
            if (i != null) {
                if (i.getAmount() == 1 && i.getType().toString().contains("SHULKER")) {
                    if (getPvpTimer(p)) {
                        p.sendMessage(Translator(PSString(DISABLECOMBATMSG)));
                        return false;
                    }
                    if (i.getItemMeta() instanceof BlockStateMeta) {
                        BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
                        if (meta != null && meta.getBlockState() instanceof ShulkerBox) {
                            ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
                            Inventory inv;
                            if (meta.hasDisplayName())
                                inv = createInventory(new ShulkerHolder(), InventoryType.SHULKER_BOX, meta.getDisplayName());
                            else inv = createInventory(new ShulkerHolder(), InventoryType.SHULKER_BOX, defaultname);
                            inv.setContents(shulker.getInventory().getContents());
                            opencontainer.put(p, p.getOpenInventory().getTopInventory());
                            getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                p.openInventory(inv);
                                p.playSound(p.getLocation(), BLOCK_SHULKER_BOX_OPEN, PSFloat(SHULKERVOL), 1);
                                openshulkers.put(p, i);
                                openinventories.put(p.getUniqueId(), p.getOpenInventory().getTopInventory());
                            }, 1);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean getPvpTimer(Player player) {
        if (pvp_timer.containsKey(player)) return System.currentTimeMillis() - pvp_timer.get(player) < 7000;
        return false;
    }

    void setPvpTimer(Player p) {
        if (PSBoolean(DISABLECOMBAT)) pvp_timer.put(p, System.currentTimeMillis());
    }

    static class ShulkerHolder implements InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
}