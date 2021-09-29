package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Items.Tools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Optional;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Utils.FluffyItems.ADVANCED_CHARGING_BENCH;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.*;
import static org.bukkit.inventory.EquipmentSlot.HAND;

public class ACBUpgradeCard extends SimpleSlimefunItem<ItemHandler> {
    public ACBUpgradeCard(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemHandler getItemHandler() {
        return (ItemUseHandler) e -> {
            // Prevent offhand right clicks
            if (e.getHand() != HAND) return;

            // Block exists
            Optional<Block> optB = e.getClickedBlock();
            if (!optB.isPresent()) return;

            // Prevent menu opening and interactions
            e.cancel();
            Block b = optB.get();
            SlimefunItem sfItem = check(b);
            Player p = e.getPlayer();
            ItemStack card = p.getInventory().getItemInMainHand();

            // Make sure the block is an ACB
            if (sfItem == null || sfItem != ADVANCED_CHARGING_BENCH.getItem()) {
                Utils.send(e.getPlayer(), "&cYou can only use this card on an Advanced Charging Bench");
                return;
            }

            // Increment the tier by 1
            int tier = Integer.parseInt(getLocationInfo(b.getLocation(), "tier"));
            if (tier == 100) {
                Utils.send(e.getPlayer(), "&cThis Advanced Charging Bench is maxed (Tier 100)");
                return;
            }
            tier++;
            addBlockInfo(b.getLocation(), "tier", String.valueOf(tier));

            // Remove a card
            card.setAmount(card.getAmount() - 1);
            Utils.send(e.getPlayer(), "&aThis Advanced Charging Bench has been upgraded! &eTier: " + tier);
        };
    }
}