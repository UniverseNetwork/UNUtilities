package id.universenetwork.utilities.Bukkit.Manager;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Sender {
    public static void send(CommandSender cmdSender, String string) {
        if (cmdSender instanceof Player) {
            cmdSender.sendMessage(Color.Translator(string));
        } else if (cmdSender instanceof ConsoleCommandSender) {
            System.out.println(Color.Translator(string));
        }
    }
}
