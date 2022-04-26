package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.PrivateStorage;

import org.bukkit.entity.Player;

public enum ChestProtectionLevel {
    /**
     * A public chest can be accessed by any {@link Player}
     * who has the permission to access chests in the {@link org.bukkit.Location}.
     * This way protection plugins can keep the chests safe.
     */
    PUBLIC,

    /**
     * A private chest can only be accessed by the very same {@link Player}
     * who has also placed the chest down.
     * <p>
     * Note: These chests cannot be used in cargo networks.
     */
    PRIVATE
}