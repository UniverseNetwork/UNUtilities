package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Sound.ENTITY_ENDERMAN_TELEPORT;

public class EnderChestPet extends SimpleBasePet {
    public EnderChestPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.openInventory(p.getEnderChest());
        p.getWorld().playSound(p.getLocation(), ENTITY_ENDERMAN_TELEPORT, 1.0F, 2.0F);
    }
}