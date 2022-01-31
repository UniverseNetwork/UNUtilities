package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Commands;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects.EffectsManager;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.PotionExpansion;
import id.universenetwork.utilities.Bukkit.Utils.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

import static id.universenetwork.utilities.Bukkit.Enums.SlimefunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;

public class PotionExpansionCommand extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    public PotionExpansionCommand() {
        super("potionexpansion", "PotionExpansion Slimefun Addons Command", true, "pe");
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length != 0) if (args[0].equalsIgnoreCase("showEffects")) {
            if (EffectsManager.hasAnyEffect(player)) PotionExpansion.Main.getEffectsTask().showEffects(player, false);
            else player.sendMessage(Color.Translate("&cYou don't have any effect!"));
        }
        Color.sendTranslated(sender, get().getString(ADDONSSETTINGS.getConfigPath() + "PotionExpansion.disabledMessage"));
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        List<String> subcommands = new ArrayList<>();
        subcommands.add("showEffects");
        if (args.length == 1) {
            if (args[0].length() == 0) return subcommands;
            String input = args[0].toLowerCase(Locale.ROOT);
            List<String> returnList = new LinkedList<>();
            for (String item : subcommands)
                if (item.toLowerCase(Locale.ROOT).contains(input)) returnList.add(item);
                else if (item.equalsIgnoreCase(input)) return Collections.emptyList();
            return returnList;
        }
        return Collections.emptyList();
    }
}