package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.Listeners;

public class AndroidListener implements org.bukkit.event.Listener {
    @org.bukkit.event.EventHandler(ignoreCancelled = true)
    public void onGrow(io.github.thebusybiscuit.slimefun4.api.events.AndroidFarmEvent e) {
        // Only for the advanced harvesting action
        if (e.isAdvanced() && e.getDrop() == null)
            e.setDrop(id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.ExoticGarden.harvestPlant(e.getBlock())); // Allow Androids to harvest our plants
    }
}