package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getVersion;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static java.util.Arrays.asList;
import static org.bukkit.Bukkit.getPluginManager;

public final class Constants {
    public static final int SERVER_VERSION = Integer.parseInt(Bukkit.getVersion().replaceFirst(".*MC: ", "").replace(")", "").replace(".", ""));
    public static final String SLIMEFUN_VERSION = getVersion();
    public static boolean SLIMEFUN_UPDATED = false;
    public static final Pattern VERSION_PATTERN = Pattern.compile("(DEV - )([0-9]+)");
    public static final NamespacedKey GLOW_ENCHANT = new NamespacedKey(plugin, "fm_glow_enchant");
    public static final boolean isSoulJarsInstalled = Enabled("SoulJars");
    public static final boolean isNCPInstalled = getPluginManager().isPluginEnabled("NoCheatPlus");
    public static final List<SlimefunItemStack> dusts = new ArrayList<>(asList(COPPER_DUST, GOLD_DUST, IRON_DUST, LEAD_DUST, ALUMINUM_DUST, ZINC_DUST, TIN_DUST, MAGNESIUM_DUST, SILVER_DUST));
    public static final int MAX_STACK_SIZE = 64;

    Constants() {
    }
}