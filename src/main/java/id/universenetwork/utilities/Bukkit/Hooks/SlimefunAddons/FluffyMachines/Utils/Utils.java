package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static id.universenetwork.utilities.Bukkit.Manager.Color.Translator;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK;
import static java.text.DecimalFormatSymbols.getInstance;
import static java.util.Locale.ROOT;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.block.BlockFace.NORTH;
import static org.bukkit.persistence.PersistentDataType.INTEGER;

public final class Utils {
    static final NamespacedKey fluffykey = new NamespacedKey(plugin, "fluffykey");
    public static final DecimalFormat powerFormat = new DecimalFormat("###,###.##", getInstance(ROOT));
    final static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    Utils() {
    }

    public static void send(Player p, String message) {
        p.sendMessage(GRAY + "[FluffyMachines] " + ChatColors.color(message));
    }

    public static String multiBlockWarning() {
        return "&cThis is a Multiblock machine!";
    }

    public static ItemStack buildNonInteractable(Material material, @Nullable String name, @Nullable String... lore) {
        ItemStack nonClickable = new ItemStack(material);
        ItemMeta NCMeta = nonClickable.getItemMeta();
        if (name != null) NCMeta.setDisplayName(ChatColors.color(name));
        else NCMeta.setDisplayName(" ");
        if (lore.length > 0) {
            List<String> lines = new ArrayList<>();
            for (String line : lore) lines.add(Translator(line));
            NCMeta.setLore(lines);
        }
        NCMeta.setCustomModelData(6969);
        nonClickable.setItemMeta(NCMeta);
        return nonClickable;
    }

    public static boolean checkNonInteractable(ItemStack item) {
        return item.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == 6969;
    }

    public static boolean checkAdjacent(Block b, Material material) {
        return b.getRelative(NORTH).getType() == material || b.getRelative(BlockFace.EAST).getType() == material || b.getRelative(BlockFace.SOUTH).getType() == material || b.getRelative(BlockFace.WEST).getType() == material;
    }

    public static String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) return map.get(number);
        return map.get(l) + toRoman(number - l);
    }

    public static ItemStack keyItem(ItemStack item) {
        ItemStack c = item.clone();
        ItemMeta m = c.getItemMeta();
        m.getPersistentDataContainer().set(fluffykey, INTEGER, 1);
        c.setItemMeta(m);
        return c;
    }

    public static ItemStack unKeyItem(ItemStack item) {
        ItemStack c = item.clone();
        ItemMeta m = c.getItemMeta();
        m.getPersistentDataContainer().remove(fluffykey);
        c.setItemMeta(m);
        return c;
    }

    public static boolean canOpen(@NotNull Block b, @NotNull Player p) {
        return (p.hasPermission("slimefun.inventory.bypass") || getProtectionManager().hasPermission(p, b.getLocation(), INTERACT_BLOCK));
    }

    // Don't use Slimefun's runsync
    public static BukkitTask runSync(Runnable r) {
        return plugin != null && plugin.isEnabled() ? getScheduler().runTask(plugin, r) : null;
    }

    public static BukkitTask runSync(Runnable r, long delay) {
        return plugin != null && plugin.isEnabled() ? getScheduler().runTaskLater(plugin, r, delay) : null;
    }
}