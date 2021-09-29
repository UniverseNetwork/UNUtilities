package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PrivateStorage;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PrivateStorage.Storage.PrivateChests;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PrivateStorage.Storage.PublicChests;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class PrivateStorage {
    public PrivateStorage() {
        if (Enabled("PrivateStorage")) {
            ItemGroup itemGroup = new ItemGroup(new NamespacedKey(plugin, "private_storage"), new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThlNTU0NGFmN2Y1NDg5Y2MyNzQ5MWNhNjhmYTkyMzg0YjhlYTVjZjIwYjVjODE5OGFkYjdiZmQxMmJjMmJjMiJ9fX0=")), "&7Private Storage - Chests and Safes", "", "&a> Click to open"));
            new PublicChests(itemGroup);
            new PrivateChests(itemGroup);
            System.out.println(prefix + " §bSuccessfully Registered §dPrivateStorage §bAddon");
        }
    }
}