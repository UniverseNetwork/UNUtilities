package id.universenetwork.utilities.Bukkit.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.Enums.Features.VillagerOptimization.DISABLEDMSG;
import static id.universenetwork.utilities.Bukkit.Manager.Config.VOString;
import static id.universenetwork.utilities.Bukkit.UNUtilities.isInVanilla;
import static id.universenetwork.utilities.Bukkit.Utils.Color.sendTranslate;
import static org.bukkit.ChatColor.DARK_GREEN;
import static org.bukkit.ChatColor.GREEN;

public class VLP extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    final boolean Enabled;

    public VLP(boolean Enabled) {
        super("vlp", "Tells you if the chunk you're in is using vanilla mechanics", "avlp.check", true);
        this.Enabled = Enabled;
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (Enabled) {
            if (isInVanilla((Entity) sender)) sender.sendMessage(GREEN + "This chunk is using vanilla mechanics!");
            else sender.sendMessage(DARK_GREEN + "This chunk is using Avl mechanics!");
        } else sendTranslate(sender, VOString(DISABLEDMSG));
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        return java.util.Collections.emptyList();
    }
}
