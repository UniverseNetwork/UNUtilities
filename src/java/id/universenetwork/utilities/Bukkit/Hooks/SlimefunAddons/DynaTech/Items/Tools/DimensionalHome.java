package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib.teleportAsync;

public class DimensionalHome extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    final NamespacedKey chunkId = new NamespacedKey(plugin, "chunk-id");
    int id = 1;
    boolean idSet = false;
    final World dimHomeWorld = Bukkit.getWorld("dimensionalhome");

    public DimensionalHome(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onRightClick());
    }

    public io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler onRightClick() {
        return e -> {
            Player p = e.getPlayer();
            Location playerPrevLocation = p.getLocation();
            if (e.getPlayer().getWorld() != dimHomeWorld && idSet) {
                if (doesntContainNewChunkID(e.getItem())) idSet = false;
                teleportAsync(p, new Location(dimHomeWorld, 16 * PersistentDataAPI.getInt(e.getItem().getItemMeta(), chunkId) + 8, 65, 8));
            } else if (idSet) {
                if (doesntContainNewChunkID(e.getItem())) idSet = false;
                teleportAsync(p, p.getWorld() != this.dimHomeWorld ? playerPrevLocation : p.getBedSpawnLocation() != null ? p.getBedSpawnLocation() : Bukkit.getWorlds().get(0).getSpawnLocation());
            } else updateLore(e.getItem());
            e.cancel();
        };
    }

    protected boolean doesntContainNewChunkID(@NotNull ItemStack item) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();
        for (String s : lore) if (s.contains("CHUNK ID: <id>")) return true;
        return false;
    }

    void updateLore(@NotNull ItemStack item) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();
        for (int line = 0; line < lore.size(); line++)
            if (lore.get(line).contains("CHUNK ID: <id>")) {
                id++;
                lore.set(line, lore.get(line).replace("<id>", String.valueOf(id)));
                PersistentDataAPI.setInt(im, chunkId, id);
                idSet = true;
            }
        im.setLore(lore);
        item.setItemMeta(im);
    }
}