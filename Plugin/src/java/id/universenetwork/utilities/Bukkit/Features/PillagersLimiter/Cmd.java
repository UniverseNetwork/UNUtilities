package id.universenetwork.utilities.Bukkit.Features.PillagersLimiter;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.suggestions.Suggestions;
import cloud.commandframework.context.CommandContext;
import id.universenetwork.utilities.Bukkit.Events.ReloadConfigEvent;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.Templates.Command;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import id.universenetwork.utilities.Bukkit.Utils.Text;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cmd extends Command implements Listener {
    private ConfigurationSection m;
    private final String p;

    public Cmd(String s) {
        Commands.register(this);
        m = UNUtilities.cfg.getConfigurationSection(s);
        p = s;
    }

    @CommandMethod("pillagerslimiter [value]")
    @CommandPermission("unutilities.command.pillagerslimiter")
    @CommandDescription("Main command of Pillagers Limiter feature")
    public void main(CommandSender s, @Argument(value = "value", suggestions = "args") String a) {
        ConfigurationSection c = m.getConfigurationSection("Messages");
        if (m.getBoolean("enabled")) {
            if (a == null) {
                Text.send(s, c.getString("Usage"));
                return;
            }
            int p = 0;
            if (a.equalsIgnoreCase("count")) {
                for (World w : Bukkit.getWorlds())
                    p += w.getEntitiesByClass(Pillager.class).size();
                Text.send(s, StringUtils.replace(c.getString("Count"),
                        "%amount%", Integer.toString(p)));
            } else if (a.equalsIgnoreCase("remove")) {
                for (World w : Bukkit.getWorlds())
                    for (Entity e : w.getEntitiesByClass(Pillager.class)) {
                        ++p;
                        e.remove();
                    }
                Text.send(s, StringUtils.replace(c.getString("Remove"),
                        "%amount%", Integer.toString(p)));
            } else Text.send(s, c.getString("Usage"));
        } else Text.send(s, c.getString("Disabled"));
    }

    @Suggestions("args")
    public List<String> args(CommandContext<CommandSender> cc, String c) {
        return Stream.of("count", "remove").filter(s -> s.toLowerCase()
                .startsWith(c.toLowerCase())).collect(Collectors.toList());
    }

    @EventHandler
    public void reload(ReloadConfigEvent e) {
        m = UNUtilities.cfg.getConfigurationSection(p);
    }
}