package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE;

public class GhastPet extends SimpleBasePet {
    public GhastPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.launchProjectile(SmallFireball.class);
        p.addPotionEffect(new PotionEffect(FIRE_RESISTANCE, 500, 1));
    }
}