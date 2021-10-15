package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.potion.PotionEffectType.*;

public class CowPet extends SimpleBasePet {
    public CowPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.removePotionEffect(BLINDNESS);
        p.removePotionEffect(CONFUSION);
        p.removePotionEffect(HUNGER);
        p.removePotionEffect(POISON);
        p.removePotionEffect(SLOW);
        p.removePotionEffect(SLOW_DIGGING);
        p.removePotionEffect(WEAKNESS);
        p.removePotionEffect(WITHER);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_COW_AMBIENT, 1.0F, 2.0F);
    }
}