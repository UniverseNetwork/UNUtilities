package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.SimpleBasePet;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Sound.BLOCK_WOODEN_BUTTON_CLICK_ON;

public class WorkbenchPet extends SimpleBasePet {
    public WorkbenchPet(ItemGroup itemGroup, SlimefunItemStack item, ItemStack food, ItemStack[] recipe) {
        super(itemGroup, item, food, recipe);
    }

    @Override
    public void onUseItem(Player p) {
        p.openWorkbench(p.getLocation(), true);
        p.getWorld().playSound(p.getLocation(), BLOCK_WOODEN_BUTTON_CLICK_ON, 1.0F, 2.0F);
    }
}