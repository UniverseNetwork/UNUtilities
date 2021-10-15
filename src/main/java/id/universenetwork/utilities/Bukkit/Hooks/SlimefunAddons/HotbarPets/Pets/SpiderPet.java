package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.Sound.ENTITY_SPIDER_AMBIENT;
import static org.bukkit.potion.PotionEffectType.JUMP;

public class SpiderPet extends SimpleBasePet {
    public SpiderPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.addPotionEffect(new PotionEffect(JUMP, 1200, 3));
        p.getWorld().playSound(p.getLocation(), ENTITY_SPIDER_AMBIENT, 1.0F, 2.0F);
    }
}