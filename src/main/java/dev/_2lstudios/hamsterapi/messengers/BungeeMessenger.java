package dev._2lstudios.hamsterapi.messengers;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

import static dev._2lstudios.hamsterapi.utils.BukkitUtils.getRandomPlayer;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;

public class BungeeMessenger {
    public void sendPluginMessage(final String subChannel, final String... args) {
        final Player messenger = getRandomPlayer();
        if (messenger != null) {
            final ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF(subChannel);
            for (final String arg : args) out.writeUTF(arg);
            messenger.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        } else getLogger().warning(prefix + " §6No player found to send PluginMessage on channel " + subChannel + "!");
    }
}