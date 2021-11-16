package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.FluffyItems;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Utils.send;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.Utils.Color.Translator;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager;
import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.*;
import static org.bukkit.inventory.EquipmentSlot.HAND;
import static org.bukkit.persistence.PersistentDataType.INTEGER;
import static org.bukkit.persistence.PersistentDataType.STRING;

public class WarpPadConfigurator extends SlimefunItem implements HologramOwner, Listener {
    final NamespacedKey xCoord = new NamespacedKey(plugin, "xCoordinate");
    final NamespacedKey yCoord = new NamespacedKey(plugin, "yCoordinate");
    final NamespacedKey zCoord = new NamespacedKey(plugin, "zCoordinate");
    final NamespacedKey world = new NamespacedKey(plugin, "world");
    static final int LORE_COORDINATE_INDEX = 4;
    final ItemSetting<Integer> MAX_DISTANCE = new ItemSetting<>(this, "max-distance", 100);

    public WarpPadConfigurator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        Events.registerListeners(this);
        addItemSetting(MAX_DISTANCE);
    }

    @EventHandler
    void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null || !(e.getHand() == HAND)) return;
        Block b = e.getClickedBlock();
        Player p = e.getPlayer();
        if (hasBlockInfo(b) && check(b).equals(FluffyItems.WARP_PAD.getItem()) && getProtectionManager().hasPermission(p, b.getLocation(), Interaction.PLACE_BLOCK)) {
            if (isItemSimilar(p.getInventory().getItemInMainHand(), FluffyItems.WARP_PAD_CONFIGURATOR, false)) {
                ItemStack item = p.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                List<String> lore = meta.getLore();
                PersistentDataContainer pdc = meta.getPersistentDataContainer();
                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    // Destination
                    if (p.isSneaking()) {
                        pdc.set(world, STRING, b.getWorld().getName());
                        pdc.set(xCoord, INTEGER, b.getX());
                        pdc.set(yCoord, INTEGER, b.getY());
                        pdc.set(zCoord, INTEGER, b.getZ());
                        lore.set(LORE_COORDINATE_INDEX, Translator("&eLinked Coordinates: &7" + b.getX() + ", " + b.getY() + ", " + b.getZ()));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        updateHologram(b, "&a&lDestination");
                        addBlockInfo(b, "type", "destination");
                        send(p, "&3This pad has been marked as a &aDestination &3and bound to your configurator");

                        // Origin
                    } else if (pdc.has(world, STRING) && b.getWorld().getName().equals(pdc.get(world, STRING))) {
                        int x = pdc.getOrDefault(xCoord, INTEGER, 0);
                        int y = pdc.getOrDefault(yCoord, INTEGER, 0);
                        int z = pdc.getOrDefault(zCoord, INTEGER, 0);
                        if (Math.abs(x - b.getX()) > MAX_DISTANCE.getValue() || Math.abs(z - b.getZ()) > MAX_DISTANCE.getValue()) {
                            send(p, "&cYou can not link blocks more than " + MAX_DISTANCE.getValue() + " blocks apart!");
                            return;
                        }
                        registerOrigin(b, x, y, z);
                        send(p, "&3This pad has been marked as an &aOrigin &3and your configurator's settings " + "have been pasted onto this pad");
                    } else
                        send(p, "&cSneak and right click on a Warp Pad to set the destination, then right click" + " " + "another Warp Pad tp set the origin!");
                }
            } else send(p, "&cConfigure this Warp Pad using a Warp Pad Configurator");
        }
    }

    void registerOrigin(Block b, int x, int y, int z) {
        addBlockInfo(b, "type", "origin");
        addBlockInfo(b, "x", String.valueOf(x));
        addBlockInfo(b, "y", String.valueOf(y));
        addBlockInfo(b, "z", String.valueOf(z));
        updateHologram(b, "&a&lOrigin");
    }
}