package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Backpacks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PicnicBasket extends io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack {
    final List<Material> defaultBlacklist = new ArrayList<>();
    final ItemSetting<List<String>> blacklistedMaterials = new ItemSetting<>(this, "blacklisted-materials", ToStringList(getDefaultBlacklist()));

    public PicnicBasket(int size, io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(size, itemGroup, item, recipeType, recipe);
        /*Maybe use Material.getMaterial() and send a set of strings*/
        addItemSetting(blacklistedMaterials);
    }

    @Override
    public boolean isItemAllowed(@org.jetbrains.annotations.NotNull ItemStack item, @org.jetbrains.annotations.Nullable io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem itemAsSlimefunItem) {
        if (id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech.isExoticGardenEnabled) {
            if (itemAsSlimefunItem instanceof id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.Items.CustomFood)
                return true;
            else
                return item.getType().isEdible() && !blacklistedMaterials.getValue().contains(item.getType().toString());
        }
        return item.getType().isEdible() && !blacklistedMaterials.getValue().contains(item.getType().toString());
    }

    List<Material> getDefaultBlacklist() {
        defaultBlacklist.add(Material.PUFFERFISH);
        defaultBlacklist.add(Material.POISONOUS_POTATO);
        defaultBlacklist.add(Material.SPIDER_EYE);
        defaultBlacklist.add(Material.CHORUS_FRUIT);
        defaultBlacklist.add(Material.ENCHANTED_GOLDEN_APPLE);
        defaultBlacklist.add(Material.GOLDEN_APPLE);
        defaultBlacklist.add(Material.ROTTEN_FLESH);

        // Returns Stuff, maybe will figure this out later.
        defaultBlacklist.add(Material.SUSPICIOUS_STEW);
        defaultBlacklist.add(Material.MUSHROOM_STEW);
        defaultBlacklist.add(Material.RABBIT_STEW);
        defaultBlacklist.add(Material.HONEY_BOTTLE);
        return defaultBlacklist;
    }

    List<String> ToStringList(List<Material> mats) {
        List<String> materials = new ArrayList<>();
        for (Material mat : mats) materials.add(mat.toString());
        return materials;
    }
}