package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Gear;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;
import static org.bukkit.ChatColor.*;

public final class InfinityMatrix extends SimpleSlimefunItem<ItemUseHandler> implements Listener, Soulbound, NotPlaceable {
    public InfinityMatrix(ItemGroup itemGroup, SlimefunItemStack item, RecipeType type, ItemStack[] recipe) {
        super(itemGroup, item, type, recipe);
        registerListener(this);
    }

    static void disableFlight(Player p) {
        p.sendMessage(RED + "Infinity Flight Disabled!");
        p.setAllowFlight(false);
    }

    static void enableFlight(Player p) {
        if (p.hasPermission("unutilities.use.infinitymatrix")) {
            p.sendMessage(GREEN + "Infinity Flight Enabled!");
            p.setAllowFlight(true);
        } else p.sendMessage(RED + "You don't have permission to use Infinity Matrix!");
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            ItemStack item = e.getItem();
            if (!item.hasItemMeta()) return;
            ItemMeta meta = item.getItemMeta();
            if (!meta.hasLore()) return;
            List<String> lore = meta.getLore();
            Player p = e.getPlayer();
            Iterator<String> iterator = lore.listIterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                if (stripColor(line).contains("UUID: ")) {
                    String uuid = stripColor(line).substring(6);
                    if (!p.getUniqueId().toString().equals(uuid)) {
                        p.sendMessage(YELLOW + "You do not own this matrix!");
                        return;
                    }
                    if (p.isSneaking()) { //remove owner
                        iterator.remove();
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        p.sendMessage(GOLD + "Ownership removed!");
                        disableFlight(p);
                    } else if (p.getAllowFlight()) disableFlight(p);
                    else enableFlight(p);
                    return;
                }
            }
            lore.add(GREEN + "UUID: " + p.getUniqueId());
            meta.setLore(lore);
            item.setItemMeta(meta);
            p.sendMessage(GOLD + "Ownership claimed!");
            enableFlight(p);
        };
    }
}