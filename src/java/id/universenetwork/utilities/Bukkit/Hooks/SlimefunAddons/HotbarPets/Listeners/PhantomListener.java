package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;

import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById;

public class PhantomListener implements org.bukkit.event.Listener {
    final HotbarPet panda;

    public PhantomListener() {
        panda = (HotbarPet) getById("HOTBAR_PET_PANDA");
    }

    @org.bukkit.event.EventHandler
    public void onPhantomSpawn(org.bukkit.event.entity.EntityTargetLivingEntityEvent e) {
        if (e.getEntityType().equals(org.bukkit.entity.EntityType.PHANTOM) && ((Phantom) e.getEntity()).getTarget() instanceof Player) {
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