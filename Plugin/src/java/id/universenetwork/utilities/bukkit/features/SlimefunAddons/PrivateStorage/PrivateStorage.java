package id.universenetwork.utilities.bukkit.features.SlimefunAddons.PrivateStorage;

import id.universenetwork.utilities.bukkit.features.SlimefunAddons.PrivateStorage.Storage.PrivateChests;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.PrivateStorage.Storage.PublicChests;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.SfAddon;
import id.universenetwork.utilities.bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;

public class PrivateStorage extends SfAddon {
    @Override
    public void Load() {
        ItemGroup itemGroup = new ItemGroup(UNUtilities.createKey("private_storage"), new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThlNTU0NGFmN2Y1NDg5Y2MyNzQ5MWNhNjhmYTkyMzg0YjhlYTVjZjIwYjVjODE5OGFkYjdiZmQxMmJjMmJjMiJ9fX0=")), "&7Private Storage - Chests and Safes", "", "&a> Click to open"));
        new PublicChests(this, itemGroup);
        new PrivateChests(this, itemGroup);
    }
}