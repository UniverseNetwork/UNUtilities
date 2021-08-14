package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Enums.Features.HatCommand;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import id.universenetwork.utilities.Bukkit.Manager.Sender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Hat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("unutilities.command.hat")) {
            if (Config.HCSettings(HatCommand.ENABLED)) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    PlayerInventory inv = p.getInventory();
                    ItemStack held = inv.getItemInMainHand();
                    ItemStack helm = inv.getHelmet();
                    inv.setHelmet(held);
                    inv.setItemInMainHand(helm);
                    p.updateInventory();
                    sender.sendMessage(Config.HCMessage(HatCommand.MESSAGE));
                } else System.out.println(Config.HCMessage(HatCommand.CONSOLE));
            } else {
                Sender.send(sender, Config.HCMessage(HatCommand.DISABLEDMSG));
                return false;
            }
        } else {
            sender.sendMessage(Config.Settings(Settings.NOPERMISSION));
            return false;
        }
        return true;
    }
}
