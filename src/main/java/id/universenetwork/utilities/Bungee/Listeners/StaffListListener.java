package id.universenetwork.utilities.Bungee.Listeners;

import id.universenetwork.utilities.Bungee.Enums.Features.Discord;
import id.universenetwork.utilities.Bungee.Enums.Features.StaffList;
import id.universenetwork.utilities.Bungee.Manager.Config;
import id.universenetwork.utilities.Bungee.Utils.DiscordUtil;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.event.EventHandler;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static id.universenetwork.utilities.Bungee.UNUtilities.jda;
import static net.md_5.bungee.api.ProxyServer.getInstance;

public class StaffListListener implements net.md_5.bungee.api.plugin.Listener {

    TextChannel channel = jda.getTextChannelById(Config.DLong(Discord.ESC));
    net.dv8tion.jda.api.EmbedBuilder eb = new net.dv8tion.jda.api.EmbedBuilder();

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
                    eb.setTitle(Config.DString(Discord.JETLT));
                    eb.setDescription(String.valueOf(desList));
                    eb.setColor(Config.DString(Discord.JEC).equalsIgnoreCase("#FFFFFF") ? Color.WHITE : Color.decode(Config.DString(Discord.JEC)));
                    channel.sendMessage(eb.build()).queue();
                    DiscordUtil.sendEmbedMessage(Config.DString(Discord.JETLT),
                            desList,
                            String.valueOf(Config.DString(Discord.JEC).equalsIgnoreCase("#FFFFFF") ? Color.WHITE : Color.decode(Config.DString(Discord.JEC))),
                            channel);
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
                    eb.setTitle(Config.DString(Discord.LETLT));
                    eb.setDescription(String.valueOf(desList));
                    eb.setColor(Config.DString(Discord.LEC).equalsIgnoreCase("#FFFFFF") ? Color.WHITE : Color.decode(Config.DString(Discord.LEC)));
                    channel.sendMessage(eb.build()).queue();
                    DiscordUtil.sendEmbedMessage(Config.DString(Discord.LETLT),
                            desList,
                            String.valueOf(Config.DString(Discord.LEC).equalsIgnoreCase("#FFFFFF") ? Color.WHITE : Color.decode(Config.DString(Discord.LEC))),
                            channel);
                }
    }
}