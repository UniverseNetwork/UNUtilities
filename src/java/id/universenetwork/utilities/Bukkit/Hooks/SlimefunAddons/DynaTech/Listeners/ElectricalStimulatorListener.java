package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Listeners;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools.ElectricalStimulator;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ElectricalStimulatorListener implements org.bukkit.event.Listener {
    final ElectricalStimulator electricalStimulator;

    public ElectricalStimulatorListener(@org.jetbrains.annotations.NotNull ElectricalStimulator electricalStimulator) {
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
        this.electricalStimulator = electricalStimulator;
    }

    @EventHandler
    public void onHungerLoss(org.bukkit.event.entity.FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (p.getFoodLevel() < 20 && feedPlayer(p)) e.setFoodLevel(20);
        }
    }

    @EventHandler
    public void onHungerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.STARVATION)
            if (feedPlayer((Player) e.getEntity())) {
                Player p = (Player) e.getEntity();
                p.setFoodLevel(20);
                p.setSaturation(20f);
            }
    }

    boolean feedPlayer(Player p) {
        if (electricalStimulator == null || electricalStimulator.isDisabled()) return false;
        for (ItemStack item : p.getInventory().getStorageContents())
            if (item != null && item.getType() == electricalStimulator.getItem().getType() && SlimefunUtils.isItemSimilar(item, id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems.ELECTRICAL_STIMULATOR, false, false) && io.github.thebusybiscuit.slimefun4.utils.ChargeUtils.getCharge(item.getItemMeta()) > electricalStimulator.getEnergyComsumption())
                if (SlimefunUtils.canPlayerUseItem(p, item, true)) {
                    p.playSound(p.getLocation(), org.bukkit.Sound.ENTITY_PLAYER_BURP, 1F, 1F);
                    electricalStimulator.removeItemCharge(item, electricalStimulator.getEnergyComsumption());
                    return true;
                }
        return false;
    }
}