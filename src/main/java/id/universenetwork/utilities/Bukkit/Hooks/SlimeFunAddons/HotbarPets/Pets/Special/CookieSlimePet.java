package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets.Special;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.Sound.BLOCK_SLIME_BLOCK_STEP;
import static org.bukkit.potion.PotionEffectType.REGENERATION;

public class CookieSlimePet extends SimpleBasePet {
    public CookieSlimePet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.addPotionEffect(new PotionEffect(REGENERATION, 160, 2));
        p.getWorld().playSound(p.getLocation(), BLOCK_SLIME_BLOCK_STEP, 1.0F, 2.0F);
    }
}