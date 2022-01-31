package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Utils;
import id.universenetwork.utilities.Bukkit.Utils.Color;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

/**
 * Holds large amounts of experience
 * Inspired by Thermal Foundation's Tome of Knowledge
 *
 * @author NCBPFluffyBear
 */
public class ExperienceTome extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem implements org.bukkit.event.Listener {
    static final NamespacedKey expAmount = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "exp-amount");
    static final int MAX_EXP = 1000000;
    static final int EXP_TRANSFER_RATE = 10;

    public ExperienceTome(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
    }

    @org.bukkit.event.EventHandler
    void onTomeUse(org.bukkit.event.player.PlayerInteractEvent e) {
        org.bukkit.entity.Player p = e.getPlayer();
        ItemStack tome = e.getItem();

        // Check if item is a tome
        if (!isItem(tome)) return;

        if (!this.canUse(p, true)) return;
        e.setCancelled(true);
        if (tome == null) return;
        org.bukkit.inventory.meta.ItemMeta tomeMeta = tome.getItemMeta();
        int tomeExp = PersistentDataAPI.getInt(tomeMeta, expAmount, 0);

        // Exp extraction
        if (p.isSneaking()) {

            // Check if the exp can be extracted from tome
            if (tomeExp == 0) {
                Utils.send(p, "&cThis Experience Tome is empty!");
                return;
            }
            int transferExp;

            // Left click is max withdraw
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
                transferExp = tomeExp;
            else transferExp = Math.min(tomeExp, EXP_TRANSFER_RATE);

            // Add Exp to player
            PersistentDataAPI.setInt(tomeMeta, expAmount, tomeExp -= transferExp);
            p.giveExp(transferExp);
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        } else {
            if (Utils.getTotalExperience(p) == 0) {
                Utils.send(p, "&cYou don't have enough exp!");
                return;
            }
            int transferExp;

            // Left click is max insert
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
                transferExp = Utils.getTotalExperience(p);
            else transferExp = Math.min(Utils.getTotalExperience(p), EXP_TRANSFER_RATE);

            // If overflow, decrease to fill tome
            if (transferExp + tomeExp > MAX_EXP) transferExp = MAX_EXP - tomeExp;

            // Check if exp can be added to the tome
            if (tomeExp + transferExp > MAX_EXP || transferExp == 0) {
                Utils.send(p, "&cThis Experience Tome is full!");
                return;
            }

            // Add Exp to player
            PersistentDataAPI.setInt(tomeMeta, expAmount, tomeExp += transferExp);
            p.giveExp(-transferExp);
            p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_AMBIENT_WATER, 1, 1);
        }

        // Update name to display stored amount
        tomeMeta.setDisplayName(Color.Translate("&eExperience Tome &a(" + tomeExp + " / 1000000)"));
        tome.setItemMeta(tomeMeta);
    }
}