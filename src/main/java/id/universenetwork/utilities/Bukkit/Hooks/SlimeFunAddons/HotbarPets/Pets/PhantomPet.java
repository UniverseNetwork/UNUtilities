package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.Sound.ENTITY_PHANTOM_AMBIENT;
import static org.bukkit.potion.PotionEffectType.SLOW_FALLING;

public class PhantomPet extends SimpleBasePet {
    public PhantomPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.addPotionEffect(new PotionEffect(SLOW_FALLING, 250, 0));
        p.getWorld().playSound(p.getLocation(), ENTITY_PHANTOM_AMBIENT, 1.0F, 2.0F);
    }
}