package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.HelicopterHat;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools.WateringCan;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines.AlternateElevatorPlate;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.FluffyItems.*;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.*;
import static org.bukkit.block.BlockFace.UP;

public class Events implements org.bukkit.event.Listener {
    final HelicopterHat helicopterHat = (HelicopterHat) HELICOPTER_HAT.getItem();
    final WateringCan wateringCan = (WateringCan) WATERING_CAN.getItem();

    @EventHandler
    public void onAdminJoin(PlayerJoinEvent e) {
        if (e.getPlayer().isOp() && !Constants.SLIMEFUN_UPDATED)
            Utils.send(e.getPlayer(), "&cYou are running a version of Slimefun before DEV 844, RC version of Slimefun " + "or running a custom build. FluffyMachines requires you to update your Slimefun version so that " + "barrels remain functional. Update before 4/15/2021, or players may encounter issues with " + "FluffyMachines that I am not accountable for. Add ignore-outdated-warning: false to the " + "FluffyMachines config to stop receiving this warning.");
    }

    @EventHandler(ignoreCancelled = true)
    public void onHelicopterHatUse(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        if (helicopterHat.isItem(p.getEquipment().getHelmet())) {
            if (e.isSneaking()) p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1000000, 4));
            else p.removePotionEffect(PotionEffectType.LEVITATION);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onWateringCanSplash(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();

        // For some reason player interact events trigger twice, probably after a method returns false
        if (wateringCan.isItem(item)) {
            e.setCancelled(true);
            Entity target = e.getRightClicked();
            if (target instanceof Player && WateringCan.updateUses(wateringCan, p, item, 3)) {
                Utils.send(p, "&bSplash!");
                Utils.send((Player) target, "&bYou were splashed by " + p.getDisplayName() + "!");
                ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && ((Player) e.getEntity()).getEquipment() != null && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player p = (Player) e.getEntity();
            ItemStack helmet = p.getEquipment().getHelmet();
            if (helmet != null && helicopterHat.isItem(helmet)) e.setCancelled(true);
        }
    }

    // This is used to make the non clickable GUI items non clickable
    @EventHandler(ignoreCancelled = true)
    public void onNonClickableClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item != null && item.getType() != Material.AIR && item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == 6969)
            e.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onHeadRemove(PlayerArmorStandManipulateEvent e) {
        if (e.getRightClicked().getCustomName() != null && e.getRightClicked().getCustomName().equals("hehexdfluff"))
            e.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemDamage(EntityDamageEvent e) {
        Entity en = e.getEntity();
        if (en instanceof Item) {
            ItemStack item = ((Item) en).getItemStack();
            if (id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.FireproofRune.isFireproof(item) && (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || e.getCause() == EntityDamageEvent.DamageCause.LAVA || e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) && !en.isDead()) {
                en.remove();
                en.getLocation().getWorld().dropItem(en.getLocation(), item);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerWarp(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            Player p = e.getPlayer();
            Block b = p.getLocation().subtract(0, 1, 0).getBlock();
            if (hasBlockInfo(b) && check(b) == FluffyItems.WARP_PAD.getItem() && getLocationInfo(b.getLocation(), "type").equals("origin")) {
                Location l = b.getLocation();
                Location destination = new Location(b.getWorld(), Integer.parseInt(getLocationInfo(l, "x")), Integer.parseInt(getLocationInfo(l, "y")), Integer.parseInt(getLocationInfo(l, "z")));
                float yaw = p.getLocation().getYaw();
                float pitch = p.getLocation().getPitch();
                if (hasBlockInfo(destination) && getLocationInfo(destination, "type") != null && getLocationInfo(destination, "type").equals("destination") && destination.getBlock().getRelative(UP).getType() == Material.AIR && destination.getBlock().getRelative(UP, 2).getType() == Material.AIR) {
                    destination.setPitch(pitch);
                    destination.setYaw(yaw);
                    p.teleport(destination.add(0.5, 1, 0.5));
                    p.playSound(p.getLocation(), org.bukkit.Sound.ITEM_CHORUS_FRUIT_TELEPORT, 0.5F, 0.5F);
                    p.spawnParticle(org.bukkit.Particle.DRAGON_BREATH, p.getLocation(), 10);
                } else Utils.send(p, "&cMissing destination Warp Pad!");
            }
        }
    }

    @EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPressurePlateEnter(PlayerInteractEvent e) {
        if (e.getAction() != Action.PHYSICAL || e.getClickedBlock() == null) return;
        String id = checkID(e.getClickedBlock());
        if (id != null && id.equals(FluffyItems.ALTERNATE_ELEVATOR_PLATE.getItemId())) {
            AlternateElevatorPlate elevator = ((AlternateElevatorPlate) FluffyItems.ALTERNATE_ELEVATOR_PLATE.getItem());
            elevator.openInterface(e.getPlayer(), e.getClickedBlock());
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onExtractionNodePlace(BlockPlaceEvent e) {
        if ((e.getBlock().getY() != e.getBlockAgainst().getY() || e.getBlockAgainst().getType() != Material.ENDER_CHEST) && isExtractionNode(e.getItemInHand())) {
            Utils.send(e.getPlayer(), "&cYou can only place this on an Ender Chest!");
            e.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onDollyDispense(org.bukkit.event.block.BlockDispenseEvent e) {
        SlimefunItem sfItem = SlimefunItem.getByItem(e.getItem());
        if (sfItem != null && sfItem.getId().equals(FluffyItems.DOLLY.getItemId())) e.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBarrelBurn(org.bukkit.event.block.BlockBurnEvent e) {
        if (check(e.getBlock()) instanceof id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Barrel)
            e.setCancelled(true);
    }

    boolean isExtractionNode(ItemStack item) {
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem == null) return false;
        return sfItem.getId().equals(ENDER_CHEST_EXTRACTION_NODE.getItemId()) || sfItem.getId().equals(ENDER_CHEST_INSERTION_NODE.getItemId());
    }

    @EventHandler(ignoreCancelled = true)
    void onCancelPlace(BlockPlaceEvent e) {
        ItemStack item = e.getItemInHand();
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem instanceof CancelPlace) e.setCancelled(true);
    }
}