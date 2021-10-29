package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HeadLimiter;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Settings;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.check;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.clearBlockInfo;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.ChatColor.RED;

public final class HeadLimiter implements org.bukkit.event.Listener {
    final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("HeadLimiter-pool-%d").build();
    final ExecutorService executorService = Executors.newFixedThreadPool(Settings("HeadLimiter").getInt("Thread-Pool-Size", 4), threadFactory);

    public HeadLimiter() {
        if (Enabled("HeadLimiter")) {
            registerListener(this);
            new CountCommand(this);
            System.out.println(prefix + " §bSuccessfully Registered §dHeadLimiter §bAddon");
        }
    }

    boolean isCargo(SlimefunItem sfItem) {
        return sfItem.isItem(SlimefunItems.CARGO_INPUT_NODE) || sfItem.isItem(SlimefunItems.CARGO_OUTPUT_NODE) || sfItem.isItem(SlimefunItems.CARGO_OUTPUT_NODE_2) || sfItem.isItem(SlimefunItems.CARGO_CONNECTOR_NODE) || sfItem.isItem(SlimefunItems.CARGO_MANAGER);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlace(BlockPlaceEvent e) {
        final SlimefunItem sfItem = SlimefunItem.getByItem(e.getItemInHand());
        if (!e.isCancelled() && (e.getBlock().getType().equals(Material.PLAYER_HEAD) || e.getBlock().getType().equals(Material.PLAYER_WALL_HEAD)) && sfItem != null && isCargo(sfItem)) {
            final Block block = e.getBlock();
            final BlockState[] te = block.getChunk().getTileEntities();
            executorService.submit(() -> {
                int i = 0;
                for (BlockState bs : te) {
                    final SlimefunItem slimefunItem = check(bs.getLocation());
                    if (slimefunItem != null && isCargo(slimefunItem)) i++;
                }
                final int threshold = Settings("HeadLimiter").getInt("Amount");
                if (i > threshold) {
                    getScheduler().runTask(plugin, () -> {
                        if (!block.getType().equals(Material.AIR)) {
                            block.setType(Material.AIR);
                            if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
                                block.getWorld().dropItemNaturally(block.getLocation(), sfItem.getItem());
                        }
                    });
                    clearBlockInfo(block.getLocation());
                    e.getPlayer().sendMessage(RED + "You hit the limit of Cargo nodes in this chunk");
                }
            });
        }
    }
}