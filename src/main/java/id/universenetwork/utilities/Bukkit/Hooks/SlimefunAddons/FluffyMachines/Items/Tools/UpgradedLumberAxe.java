package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Orientable;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class UpgradedLumberAxe extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {
    static final int MAX_BROKEN = 200;
    static final int MAX_STRIPPED = 200;
    static final int RANGE = 2;
    final ItemSetting<Boolean> triggerOtherPlugins = new ItemSetting<>(this, "trigger-other-plugins", true);

    public UpgradedLumberAxe(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(onBlockBreak());
        addItemSetting(triggerOtherPlugins);
    }

    ToolUseHandler onBlockBreak() {
        return (e, tool, fortune, drops) -> {
            if (Tag.LOGS.getValues().contains(e.getBlock().getType())) {
                List<Block> logs = find(e.getBlock(), MAX_BROKEN, b -> Tag.LOGS.isTagged(b.getType()));
                logs.remove(e.getBlock());
                for (Block b : logs)
                    if (Slimefun.getProtectionManager().hasPermission(e.getPlayer(), b, Interaction.BREAK_BLOCK)) {
                        b.breakNaturally(tool);
                        if (triggerOtherPlugins.getValue())
                            Bukkit.getPluginManager().callEvent(new AlternateBreakEvent(b, e.getPlayer()));
                    }
            }
        };
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (e.getClickedBlock().isPresent()) {
                Block block = e.getClickedBlock().get();
                if (isUnstrippedLog(block)) {
                    List<Block> logs = find(block, MAX_STRIPPED, this::isUnstrippedLog);
                    logs.remove(block);
                    for (Block b : logs)
                        if (Slimefun.getProtectionManager().hasPermission(e.getPlayer(), b, Interaction.BREAK_BLOCK))
                            stripLog(b);
                }
            }
        };
    }

    boolean isUnstrippedLog(Block block) {
        return Tag.LOGS.isTagged(block.getType()) && !block.getType().name().startsWith("STRIPPED_");
    }

    void stripLog(Block b) {
        b.getWorld().playSound(b.getLocation(), Sound.ITEM_AXE_STRIP, 1, 1);
        Axis axis = ((Orientable) b.getBlockData()).getAxis();
        b.setType(Material.valueOf("STRIPPED_" + b.getType().name()));
        Orientable orientable = (Orientable) b.getBlockData();
        orientable.setAxis(axis);
        b.setBlockData(orientable);
    }

    public static List<Block> find(Block b, int limit, Predicate<Block> predicate) {
        List<Block> list = new LinkedList<>();
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
}