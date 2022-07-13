package id.universenetwork.utilities.bukkit.templates;

import cloud.commandframework.annotations.suggestions.Suggestions;
import cloud.commandframework.context.CommandContext;
import id.universenetwork.utilities.bukkit.UNUtilities;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Command {
    @Suggestions("players")
    public List<String> player(CommandContext<CommandSender> sender, String context) {
        List<String> players = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> players.add(player.getName()));
        players.add("*");
        players.add("@a");
        players.add("@all");
        return players.stream().filter(s -> s.startsWith(context)).collect(Collectors.toList());
    }

    @Suggestions("player")
    public List<String> onePlayer(CommandContext<CommandSender> sender, String context) {
        return UNUtilities.getOnlinePlayers(context);
    }

    @Suggestions("toggles")
    public List<String> toggle(CommandContext<CommandSender> sender, String context) {
        return Stream.of("on", "off", "toggle").filter(s -> s.toLowerCase().startsWith(context.toLowerCase())).collect(Collectors.toList());
    }
}