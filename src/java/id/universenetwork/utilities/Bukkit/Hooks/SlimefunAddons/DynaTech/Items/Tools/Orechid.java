package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Tools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.RandomizedSet;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import java.util.List;

// I feel like this can somehow be much better :O
public class Orechid extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine implements io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem {
    static RandomizedSet<Material> OVERWORLD_ORES = new RandomizedSet<>();
    static RandomizedSet<Material> NETHER_ORES = new RandomizedSet<>();
    // static List<Material> END_ORES = new ArrayList<>();
    // Somehow setup a RecipeType for this for people to use in addons
    // static RecipeType ORECHID = new RecipeType(new NamespacedKey(DynaTech.getInstance(), "dt_orechid"), new CustomItem(Material.WITHER_ROSE, "&bConverted with the Orechid"));

    public Orechid(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void tick(Block b) {
        if (DynaTech.getInstance().getTickInterval() % 10 == 0) for (BlockFace relative : BlockFace.values()) {
            if (getCharge(b.getLocation()) < getEnergyConsumption()) break;
            if (relative == BlockFace.UP || relative == BlockFace.DOWN) continue;
            Block relBlock = b.getRelative(relative);
            if (relBlock.getType() == Material.STONE) {
                DynaTech.runSync(() -> relBlock.setType(getOverWorldOres().getRandom()));
                removeCharge(b.getLocation(), getEnergyConsumption());
            } else if (relBlock.getType() == Material.NETHERRACK) {
                DynaTech.runSync(() -> relBlock.setType(getNetherOres().getRandom()));
                removeCharge(b.getLocation(), getEnergyConsumption());
            }
        }
    }


    static RandomizedSet<Material> getOverWorldOres() {
        OVERWORLD_ORES.add(Material.COAL_ORE, 3);
        OVERWORLD_ORES.add(Material.IRON_ORE, 2);
        OVERWORLD_ORES.add(Material.GOLD_ORE, 2);
        OVERWORLD_ORES.add(Material.DIAMOND_ORE, 1);
        OVERWORLD_ORES.add(Material.EMERALD_ORE, 1);
        OVERWORLD_ORES.add(Material.REDSTONE_ORE, 3);
        OVERWORLD_ORES.add(Material.LAPIS_ORE, 3);
        return OVERWORLD_ORES;
    }

    static RandomizedSet<Material> getNetherOres() {
        NETHER_ORES.add(Material.NETHER_QUARTZ_ORE, 3);
        NETHER_ORES.add(Material.NETHER_GOLD_ORE, 3);
        NETHER_ORES.add(Material.ANCIENT_DEBRIS, 1);
        NETHER_ORES.add(Material.BASALT, 5);
        NETHER_ORES.add(Material.BLACKSTONE, 5);
        return NETHER_ORES;
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayList = new java.util.ArrayList<>();
        for (Material m : getOverWorldOres()) {
            displayList.add(new ItemStack(Material.STONE));
            displayList.add(new ItemStack(m));
        }
        for (Material m : getNetherOres()) {
            displayList.add(new ItemStack(Material.NETHERRACK));
            displayList.add(new ItemStack(m));
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