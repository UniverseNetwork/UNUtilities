package id.universenetwork.utilities.bukkit.Commands;

import id.universenetwork.utilities.bukkit.manager.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.Collections;
import java.util.List;

import static id.universenetwork.utilities.bukkit.Enums.VillagerOptimization.DISABLEDMSG;
import static id.universenetwork.utilities.bukkit.manager.Config.VOString;
import static id.universenetwork.utilities.bukkit.UNUtilities.isInVanilla;
import static id.universenetwork.utilities.bukkit.utils.Color.sendTranslated;
import static org.bukkit.ChatColor.DARK_GREEN;
import static org.bukkit.ChatColor.GREEN;

public class VLP extends Commands {
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
        } else sendTranslated(sender, VOString(DISABLEDMSG));
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        return Collections.emptyList();
    }
}
