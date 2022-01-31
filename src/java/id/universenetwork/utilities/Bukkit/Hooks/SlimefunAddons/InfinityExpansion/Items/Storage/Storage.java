package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Storage;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups.STORAGE;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Materials.*;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static org.bukkit.Material.*;

@UtilityClass
public final class Storage {
    public static final SlimefunItemStack STORAGE_FORGE = new SlimefunItemStack("STORAGE_FORGE", BEEHIVE, "&6Storage Forge", "&7Upgrades the tier of Storage Units", "&7Retains stored items");
    static final int BASIC_AMOUNT = 6400;
    static final int ADVANCED_AMOUNT = 25600;
    static final int REINFORCED_AMOUNT = 102400;
    static final int VOID_AMOUNT = 409600;
    static final int INFINITY_AMOUNT = 1_600_000_000;
    public static final SlimefunItemStack BASIC_STORAGE = new SlimefunItemStack("BASIC_STORAGE", OAK_WOOD, "&9Basic &8Storage Unit", "&6Capacity: &e" + MachineLore.format(BASIC_AMOUNT) + " &eitems");
    public static final SlimefunItemStack ADVANCED_STORAGE = new SlimefunItemStack("ADVANCED_STORAGE", DARK_OAK_WOOD, "&cAdvanced &8Storage Unit", "&6Capacity: &e" + MachineLore.format(ADVANCED_AMOUNT) + " &eitems");
    public static final SlimefunItemStack REINFORCED_STORAGE = new SlimefunItemStack("REINFORCED_STORAGE", ACACIA_WOOD, "&fReinforced &8Storage Unit", "&6Capacity: &e" + MachineLore.format(REINFORCED_AMOUNT) + " &eitems");
    public static final SlimefunItemStack VOID_STORAGE = new SlimefunItemStack("VOID_STORAGE", CRIMSON_HYPHAE, "&8Void &8Storage Unit", "&6Capacity: &e" + MachineLore.format(VOID_AMOUNT) + " &eitems");
    public static final SlimefunItemStack INFINITY_STORAGE = new SlimefunItemStack("INFINITY_STORAGE", WARPED_HYPHAE, "&bInfinity &8Storage Unit", "&6Capacity: &e" + MachineLore.format(INFINITY_AMOUNT) + " &eitems");

    public static void setup() {
        new StorageForge(STORAGE, STORAGE_FORGE, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MAGSTEEL, new ItemStack(ANVIL), MAGSTEEL, MAGSTEEL, new ItemStack(CRAFTING_TABLE), MAGSTEEL, MAGSTEEL, new ItemStack(BARREL), MAGSTEEL,}).register(addon);
        new StorageUnit(BASIC_STORAGE, BASIC_AMOUNT, new ItemStack[]{new ItemStack(OAK_LOG), MAGSTEEL, new ItemStack(OAK_LOG), new ItemStack(OAK_LOG), new ItemStack(BARREL), new ItemStack(OAK_LOG), new ItemStack(OAK_LOG), MAGSTEEL, new ItemStack(OAK_LOG)}).register(addon);
        new StorageUnit(ADVANCED_STORAGE, ADVANCED_AMOUNT, new ItemStack[]{MAGSTEEL, MACHINE_CIRCUIT, MAGSTEEL, MAGSTEEL, BASIC_STORAGE, MAGSTEEL, MAGSTEEL, MACHINE_CIRCUIT, MAGSTEEL}).register(addon);
        new StorageUnit(REINFORCED_STORAGE, REINFORCED_AMOUNT, new ItemStack[]{MAGSTEEL_PLATE, MACHINE_CIRCUIT, MAGSTEEL_PLATE, MAGSTEEL_PLATE, ADVANCED_STORAGE, MAGSTEEL_PLATE, MAGSTEEL_PLATE, MACHINE_PLATE, MAGSTEEL_PLATE}).register(addon);
        new StorageUnit(VOID_STORAGE, VOID_AMOUNT, new ItemStack[]{VOID_INGOT, MACHINE_PLATE, VOID_INGOT, MAGNONIUM, REINFORCED_STORAGE, MAGNONIUM, VOID_INGOT, MACHINE_CORE, VOID_INGOT}).register(addon);
        new StorageUnit(INFINITY_STORAGE, INFINITY_AMOUNT, new ItemStack[]{INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_STORAGE, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, INFINITE_INGOT}).register(addon);
    }
}