package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HeadLimiter;

import id.universenetwork.utilities.Bukkit.Manager.Commands;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.ChatColor;
import org.bukkit.block.BlockState;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Settings;
import static java.util.Collections.emptyList;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.check;

public class CountCommand extends Commands {
    final HeadLimiter INSTANCE;
    static final String[] IDS_TO_COUNT = {
            "CARGO_NODE_INPUT",
            "CARGO_NODE_OUTPUT",
            "CARGO_NODE_OUTPUT_ADVANCED",
            "CARGO_NODE",
            "CARGO_MANAGER"
    };

    public CountCommand(HeadLimiter Instance) {
        super("headlimiter", "Basic HeadLimiter command", true, "hl");
        INSTANCE = Instance;
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        final BlockState[] tileEntities = p.getChunk().getTileEntities();
        INSTANCE.executorService.submit(() -> {
            int[] counts = new int[IDS_TO_COUNT.length];
            int total = 0;
            for (BlockState state : tileEntities) {
                final SlimefunItem slimefunItem = check(state.getLocation());
                if (slimefunItem != null) for (int i = 0; i < IDS_TO_COUNT.length; i++)
                    if (slimefunItem.getId().equals(IDS_TO_COUNT[i])) {
                        counts[i]++;
                        total++;
                        break;
                    }
            }
            StringBuilder message = new StringBuilder().append(ChatColor.GOLD).append("Current count: ").append(total).append("/").append(Settings("HeadLimiter").getInt("Amount")).append('\n');
            for (int i = 0; i < IDS_TO_COUNT.length; i++)
                if (counts[i] > 0)
                    message.append("  ").append(ChatColor.GRAY).append(IDS_TO_COUNT[i]).append(": ").append(ChatColor.YELLOW).append(counts[i]).append('\n');
            p.sendMessage(message.toString());
        });
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        return emptyList();
    }
}