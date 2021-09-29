package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SlimyTreeTaps;

import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

class MagicalMirrorListener implements Listener {
    final MagicalMirror mirror;

    public MagicalMirrorListener(MagicalMirror mirror) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.mirror = mirror;
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof ItemFrame) {
            ItemFrame frame = (ItemFrame) e.getRightClicked();
            ItemStack item = frame.getItem();
            if (mirror.isItem(item)) {
                e.setCancelled(true);
                if (mirror.canUse(e.getPlayer(), true)) mirror.teleport(e.getPlayer(), item);
            }
        }
    }
}