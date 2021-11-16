package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Manager.Commands;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.longs.LongSet;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.persistence.PersistentDataContainer;

import java.awt.*;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Enums.Features.VillagerOptimization.DISABLEDMSG;
import static id.universenetwork.utilities.Bukkit.Manager.Config.VOString;
import static id.universenetwork.utilities.Bukkit.Tasks.NormalActivityTask.activateVillager;
import static id.universenetwork.utilities.Bukkit.UNUtilities.*;
import static id.universenetwork.utilities.Bukkit.Utils.Color.sendTranslate;
import static java.util.Collections.emptyList;
import static org.bukkit.ChatColor.*;
import static org.bukkit.persistence.PersistentDataType.LONG_ARRAY;

public class AAVLP extends Commands {
    final NamespacedKey Key = new NamespacedKey(plugin, "avlp.activatedChunks");
    final boolean Enabled;

    public AAVLP(boolean Enabled) {
        super("aavlp", "Prevents UNUtilities from modifying villagers in the chunk the player is currently standing in (anti anti villager lag prevention)", "avlp.vanilla", true);
        this.Enabled = Enabled;
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (Enabled) {
            Player player = (Player) sender;
            Chunk chunk = player.getLocation().getChunk();
            Point point = new Point(chunk.getX(), chunk.getZ());
            PersistentDataContainer container = player.getPersistentDataContainer();
            long[] chunks = container.get(Key, LONG_ARRAY);
            LongSet set;
            if (chunks == null) set = new LongOpenHashSet();
            else set = new LongOpenHashSet(chunks);
            long key = from(point);
            if (VANILLA_CHUNKS.contains(point)) { // modified chunk
                if (set.remove(key) || player.hasPermission("avlp.override")) {
                    VANILLA_CHUNKS.remove(point);
                    player.sendMessage(DARK_GREEN + "This chunk has been reverted to AVL mechanics!");
                } else
                    player.sendMessage(RED + "You do not have perms to this chunk to vanilla mechanics, only the original modifier may do this!");
            } else { // unmodified
                if (set.size() < maxChunks) {
                    int count = 0;
                    for (Entity entity : chunk.getEntities()) // get entities in the chunk the player is in
                        if (entity instanceof Villager) { // filter villagers only
                            count++;
                            activateVillager((Villager) entity); // activate all the villagers and prevent them from being
                        }
                    VANILLA_CHUNKS.add(new Point(chunk.getX(), chunk.getZ()));
                    set.add(key);
                    player.sendMessage(GREEN + "Your " + count + " villagers have come back to life!");
                } else {
                    player.sendMessage(RED + "You have exceeded your maximum vanilla chunk count!");
                    player.sendMessage(RED + "vchunks:");
                    for (Long l : set) {
                        Point loc = to(l);
                        player.sendMessage(YELLOW + "\t[" + loc.x + "," + loc.y + "]");
                    }
                }
            }
            container.set(Key, LONG_ARRAY, set.toLongArray());
        } else sendTranslate(sender, VOString(DISABLEDMSG));
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        return emptyList();
    }
}