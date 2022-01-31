package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects.EffectsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class DrinkMilkListener implements org.bukkit.event.Listener {
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        if (e.isCancelled()) return;
        Player player = e.getPlayer();
        if (e.getItem().getType().equals(Material.MILK_BUCKET))
            if (EffectsManager.hasAnyEffect(player)) EffectsManager.removePlayer(player.getUniqueId());
    }
}