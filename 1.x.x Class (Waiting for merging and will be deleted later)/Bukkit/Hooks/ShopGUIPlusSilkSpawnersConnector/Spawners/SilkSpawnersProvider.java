package id.universenetwork.utilities.Bukkit.Hooks.ShopGUIPlusSilkSpawnersConnector.Spawners;

import net.brcdev.shopgui.spawner.external.provider.ExternalSpawnerProvider;
import org.bukkit.plugin.Plugin;

public interface SilkSpawnersProvider extends ExternalSpawnerProvider {
    void hookIntoSilkSpawners(Plugin v1);
}
