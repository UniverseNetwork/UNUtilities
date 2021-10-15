package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Commands;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects.EffectsManager;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.PotionExpansion;
import id.universenetwork.utilities.Bukkit.Manager.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Enums.Settings.DENYCONSOLE;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.PotionExpansion.enabled;
import static id.universenetwork.utilities.Bukkit.Manager.Config.Settings;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;

public class PotionExpansionCommand implements CommandExecutor {
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (enabled) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Settings(DENYCONSOLE));
                return false;
            }
            Player player = (Player) sender;
            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("showEffects")) {
                    if (EffectsManager.hasAnyEffect(player)) {
                        PotionExpansion.Main.getEffectsTask().showEffects(player, false);
                    } else player.sendMessage(Color.Translator("&cYou don't have any effect!"));
                }
                return true;
            }
        }
        Color.sendTranslate(sender, get().getString(ADDONSSETTINGS.getConfigPath() + "PotionExpansion.disabledMessage"));
        return false;
    }
}