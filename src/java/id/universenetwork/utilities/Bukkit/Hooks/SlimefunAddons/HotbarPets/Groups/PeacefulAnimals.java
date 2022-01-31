package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Groups;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.PetGroup;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets.DolphinPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets.RabbitPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets.SquidPet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.PetTexture.*;
import static org.bukkit.Material.*;

public final class PeacefulAnimals implements PetGroup {
    public PeacefulAnimals(HotbarPets Main) {
        load(Main);
    }

    @Override
    public String getName() {
        return "&aAnimal (Peaceful)";
    }

    @Override
    public void load(HotbarPets Main) {
        // @formatter:off
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_FISH", FISH_PET.getHash(), "&3Fish Pet", getName(), "&7Favourite Food: Seeds", "", "&fGives you Fish over time..."), new ItemStack(WHEAT_SEEDS), new ItemStack[]{new ItemStack(IRON_INGOT), new ItemStack(COD), new ItemStack(IRON_INGOT), new ItemStack(COD), new ItemStack(DIAMOND), new ItemStack(COD), new ItemStack(WATER_BUCKET), SlimefunItems.GOLD_18K, new ItemStack(WATER_BUCKET)}).register(addon);
        new SquidPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_SQUID", SQUID_PET.getHash(), "&bSquid Pet", getName(), "&7Favourite Food: Raw Cod", "", "&fRight-Click: &7Gives you Water Breathing"), new ItemStack(COD), new ItemStack[]{new ItemStack(COAL), new ItemStack(COD), new ItemStack(COAL), new ItemStack(WATER_BUCKET), new ItemStack(DIAMOND), new ItemStack(WATER_BUCKET), new ItemStack(COD), SlimefunItems.GOLD_16K, new ItemStack(COD)}).register(addon);
        new RabbitPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_RABBIT", RABBIT_PET.getHash(), "&eRabbit Pet", getName(), "&7Favourite Food: Carrots", "", "&fRight-Click: &7Gives you 30 seconds of Luck"), new ItemStack(CARROT), new ItemStack[]{new ItemStack(GOLDEN_CARROT), new ItemStack(RABBIT_HIDE), new ItemStack(GOLDEN_CARROT), new ItemStack(RABBIT_HIDE), new ItemStack(DIAMOND), new ItemStack(RABBIT_HIDE), new ItemStack(GOLDEN_CARROT), new ItemStack(RABBIT_FOOT), new ItemStack(GOLDEN_CARROT)}).register(addon);
        new DolphinPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_DOLPHIN", DOLPHIN_PET.getHash(), "&bDolphin Pet", getName(), "&7Favourite Food: Ink Sacks", "", "&fRight-Click: &7Dolphin's Grace"), new ItemStack(INK_SAC), new ItemStack[]{new ItemStack(LAPIS_LAZULI), new ItemStack(COD), new ItemStack(LAPIS_LAZULI), new ItemStack(SALMON), new ItemStack(EMERALD), new ItemStack(SALMON), new ItemStack(LAPIS_LAZULI), new ItemStack(COD), new ItemStack(LAPIS_LAZULI)}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_PANDA", PANDA_PET.getHash(), "&8Panda &fPet", getName(), "&7Favorite Food: Bamboo", "", "&fThis sleepy Panda protects you from Insomnia", "&fPhantoms will no longer chase you at night"), new ItemStack(BAMBOO), new ItemStack[]{new ItemStack(BAMBOO), new ItemStack(DIAMOND), new ItemStack(BAMBOO), new ItemStack(DIAMOND), new ItemStack(EMERALD), new ItemStack(DIAMOND), new ItemStack(ACACIA_LEAVES), new ItemStack(BAMBOO), new ItemStack(ACACIA_LEAVES)}).register(addon);
        // @formatter:on
    }
}