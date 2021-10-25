package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Food.*;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization;
import static org.bukkit.potion.PotionEffectType.*;

public class FoodEvent implements org.bukkit.event.Listener {
    @EventHandler
    public void onRight(PlayerInteractEvent e) {
        try {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Player p = e.getPlayer();
                if (e.getItem().getItemMeta().equals(xueBi_.getItemMeta())) {
                    p.setFoodLevel(20);
                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oA cool summer!", false);
                    p.addPotionEffect(new PotionEffect(LUCK, 2000, 5));
                    p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                }
                if (e.getItem().getItemMeta().equals(keLe_.getItemMeta())) {
                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oThe victory of the fat house!", false);
                    p.addPotionEffect(new PotionEffect(DOLPHINS_GRACE, 2000, 5));
                    p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                }
                if (e.getItem().getItemMeta().equals(kouXiangTang_.getItemMeta())) {
                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oFairy spirit fluttering!", false);
                    p.addPotionEffect(new PotionEffect(LEVITATION, 2000, 5));
                    p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                }
                if (e.getItem().getItemMeta().equals(laTiao_.getItemMeta())) {
                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oThis is spicy enough!", false);
                    p.addPotionEffect(new PotionEffect(UNLUCK, 2000, 5));
                    p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                }
                if (e.getItem().getItemMeta().equals(fangBianMian_.getItemMeta())) {
                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oThe taste of childhood!", false);
                    p.addPotionEffect(new PotionEffect(HEALTH_BOOST, 2000, 5));
                    p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                }
            }
        } catch (Exception ignore) {
        }
    }
}