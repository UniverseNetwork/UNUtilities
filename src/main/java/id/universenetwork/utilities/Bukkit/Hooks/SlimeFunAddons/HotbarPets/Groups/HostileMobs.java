package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Groups;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.PetGroup;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.Pets.*;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.HotbarPets.PetTexture.*;
import static org.bukkit.Material.*;

public final class HostileMobs implements PetGroup {
    public HostileMobs(HotbarPets Main) {
        load(Main);
    }

    @Override
    public String getName() {
        return "&cMob (Hostile)";
    }

    @Override
    public void load(HotbarPets Main) {
        // @formatter:off
        new SpiderPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_SPIDER", SPIDER_PET.getHash(), "&8Spider Pet", getName(), "&7Favourite Food: Rotten Flesh", "", "&fRight-Click: &7Gives you Jump Boost"), new ItemStack(ROTTEN_FLESH), new ItemStack[]{new ItemStack(COAL), new ItemStack(STRING), new ItemStack(COAL), new ItemStack(SPIDER_EYE), new ItemStack(EMERALD), new ItemStack(SPIDER_EYE), new ItemStack(STRING), SlimefunItems.GOLD_16K, new ItemStack(STRING)}).register(addon);
        new GhastPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_GHAST", GHAST_PET.getHash(), "&fGhast Pet", getName(), "&7Favourite Food: Fire Charge", "", "&fRight-Click: &7Launches Fireballs"), new ItemStack(FIRE_CHARGE), new ItemStack[]{new ItemStack(GHAST_TEAR), new ItemStack(DIAMOND), new ItemStack(GHAST_TEAR), new ItemStack(QUARTZ_BLOCK), new ItemStack(BLAZE_POWDER), new ItemStack(QUARTZ_BLOCK), SlimefunItems.GOLD_24K, new ItemStack(TNT), SlimefunItems.GOLD_24K}).register(addon);
        new ShulkerPet(Main, new SlimefunItemStack("HOTBAR_PET_SHULKER", SHULKER_PET.getHash(), "&dShulker Pet", getName(), "&7Favourite Food: Chorus Fruit", "", "&fRight-Click: &7Shoots arrows tipped with levitation"), new ItemStack(CHORUS_FRUIT), new ItemStack[]{new ItemStack(NETHER_WART), new ItemStack(SHULKER_SHELL), new ItemStack(NETHER_WART), new ItemStack(QUARTZ), new ItemStack(GLOWSTONE_DUST), new ItemStack(QUARTZ), new ItemStack(FEATHER), new ItemStack(SHULKER_SHELL), new ItemStack(FEATHER)}).register(addon);
        new PhantomPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_PHANTOM", PHANTOM_PET.getHash(), "&7Phantom Pet", getName(), "&7Favourite Food: Beetroot", "", "&fRight-Click: &7Gives Slow Falling"), new ItemStack(BEETROOT), new ItemStack[]{new ItemStack(PHANTOM_MEMBRANE), new ItemStack(RABBIT_FOOT), new ItemStack(PHANTOM_MEMBRANE), new ItemStack(DIAMOND), SlimefunItems.GOLD_6K, new ItemStack(DIAMOND), new ItemStack(PHANTOM_MEMBRANE), new ItemStack(RABBIT_FOOT), new ItemStack(PHANTOM_MEMBRANE)}).register(addon);
        new EndermanPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_ENDER_MAN", ENDERMAN_PET.getHash(), "&8Enderman Pet", getName(), "&7Favourite Food: End Stone", "", "&fRight-Click: &7Shoots an Ender Pearl"), new ItemStack(END_STONE), new ItemStack[]{new ItemStack(OBSIDIAN), new ItemStack(ENDER_PEARL), new ItemStack(OBSIDIAN), new ItemStack(ENDER_EYE), new ItemStack(EMERALD), new ItemStack(ENDER_EYE), new ItemStack(ENDER_PEARL), SlimefunItems.GOLD_16K, new ItemStack(ENDER_PEARL)}).register(addon);
        new CreeperPet(Main, new SlimefunItemStack("HOTBAR_PET_CREEPER", CREEPER_HEAD, "&2Creeper Pet", getName(), "&7Favourite Food: Gunpowder", "", "&fImmune to Explosions", "&fRight-Click: &7Explode"), new ItemStack(GUNPOWDER), new ItemStack[]{new ItemStack(DIAMOND), new ItemStack(CREEPER_HEAD), new ItemStack(DIAMOND), new ItemStack(GUNPOWDER), new ItemStack(EMERALD), new ItemStack(GUNPOWDER), new ItemStack(DIAMOND), SlimefunItems.GOLD_20K, new ItemStack(DIAMOND)}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_MAGMA_CUBE", MAGMA_CUBE_PET.getHash(), "&4Magma Cube Pet", getName(), "&7Favourite Food: Nether Quartz", "", "&fImmune to Lava", "&fQuite hungry"), new ItemStack(QUARTZ), new ItemStack[]{new ItemStack(REDSTONE), new ItemStack(MAGMA_CREAM), new ItemStack(REDSTONE), new ItemStack(BLAZE_POWDER), new ItemStack(EMERALD), new ItemStack(BLAZE_POWDER), new ItemStack(NETHERRACK), SlimefunItems.GOLD_20K, new ItemStack(NETHERRACK)}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_BLAZE", BLAZE_PET.getHash(), "&6Blaze Pet", getName(), "&7Favourite Food: Nether Quartz", "", "&fImmune to Fire"), new ItemStack(QUARTZ), new ItemStack[]{new ItemStack(GLOWSTONE_DUST), new ItemStack(BLAZE_ROD), new ItemStack(GLOWSTONE_DUST), new ItemStack(BLAZE_POWDER), new ItemStack(EMERALD), new ItemStack(BLAZE_POWDER), SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_ZOMBIE", ZOMBIE_HEAD, "&2Zombie Pet", getName(), "", "&fAllows you to eat Rotten Flesh", "&fwithout getting Hunger"), new ItemStack(ROTTEN_FLESH), new ItemStack[]{new ItemStack(DIAMOND), new ItemStack(ZOMBIE_HEAD), new ItemStack(DIAMOND), new ItemStack(MOSSY_COBBLESTONE), new ItemStack(EMERALD), new ItemStack(MOSSY_COBBLESTONE), new ItemStack(ROTTEN_FLESH), SlimefunItems.NECROTIC_SKULL, new ItemStack(ROTTEN_FLESH)}).register(addon);
        // @formatter:on
    }
}