package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Items;

import id.universenetwork.utilities.Bukkit.Utils.Color;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;

/**
 * Charms are held in the offhand to provide
 * various buffs to the player
 *
 * @author NCBPFluffyBear
 */
public class AttributeCharms extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ItemUseHandler> implements org.bukkit.event.Listener {
    static final NamespacedKey inspectedKey = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "inspected");
    static final int LORE_INDEX = 1;
    final Charm type;

    public AttributeCharms(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe, Charm type) {
        super(itemGroup, item, recipeType, recipe);
        this.type = type;
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            ItemStack charm = e.getItem();
            org.bukkit.inventory.meta.ItemMeta charmMeta = charm.getItemMeta();
            org.bukkit.entity.Player p = e.getPlayer();

            // Don't reinspect charms
            if (PersistentDataAPI.getByte(charmMeta, inspectedKey, (byte) 0) == 1) return;

            // Add specified attribute to offhand
            double level = java.util.concurrent.ThreadLocalRandom.current().nextDouble(type.minLvl, type.maxLvl);
            AttributeModifier modifier = new AttributeModifier(java.util.UUID.randomUUID(), type.attribute.getKey().getKey(), level, AttributeModifier.Operation.ADD_NUMBER, org.bukkit.inventory.EquipmentSlot.OFF_HAND);
            charmMeta.addAttributeModifier(type.attribute, modifier);

            // Update lore
            java.util.List<String> lore = charmMeta.getLore();
            lore.set(LORE_INDEX, Color.Translate("&aThis charm has been inspected"));
            charmMeta.setLore(lore);

            p.playSound(p.getLocation(), org.bukkit.Sound.BLOCK_BELL_RESONATE, 1, 1);

            // Set inspected status to true
            PersistentDataAPI.setByte(charmMeta, inspectedKey, (byte) 1);
            charm.setItemMeta(charmMeta);
        };
    }

    public enum Charm {
        MOVEMENT_SPEED(Attribute.GENERIC_MOVEMENT_SPEED, 0.01, 0.2),
        ATTACK_SPEED(Attribute.GENERIC_ATTACK_SPEED, 0.1, 0.5),
        FLY_SPEED(Attribute.GENERIC_FLYING_SPEED, 0.01, 0.3),
        DAMAGE(Attribute.GENERIC_ATTACK_DAMAGE, 0.01, 0.5),
        MAX_HEALTH(Attribute.GENERIC_MAX_HEALTH, 1, 5);
        final Attribute attribute;
        final double minLvl;
        final double maxLvl;

        Charm(Attribute attribute, double minLvl, double maxLvl) {
            this.attribute = attribute;
            this.minLvl = minLvl;
            this.maxLvl = maxLvl;
        }
    }
}