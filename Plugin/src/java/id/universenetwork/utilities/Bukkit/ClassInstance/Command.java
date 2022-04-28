package id.universenetwork.utilities.Bukkit.ClassInstance;

import cloud.commandframework.annotations.suggestions.Suggestions;
import cloud.commandframework.context.CommandContext;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import lombok.Data;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Consumer;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static id.universenetwork.utilities.Bukkit.Utils.Text.send;
import static java.util.stream.Collectors.toList;
import static org.bukkit.Bukkit.*;

public class Command {
    protected Commands manager = new Commands();
    final String NOPLAYER = UNUtilities.cfg.getString("Settings.player-not-found");
    final String NOSPECIFYPLAYER = UNUtilities.cfg.getString("Settings.no-specify-player");

    protected TargetsCallback getTargets(CommandSender sender, String arg) {
        TargetsCallback callback = new TargetsCallback();
        if (sender instanceof Player) {
            if (arg == null) {
                callback.add((Player) sender);
                return callback;
            }
            switch (arg.toLowerCase()) {
                case "self": {
                    callback.add((Player) sender);
                    return callback;
                }
                case "*":
                case "@a":
                case "@all": {
                    callback.addAll(getOnlinePlayers());
                    return callback;
                }
            }
            Player targetName = getPlayer(arg);
            if (targetName == null) {
                send(sender, NOPLAYER);
                return callback;
            }
            callback.add(targetName);
            return callback;
        }
        if (arg == null) {
            send(sender, NOSPECIFYPLAYER);
            callback.setNotify(true);
            return callback;
        }
        switch (arg.toLowerCase()) {
            case "*":
            case "@a":
            case "@all": {
                callback.addAll(getOnlinePlayers());
                return callback;
            }
        }
        Player targetName = getPlayer(arg);
        if (targetName == null) {
            send(sender, NOPLAYER);
            return callback;
        }
        callback.add(targetName);
        return callback;
    }

    protected OfflineTargetsCallback getOfflineTargets(CommandSender sender, String arg) {
        OfflineTargetsCallback callback = new OfflineTargetsCallback();
        if (sender instanceof Player) {
            if (arg == null) {
                callback.add((Player) sender);
                return callback;
            }
            switch (arg.toLowerCase()) {
                case "self": {
                    callback.add((Player) sender);
                    return callback;
                }
                case "*":
                case "@a":
                case "@all": {
                    callback.addAll(getOnlinePlayers());
                    return callback;
                }
            }
            OfflinePlayer targetName = getOfflinePlayer(arg);
            if (targetName == null) {
                send(sender, NOPLAYER);
                return callback;
            }
            callback.add(targetName);
            return callback;
        }
        if (arg == null) {
            send(sender, NOSPECIFYPLAYER);
            callback.setNotify(true);
            return callback;
        }
        switch (arg.toLowerCase()) {
            case "*":
            case "@a":
            case "@all": {
                callback.addAll(getOnlinePlayers());
                return callback;
            }
        }
        OfflinePlayer targetName = getOfflinePlayer(arg);
        if (targetName == null) {
            send(sender, NOPLAYER);
            return callback;
        }
        callback.add(targetName);
        return callback;
    }

    @Suggestions("players")
    public List<String> player(CommandContext<CommandSender> sender, String context) {
        List<String> players = new ArrayList<>();
        getOnlinePlayers().forEach(player -> players.add(player.getName()));
        players.add("*");
        players.add("@a");
        players.add("@all");
        return players.stream().filter(s -> s.startsWith(context)).collect(toList());
    }

    @Suggestions("onePlayers")
    public List<String> onePlayer(CommandContext<CommandSender> sender, String context) {
        return UNUtilities.getOnlinePlayers(context);
    }

    @Suggestions("toggles")
    public List<String> toggle(CommandContext<CommandSender> sender, String context) {
        return Stream.of("on", "off", "toggle").filter(s -> s.toLowerCase().startsWith(context.toLowerCase())).collect(toList());
    }

    @Data
    protected static class TargetsCallback {
        boolean notify = false;
        Set<Player> targets = new HashSet<>();

        public void add(Player player) {
            targets.add(player);
        }

        public void addAll(Collection<? extends Player> players) {
            targets.addAll(players);
        }

        public int size() {
            return targets.size();
        }

        public boolean isEmpty() {
            return targets.isEmpty();
        }

        public boolean notifyIfEmpty() {
            return isEmpty() && !isNotify();
        }

        public boolean doesNotContain(Player player) {
            return !targets.contains(player);
        }

        public Stream<Player> stream() {
            return StreamSupport.stream(Spliterators.spliterator(targets, 0), false);
        }

        public void forEach(Consumer<? super Player> action) {
            for (Player target : targets) action.accept(target);
        }
    }

    @Data
    protected static class OfflineTargetsCallback {
        private boolean notify = false;
        private Set<OfflinePlayer> targets = new HashSet<>();

        public void add(OfflinePlayer player) {
            targets.add(player);
        }

        public void addAll(Collection<? extends OfflinePlayer> players) {
            targets.addAll(players);
        }

        public int size() {
            return targets.size();
        }

        public boolean isEmpty() {
            return targets.isEmpty();
        }

        public boolean notifyIfEmpty() {
            return isEmpty() && !isNotify();
        }

        public boolean doesNotContain(Player player) {
            return !targets.contains(player);
        }

        public Stream<OfflinePlayer> stream() {
            return StreamSupport.stream(Spliterators.spliterator(targets, 0), false);
        }

        public void forEach(Consumer<? super OfflinePlayer> action) {
            for (OfflinePlayer target : targets) action.accept(target);
        }
    }
}