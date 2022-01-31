package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets.Special;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.Sound.ENTITY_COW_AMBIENT;
import static org.bukkit.potion.PotionEffectType.REGENERATION;

public class PurpliciousCowPet extends SimpleBasePet {
    public PurpliciousCowPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.addPotionEffect(new PotionEffect(REGENERATION, 160, 2));
        p.getWorld().playSound(p.getLocation(), ENTITY_COW_AMBIENT, 1.0F, 2.0F);
    }
}