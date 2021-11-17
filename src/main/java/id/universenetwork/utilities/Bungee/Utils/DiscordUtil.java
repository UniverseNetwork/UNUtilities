package id.universenetwork.utilities.Bungee.Utils;

import id.universenetwork.utilities.Bungee.Enums.Features.Discord;
import id.universenetwork.utilities.Bungee.Manager.Config;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.awt.Color;
import java.util.List;

import static id.universenetwork.utilities.Bungee.UNUtilities.jda;

public class DiscordUtil {

    public static void sendEmbedMessage(String title, List<String> description, String color, TextChannel channelId) {
        net.dv8tion.jda.api.EmbedBuilder eb = new net.dv8tion.jda.api.EmbedBuilder();
        TextChannel channel = jda.getTextChannelById(String.valueOf(channelId));
        if (channel != null) {
            eb.setTitle(title);
            eb.setDescription(description.toString());
            eb.setColor(Color.decode(color));
            channel.sendMessage(eb.build()).queue();
        }
    }

}
