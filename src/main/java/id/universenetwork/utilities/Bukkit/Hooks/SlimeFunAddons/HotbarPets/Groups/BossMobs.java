package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Groups;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.PetGroup;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.PetTexture;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets.EnderDragonPet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;

public final class BossMobs implements PetGroup {
    public BossMobs(HotbarPets Main) {
        load(Main);
    }

    @Override
    public String getName() {
        return "&4Boss Mob (Hostile)";
    }

    @Override
    public void load(HotbarPets Main) {
        // @formatter:off
        new EnderDragonPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_DRAGON", Material.DRAGON_HEAD, "&5Ender Dragon Pet", getName(), "&7Favourite Food: Eyes Of Ender", "", "&fRight-Click: &7Shoots Dragon Fireball & Gives Resistance"), new ItemStack(Material.ENDER_EYE), new ItemStack[]{new ItemStack(Material.PRISMARINE_CRYSTALS), new ItemStack(Material.DRAGON_BREATH), new ItemStack(Material.PRISMARINE_CRYSTALS), SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.DRAGON_HEAD), SlimefunItems.ENDER_LUMP_3, new ItemStack(Material.PRISMARINE_CRYSTALS), new ItemStack(Material.DRAGON_BREATH), new ItemStack(Material.PRISMARINE_CRYSTALS)}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_WITHER", PetTexture.WITHER_PET.getHash(), "&8Wither Pet", getName(), "&7Favourite Food: Soul Sand", "", "&fImmune to Wither Effect"), new ItemStack(Material.SOUL_SAND), new ItemStack[]{new ItemStack(Material.COAL), new ItemStack(Material.WITHER_SKELETON_SKULL), new ItemStack(Material.COAL), new ItemStack(Material.SOUL_SAND), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.SOUL_SAND), new ItemStack(Material.SOUL_SAND), SlimefunItems.GOLD_24K, new ItemStack(Material.SOUL_SAND)}).register(addon);
        // @formatter:on
    }
}