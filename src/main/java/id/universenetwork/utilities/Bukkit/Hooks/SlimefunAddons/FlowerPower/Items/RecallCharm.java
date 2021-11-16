package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Utils;
import id.universenetwork.utilities.Bukkit.Utils.Color;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

/**
 * An item that allows players to bind locations
 * and teleport back to them for an exp cost
 *
 * @author NCBPFluffyBear
 */
public class RecallCharm extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> {
    public static final int TELEPORT_COST = 50;
    static final int LORE_INDEX = 7;
    static final NamespacedKey location = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "location");

    public RecallCharm(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            if (e.getInteractEvent().getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) return;
            org.bukkit.entity.Player p = e.getPlayer();
            ItemStack charm = e.getItem();
            org.bukkit.inventory.meta.ItemMeta charmMeta = charm.getItemMeta();

            // Assign teleport location mode
            if (p.isSneaking()) {
                Location l = p.getLocation();
                if (!io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(p, l.getBlock(), io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK)) {
                    Utils.send(p, "You do not have permission to bind your Recall Charm here!");
                    return;
                }

                // Store location info into PDC
                PersistentDataAPI.setString(charmMeta, location, l.getWorld().getUID() + "_" + l.getBlockX() + "_" + l.getBlockY() + "_" + l.getBlockZ());

                // Put location info into lore
                java.util.List<String> lore = charmMeta.getLore();
                lore.set(LORE_INDEX, Color.Translator("&3Bound Location: " + l.getWorld().getName() + " @ " + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ()));
                charmMeta.setLore(lore);
                charm.setItemMeta(charmMeta);

                Utils.send(p, "&aYour recall charm has been bound to your current location");
                return;
            }

            // Teleport player

            // Check if player has sufficient exp
            if (Utils.getTotalExperience(p) < TELEPORT_COST) {
                Utils.send(p, "&cYou can not afford to teleport! Needed exp points: " + TELEPORT_COST);
                return;
            }

            String locationDat = PersistentDataAPI.getString(charmMeta, location);

            // Charm not bound yet
            if (locationDat == null) {
                Utils.send(p, "&cThis recall charm has not been bound yet!");
                return;
            }

            // Parse location data
            String[] location = locationDat.split("_");

            // Consume exp and teleport player
            p.giveExp(-TELEPORT_COST);
            p.teleport(new Location(org.bukkit.Bukkit.getWorld(java.util.UUID.fromString(location[0])), Integer.parseInt(location[1]) + 0.5, Integer.parseInt(location[2]), Integer.parseInt(location[3]) + 0.5));
            p.playSound(p.getLocation(), org.bukkit.Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

            Utils.send(p, "&aYou have teleported successfully");
        };
    }
}