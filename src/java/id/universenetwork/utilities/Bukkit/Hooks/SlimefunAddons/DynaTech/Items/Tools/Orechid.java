package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.RandomizedSet;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

// I feel like this can somehow be much better :O (review)
public class Orechid extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine implements io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem {
    static final Map<Material, RandomizedSet<ItemStack>> oreMap = new EnumMap<>(Material.class);
    // static List<Material> END_ORES = new ArrayList<>();

    public Orechid(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        registerDefaultOres();
    }

    public static void registerOre(@NotNull Material from, @NotNull Material result, float weight) {
        oreMap.computeIfAbsent(from, k -> new RandomizedSet<>()).add(new ItemStack(result), weight);
    }

    /**
     * For Slimefun items
     */
    public static void registerOre(@NotNull Material from, @NotNull SlimefunItemStack result, float weight) {
        oreMap.computeIfAbsent(from, k -> new RandomizedSet<>()).add(result, weight);
    }

    static void registerDefaultOres() {
        registerOre(Material.STONE, Material.COAL_ORE, 3);
        registerOre(Material.STONE, Material.IRON_ORE, 2);
        registerOre(Material.STONE, Material.GOLD_ORE, 2);
        registerOre(Material.STONE, Material.DIAMOND_ORE, 1);
        registerOre(Material.STONE, Material.EMERALD_ORE, 1);
        registerOre(Material.STONE, Material.REDSTONE_ORE, 3);
        registerOre(Material.STONE, Material.LAPIS_ORE, 3);
        registerOre(Material.STONE, Material.COPPER_ORE, 3);
        registerOre(Material.NETHERRACK, Material.NETHER_QUARTZ_ORE, 3);
        registerOre(Material.NETHERRACK, Material.NETHER_GOLD_ORE, 3);
        registerOre(Material.NETHERRACK, Material.ANCIENT_DEBRIS, 1);
        registerOre(Material.NETHERRACK, Material.BASALT, 5);
        registerOre(Material.NETHERRACK, Material.BLACKSTONE, 5);
    }

    @Override
    public void tick(Block b) {
        if (DynaTech.getInstance().getTickInterval() % 10 == 0) for (BlockFace relative : BlockFace.values()) {
            if (getCharge(b.getLocation()) < getEnergyConsumption()) break;
            if (relative == BlockFace.UP || relative == BlockFace.DOWN) continue;
            Block relBlock = b.getRelative(relative);
            if (oreMap.containsKey(relBlock.getType())) {
                ItemStack item = oreMap.get(relBlock.getType()).getRandom();
                SlimefunItem sfi = SlimefunItem.getByItem(item);
                DynaTech.runSync(() -> {
                    relBlock.setType(item.getType());
                    if (sfi != null)
                        me.mrCookieSlime.Slimefun.api.BlockStorage.addBlockInfo(relBlock, "id", sfi.getId());
                });
                removeCharge(b.getLocation(), getEnergyConsumption());
            }
        }
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayList = new java.util.ArrayList<>();
        for (Material m : oreMap.keySet())
            for (ItemStack item : oreMap.get(m)) {
                displayList.add(new ItemStack(m));
                displayList.add(item);
            }
        return displayList;
    }

    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public String getMachineIdentifier() {
        return "ORECHID";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.WITHER_ROSE);
    }
}