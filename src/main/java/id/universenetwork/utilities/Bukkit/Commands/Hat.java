package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Enums.Features.HatCommand;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

import static java.util.Collections.emptyList;

public class Hat extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    public Hat() {
        super("Hat", "Hat Command Features", "unutilities.command.hat", true);
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (Config.HCBoolean(HatCommand.ENABLED)) {
            Player p = (Player) sender;
            PlayerInventory inv = p.getInventory();
            ItemStack held = inv.getItemInMainHand();
            ItemStack helm = inv.getHelmet();
            if (inv.getItemInMainHand().getType().isEmpty()) {
                sender.sendMessage(Config.HCMessage(HatCommand.EMPTY));
            }
            inv.setHelmet(held);
            inv.setItemInMainHand(helm);
            p.updateInventory();
            sender.sendMessage(Config.HCMessage(HatCommand.MESSAGE));
        } else sender.sendMessage(Config.HCMessage(HatCommand.DISABLEDMSG));
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        return emptyList();
    }
}
