package dev._2lstudios.hamsterapi.messengers;

import dev._2lstudios.hamsterapi.utils.BukkitUtils;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

import static id.universenetwork.utilities.bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;

public class BungeeMessenger {
    public void sendPluginMessage(String subChannel, String... args) {
        Player messenger = BukkitUtils.getRandomPlayer();
        if (messenger != null) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF(subChannel);
            for (String arg : args) out.writeUTF(arg);
            messenger.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        } else getLogger().warning(prefix + " §6No player found to send PluginMessage on channel " + subChannel + "!");
    }
}