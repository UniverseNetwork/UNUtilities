package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Groups;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPet;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.HotbarPets;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.PetGroup;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Pets.CowPet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.PetTexture.*;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.GOLD_16K;
import static org.bukkit.Material.*;

public final class FarmAnimals implements PetGroup {
    public FarmAnimals(HotbarPets Main) {
        load(Main);
    }

    @Override
    public String getName() {
        return "&2Farm Animal (Peaceful)";
    }

    @Override
    public void load(HotbarPets Main) {
        // @formatter:off
        SlimefunItemStack cow = new SlimefunItemStack("HOTBAR_PET_COW", COW_PET.getHash(), "&6Cow Pet", getName(), "&7Favourite Food: Wheat", "", "&fRight-Click: &7Removes negative Potion Effects");
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_PIG", PIG_PET.getHash(), "&dPig Pet", getName(), "&7Favourite Food: Carrots", "", "&fBonus Saturation when eating", "&fAllows you to eat poisonous Food"), new ItemStack(CARROT), new ItemStack[]{new ItemStack(REDSTONE), new ItemStack(CARROT), new ItemStack(REDSTONE), new ItemStack(PORKCHOP), new ItemStack(DIAMOND), new ItemStack(PORKCHOP), new ItemStack(REDSTONE), GOLD_16K, new ItemStack(REDSTONE)}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_CHICKEN", CHICKEN_PET.getHash(), "&fChicken Pet", getName(), "&7Favourite Food: Seeds", "", "&fGives you Eggs over time..."), new ItemStack(WHEAT_SEEDS), new ItemStack[]{new ItemStack(REDSTONE), new ItemStack(FEATHER), new ItemStack(REDSTONE), new ItemStack(COOKED_CHICKEN), new ItemStack(DIAMOND), new ItemStack(COOKED_CHICKEN), new ItemStack(REDSTONE), GOLD_16K, new ItemStack(REDSTONE)}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_MOOSHROOM", MOOSHROOM_PET.getHash(), "&dMooshroom Pet", getName(), "&7Favourite Food: Red Mushrooms", "", "&fGives you Mushroom Stew over time..."), new ItemStack(RED_MUSHROOM), new ItemStack[]{new ItemStack(LAPIS_LAZULI), new ItemStack(COOKED_BEEF), new ItemStack(LAPIS_LAZULI), new ItemStack(RED_MUSHROOM), new ItemStack(DIAMOND), new ItemStack(BROWN_MUSHROOM), new ItemStack(LAPIS_LAZULI), GOLD_16K, new ItemStack(LAPIS_LAZULI)}).register(addon);
        new CowPet(Main.getItemGroup(), cow, new ItemStack(WHEAT), new ItemStack[]{new ItemStack(COAL), new ItemStack(WHEAT), new ItemStack(COAL), new ItemStack(COOKED_BEEF), new ItemStack(DIAMOND), new ItemStack(COOKED_BEEF), new ItemStack(COAL), GOLD_16K, new ItemStack(COAL)}).register(addon);
        new HotbarPet(Main.getItemGroup(), new SlimefunItemStack("HOTBAR_PET_GOLDEN_COW", GOLDEN_COW_PET.getHash(), "&6Golden Cow Pet", getName(), "&7Favourite Food: Golden Carrots", "", "&fGives you Golden Ingots over time...", "&f(That means you have a net gain of 1 golden nugget)"), new ItemStack(GOLDEN_CARROT), new ItemStack[]{new ItemStack(GOLDEN_CARROT), new ItemStack(GOLD_NUGGET), new ItemStack(GOLDEN_CARROT), new ItemStack(GOLD_NUGGET), cow, new ItemStack(GOLD_NUGGET), new ItemStack(GOLDEN_CARROT), new ItemStack(GOLD_NUGGET), new ItemStack(GOLDEN_CARROT)}).register(addon);
        // @formatter:on
    }
}