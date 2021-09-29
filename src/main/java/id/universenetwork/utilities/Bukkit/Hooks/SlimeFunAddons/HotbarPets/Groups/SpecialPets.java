package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Groups;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.PetGroup;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets.Special.CookieSlimePet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets.Special.PatriotPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets.Special.PurpliciousCowPet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.PetTexture.*;
import static org.bukkit.Material.*;

public final class SpecialPets implements PetGroup {
    public SpecialPets(HotbarPets Main) {
        load(Main);
    }

    @Override
    public String getName() {
        return "&9Special";
    }

    @Override
    public void load(HotbarPets Main) {
        // @formatter:off
        new PurpliciousCowPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_PURPLICIOUS_COW", PURPLICIOUS_COW_PET.getHash(), "&5Purplicious Cow Pet", getName(), "&7Favourite Food: Golden Apples", "", "&fRight-Click: &7Gives you Health Regeneration"), new ItemStack(GOLDEN_APPLE), new ItemStack[]{new ItemStack(COOKED_BEEF), new ItemStack(DIAMOND), new ItemStack(COOKED_BEEF), new ItemStack(DIAMOND), new ItemStack(EMERALD), new ItemStack(DIAMOND), SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K}).register(addon);
        new CookieSlimePet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_MRCOOKIESLIME", MR_COOKIE_SLIME_PET.getHash(), "&amrCookieSlime Pet", getName(), "&7Favourite Food: Cookies", "", "&fImmune to Fall Damage", "&fRight-Click: &7Gives you Health Regeneration"), new ItemStack(COOKIE), new ItemStack[]{new ItemStack(COOKIE), new ItemStack(DIAMOND), new ItemStack(COOKIE), new ItemStack(DIAMOND), new ItemStack(EMERALD), new ItemStack(DIAMOND), SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K}).register(addon);
        new PatriotPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_PATRIOT", PATRIOT_PET.getHash(), "&5Patriot Pet", getName(), "&7Favourite Food: Nether Wart", "", "&fGives Resistance, Regeneration, Strength, ", "&fand Saturation"), new ItemStack(NETHER_WART), new ItemStack[]{new ItemStack(REDSTONE), SlimefunItems.MAGIC_LUMP_1, new ItemStack(REDSTONE), SlimefunItems.MAGIC_LUMP_1, SlimefunItems.RAW_CARBONADO, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.GOLD_4K, new ItemStack(REDSTONE), SlimefunItems.GOLD_4K}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_WALSHRUS", WALSHRUS_PET.getHash(), "&bWalshrus Pet", getName(), "&7Favourite Food: Raw Cod", "", "&fCannot drown"), new ItemStack(COD), new ItemStack[]{new ItemStack(COD), new ItemStack(DIAMOND), new ItemStack(COD), new ItemStack(DIAMOND), new ItemStack(EMERALD), new ItemStack(DIAMOND), SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_EYAMAZ", EYAMAZ_PET.getHash(), "&4Eyamaz Pet", getName(), "&7Favourite Food: Souls", "", "&fBeware! Eyamaz makes a Soul Pie", "&fout of the Souls of everything you kill"), new ItemStack(SOUL_SAND), new ItemStack[]{new ItemStack(SOUL_SAND), new ItemStack(DIAMOND), new ItemStack(SOUL_SAND), new ItemStack(DIAMOND), new ItemStack(EMERALD), new ItemStack(DIAMOND), SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K}).register(addon);
        // @formatter:on
    }
}