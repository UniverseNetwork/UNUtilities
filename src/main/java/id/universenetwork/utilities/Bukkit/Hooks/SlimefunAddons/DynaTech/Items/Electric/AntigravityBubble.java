package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static org.bukkit.Bukkit.getPlayer;

public class AntigravityBubble extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    final Set<UUID> enabledPlayers = new HashSet<>();
    static final int[] BORDER = new int[]{1, 2, 6, 7, 9, 10, 11, 15, 16, 17, 19, 20, 24, 25};
    static final int[] BORDER_IN = new int[]{3, 4, 5, 12, 14, 21, 22, 23};
    static final int[] BORDER_OUT = new int[]{0, 8, 18, 26};

    public AntigravityBubble(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onBreak());
    }

    @Override
    public void preRegister() {
        addItemHandler(new me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker() {
            @Override
            public void tick(Block b, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem sfItem, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                AntigravityBubble.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    @Override
    public void tick(Block b) {
        Collection<Entity> bubbledEntities = b.getWorld().getNearbyEntities(b.getLocation(), 25, 25, 25);
        for (Entity entity : bubbledEntities)
            if (entity instanceof Player) {
                Player p = (Player) entity;
                if (!p.getAllowFlight() && p.hasPermission("unutilities.use.antigravitybubble")) {
                    enabledPlayers.add(p.getUniqueId());
                    p.setAllowFlight(true);
                    removeCharge(b.getLocation(), getEnergyConsumption());
                }
            }

        final Iterator<UUID> playerIterator = enabledPlayers.iterator();
        while (playerIterator.hasNext()) {
            final UUID uuid = playerIterator.next();
            Player p = getPlayer(uuid);
            if (p != null && !bubbledEntities.contains(p)) {
                p.setAllowFlight(false);
                p.setFlying(false);
                p.setFallDistance(0.0f);
                playerIterator.remove();
            }
        }
    }

    io.github.thebusybiscuit.slimefun4.api.items.ItemHandler onBreak() {
        return new io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(org.bukkit.event.block.BlockBreakEvent e, ItemStack tool, List<ItemStack> drops) {
                final Iterator<UUID> playerIterator = enabledPlayers.iterator();
                while (playerIterator.hasNext()) {
                    final UUID uuid = playerIterator.next();
                    Player p = getPlayer(uuid);
                    if (p != null) {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.setFallDistance(0.0F);
                        playerIterator.remove();
                    }
                }
            }
        };
    }

    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public String getMachineIdentifier() {
        return "ANTIGRAVITY_BUBBLE";
    }

    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        return borders;
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{13};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{13};
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(org.bukkit.Material.DRAGON_EGG);
    }

    @Override
    public int getProgressBarSlot() {
        return 4;
    }
}