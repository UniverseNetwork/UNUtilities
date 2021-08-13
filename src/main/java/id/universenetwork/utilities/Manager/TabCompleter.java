package id.universenetwork.utilities.Manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(sender.hasPermission("unutilities.command")){
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
