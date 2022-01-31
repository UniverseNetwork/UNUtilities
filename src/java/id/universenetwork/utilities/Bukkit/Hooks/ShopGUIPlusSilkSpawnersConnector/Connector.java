package id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector;

import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class Connector {
    static id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Spawners.SilkSpawnersProvider spawnerProvider;

    public static void hooks() {
        System.out.println(prefix + " §6Found SilkSpawners & ShopGUI+. Hooking...");
        hookIntoSilkSpawners();
        if (hookIntoShopGui())
            System.out.println(prefix + " §aSuccessfully hooked with SilkSpawners as Spawner Provider from ShopGUI+");
    }

    static void hookIntoSilkSpawners() {
        if (usingLegacySilkSpawners())
            spawnerProvider = new id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Spawners.SilkSpawners5Provider();
        else
            spawnerProvider = new id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Spawners.SilkSpawners6Provider();
        spawnerProvider.hookIntoSilkSpawners(Bukkit.getPluginManager().getPlugin("SilkSpawners"));
    }

    static boolean hookIntoShopGui() {
        try {
            net.brcdev.shopgui.ShopGuiPlusApi.registerSpawnerProvider(spawnerProvider);
            return true;
        } catch (net.brcdev.shopgui.exception.api.ExternalSpawnerProviderNameConflictException e) {
            Bukkit.getLogger().warning(prefix + " §eFailed to hook into ShopGUI+: §c" + e.getMessage());
            return false;
        }
    }

    static boolean usingLegacySilkSpawners() {
        String version = Bukkit.getPluginManager().getPlugin("SilkSpawners").getDescription().getVersion();
        int versionMajorNumber;
        try {
            versionMajorNumber = Integer.parseInt(String.valueOf(version.charAt(0)));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException v4) {
            return true;
        }
        return versionMajorNumber < 6;
    }
}