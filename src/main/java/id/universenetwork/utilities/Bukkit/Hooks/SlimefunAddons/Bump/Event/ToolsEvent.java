package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Event;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Tools;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization;
import static org.bukkit.Material.*;

public class ToolsEvent implements org.bukkit.event.Listener {
    @EventHandler
    public void onRight(PlayerInteractEvent e) {
        try {
            if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                    e.getItem().getItemMeta().getLore().contains("§b§k|§b- Very expensive, be sure to keep it...")) {
                Player p = e.getPlayer();
                Inventory inventory = Bukkit.createInventory(null, 27, "Composite interface");
                for (int i = 0; i < 27; i++) {
                    if (i == 10 || i == 13 || i == 16)
                        inventory.setItem(13, new CustomItemStack(GREEN_STAINED_GLASS_PANE, "&aConfirmation", "&e<- &bPut the identifier on the left", "&e-> &bPlace items on the right"));
                    else inventory.setItem(i, new CustomItemStack(BLACK_STAINED_GLASS_PANE, "---"));
                }
                p.openInventory(inventory);
            }
        } catch (Exception ignore) {
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        try {
            if (e.getView().getTitle().equalsIgnoreCase("Composite interface") && e.getInventory().getSize() == 27) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("---") || e.getRawSlot() == 13)
                    e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                if (e.getInventory().getItem(10).getItemMeta() != null && e.getInventory().getItem(16).getItemMeta() != null && e.getInventory().getItem(10).getItemMeta().equals(Tools.appraisalPaperDamage_.getItemMeta()) &&
                        e.getRawSlot() == 13) {
                    ItemStack itemStack = e.getInventory().getItem(16);
                    ItemMeta meta = itemStack.getItemMeta();
                    List<String> list = new ArrayList<>();
                    if (itemStack.getItemMeta().hasLore()) list.addAll(meta.getLore());
                    if (itemStack.getItemMeta().getLore().contains("§b§k|§b- §7§oAuthenticated")) {
                        p.sendTitle("§b§k|§b- §7§oSorry that the item cannot be identified or has already been identified!", null, 10, 70, 20);
                        e.getInventory().clear();
                        p.closeInventory();
                        p.getInventory().addItem(itemStack);
                        p.getInventory().addItem(Tools.appraisalPaperDamage_);
                    } else {
                        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐⭐⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐");
                        list.remove("");
                        list.remove("§b§k|§b- §7§oIt has been authenticated!");
                        list.add("");
                        list.add("§b§k|§b- §7§oThe weapon has not yet been identified...");
                        meta.setLore(list);
                        itemStack.setItemMeta(meta);
                        p.getInventory().addItem(itemStack);
                        e.getInventory().clear();
                        p.closeInventory();
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oSuccessful identification!", false);
                    }
                }
                if (e.getInventory().getItem(10).getItemMeta() != null && e.getInventory().getItem(16).getItemMeta() != null && e.getInventory().getItem(10).getItemMeta().equals(Tools.appraisalPaperArmor_.getItemMeta()) && e.getRawSlot() == 13) {
                    ItemStack itemStack = e.getInventory().getItem(16);
                    ItemMeta meta = itemStack.getItemMeta();
                    List<String> list = new ArrayList<>();
                    if (itemStack.getItemMeta().hasLore()) list.addAll(meta.getLore());
                    if (itemStack.getItemMeta().getLore().contains("§b§k|§b- §7§oAuthenticated")) {
                        p.sendTitle("§b§k|§b- §7§oSorry that the item cannot be identified or has already been identified!", null, 10, 70, 20);
                        e.getInventory().clear();
                        p.closeInventory();
                        p.getInventory().addItem(itemStack);
                        p.getInventory().addItem(Tools.appraisalPaperArmor_);
                    } else {
                        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐⭐⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐");
                        list.remove("");
                        list.remove("§b§k|§b- §7§oIt has been authenticated!");
                        list.add("");
                        list.add("§b§k|§b- §7§oThe armor has not been verified...");
                        meta.setLore(list);
                        itemStack.setItemMeta(meta);
                        p.getInventory().addItem(itemStack);
                        e.getInventory().clear();
                        p.closeInventory();
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oSuccessful identification!", false);
                    }
                }
            }
        } catch (Exception ignore) {
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        try {
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§b§k|§b- There is a certain chance to dig out broken gold coins in the sand!") && e.getBlock().getType().equals(SAND)) {
                Random random = new Random();
                if (random.nextInt(4) == 0) {
                    e.setDropItems(false);
                    p.getWorld().dropItem(e.getBlock().getLocation(), id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Stuff.oldCoin_);
                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oOMG, gold...", false);
                }
            }
        } catch (Exception ignore) {
        }
    }
}