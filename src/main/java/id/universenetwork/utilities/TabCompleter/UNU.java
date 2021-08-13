package id.universenetwork.utilities.TabCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class UNU implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender.hasPermission("unutilities.command")) {
            if (args.length == 1) {
                List<String> arguments = new ArrayList<>();
                arguments.add("reload");
                arguments.add("rl");
                return arguments;
            }
        }

        return null;
    }
}