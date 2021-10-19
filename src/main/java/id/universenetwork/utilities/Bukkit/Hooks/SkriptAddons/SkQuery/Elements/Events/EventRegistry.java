package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.util.Getter;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.AbstractTask;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.Bukkit.AttachedTabCompleteEvent;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.Lang.ScriptOptionsEvent;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Projectile.ItemProjectileHitEvent;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

import static ch.njol.skript.registrations.EventValues.registerEventValue;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.addon;

public class EventRegistry extends AbstractTask {
    @Override
    public void run() {
        registerEvent("Enchant", EnchantItemEvent.class, "enchant");
        registerEventValue(EnchantItemEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(EnchantItemEvent enchantItemEvent) {
                return enchantItemEvent.getItem();
            }
        }, 0);
        registerEventValue(EnchantItemEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(EnchantItemEvent enchantItemEvent) {
                return enchantItemEvent.getEnchanter();
            }
        }, 0);
        registerEvent("Sheep Dye", SheepDyeWoolEvent.class, "sheep dye");
        registerEvent("Horse Jump", HorseJumpEvent.class, "horse jump");
        registerEvent("Book Editing", PlayerEditBookEvent.class, "[book] edit");
        registerEvent("Flight Toggle", PlayerToggleFlightEvent.class, "[player] toggl(e|ing) (flight|fly)", "[player] (flight|fly) toggl(e|ing)");
        registerEvent("Inventory Click", InventoryClickEvent.class, "inventory click");
        registerEventValue(InventoryClickEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(InventoryClickEvent inventoryClickEvent) {
                return inventoryClickEvent.getCurrentItem();
            }
        }, 0);
        registerEvent("Generic Move", PlayerMoveEvent.class, "any move[ment]");
        registerEvent("Server Ping", ServerListPingEvent.class, "[server] [list] ping");
        registerEvent("Item Projectile Hit", ItemProjectileHitEvent.class, "item [projectile] hit");
        registerEventValue(ItemProjectileHitEvent.class, ItemType.class, new Getter<>() {
            @Override
            public ItemType get(ItemProjectileHitEvent itemProjectileHitEvent) {
                return new ItemType(itemProjectileHitEvent.getProjectile().getItemStack());
            }
        }, 0);
        registerEventValue(ItemProjectileHitEvent.class, LivingEntity.class, new Getter<>() {
            @Override
            public LivingEntity get(ItemProjectileHitEvent itemProjectileHitEvent) {
                return itemProjectileHitEvent.getShooter();
            }
        }, 0);
        registerEventValue(ItemProjectileHitEvent.class, Location.class, new Getter<>() {
            @Override
            public Location get(ItemProjectileHitEvent itemProjectileHitEvent) {
                return itemProjectileHitEvent.getProjectile().getLocation();
            }
        }, 0);
        registerEvent("Falling Block Land", EvtBlockLand.class, EntityChangeBlockEvent.class, "block land");
        registerEventValue(EntityChangeBlockEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(EntityChangeBlockEvent entityChangeBlockEvent) {
                return entityChangeBlockEvent.getEntity() instanceof FallingBlock ? new ItemStack(((FallingBlock) entityChangeBlockEvent.getEntity()).getBlockData().getMaterial()) : null;
            }
        }, 0);
        registerEventValue(EntityChangeBlockEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(EntityChangeBlockEvent entityChangeBlockEvent) {
                return entityChangeBlockEvent.getEntity() instanceof FallingBlock ? entityChangeBlockEvent.getEntity() : null;
            }
        }, 0);
        registerEvent("Close Inventory", InventoryCloseEvent.class, "inventory [window] close");
        registerEventValue(InventoryCloseEvent.class, Inventory.class, new Getter<>() {
            @Override
            public Inventory get(InventoryCloseEvent inventoryCloseEvent) {
                return inventoryCloseEvent.getInventory();
            }
        }, 0);
        registerEventValue(InventoryCloseEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(InventoryCloseEvent inventoryCloseEvent) {
                return inventoryCloseEvent.getPlayer() instanceof Player ? (Player) inventoryCloseEvent.getPlayer() : null;
            }
        }, 0);
        registerEvent("Vehicle Collide With Block", VehicleBlockCollisionEvent.class, "vehicle (block collide|collide with block)");
        registerEventValue(VehicleBlockCollisionEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(VehicleBlockCollisionEvent vehicleBlockCollisionEvent) {
                return vehicleBlockCollisionEvent.getVehicle();
            }
        }, 0);
        registerEventValue(VehicleBlockCollisionEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(VehicleBlockCollisionEvent vehicleBlockCollisionEvent) {
                return vehicleBlockCollisionEvent.getBlock();
            }
        }, 0);
        registerEvent("Vehicle Collide With Entity", VehicleBlockCollisionEvent.class, "vehicle (entity collide|collide with entity)");
        registerEventValue(VehicleEntityCollisionEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(VehicleEntityCollisionEvent vehicleEntityCollisionEvent) {
                return vehicleEntityCollisionEvent.getVehicle();
            }
        }, 0);
        registerEventValue(VehicleEntityCollisionEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(VehicleEntityCollisionEvent vehicleEntityCollisionEvent) {
                return vehicleEntityCollisionEvent.getEntity();
            }
        }, 0);
        registerEvent("*Script Options Header", ScriptOptionsEvent.class, "script options");
        registerEvent("*Tab Complete", EvtAttachCompleter.class, AttachedTabCompleteEvent.class, "tab complet(er|ion) [for [command]] %string%");
        registerEventValue(AttachedTabCompleteEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(AttachedTabCompleteEvent attachedTabCompleteEvent) {
                return attachedTabCompleteEvent.getSender() instanceof Player ? ((Player) attachedTabCompleteEvent.getSender()) : null;
            }
        }, 0);
        try {
            addon.loadClasses("id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements", "Events");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}