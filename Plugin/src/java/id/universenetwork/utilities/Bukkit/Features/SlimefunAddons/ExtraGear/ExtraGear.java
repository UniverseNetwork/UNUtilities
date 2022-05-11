package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraGear;

import id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SfAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.UNUtilities.createKey;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize;
import static java.util.Arrays.asList;

public class ExtraGear extends SfAddon {
    int researchId = 3300;
    ItemGroup itemGroup;

    @Override
    public void Load() {
        itemGroup = new ItemGroup(createKey("items"), new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(Material.DIAMOND_SWORD, "&6ExtraGear"), 1);
        registerSword(Material.IRON_SWORD, "COPPER", COPPER_INGOT, asList(new Pair<>(Enchantment.DAMAGE_UNDEAD, 2)));
        registerArmor(ArmorSet.LEATHER, "COPPER", COPPER_INGOT, asList(new Pair<>(Enchantment.PROTECTION_EXPLOSIONS, 2)));
        registerSword(Material.IRON_SWORD, "TIN", TIN_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 1)));
        registerArmor(ArmorSet.IRON, "TIN", TIN_INGOT, asList(new Pair<>(Enchantment.PROTECTION_EXPLOSIONS, 3)));
        registerSword(Material.IRON_SWORD, "SILVER", SILVER_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 2)));
        registerArmor(ArmorSet.IRON, "SILVER", SILVER_INGOT, asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 2)));
        registerSword(Material.IRON_SWORD, "ALUMINUM", ALUMINUM_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ARTHROPODS, 3)));
        registerArmor(ArmorSet.IRON, "ALUMINUM", ALUMINUM_INGOT, asList(new Pair<>(Enchantment.PROTECTION_EXPLOSIONS, 2), new Pair<>(Enchantment.DURABILITY, 2)));
        registerSword(Material.IRON_SWORD, "LEAD", LEAD_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 3), new Pair<>(Enchantment.DURABILITY, 8)));
        registerArmor(ArmorSet.IRON, "LEAD", LEAD_INGOT, asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 3), new Pair<>(Enchantment.DURABILITY, 8)));
        registerSword(Material.IRON_SWORD, "ZINC", ZINC_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 2)));
        registerArmor(ArmorSet.IRON, "ZINC", ZINC_INGOT, asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));
        registerSword(Material.IRON_SWORD, "MAGNESIUM", MAGNESIUM_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 2), new Pair<>(Enchantment.DURABILITY, 5)));
        registerArmor(ArmorSet.IRON, "MAGNESIUM", MAGNESIUM_INGOT, asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 2), new Pair<>(Enchantment.DURABILITY, 5)));
        registerSword(Material.IRON_SWORD, "STEEL", STEEL_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 5), new Pair<>(Enchantment.DURABILITY, 6)));
        registerArmor(ArmorSet.IRON, "STEEL", STEEL_INGOT, asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 3), new Pair<>(Enchantment.DURABILITY, 4)));
        registerSword(Material.IRON_SWORD, "BRONZE", BRONZE_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 3), new Pair<>(Enchantment.DURABILITY, 6)));
        registerSword(Material.IRON_SWORD, "DURALUMIN", DURALUMIN_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 3), new Pair<>(Enchantment.DURABILITY, 6)));
        registerSword(Material.IRON_SWORD, "BILLON", BILLON_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 4), new Pair<>(Enchantment.DURABILITY, 5)));
        registerSword(Material.IRON_SWORD, "BRASS", BRASS_INGOT, asList(new Pair<>(Enchantment.DAMAGE_UNDEAD, 4), new Pair<>(Enchantment.DURABILITY, 6)));
        registerSword(Material.IRON_SWORD, "ALUMINUM_BRASS", ALUMINUM_BRASS_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ARTHROPODS, 4), new Pair<>(Enchantment.DURABILITY, 4)));
        registerSword(Material.IRON_SWORD, "ALUMINUM_BRONZE", ALUMINUM_BRONZE_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ARTHROPODS, 4), new Pair<>(Enchantment.DURABILITY, 5)));
        registerSword(Material.IRON_SWORD, "CORINTHIAN_BRONZE", CORINTHIAN_BRONZE_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 5), new Pair<>(Enchantment.DURABILITY, 5)));
        registerSword(Material.IRON_SWORD, "SOLDER", SOLDER_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 4), new Pair<>(Enchantment.DURABILITY, 6)));
        registerSword(Material.IRON_SWORD, "DAMASCUS_STEEL", DAMASCUS_STEEL_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 6), new Pair<>(Enchantment.DURABILITY, 7)));
        registerSword(Material.IRON_SWORD, "HARDENED", HARDENED_METAL_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 7), new Pair<>(Enchantment.DURABILITY, 10)));
        registerSword(Material.IRON_SWORD, "REINFORCED", REINFORCED_ALLOY_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 8), new Pair<>(Enchantment.DURABILITY, 8)));
        registerSword(Material.IRON_SWORD, "FERROSILICON", FERROSILICON, asList(new Pair<>(Enchantment.DAMAGE_UNDEAD, 8), new Pair<>(Enchantment.DURABILITY, 4)));
        registerSword(Material.GOLDEN_SWORD, "GILDED_IRON", GILDED_IRON, asList(new Pair<>(Enchantment.DAMAGE_ARTHROPODS, 8), new Pair<>(Enchantment.DURABILITY, 10)));
        registerSword(Material.IRON_SWORD, "NICKEL", NICKEL_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 6), new Pair<>(Enchantment.DURABILITY, 5)));
        registerSword(Material.IRON_SWORD, "COBALT", COBALT_INGOT, asList(new Pair<>(Enchantment.DAMAGE_ALL, 7), new Pair<>(Enchantment.DURABILITY, 7)));
        registerArmor(ArmorSet.IRON, "COBALT", COBALT_INGOT, asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 7), new Pair<>(Enchantment.DURABILITY, 7)));
    }

    void registerSword(Material type, String component, ItemStack item, List<Pair<Enchantment, Integer>> enchantments) {
        SlimefunItemStack is = new SlimefunItemStack(component + "_SWORD", type, "&r" + humanize(component) + " Sword");
        for (Pair<Enchantment, Integer> enchantment : enchantments)
            is.addUnsafeEnchantment(enchantment.getFirstValue(), enchantment.getSecondValue());
        SlimefunItem slimefunItem = new SlimefunItem(itemGroup, is, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, item, null, null, item, null, null, new ItemStack(Material.STICK), null});
        slimefunItem.register(this);
        researchId++;
        Research research = new Research(createKey(component.toLowerCase() + "_sword"), researchId, humanize(component) + " Sword", 3);
        research.addItems(slimefunItem);
        research.register();
    }

    void registerArmor(ArmorSet armorset, String component, ItemStack item, List<Pair<Enchantment, Integer>> enchantments) {
        String humanizedComponent = humanize(component);
        SlimefunItemStack[] armor = {new SlimefunItemStack(component + "_HELMET", armorset.getHelmet(), "&f" + humanizedComponent + " Helmet"), new SlimefunItemStack(component + "_CHESTPLATE", armorset.getChestplate(), "&f" + humanizedComponent + " Chestplate"), new SlimefunItemStack(component + "_LEGGINGS", armorset.getLeggings(), "&f" + humanizedComponent + " Leggings"), new SlimefunItemStack(component + "_BOOTS", armorset.getBoots(), "&f" + humanizedComponent + " Boots")};
        for (Pair<Enchantment, Integer> enchantment : enchantments)
            for (ItemStack is : armor)
                is.addUnsafeEnchantment(enchantment.getFirstValue(), enchantment.getSecondValue());
        SlimefunItem helmet = new SlimefunItem(itemGroup, armor[0], RecipeType.ARMOR_FORGE, new ItemStack[]{item, item, item, item, null, item, null, null, null});
        helmet.register(this);
        SlimefunItem chestplate = new SlimefunItem(itemGroup, armor[1], RecipeType.ARMOR_FORGE, new ItemStack[]{item, null, item, item, item, item, item, item, item});
        chestplate.register(this);
        SlimefunItem leggings = new SlimefunItem(itemGroup, armor[2], RecipeType.ARMOR_FORGE, new ItemStack[]{item, item, item, item, null, item, item, null, item});
        leggings.register(this);
        SlimefunItem boots = new SlimefunItem(itemGroup, armor[3], RecipeType.ARMOR_FORGE, new ItemStack[]{null, null, null, item, null, item, item, null, item});
        boots.register(this);
        researchId++;
        Research research = new Research(createKey(component.toLowerCase() + "_armor"), researchId, humanizedComponent + " Armor", 5);
        research.addItems(helmet, chestplate, leggings, boots);
        research.register();
    }
}