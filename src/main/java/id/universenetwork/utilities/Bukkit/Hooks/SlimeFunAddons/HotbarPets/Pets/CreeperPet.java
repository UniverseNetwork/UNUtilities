package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Sound.ENTITY_CREEPER_PRIMED;
import static org.bukkit.entity.EntityType.PRIMED_TNT;

public class CreeperPet extends SimpleBasePet {
    public CreeperPet(HotbarPets Main, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(Main.getItemGroup(), item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        TNTPrimed tnt = (TNTPrimed) p.getWorld().spawnEntity(p.getLocation(), PRIMED_TNT);
        tnt.setMetadata("hotbarpets_player", new FixedMetadataValue(plugin, p.getUniqueId()));
        tnt.setFuseTicks(0);
        p.getWorld().playSound(p.getLocation(), ENTITY_CREEPER_PRIMED, 1.0F, 2.0F);
    }
}