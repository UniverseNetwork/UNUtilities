package id.universenetwork.utilities.Bukkit;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import id.universenetwork.utilities.Bukkit.Utils.Text;
import org.bukkit.command.CommandSender;

import static id.universenetwork.utilities.Bukkit.UNUtilities.*;

public class MainCommand extends id.universenetwork.utilities.Bukkit.ClassInstance.Command {
    @CommandMethod("universeutilities|unutilities|unu|uu")
    @CommandDescription("Main command of UNUtilities")
    public void cmd(CommandSender sender) {
        Text.sendCentered(sender, "&b");
        Text.sendCentered(sender, "&bU&eN&9Utilities &6v" + plugin.getDescription().getVersion());
        Text.sendCentered(sender, "&dMade By &bARVIN&a3108 &cI&fD");
        Text.sendCentered(sender, "&b");
    }

    @CommandMethod("universeutilities|unutilities|unu|uu reload|rl")
    @CommandPermission("unutilities.command.reload")
    public void cmdReload(CommandSender sender) {
        reloadCfg();
        if (sender instanceof org.bukkit.entity.Player)
            Text.send(sender, cfg.getString("Settings.reload"));
    }
}