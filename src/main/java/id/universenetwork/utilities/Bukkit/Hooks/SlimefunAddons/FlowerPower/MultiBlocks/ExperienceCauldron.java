package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.MultiBlocks;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.FlowerPowerItems;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.ItemStackComparator;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Constants.SERVER_VERSION;

/**
 * A multiblock that stores experience displayed
 * through the cauldron's water level
 * Also stores the handler for the MagicBasin
 *
 * @author NCBPFluffyBear
 */
public class ExperienceCauldron extends SlimefunItem implements org.bukkit.event.Listener {
    static final int EXP_PER_LEVEL = 50;
    static final MultiBlockMachine MAGIC_BASIN = (MultiBlockMachine) MagicBasin.BASIN_RECIPE.getMachine();
    static final int MAX_CAULDRON_LEVEL = 3;

    public ExperienceCauldron(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
    }

    /**
     * Handles experience deposit/extraction
     * and the {@link MagicBasin} crafting process
     *
     * @param e the {@link PlayerRightClickEvent}
     */
    @EventHandler(ignoreCancelled = true)
    void onCauldronInteract(PlayerRightClickEvent e) {
        // Verify the block is legit
        java.util.Optional<Block> optB = e.getClickedBlock();
        if (!optB.isPresent()) return;
        Block b = optB.get();

        // Make sure it's an Experience Cauldron
        SlimefunItem sfItem = BlockStorage.check(b);

        if (sfItem == null || !isItem(sfItem.getItem())) return;
        if (!(b.getType() == Material.CAULDRON) && !(b.getType() == Material.WATER_CAULDRON)) return;

        // Prevent double interaction
        if (e.getHand() == org.bukkit.inventory.EquipmentSlot.OFF_HAND) return;

        org.bukkit.entity.Player p = e.getPlayer();
        ItemStack handItem = p.getInventory().getItemInMainHand();
        int cauldronLevel = getCauldronLevel(b);

        // Prevent insertion/extraction of other liquids
        if (handItem.getType() != Material.ITEM_FRAME) e.cancel();
        else return;

        // Crafting with wand
        if (SlimefunUtils.isItemSimilar(handItem, FlowerPowerItems.MAGICAL_WAND, false, false)) {
            if (cauldronLevel == 0) {
                Utils.send(p, "&cThis Experience Cauldron is out of experience!");
                return;
            }

            // Get surrounding item frames
            List<ItemFrame> itemFrames = new ArrayList<>();

            for (org.bukkit.entity.Entity en : b.getWorld().getNearbyEntities(b.getLocation(), 1.5, 1, 1.5))
                if (en instanceof ItemFrame && itemFrames.size() < 4) itemFrames.add((ItemFrame) en);
            if (itemFrames.size() != 4) {
                Utils.send(p, "&cYou need 4 item frames on each side of the Experience Cauldron");
                return;
            }

            // Retrieve all items in the itemframes
            List<ItemStack> frameItems = getFrameItems(itemFrames);

            for (ItemStack[] recipeInputs : RecipeType.getRecipeInputList(MAGIC_BASIN)) {
                ItemStack output = checkRecipe(frameItems, recipeInputs);
                if (output == null) continue;
                craft(b, itemFrames, output);
            }

            // None of the recipes worked
            Utils.send(p, "&cInvalid Recipe!");
            return;
        }
        int exp = Utils.getTotalExperience(p);

        // Retrieve experience from cauldron
        if (p.isSneaking()) {
            // Check if cauldron is empty
            if (cauldronLevel == 0) {
                Utils.send(p, "&cThis Experience Cauldron is already empty");
                return;
            }

            // Add exp and decrease cauldron level
            p.giveExp(EXP_PER_LEVEL);
            changeLevel(b, -1);
            p.playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL, 1, 0.1f);
            return;
        }

        // Insert experience into cauldron
        // Exp requirement
        if (exp < EXP_PER_LEVEL) {
            Utils.send(p, "&cYou do not have enough exp to deposit");
            return;
        }

        // Check if cauldron is full
        if (cauldronLevel == MAX_CAULDRON_LEVEL) {
            Utils.send(p, "&cThis Experience Cauldron is full");
            return;
        }

        // Remove exp and raise cauldron level
        p.giveExp(-EXP_PER_LEVEL);
        changeLevel(b, 1);
        p.playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL, 1, 1);
    }

    /**
     * Prevent changing the cauldron level through
     * other means
     *
     * @param e the {@link CauldronLevelChangeEvent}
     */
    @EventHandler(ignoreCancelled = true)
    void onCauldronLevelChange(CauldronLevelChangeEvent e) {
        SlimefunItem sfItem = BlockStorage.check(e.getBlock());
        if (sfItem != null && isItem(sfItem.getItem())) e.setCancelled(true);
    }

    /**
     * Changes the level of a cauldron
     *
     * @param b the cauldron block
     * @param i the amount to change it by
     */
    static void changeLevel(Block b, int i) {
        Material mat = b.getType();
        if (SERVER_VERSION.contains("1.17") && mat == Material.CAULDRON) {
            b.setType(Material.WATER_CAULDRON);
            Levelled cauldron = (Levelled) b.getBlockData();
            cauldron.setLevel(i);
            b.setBlockData(cauldron);

        } else { //Already water cauldron or version is pre 1.17
            Levelled cauldron = (Levelled) b.getBlockData();

            // Empty
            if (i == -1 && cauldron.getLevel() == 1 && SERVER_VERSION.contains("1.17"))
                b.setType(Material.CAULDRON);
            else {
                cauldron.setLevel(cauldron.getLevel() + i);
                b.setBlockData(cauldron);
            }
        }
    }

    static int getCauldronLevel(Block b) {
        Material mat = b.getType();
        if (SERVER_VERSION.contains("1.17")) {
            if (mat == Material.CAULDRON) return 0;
            else if (mat == Material.WATER_CAULDRON) {
                Levelled cauldron = (Levelled) b.getBlockData();
                return cauldron.getLevel();
            }
        } else { // Version is pre 1.17
            Levelled cauldron = (Levelled) b.getBlockData();
            return cauldron.getLevel();
        }
        return 0;
    }

    /**
     * Collects and returns the items in crafting item frames
     *
     * @param itemFrames the {@link ItemFrame}s used for crafting
     */
    static List<ItemStack> getFrameItems(List<ItemFrame> itemFrames) {
        List<ItemStack> frameItems = new ArrayList<>();
        for (ItemFrame frame : itemFrames) {
            ItemStack frameItem = frame.getItem();
            if (frameItem.getType() != Material.AIR) {
                SlimefunItem sfFrameItem = SlimefunItem.getByItem(frameItem);
                if (sfFrameItem != null) frameItems.add(sfFrameItem.getItem()); // Convert to SlimefunItemStack
                else frameItems.add(frameItem); // Add vanilla item
            }
        }
        return frameItems;
    }

    static ItemStack checkRecipe(List<ItemStack> frameItems, ItemStack[] recipeInputs) {
        // Build checklists
        List<ItemStack> inputItems = new ArrayList<>(frameItems);
        List<ItemStack> recipeItems = new ArrayList<>();

        // Add recipe items
        for (ItemStack recipeItem : recipeInputs) if (recipeItem != null) recipeItems.add(recipeItem);

        // Sort the items so they are in the same order
        inputItems.sort(new ItemStackComparator());
        recipeItems.sort(new ItemStackComparator());

        if (inputItems.size() == recipeItems.size()) {
            // Check if the items match
            for (int i = inputItems.size() - 1; i >= 0; i--) {
                if (SlimefunUtils.isItemSimilar(inputItems.get(i), recipeItems.get(i), false, true)) { // No issues found, continue
                    inputItems.remove(i);
                    recipeItems.remove(i);
                } else return null; // Item does not match, proceed to next recipe
            }
        }

        // Recipe found, return the output
        if (inputItems.size() == 0 && recipeItems.size() == 0)
            return RecipeType.getRecipeOutputList(MAGIC_BASIN, recipeInputs);

        // Recipe sizes did not match
        return null;
    }

    static void craft(Block b, List<ItemFrame> itemFrames, ItemStack output) {
        // Clear item frames
        for (ItemFrame frame : itemFrames) frame.setItem(new ItemStack(Material.AIR));

        // Consume exp from cauldron
        changeLevel(b, -1);

        // Drop output
        b.getWorld().playSound(b.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5F, 1F);

        Utils.runSync(() -> {
            b.getWorld().playEffect(b.getLocation(), org.bukkit.Effect.POTION_BREAK, 1);
            b.getWorld().dropItem(b.getLocation().add(0, 1, 0), output);
        }, 20);
    }
}