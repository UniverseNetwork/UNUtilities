package id.universenetwork.utilities.Bukkit;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import id.universenetwork.utilities.Bukkit.Templates.Command;
import id.universenetwork.utilities.Bukkit.Utils.Text;
import id.universenetwork.utilities.Universal.Utils.GitHubLatestCommit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class MainCommand extends Command {
    @CommandMethod("universeutilities|unutilities|unu|uu")
    @CommandDescription("Main command of UNUtilities")
    public void cmd(CommandSender sender) {
        Text.sendCentered(sender, "&b");
        Text.sendCentered(sender, "&bU&eN&9Utilities &6v" + UNUtilities.plugin.getDescription().getVersion());
        Text.sendCentered(sender, "&dMade By &bARVIN&a3108 &cI&fD");
        Text.sendCentered(sender, "&b");
    }

    @CommandMethod("universeutilities|unutilities|unu|uu reload|rl")
    @CommandPermission("unutilities.command.reload")
    public void cmdReload(CommandSender sender) {
        UNUtilities.reloadCfg();
        if (sender instanceof Player) Text.send(sender, UNUtilities.cfg.getString("Settings.reload"));
    }

    @CommandMethod("universeutilities|unutilities|unu|uu checkupdate|update")
    @CommandPermission("unutilities.command.checkupdate")
    public void cmdUpdate(CommandSender sender) {
        try {
            if (GitHubLatestCommit.check("UniverseNetwork", "UNUtilities")
                    .equalsIgnoreCase(UNUtilities.commit))
                Text.send(sender, UNUtilities.cfg.getString("Settings.update-msg.no-update"));
            else Text.send(sender, UNUtilities.cfg.getString("Settings.update-msg.update-found"));
        } catch (IOException e) {
            Text.send(sender, UNUtilities.cfg.getString("Settings.update-msg.error"));
        }
    }
}