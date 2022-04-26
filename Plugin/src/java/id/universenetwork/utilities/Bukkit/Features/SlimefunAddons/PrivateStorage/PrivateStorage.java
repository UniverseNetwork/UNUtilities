package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.PrivateStorage;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;

public class PrivateStorage extends id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SFInstance {
    @Override
    public void Load() {
        ItemGroup itemGroup = new ItemGroup(id.universenetwork.utilities.Bukkit.UNUtilities.createKey("private_storage"), new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead.getItemStack(io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin.fromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThlNTU0NGFmN2Y1NDg5Y2MyNzQ5MWNhNjhmYTkyMzg0YjhlYTVjZjIwYjVjODE5OGFkYjdiZmQxMmJjMmJjMiJ9fX0=")), "&7Private Storage - Chests and Safes", "", "&a> Click to open"));
        new id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.PrivateStorage.Storage.PublicChests(this, itemGroup);
        new id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.PrivateStorage.Storage.PrivateChests(this, itemGroup);
    }
}