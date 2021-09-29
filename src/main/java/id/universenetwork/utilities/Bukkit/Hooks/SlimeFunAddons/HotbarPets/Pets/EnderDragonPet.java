package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Sound;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnderDragonPet extends SimpleBasePet {
    public EnderDragonPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 500, 0));
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1.0F, 2.0F);
        p.launchProjectile(DragonFireball.class);
    }
}