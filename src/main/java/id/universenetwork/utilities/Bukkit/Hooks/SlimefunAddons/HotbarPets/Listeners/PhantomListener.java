package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;
import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById;
import static org.bukkit.entity.EntityType.PHANTOM;

public class PhantomListener implements org.bukkit.event.Listener {
    final HotbarPet panda;

    public PhantomListener() {
        panda = (HotbarPet) getById("HOTBAR_PET_PANDA");
        registerListener(this);
    }

    @EventHandler
    public void onPhantomSpawn(EntityTargetLivingEntityEvent e) {
        if (e.getEntityType().equals(PHANTOM) && ((Phantom) e.getEntity()).getTarget() instanceof Player) {
            Player p = (Player) ((Phantom) e.getEntity()).getTarget();
            if (!hasPandaPet(p) || !panda.checkAndConsumeFood(p)) return;
            e.getEntity().remove();
            e.setCancelled(true);
        }
    }

    boolean hasPandaPet(Player player) {
        for (int i = 0; i < 9; i++) if (panda.isItem(player.getInventory().getItem(i))) return true;
        return false;
    }
}