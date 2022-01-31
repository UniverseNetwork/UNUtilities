package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Sound.ENTITY_ENDERMAN_AMBIENT;

public class EndermanPet extends SimpleBasePet {
    public EndermanPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.launchProjectile(EnderPearl.class);
        p.getWorld().playSound(p.getLocation(), ENTITY_ENDERMAN_AMBIENT, 1.0F, 2.0F);
    }
}