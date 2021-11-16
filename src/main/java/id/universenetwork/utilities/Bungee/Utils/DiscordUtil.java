package id.universenetwork.utilities.Bungee.Utils;

import id.universenetwork.utilities.Bungee.UNUtilities;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;

public class DiscordUtil {

    public static TextChannel getTextChannel(String name) {
        return null;
    }

    // get text channel id
    public static TextChannel getTextChannelId(long id) {
        DiscordApi api = UNUtilities.api;
        if (api != null) {
            api.getChannelById(id).isPresent();
            api.getChannelById(id).get();
        }
        return (TextChannel) (api != null ? api.getChannelById(id).get() : null);
    }

}
