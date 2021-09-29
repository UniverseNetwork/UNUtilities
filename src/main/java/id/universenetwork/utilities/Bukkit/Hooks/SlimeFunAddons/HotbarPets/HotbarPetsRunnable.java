package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById;
import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;
import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Material.*;
import static org.bukkit.Sound.BLOCK_WATER_AMBIENT;
import static org.bukkit.Sound.ENTITY_COW_AMBIENT;

public class HotbarPetsRunnable implements Runnable {
    final HotbarPet chicken;
    final HotbarPet mooshroom;
    final HotbarPet fish;
    final HotbarPet goldenCow;

    protected HotbarPetsRunnable() {
        chicken = (HotbarPet) getById("HOTBAR_PET_CHICKEN");
        mooshroom = (HotbarPet) getById("HOTBAR_PET_MOOSHROOM");
        fish = (HotbarPet) getById("HOTBAR_PET_FISH");
        goldenCow = (HotbarPet) getById("HOTBAR_PET_GOLDEN_COW");
    }

    @Override
    public void run() {
        for (Player p : getOnlinePlayers())
            for (int i = 0; i < 9; ++i) {
                ItemStack item = p.getInventory().getItem(i);
                if (item == null || item.getType() == Material.AIR) continue;
                if (isPet(item, chicken)) {
                    if (chicken.checkAndConsumeFood(p)) {
                        p.getInventory().addItem(new ItemStack(Material.EGG));
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 2.0F);
                    }
                } else if (isPet(item, mooshroom)) {
                    if (mooshroom.checkAndConsumeFood(p)) {
                        p.getInventory().addItem(new ItemStack(MUSHROOM_STEW));
                        p.getWorld().playSound(p.getLocation(), ENTITY_COW_AMBIENT, 1.0F, 2.0F);
                    }
                } else if (isPet(item, fish)) {
                    if (fish.checkAndConsumeFood(p)) {
                        p.getInventory().addItem(new ItemStack(COOKED_COD));
                        p.getWorld().playSound(p.getLocation(), BLOCK_WATER_AMBIENT, 1.0F, 2.0F);
                    }
                } else if (isPet(item, goldenCow)) if (goldenCow.checkAndConsumeFood(p)) {
                    p.getInventory().addItem(new ItemStack(GOLD_INGOT));
                    p.getWorld().playSound(p.getLocation(), ENTITY_COW_AMBIENT, 0.8F, 2.0F);
                }
            }
    }

    boolean isPet(ItemStack item, HotbarPet pet) {
        return pet != null && isItemSimilar(item, pet.getItem(), true);
    }
}