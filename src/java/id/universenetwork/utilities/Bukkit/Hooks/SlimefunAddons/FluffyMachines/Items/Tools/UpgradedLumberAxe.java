package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.block.Block;
import org.bukkit.block.data.Orientable;

import java.util.List;
import java.util.function.Predicate;

import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.BREAK_BLOCK;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.checkID;
import static org.bukkit.Tag.LOGS;

public class UpgradedLumberAxe extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    static final int MAX_BROKEN = 200;
    static final int MAX_STRIPPED = 200;
    static final int RANGE = 2;
    final ItemSetting<Boolean> triggerOtherPlugins = new ItemSetting<>(this, "trigger-other-plugins", true);

    public UpgradedLumberAxe(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static List<Block> find(Block b, int limit, Predicate<Block> predicate) {
        List<Block> list = new java.util.LinkedList<>();
        expand(b, list, limit, predicate);
        return list;
    }

    static void expand(Block anchor, List<Block> list, int limit, Predicate<Block> predicate) {
        if (list.size() < limit) {
            list.add(anchor);
            for (int x = -RANGE; x <= RANGE; x++)
                for (int z = -RANGE; z <= RANGE; z++)
                    for (int y = -RANGE; y <= RANGE; y++) {
                        Block next = anchor.getRelative(x, y, z);
                        if (!list.contains(next) && predicate.test(next)) expand(next, list, limit, predicate);
                    }
        }
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(onBlockBreak());
        addItemSetting(triggerOtherPlugins);
    }

    io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler onBlockBreak() {
        return (e, tool, fortune, drops) -> {
            if (LOGS.getValues().contains(e.getBlock().getType())) {
                // Prevent use on Slimefun blocks
                if (checkID(e.getBlock()) != null) return;
                List<Block> logs = find(e.getBlock(), MAX_BROKEN, b -> LOGS.isTagged(b.getType()));
                logs.remove(e.getBlock());
                for (Block b : logs)
                    if (getProtectionManager().hasPermission(e.getPlayer(), b, BREAK_BLOCK) && checkID(b) == null) {
                        b.breakNaturally(tool);
                        if (triggerOtherPlugins.getValue())
                            org.bukkit.Bukkit.getPluginManager().callEvent(new AlternateBreakEvent(b, e.getPlayer()));
                    }
            }
        };
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (e.getClickedBlock().isPresent()) {
                Block block = e.getClickedBlock().get();
                if (isUnstrippedLog(block)) {
                    List<Block> logs = find(block, MAX_STRIPPED, this::isUnstrippedLog);
                    logs.remove(block);
                    for (Block b : logs)
                        if (getProtectionManager().hasPermission(e.getPlayer(), b, BREAK_BLOCK) && checkID(b) == null)
                            stripLog(b);
                }
            }
        };
    }

    boolean isUnstrippedLog(Block block) {
        return LOGS.isTagged(block.getType()) && !block.getType().name().startsWith("STRIPPED_");
    }

    void stripLog(Block b) {
        b.getWorld().playSound(b.getLocation(), org.bukkit.Sound.ITEM_AXE_STRIP, 1, 1);
        org.bukkit.Axis axis = ((Orientable) b.getBlockData()).getAxis();
        b.setType(org.bukkit.Material.valueOf("STRIPPED_" + b.getType().name()));
        Orientable orientable = (Orientable) b.getBlockData();
        orientable.setAxis(axis);
        b.setBlockData(orientable);
    }
}