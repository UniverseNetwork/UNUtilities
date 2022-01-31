package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.Items;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors.color;

public abstract class WirelessTerminal extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable {
    public WirelessTerminal(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public abstract int getRange();

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.getInteractEvent().setCancelled(true);
            ItemStack stack = e.getItem();
            org.bukkit.inventory.meta.ItemMeta im = stack.getItemMeta();
            java.util.List<String> lore = im.getLore();
            if (lore.isEmpty()) return;
            if (e.getClickedBlock().isPresent() && e.getSlimefunBlock().isPresent()) {
                Player p = e.getPlayer();
                Block b = e.getClickedBlock().get();
                if (e.getSlimefunBlock().get() instanceof AccessTerminal) {
                    lore.set(0, color("&8\u21E8 &7Linked to: &8") + b.getWorld().getName() + " X: " + b.getX() + " Y: " + b.getY() + " Z: " + b.getZ());
                    p.sendMessage(color("&bLink established!"));
                    im.setLore(lore);
                    stack.setItemMeta(im);
                    p.getInventory().setItemInMainHand(stack);
                } else openRemoteTerminal(p, stack, lore.get(0), getRange());
                e.cancel();
            } else openRemoteTerminal(e.getPlayer(), stack, lore.get(0), getRange());
        };
    }

    void openRemoteTerminal(Player p, ItemStack stack, String loc, int range) {
        if (loc.equals(color("&8\u21E8 &7Linked to: &cNowhere"))) {
            p.sendMessage(color("&4Failed &c- This Device has not been linked to a Chest Terminal!"));
            return;
        }
        loc = loc.replace(color("&8\u21E8 &7Linked to: &8"), "");
        org.bukkit.World world = org.bukkit.Bukkit.getWorld(loc.split(" X: ")[0]);
        if (world == null) {
            p.sendMessage(color("&4Failed &c- The Chest Terminal that this Device has been linked to no longer exists!"));
            return;
        }
        int x = Integer.parseInt(loc.split(" X: ")[1].split(" Y: ")[0]);
        int y = Integer.parseInt(loc.split(" Y: ")[1].split(" Z: ")[0]);
        int z = Integer.parseInt(loc.split(" Z: ")[1]);
        Block block = world.getBlockAt(x, y, z);
        if (!io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(p, block.getLocation(), io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK)) {
            p.sendMessage(color("&4You are not permitted to access this terminal in that area!"));
            return;
        }
        if (!BlockStorage.check(block, "CHEST_TERMINAL")) {
            p.sendMessage(color("&4Failed &c- The Chest Terminal that this Device has been linked to no longer exists!"));
            return;
        }
        float charge = getItemCharge(stack);
        if (charge < 0.5F) {
            p.sendMessage(color("&4Failed &c- You are out of Energy!"));
            return;
        }
        if (range > 0 && !world.getUID().equals(p.getWorld().getUID())) {
            p.sendMessage(color("&4Failed &c- You are out of Range!"));
            return;
        }
        if (range > 0 && block.getLocation().distance(p.getLocation()) > range) {
            p.sendMessage(color("&4Failed &c- You are out of Range!"));
            return;
        }
        removeItemCharge(stack, 0.5F);
        BlockStorage.getInventory(block).open(p);
    }
}