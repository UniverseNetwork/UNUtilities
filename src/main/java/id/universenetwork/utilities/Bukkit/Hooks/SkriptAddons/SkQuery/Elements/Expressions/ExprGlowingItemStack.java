package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Patterns("[skquery] glowing %itemstacks%")
public class ExprGlowingItemStack extends SimplePropertyExpression<ItemStack, ItemStack> {
    @Override
    protected String getPropertyName() {
        return "glowy forme";
    }

    @Override
    public ItemStack convert(ItemStack itemStack) {
        if (itemStack.getType() == Material.BOW) itemStack.addUnsafeEnchantment(Enchantment.WATER_WORKER, 69);
        else itemStack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 69);
        ItemMeta metadata = itemStack.getItemMeta();
        metadata.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(metadata);
        return itemStack;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }
}