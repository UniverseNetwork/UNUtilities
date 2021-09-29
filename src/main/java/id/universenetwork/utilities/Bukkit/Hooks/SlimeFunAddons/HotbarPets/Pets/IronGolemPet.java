package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.Sound.ENTITY_IRON_GOLEM_STEP;
import static org.bukkit.potion.PotionEffectType.DAMAGE_RESISTANCE;

public class IronGolemPet extends SimpleBasePet {
    public IronGolemPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.addPotionEffect(new PotionEffect(DAMAGE_RESISTANCE, 500, 0));
        p.getWorld().playSound(p.getLocation(), ENTITY_IRON_GOLEM_STEP, 1.0F, 2.0F);
    }
}