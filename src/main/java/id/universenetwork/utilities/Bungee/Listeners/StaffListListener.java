package id.universenetwork.utilities.Bungee.Listeners;

import id.universenetwork.utilities.Bungee.Enums.Features.Discord;
import id.universenetwork.utilities.Bungee.Enums.Features.StaffList;
import id.universenetwork.utilities.Bungee.Manager.Config;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.event.EventHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static id.universenetwork.utilities.Bungee.UNUtilities.api;
import static net.md_5.bungee.api.ProxyServer.getInstance;

public class StaffListListener implements net.md_5.bungee.api.plugin.Listener {

    private final TextChannel channel = (TextChannel) api.getChannelById(Config.DLong(Discord.ESC)).get();

    @EventHandler
    public void onJoin(net.md_5.bungee.api.event.PostLoginEvent e) {
        if (Config.SLBoolean(StaffList.ENABLED) && e.getPlayer().hasPermission("unutilities.command.stafflist"))
            for (ProxiedPlayer p : getInstance().getPlayers())
                if (p.hasPermission("unutilities.command.stafflist")) {
                    p.sendMessage(Config.SLString(StaffList.JM).replaceAll("%player%", e.getPlayer().getName()));
                    List<String> desList = Config.DList(Discord.JED).stream()
                            .map(s -> s.replaceAll("%player%", e.getPlayer().getName())
                                    .replaceAll("%ip%", e.getPlayer().getSocketAddress().toString())
                                    .replaceAll("&", "§")).collect(Collectors.toList());
                    new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle(Config.DString(Discord.JETLT))
                                    .setDescription(String.valueOf(desList))
                                    .setColor(Config.DString(Discord.JEC).equalsIgnoreCase("#FFFFFF") ? Color.WHITE : Color.decode(Config.DString(Discord.JEC))))
                            .send(channel);
                }
    }

    @EventHandler
    public void onLeave(net.md_5.bungee.api.event.PlayerDisconnectEvent e) {
        if (Config.SLBoolean(StaffList.ENABLED) && e.getPlayer().hasPermission("unutilities.command.stafflist"))
            for (ProxiedPlayer p : getInstance().getPlayers())
                if (p.hasPermission("unutilities.command.stafflist")) {
                    p.sendMessage(Config.SLString(StaffList.LM).replaceAll("%player%", e.getPlayer().getName()));
                    List<String> desList = Config.DList(Discord.LED).stream()
                            .map(s -> s.replaceAll("%player%", e.getPlayer().getName())
                                    .replaceAll("%ip%", e.getPlayer().getSocketAddress().toString())
                                    .replaceAll("&", "§")).collect(Collectors.toList());
                    new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle(Config.DString(Discord.LETLT))
                                    .setDescription(String.valueOf(desList))
                                    .setColor(Config.DString(Discord.LEC).equalsIgnoreCase("#FFFFFF") ? Color.WHITE : Color.decode(Config.DString(Discord.LEC))))
                            .send(channel);
                }
    }
}