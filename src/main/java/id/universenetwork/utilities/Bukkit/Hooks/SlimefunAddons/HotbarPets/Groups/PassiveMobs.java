package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Groups;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.PetGroup;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets.IronGolemPet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.PetTexture.IRON_GOLEM_PET;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.PetTexture.SLIME_PET;
import static org.bukkit.Material.*;

public final class PassiveMobs implements PetGroup {
    public PassiveMobs(HotbarPets Main) {
        load(Main);
    }

    @Override
    public String getName() {
        return "&eMob (Passive)";
    }

    @Override
    public void load(HotbarPets Main) {
        // @formatter:off
        new IronGolemPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_IRON_GOLEM", IRON_GOLEM_PET.getHash(), "&7Iron Golem Pet", getName(), "&7Favourite Food: Iron Ingots", "", "&fRight-Click: &7Gives you Resistance"), new ItemStack(IRON_INGOT), new ItemStack[]{new ItemStack(IRON_BLOCK), new ItemStack(PUMPKIN), new ItemStack(IRON_BLOCK), new ItemStack(IRON_INGOT), new ItemStack(EMERALD), new ItemStack(IRON_INGOT), new ItemStack(IRON_INGOT), SlimefunItems.GOLD_16K, new ItemStack(IRON_INGOT)}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_SLIME", SLIME_PET.getHash(), "&aSlime Pet", getName(), "&7Favourite Food: Glowstone Dust", "", "&fImmune to Fall Damage"), new ItemStack(GLOWSTONE_DUST), new ItemStack[]{new ItemStack(SLIME_BALL), new ItemStack(DIAMOND), new ItemStack(SLIME_BALL), new ItemStack(SLIME_BALL), new ItemStack(EMERALD), new ItemStack(SLIME_BALL), SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K, SlimefunItems.GOLD_20K}).register(addon);
        // @formatter:on
    }
}