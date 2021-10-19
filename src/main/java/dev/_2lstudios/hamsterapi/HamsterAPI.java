package dev._2lstudios.hamsterapi;

import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayer;
import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayerManager;
import dev._2lstudios.hamsterapi.listeners.PlayerJoinListener;
import dev._2lstudios.hamsterapi.listeners.PlayerQuitListener;
import dev._2lstudios.hamsterapi.messengers.BungeeMessenger;
import dev._2lstudios.hamsterapi.utils.BufferIO;
import dev._2lstudios.hamsterapi.utils.Reflection;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getServer;

public class HamsterAPI {
    static HamsterAPI instance;
    Reflection reflection;
    BufferIO bufferIO;
    BungeeMessenger bungeeMessenger;
    HamsterPlayerManager hamsterPlayerManager;

    static synchronized void setInstance(final HamsterAPI hamsterAPI) {
        HamsterAPI.instance = hamsterAPI;
    }

    public static synchronized HamsterAPI getInstance() {
        return instance;
    }

    static String getVersion(Server server) {
        final String packageName = server.getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    void initialize() {
        final Server server = getServer();
        final Properties properties = getProperties();
        final String bukkitVersion = getVersion(server).replaceAll("[^0-9]", "");
        final int compressionThreshold = (int) properties.getOrDefault("network_compression_threshold", 256);
        setInstance(this);
        this.reflection = new Reflection(server.getClass().getPackage().getName().split("\\.")[3]);
        this.bufferIO = new BufferIO(this.reflection, bukkitVersion, compressionThreshold);
        this.hamsterPlayerManager = new HamsterPlayerManager();
        this.bungeeMessenger = new BungeeMessenger();
    }

    Properties getProperties() {
        final File propertiesFile = new File("./server.properties");
        final Properties properties = new Properties();
        try (final InputStream inputStream = new FileInputStream(propertiesFile)) {
            properties.load(inputStream);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void onEnable() {
        final Server server = getServer();
        final PluginManager pluginManager = server.getPluginManager();
        initialize();
        server.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        pluginManager.registerEvents(new PlayerJoinListener(this), plugin);
        pluginManager.registerEvents(new PlayerQuitListener(hamsterPlayerManager), plugin);
        for (final Player player : getOnlinePlayers()) {
            final HamsterPlayer hamsterPlayer = this.hamsterPlayerManager.add(player);
            hamsterPlayer.tryInject();
        }
    }

    public void onDisable() {
        for (final Player player : getOnlinePlayers()) {
            try {
                final HamsterPlayer hamsterPlayer = this.hamsterPlayerManager.get(player);
                if (hamsterPlayer != null) hamsterPlayer.uninject();
                this.hamsterPlayerManager.remove(player);
            } catch (NullPointerException ignored) {
            }
        }
    }

    public BufferIO getBufferIO() {
        return this.bufferIO;
    }

    public BungeeMessenger getBungeeMessenger() {
        return this.bungeeMessenger;
    }

    public HamsterPlayerManager getHamsterPlayerManager() {
        return this.hamsterPlayerManager;
    }

    public Reflection getReflection() {
        return this.reflection;
    }
}
