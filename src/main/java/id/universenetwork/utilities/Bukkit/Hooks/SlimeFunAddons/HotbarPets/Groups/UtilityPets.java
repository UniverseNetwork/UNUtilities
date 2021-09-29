package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Groups;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.PetGroup;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets.BedPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets.EnderChestPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets.WorkbenchPet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.PetTexture.ENDER_CHEST_PET;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.PetTexture.WORKBENCH_PET;
import static org.bukkit.Material.*;

public final class UtilityPets implements PetGroup {
    public UtilityPets(HotbarPets Main) {
        load(Main);
    }

    @Override
    public String getName() {
        return "&aUtility (Peaceful)";
    }

    @Override
    public void load(HotbarPets Main) {
        // @formatter:off
        new BedPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_BED", RED_BED, "&fBed Pet", getName(), "&7Favourite Food: Wool", "", "&fRight-Click: &7Sleep"), new ItemStack(WHITE_WOOL), new ItemStack[]{new ItemStack(IRON_INGOT), new ItemStack(RED_BED), new ItemStack(IRON_INGOT), new ItemStack(WHITE_WOOL), new ItemStack(DIAMOND), new ItemStack(WHITE_WOOL), new ItemStack(OAK_PLANKS), SlimefunItems.GOLD_14K, new ItemStack(OAK_PLANKS)}).register(addon);
        new EnderChestPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_ENDER_CHEST", ENDER_CHEST_PET.getHash(), "&5Ender Chest Pet", getName(), "&7Favourite Food: Ender Pearls", "", "&fRight-Click: &7Open"), new ItemStack(ENDER_PEARL), new ItemStack[]{new ItemStack(OBSIDIAN), new ItemStack(ENDER_EYE), new ItemStack(OBSIDIAN), new ItemStack(ENDER_PEARL), new ItemStack(EMERALD), new ItemStack(ENDER_PEARL), new ItemStack(OBSIDIAN), SlimefunItems.GOLD_16K, new ItemStack(OBSIDIAN)}).register(addon);
        new WorkbenchPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_WORKBENCH", WORKBENCH_PET.getHash(), "&6Workbench Pet", getName(), "&7Favourite Food: Wooden Planks", "", "&fRight-Click: &7Open"), new ItemStack(OAK_PLANKS), new ItemStack[]{new ItemStack(OAK_PLANKS), new ItemStack(CRAFTING_TABLE), new ItemStack(OAK_PLANKS), new ItemStack(OAK_PLANKS), new ItemStack(IRON_INGOT), new ItemStack(OAK_PLANKS), new ItemStack(OAK_PLANKS), SlimefunItems.GOLD_16K, new ItemStack(OAK_PLANKS)}).register(addon);
        // @formatter:on
    }
}