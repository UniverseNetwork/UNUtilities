package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

import java.util.List;

import static org.bukkit.Bukkit.getPluginManager;

public class TabCompletion implements TabCompleter {
    public TabCompletion(PluginCommand toAttach) {
        toAttach.setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        AttachedTabCompleteEvent event = new AttachedTabCompleteEvent(sender, command, args);
        getPluginManager().callEvent(event);
        if (event.isCancelled()) return null;
        return event.getResult();
    }
}