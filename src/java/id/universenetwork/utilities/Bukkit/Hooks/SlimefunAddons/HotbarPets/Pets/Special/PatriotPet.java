package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets.Special;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.Sound.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED;
import static org.bukkit.potion.PotionEffectType.*;

public class PatriotPet extends SimpleBasePet {
    public PatriotPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.addPotionEffect(new PotionEffect(REGENERATION, 200, 0));
        p.addPotionEffect(new PotionEffect(DAMAGE_RESISTANCE, 200, 0));
        p.addPotionEffect(new PotionEffect(INCREASE_DAMAGE, 200, 0));
        p.addPotionEffect(new PotionEffect(SATURATION, 100, 0));
        p.getWorld().playSound(p.getLocation(), ENTITY_ZOMBIE_CONVERTED_TO_DROWNED, 1.0F, 2.0F);
    }
}