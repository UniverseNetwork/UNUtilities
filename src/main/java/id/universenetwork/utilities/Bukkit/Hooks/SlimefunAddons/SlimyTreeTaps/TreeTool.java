package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SlimyTreeTaps;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

class TreeTool extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable, DamageableItem {
    final int chance;
    final SlimefunItemStack output;

    public TreeTool(ItemGroup itemGroup, SlimefunItemStack item, int chance, SlimefunItemStack output, ItemStack[] recipe) {
        super(itemGroup, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
        this.chance = chance;
        this.output = output;
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            if (e.getClickedBlock().isPresent()) {
                Player p = e.getPlayer();
                Block b = e.getClickedBlock().get();
                if (isLog(b) && Slimefun.getProtectionManager().hasPermission(p, b, Interaction.BREAK_BLOCK)) {
                    p.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
                    if (ThreadLocalRandom.current().nextInt(100) < chance) {
                        b.setType(Material.valueOf("STRIPPED_" + b.getType().name()));
                        Location l = b.getRelative(e.getClickedFace()).getLocation().add(0.5, 0.5, 0.5);
                        b.getWorld().dropItem(l, output.clone());
                    }
                    damageItem(p, e.getItem());
                }
            }
        };
    }

    boolean isLog(Block b) {
        return b != null && Tag.LOGS.isTagged(b.getType()) && !b.getType().name().startsWith("STRIPPED_") && !BlockStorage.hasBlockInfo(b.getLocation());
    }

    @Override
    public boolean isDamageable() {
        return true;
    }
}