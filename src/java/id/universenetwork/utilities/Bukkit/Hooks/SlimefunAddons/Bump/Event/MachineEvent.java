package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Event;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static java.util.UUID.randomUUID;

public class MachineEvent implements org.bukkit.event.Listener {
    @EventHandler
    public void onRightBlock(PlayerRightClickEvent e) {
        Player p = e.getPlayer();
        try {
            if (e.getSlimefunBlock().isPresent() && (e.getSlimefunBlock().get()).equals(id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Machine.appraisal))
                if (e.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§oThe weapon has not yet been identified...") || e.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§oThe armor has not been verified...")) {
                    Random r = new Random();
                    double ra = r.nextDouble() * 20.0D;
                    double ra1 = r.nextDouble() * 20.0D;
                    double ra3 = r.nextDouble() * 20.0D;
                    ItemMeta meta = e.getItem().getItemMeta();
                    if (e.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§oThe weapon has not yet been identified...")) {
                        EquipmentSlot equipmentSlot;
                        if (ra3 <= 10.0D) equipmentSlot = EquipmentSlot.HAND;
                        else equipmentSlot = EquipmentSlot.OFF_HAND;
                        AttributeModifier modifier = new AttributeModifier(randomUUID(), "Random attribute", ra, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
                        AttributeModifier modifier1 = new AttributeModifier(randomUUID(), "Random attribute 1", ra1, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
                        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
                        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
                        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
                    } else if (e.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§oThe armor has not been verified...")) {
                        EquipmentSlot equipmentSlot;
                        if (ra3 <= 5.0D) equipmentSlot = EquipmentSlot.HEAD;
                        else if (ra3 <= 10.0D) equipmentSlot = EquipmentSlot.CHEST;
                        else if (ra3 <= 15.0D) equipmentSlot = EquipmentSlot.LEGS;
                        else equipmentSlot = EquipmentSlot.FEET;
                        AttributeModifier modifier = new AttributeModifier(randomUUID(), "Random attribute", ra, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
                        AttributeModifier modifier1 = new AttributeModifier(randomUUID(), "Random attribute 1", ra1, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
                        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);
                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier1);
                    }
                    List<String> list = new ArrayList<>();
                    Iterator<String> iterator = meta.getLore().iterator();
                    meta.setLore(list);
                    while (iterator.hasNext()) {
                        String next = iterator.next();
                        list.remove("§b§k|§b- §7§oAuthenticated");
                        list.add(next.replaceAll("§7§oThe weapon has not yet been identified...", "§7§oAuthenticated").replaceAll("§7§oThe armor has not been verified...", "§7§oAuthenticated"));
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐⭐⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐⭐");
                        list.remove("§b§k|§b- §7§oQuality: §c⭐");
                        list.remove("");
                        list.remove("§b§k|§b- §7§oIt has been authenticated!");
                    }
                    list.add("");
                    if (ra >= 15.0D) list.add("§b§k|§b- §7§oQuality: §c⭐⭐⭐⭐⭐");
                    else if (ra < 15.0D && ra >= 10.0D) list.add("§b§k|§b- §7§oQuality: §c⭐⭐⭐⭐");
                    else if (ra < 10.0D && ra >= 7.0D) list.add("§b§k|§b- §7§oQuality: §c⭐⭐⭐");
                    else if (ra < 7.0D && ra >= 5.0D) list.add("§b§k|§b- §7§oQuality: §c⭐⭐");
                    else list.add("§b§k|§b- §7§oQuality: §c⭐");
                    list.add("");
                    meta.setLore(list);
                    e.getItem().setItemMeta(meta);
                    p.sendTitle("§b§k|§b- §7§oSuccessful identification!", null, 10, 70, 20);
                } else
                    p.sendTitle("§b§k|§b- §7§oSorry that the item cannot be identified or has already been identified!", null, 10, 70, 20);
        } catch (Exception ignore) {
        }
    }
}