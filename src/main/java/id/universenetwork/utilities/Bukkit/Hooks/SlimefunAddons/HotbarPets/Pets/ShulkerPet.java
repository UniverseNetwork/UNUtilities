package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Sound.ENTITY_SHULKER_AMBIENT;
import static org.bukkit.potion.PotionEffectType.LEVITATION;

public class ShulkerPet extends SimpleBasePet {
    public ShulkerPet(HotbarPets Main, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(Main.getItemGroup(), item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        Arrow arrow = p.launchProjectile(Arrow.class);
        arrow.addCustomEffect(new PotionEffect(LEVITATION, 10, 0), true);
        arrow.setMetadata("hotbarpets_projectile", new FixedMetadataValue(plugin, true));
        p.getWorld().playSound(p.getLocation(), ENTITY_SHULKER_AMBIENT, 1.0F, 2.0F);
    }
}