package id.universenetwork.utilities.bukkit.Commands;

import id.universenetwork.utilities.bukkit.Filters.BookFilter;
import id.universenetwork.utilities.bukkit.manager.Config;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.bukkit.Enums.AntiBookExploit.*;

public class Filter extends id.universenetwork.utilities.bukkit.manager.Commands {
    final BookFilter bookFilter;

    public Filter(BookFilter bookFilter) {
        super("filter", "Anti Book Exploit Command Features", "unutilities.command.filter", true, "bookfilter", "filterbook");
        this.bookFilter = bookFilter;
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (Config.ABEEnabled()) {
            final Player p = (Player) sender;
            final ItemStack b = p.getInventory().getItemInMainHand();
            if (b.getType() != Material.WRITTEN_BOOK && b.getType() != Material.WRITABLE_BOOK) {
                sender.sendMessage(Config.ABEMessage(NO_HOLD_WRITTEN_BOOK));
                return;
            }
            final ItemStack newBook = bookFilter.filterBook(b, p, id.universenetwork.utilities.bukkit.Filters.FilterAction.COMMAND);
            if (newBook != null) {
                p.getInventory().addItem(newBook);
                sender.sendMessage(Config.ABEMessage(SUCCESS));
            } else sender.sendMessage(Config.ABEMessage(NO_ILLEGAL_CONTENT));
        } else sender.sendMessage(Config.ABEMessage(DISABLED_MESSAGE));
    }

    @Override
    public java.util.List<String> TabComplete(CommandSender sender, String str, String[] args) {
        return java.util.Collections.emptyList();
    }
}
