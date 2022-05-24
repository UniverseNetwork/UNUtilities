package id.universenetwork.utilities.Bukkit.Features.PocketShulker;

import id.universenetwork.utilities.Bukkit.Events.ReloadConfigEvent;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events;
import id.universenetwork.utilities.Bukkit.Templates.Feature;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import id.universenetwork.utilities.Bukkit.Utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Main extends Feature implements Listener {
    private final Map<Player, ItemStack> openshulkers = new HashMap<>();
    final Map<Player, Boolean> fromhand = new HashMap<>();
    private final Map<UUID, Inventory> openinventories = new HashMap<>();
    private final Map<Player, Inventory> opencontainer = new HashMap<>();
    private final Map<Player, Long> pvp_timer = new HashMap<>();
    private String name;

    @EventHandler
    public void onConfigReload(ReloadConfigEvent e) {
        name = Text.translateColor(UNUtilities.cfg.getString(configPath + "defaultname", "&5Shulker Box"));
    }

    @Override
    public void Load() {
        if (!UNUtilities.cfg.getBoolean(configPath + "enabled")) return;
        Events.registerListeners(this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(UNUtilities.plugin, () -> {
            for (Player p : openshulkers.keySet()) {
                if (openshulkers.get(p).getType() == Material.AIR) p.closeInventory();
                if (opencontainer.containsKey(p)
                        && opencontainer.get(p).getLocation() != null
                        && opencontainer.get(p).getLocation().getWorld() == p.getWorld())
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
            Bukkit.getScheduler().scheduleSyncDelayedTask(UNUtilities.plugin, () -> {
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
        boolean c = UNUtilities.cfg.getBoolean(configPath + "canopeninchests");
        if (openshulkers.containsKey(p)) if (openshulkers.get(p).getType() == Material.AIR) {
            e.setCancelled(true);
            p.closeInventory();
            return;
        }

        // Cancels the event if the player is trying to remove an open shulker
        if (checkIfOpen(e.getCurrentItem()) && e.getClick() != org.bukkit.event.inventory.ClickType.RIGHT) {
            e.setCancelled(true);
            return;
        }

        if (e.getWhoClicked() instanceof Player && e.getClickedInventory() != null) {
            if (e.getCurrentItem() != null && (openshulkers.containsKey(p)
                    && e.getCurrentItem().equals(openshulkers.get(p)))) {
                e.setCancelled(true);
                return;
            }
            if (e.getClickedInventory() != null && (e.getClickedInventory().getType() == InventoryType.CHEST))
                if (!c || !p.hasPermission("unutilities.pocketshulker.open_in_chests"))
                    return;
            InventoryType type = e.getClickedInventory().getType();
            String typeStr = type.toString();
            if (typeStr.equals("WORKBENCH") || typeStr.equals("ANVIL") || typeStr.equals("BEACON")
                    || typeStr.equals("MERCHANT") || typeStr.equals("ENCHANTING") || typeStr.equals("GRINDSTONE")
                    || typeStr.equals("CARTOGRAPHY") || typeStr.equals("LOOM") || typeStr.equals("STONECUTTER"))
                return;
            if (type == InventoryType.CRAFTING && e.getRawSlot() >= 1 && e.getRawSlot() <= 4) return;
            if ((p.getInventory() == e.getClickedInventory()))
                if (!UNUtilities.cfg.getBoolean(configPath + "canopenininventory")
                        || !p.hasPermission("unutilities.pocketshulker.open_in_inventory"))
                    return;
            if (e.getSlotType() == InventoryType.SlotType.RESULT) return;
            if (e.getClickedInventory() != null && e.getClickedInventory().getHolder() != null
                    && e.getClickedInventory().getHolder().getClass().toString().endsWith(".CraftBarrel")
                    && !UNUtilities.cfg.getBoolean(configPath + "canopeninbarrels"))
                return;
            if (!UNUtilities.cfg.getBoolean(configPath + "canopeninenderchest")
                    && type == InventoryType.ENDER_CHEST)
                return;
            for (String s : UNUtilities.cfg.getStringList(configPath + "blacklistedinventories"))
                if (ChatColor.stripColor(p.getOpenInventory().getTitle())
                        .contains(ChatColor.stripColor(Text.translateColor(s))))
                    return;
            if (!UNUtilities.cfg.getBoolean(configPath + "shiftclicktoopen") || e.isShiftClick()) {
                e.setCancelled(true);
                if (e.isRightClick() && openInventoryIfShulker(e.getCurrentItem(), p)) {
                    fromhand.remove(p);
                    return;
                }
                e.setCancelled(false);
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(UNUtilities.plugin, () -> {
                if (!saveShulker(p, e.getView().getTitle())) e.setCancelled(true);
            }, 1);
        }
    }

    // Deals with multiple people opening the same shulker
    private boolean checkIfOpen(ItemStack shulker) {
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
                p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE,
                        (float) UNUtilities.cfg.getDouble(configPath + "shulkervolume"), 1);
            openshulkers.remove(p);
        }
    }

    /*
     * Opens the shulker if the air was clicked with one
     */
    @EventHandler
    public void onClickAir(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        boolean a = UNUtilities.cfg.getBoolean(configPath + "canopeninair");
        if (a && (e.getClickedBlock() == null || e.getClickedBlock().getType() == Material.AIR))
            if ((!UNUtilities.cfg.getBoolean(configPath + "shiftclicktoopen") || p.isSneaking()))
                if (e.getAction() == Action.RIGHT_CLICK_AIR)
                    if (p.hasPermission("unutilities.pocketshulker.open_in_air")) {
                        ItemStack item = e.getItem();
                        openInventoryIfShulker(item, e.getPlayer());
                        fromhand.put(p, true);
                    }
    }

    @EventHandler
    public void onShulkerPlace(BlockPlaceEvent e) {
        if (e.getBlockPlaced().getType().toString().contains("SHULKER_BOX"))
            if (!UNUtilities.cfg.getBoolean(configPath + "canplaceshulker")) e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerHit(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            setPvpTimer((Player) e.getDamager());
            setPvpTimer((Player) e.getEntity());
        }
    }

    @EventHandler
    public void onPlayerShoot(org.bukkit.event.entity.ProjectileHitEvent e) {
        if (e.getHitEntity() instanceof Player && e.getEntity().getShooter() instanceof Player) {
            setPvpTimer((Player) e.getEntity().getShooter());
            setPvpTimer((Player) e.getHitEntity());
        }
    }

    /*
     * Saves the shulker data in the itemmeta
     */
    private boolean saveShulker(Player p, String t) {
        try {
            if (openshulkers.containsKey(p))
                if (t.equals(name) || (openshulkers.get(p).hasItemMeta()
                        && openshulkers.get(p).getItemMeta().hasDisplayName()
                        && (openshulkers.get(p).getItemMeta().getDisplayName().equals(t)))) {
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
        } catch (Exception e) {
            openshulkers.remove(p);
            p.closeInventory();
            return false;
        }
        return false;
    }

    private void updateAllInventories(ItemStack item) {
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
    private boolean openInventoryIfShulker(ItemStack i, Player p) {
        if (p.hasPermission("unutilities.pocketshulker.use") && i != null)
            if (i.getAmount() == 1 && i.getType().toString().contains("SHULKER")) {
                if (getPvpTimer(p)) {
                    Text.send(p, UNUtilities.cfg.getString(configPath + "disable-in-combat-message"));
                    return false;
                }
                if (i.getItemMeta() instanceof BlockStateMeta) {
                    BlockStateMeta meta = (BlockStateMeta) i.getItemMeta();
                    if (meta != null && meta.getBlockState() instanceof ShulkerBox) {
                        ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
                        Inventory inv;
                        if (meta.hasDisplayName())
                            inv = Bukkit.createInventory(new ShulkerHolder(), InventoryType.SHULKER_BOX, meta.getDisplayName());
                        else inv = Bukkit.createInventory(new ShulkerHolder(), InventoryType.SHULKER_BOX, name);
                        inv.setContents(shulker.getInventory().getContents());
                        opencontainer.put(p, p.getOpenInventory().getTopInventory());
                        Bukkit.getScheduler().scheduleSyncDelayedTask(UNUtilities.plugin, () -> {
                            p.openInventory(inv);
                            p.playSound(p.getLocation(), Sound.BLOCK_SHULKER_BOX_OPEN,
                                    (float) UNUtilities.cfg.getDouble(configPath + "shulkervolume"), 1);
                            openshulkers.put(p, i);
                            openinventories.put(p.getUniqueId(), p.getOpenInventory().getTopInventory());
                        }, 1);
                        return true;
                    }
                }
            }
        return false;
    }

    private boolean getPvpTimer(Player player) {
        if (pvp_timer.containsKey(player)) return System.currentTimeMillis() - pvp_timer.get(player) < 7000;
        return false;
    }

    private void setPvpTimer(Player p) {
        if (UNUtilities.cfg.getBoolean(configPath + "disable-in-combat"))
            pvp_timer.put(p, System.currentTimeMillis());
    }

    protected static class ShulkerHolder implements InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
}