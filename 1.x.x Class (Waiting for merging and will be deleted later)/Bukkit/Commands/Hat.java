package id.universenetwork.utilities.bukkit.Commands;

import id.universenetwork.utilities.bukkit.manager.Config;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.bukkit.Enums.HatCommand.*;

public class Hat extends id.universenetwork.utilities.bukkit.manager.Commands {
    public Hat() {
        super("Hat", "Hat Command Features", "unutilities.command.hat", true);
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (Config.HCBoolean(ENABLED)) {
            Player p = (Player) sender;
            org.bukkit.inventory.PlayerInventory inv = p.getInventory();
            ItemStack held = inv.getItemInMainHand();
            ItemStack helm = inv.getHelmet();
            if (inv.getItemInMainHand().getType().isEmpty()) sender.sendMessage(Config.HCMessage(EMPTY));
            inv.setHelmet(held);
            inv.setItemInMainHand(helm);
            p.updateInventory();
            sender.sendMessage(Config.HCMessage(MESSAGE));
        } else sender.sendMessage(Config.HCMessage(DISABLEDMSG));
    }

    @Override
    public java.util.List<String> TabComplete(CommandSender sender, String str, String[] args) {
        return java.util.Collections.emptyList();
    }
}