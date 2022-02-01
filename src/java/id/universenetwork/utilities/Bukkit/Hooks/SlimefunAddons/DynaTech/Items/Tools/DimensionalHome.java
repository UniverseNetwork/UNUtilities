package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import id.universenetwork.utilities.Bukkit.Manager.Data;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib.teleportAsync;

/// THIS ABSOLUTELY NEEDS TO BE REDONE
public class DimensionalHome extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    static final NamespacedKey CHUNK_KEY = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "chunk-key");
    static final org.bukkit.World DIM_HOME_WORLD = org.bukkit.Bukkit.getServer().getWorld("dimensionalhome");
    int ID = Data.get().getInt("current-chunk-highest-id");

    public DimensionalHome(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onRightClick());
    }

    public io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler onRightClick() {
        return e -> {
            e.cancel();
            org.bukkit.entity.Player p = e.getPlayer();
            ItemStack item = e.getItem();
            int chunkKey = PersistentDataAPI.getInt(item.getItemMeta(), CHUNK_KEY);
            if (io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar(item, id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems.DIMENSIONAL_HOME, true)) {
                if (chunkKey > 0) {
                    if (p.getLocation().getWorld() != DIM_HOME_WORLD) {
                        Location dimHomeLocation = new Location(DIM_HOME_WORLD, 16 * chunkKey + 8d, 65, 8);
                        teleportAsync(p, dimHomeLocation);
                    } else {
                        if (p.getBedSpawnLocation() != null) teleportAsync(p, p.getBedSpawnLocation());
                        else teleportAsync(p, org.bukkit.Bukkit.getWorlds().get(0).getSpawnLocation());
                    }
                } else updateLore(item); // Setup ChunkKey
            }
        };
    }

    void updateLore(@NotNull ItemStack item) {
        org.bukkit.inventory.meta.ItemMeta im = item.getItemMeta();
        java.util.List<String> lore = im.getLore();
        for (int line = 0; line < lore.size(); line++)
            if (lore.get(line).contains("CHUNK ID: <id>")) {
                ID++;
                lore.set(line, lore.get(line).replace("<id>", String.valueOf(ID)));
                PersistentDataAPI.setInt(im, CHUNK_KEY, ID);
                Data.set("current-chunk-highest-id", ID);
            }
        im.setLore(lore);
        item.setItemMeta(im);
    }
}