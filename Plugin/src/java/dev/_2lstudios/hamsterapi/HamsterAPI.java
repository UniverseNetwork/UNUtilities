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
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.Utils.Logger.info;
import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getServer;

public class HamsterAPI {
    static HamsterAPI instance;
    Reflection reflection;
    BufferIO bufferIO;
    BungeeMessenger bungeeMessenger;
    HamsterPlayerManager hamsterPlayerManager;

    static synchronized void setInstance(HamsterAPI hamsterAPI) {
        HamsterAPI.instance = hamsterAPI;
    }

    public static synchronized HamsterAPI getInstance() {
        return instance;
    }

    static String getVersion(Server server) {
        String packageName = server.getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    void initialize() {
        Server server = getServer();
        Properties properties = getProperties();
        String bukkitVersion = getVersion(server).replaceAll("[^0-9]", "");
        int compressionThreshold = (int) properties.getOrDefault("network_compression_threshold", 256);
        setInstance(this);
        reflection = new Reflection(server.getClass().getPackage().getName().split("\\.")[3]);
        bufferIO = new BufferIO(reflection, bukkitVersion, compressionThreshold);
        hamsterPlayerManager = new HamsterPlayerManager();
        bungeeMessenger = new BungeeMessenger();
    }

    Properties getProperties() {
        File propertiesFile = new File("./server.properties");
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(propertiesFile.toPath())) {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void onEnable() {
        Server server = getServer();
        PluginManager pluginManager = server.getPluginManager();
        initialize();
        server.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        pluginManager.registerEvents(new PlayerJoinListener(this), plugin);
        pluginManager.registerEvents(new PlayerQuitListener(hamsterPlayerManager), plugin);
        for (Player player : getOnlinePlayers()) {
            HamsterPlayer hamsterPlayer = hamsterPlayerManager.add(player);
            hamsterPlayer.tryInject();
        }
        info("&bSuccessfully initialized &eHamsterAPI");
    }

    public void onDisable() {
        for (Player player : getOnlinePlayers())
            try {
                HamsterPlayer hamsterPlayer = hamsterPlayerManager.get(player);
                if (hamsterPlayer != null) hamsterPlayer.uninject();
                hamsterPlayerManager.remove(player);
            } catch (NullPointerException ignore) {
            }
        info("&cSuccessfully declared &eHamsterAPI");
    }

    public BufferIO getBufferIO() {
        return bufferIO;
    }

    public BungeeMessenger getBungeeMessenger() {
        return bungeeMessenger;
    }

    public HamsterPlayerManager getHamsterPlayerManager() {
        return hamsterPlayerManager;
    }

    public Reflection getReflection() {
        return reflection;
    }
}