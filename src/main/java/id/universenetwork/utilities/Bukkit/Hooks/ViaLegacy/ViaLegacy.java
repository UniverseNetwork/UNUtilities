package id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy;

import id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Adapter.WorldEventAdapter;
import id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Injector.BoundingBoxFixer;
import id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Listeners.*;
import id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.VersionInfo.VersionInformer;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.Enums.Features.ViaLegacy.*;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners;
import static id.universenetwork.utilities.Bukkit.Manager.Config.VLBoolean;

public class ViaLegacy {
    public ViaLegacy() {
        new org.bukkit.scheduler.BukkitRunnable() {
            @Override
            public void run() {
                int serverProtocol = com.viaversion.viaversion.api.Via.getAPI().getServerVersion().lowestSupportedVersion();
                if (serverProtocol == -1) return;
                cancel();
                if (serverProtocol > 5) {
                    if (VLBoolean(ENCHANTING_GUI_FIX)) registerListeners(new EnchantingListener());
                    if (VLBoolean(SLIME_FIX)) registerListeners(new BounceListener());
                }
                if (serverProtocol > 78 && VLBoolean(BREWING_STAND_GUI_FIX)) registerListeners(new BrewingListener());
                if (serverProtocol > 84 && VLBoolean(LILY_PAD_FIX)) BoundingBoxFixer.fixLilyPad();
                if (serverProtocol > 48 && VLBoolean(LADDER_FIX)) BoundingBoxFixer.fixLadder();
                if (serverProtocol > 47 && VLBoolean(SOUND_FIX)) registerListeners(new SoundListener());
                if (serverProtocol > 76 && VLBoolean(ELYTRA_FIX)) registerListeners(new ElytraListener());
                if (serverProtocol > 54 && VLBoolean(AREA_EFFECT_CLOUD_PARTICLES))
                    registerListeners(new AreaEffectCloudListener());
                if (VLBoolean(VERSIONINFO_ACTIVE)) new VersionInformer();
                if (VLBoolean(POTION_FIX)) {
                    if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
                        final com.comphenix.protocol.ProtocolManager pm = com.comphenix.protocol.ProtocolLibrary.getProtocolManager();
                        pm.addPacketListener(new PotionListener());
                        pm.addPacketListener(new WorldEventAdapter());
                    } else
                        Bukkit.getLogger().severe(UNUtilities.prefix + " §eProtocolLib not found. §cViaLegacy could not be fix potion!");
                }
            }
        }.runTaskTimer(UNUtilities.plugin, 1L, 1L);
        System.out.println(UNUtilities.prefix + " §aViaVersion found. §bViaLegacy features has been enabled");
    }
}