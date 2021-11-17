package id.universenetwork.utilities.Bungee;

import id.universenetwork.utilities.Bungee.Enums.Features.Discord;
import id.universenetwork.utilities.Bungee.Manager.Config;
import id.universenetwork.utilities.Bungee.Manager.Settings;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.javacord.api.DiscordApi;

public class UNUtilities extends net.md_5.bungee.api.plugin.Plugin {
    public static UNUtilities plugin;
    public static String prefix;
    public static Settings settings;
    public static DiscordApi api;
    public static JDA jda;
    public static JDABuilder builder;

    @Override
    public void onLoad() {
        plugin = this;
        settings = new Settings(false);
        prefix = id.universenetwork.utilities.Bungee.Manager.Config.Settings(id.universenetwork.utilities.Bungee.Enums.Settings.PREFIX);
        System.out.println(UNUtilities.prefix + " §aSettings Manager have been prepared");
        System.out.println("\n\n\n" +
                "§b██╗░░░██╗§e███╗░░██╗§9██╗░░░██╗████████╗██╗██╗░░░░░██╗████████╗██╗███████╗░██████╗\n" +
                "§b██║░░░██║§e████╗░██║§9██║░░░██║╚══██╔══╝██║██║░░░░░██║╚══██╔══╝██║██╔════╝██╔════╝\n" +
                "§b██║░░░██║§e██╔██╗██║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║█████╗░░╚█████╗░\n" +
                "§b██║░░░██║§e██║╚████║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║██╔══╝░░░╚═══██╗\n" +
                "§b╚██████╔╝§e██║░╚███║§9╚██████╔╝░░░██║░░░██║███████╗██║░░░██║░░░██║███████╗██████╔╝\n" +
                "§b░╚═════╝░§e╚═╝░░╚══╝§9░╚═════╝░░░░╚═╝░░░╚═╝╚══════╝╚═╝░░░╚═╝░░░╚═╝╚══════╝╚═════╝░\n\n" +
                "§d                     █░░ █▀█ ▄▀█ █▀▄ █ █▄░█ █▀▀ ░ ░ ░\n" +
                "§d                     █▄▄ █▄█ █▀█ █▄▀ █ █░▀█ █▄█ ▄ ▄ ▄\n\n\n");
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        // Plugin startup logic
        jda = JDABuilder.createDefault(Config.DString(Discord.TOKEN)).build().awaitReady();
        new id.universenetwork.utilities.Bungee.Manager.Listeners();
        id.universenetwork.utilities.Bungee.Manager.Commands.Register();
        System.out.println("\n\n\n" +
                "§b██╗░░░██╗§e███╗░░██╗§9██╗░░░██╗████████╗██╗██╗░░░░░██╗████████╗██╗███████╗░██████╗\n" +
                "§b██║░░░██║§e████╗░██║§9██║░░░██║╚══██╔══╝██║██║░░░░░██║╚══██╔══╝██║██╔════╝██╔════╝\n" +
                "§b██║░░░██║§e██╔██╗██║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║█████╗░░╚█████╗░\n" +
                "§b██║░░░██║§e██║╚████║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║██╔══╝░░░╚═══██╗\n" +
                "§b╚██████╔╝§e██║░╚███║§9╚██████╔╝░░░██║░░░██║███████╗██║░░░██║░░░██║███████╗██████╔╝\n" +
                "§b░╚═════╝░§e╚═╝░░╚══╝§9░╚═════╝░░░░╚═╝░░░╚═╝╚══════╝╚═╝░░░╚═╝░░░╚═╝╚══════╝╚═════╝░\n\n" +
                "§a         █░█ ▄▀█ █▀   █▄▄ █▀▀ █▀▀ █▄░█   █▀▀ █▄░█ ▄▀█ █▄▄ █░░ █▀▀ █▀▄\n" +
                "§a         █▀█ █▀█ ▄█   █▄█ ██▄ ██▄ █░▀█   ██▄ █░▀█ █▀█ █▄█ █▄▄ ██▄ █▄▀\n\n\n");
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (jda != null) jda.shutdown();
        if (api != null) {
            api.disconnect();
            api = null;
        }
        plugin = null;
        System.out.println("\n\n\n" +
                "§b██╗░░░██╗§e███╗░░██╗§9██╗░░░██╗████████╗██╗██╗░░░░░██╗████████╗██╗███████╗░██████╗\n" +
                "§b██║░░░██║§e████╗░██║§9██║░░░██║╚══██╔══╝██║██║░░░░░██║╚══██╔══╝██║██╔════╝██╔════╝\n" +
                "§b██║░░░██║§e██╔██╗██║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║█████╗░░╚█████╗░\n" +
                "§b██║░░░██║§e██║╚████║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║██╔══╝░░░╚═══██╗\n" +
                "§b╚██████╔╝§e██║░╚███║§9╚██████╔╝░░░██║░░░██║███████╗██║░░░██║░░░██║███████╗██████╔╝\n" +
                "§b░╚═════╝░§e╚═╝░░╚══╝§9░╚═════╝░░░░╚═╝░░░╚═╝╚══════╝╚═╝░░░╚═╝░░░╚═╝╚══════╝╚═════╝░\n\n" +
                "§c        █░█ ▄▀█ █▀   █▄▄ █▀▀ █▀▀ █▄░█   █▀▄ █ █▀ ▄▀█ █▄▄ █░░ █▀▀ █▀▄\n" +
                "§c        █▀█ █▀█ ▄█   █▄█ ██▄ ██▄ █░▀█   █▄▀ █ ▄█ █▀█ █▄█ █▄▄ ██▄ █▄▀\n\n\n");
    }
}