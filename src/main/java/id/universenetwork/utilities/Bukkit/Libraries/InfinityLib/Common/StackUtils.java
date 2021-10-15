package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@UtilityClass
@ParametersAreNonnullByDefault
public final class StackUtils {

    private static final NamespacedKey ID_KEY = Slimefun.getItemDataService().getKey();

    @Nullable
    public static String getId(ItemStack item) {
        if (item instanceof SlimefunItemStack) return ((SlimefunItemStack) item).getItemId();
        else if (item.hasItemMeta()) return getId(item.getItemMeta());
        else return null;
    }

    @Nullable
    public static String getId(ItemMeta meta) {
        return meta.getPersistentDataContainer().get(ID_KEY, PersistentDataType.STRING);
    }

    @NotNull
    public static String getIdOrType(ItemStack item) {
        if (item instanceof SlimefunItemStack) return ((SlimefunItemStack) item).getItemId();
        else if (item.hasItemMeta()) {
            String id = getId(item.getItemMeta());
            return id == null ? item.getType().name() : id;
        } else return item.getType().name();
    }

    @Nullable
    public static ItemStack itemById(String id) {
        SlimefunItem item = SlimefunItem.getById(id);
        return item == null ? null : item.getItem().clone();
    }

    @NotNull
    public static ItemStack itemByIdOrType(String idOrType) {
        SlimefunItem item = SlimefunItem.getById(idOrType);
        return item == null ? new ItemStack(Material.valueOf(idOrType)) : item.getItem().clone();
    }

    public static boolean isSimilar(@Nullable ItemStack first, @Nullable ItemStack second) {
        if (first == null || first.getType().isAir()) return second == null || second.getType().isAir();
        else if (second == null || second.getType().isAir()) return false;
        else if (first.hasItemMeta()) {
            if (second.hasItemMeta()) {
                ItemMeta firstMeta = first.getItemMeta();
                ItemMeta secondMeta = second.getItemMeta();
                String firstId = getId(firstMeta);
                if (firstId == null) {
                    if (getId(secondMeta) == null) {
                        if (first.getType() == second.getType()) {
                            if (firstMeta.hasDisplayName())
                                return secondMeta.hasDisplayName() && firstMeta.getDisplayName().equals(secondMeta.getDisplayName());
                            else return !secondMeta.hasDisplayName();
                        } else return false;
                    } else return false;
                } else return firstId.equals(getId(secondMeta));
            } else return false;
        } else if (second.hasItemMeta()) return false;
        else return first.getType() == second.getType();
    }
}