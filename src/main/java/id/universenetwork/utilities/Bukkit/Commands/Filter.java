package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Filters.BookFilter;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Enums.Features.AntiBookExploit.*;
import static id.universenetwork.utilities.Bukkit.Filters.FilterAction.COMMAND;
import static id.universenetwork.utilities.Bukkit.Manager.Config.ABEEnabled;
import static id.universenetwork.utilities.Bukkit.Manager.Config.ABEMessage;

public class Filter extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    final BookFilter bookFilter;

    public Filter(BookFilter bookFilter) {
        super("filter", "unutilities.command.filter", true);
        this.bookFilter = bookFilter;
    }

    @Override
    public void Execute(CommandSender sender, Command command, String[] args) {
        if (ABEEnabled()) {
            final Player p = (Player) sender;
            final ItemStack b = p.getInventory().getItemInMainHand();
            if (b.getType() != Material.WRITTEN_BOOK && b.getType() != Material.WRITABLE_BOOK) {
                sender.sendMessage(ABEMessage(NO_HOLD_WRITTEN_BOOK));
                return;
            }
            final ItemStack newBook = bookFilter.filterBook(b, p, COMMAND);
            if (newBook != null) {
                p.getInventory().addItem(newBook);
                sender.sendMessage(ABEMessage(SUCCESS));
            } else sender.sendMessage(ABEMessage(NO_ILLEGAL_CONTENT));
        } else sender.sendMessage(ABEMessage(DISABLED_MESSAGE));
    }

    @Override
    public java.util.List<String> TabComplete(CommandSender sender, Command command, String str, String[] args) {
        return java.util.Collections.emptyList();
    }
}
