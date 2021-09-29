package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.PotionExpansion.enabled;

public class PotionExpansionTab implements TabCompleter {
    List<String> subcommands;

    public PotionExpansionTab() {
        subcommands = new ArrayList<>();
        subcommands.add("showEffects");
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (enabled) {
            if (args.length == 1) {
                if (args[0].length() == 0) return subcommands;
                String input = args[0].toLowerCase(Locale.ROOT);
                List<String> returnList = new LinkedList<>();
                for (String item : subcommands)
                    if (item.toLowerCase(Locale.ROOT).contains(input)) {
                        returnList.add(item);
                    } else if (item.equalsIgnoreCase(input)) return Collections.emptyList();
                return returnList;
            }
        }
        return Collections.emptyList();
    }
}