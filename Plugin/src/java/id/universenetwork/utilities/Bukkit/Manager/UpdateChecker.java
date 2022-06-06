package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.UNUtilities;
import id.universenetwork.utilities.Bukkit.Utils.Logger;
import id.universenetwork.utilities.Universal.Utils.GitHubLatestCommit;

import java.io.IOException;

public class UpdateChecker {
    public static void init() {
        UNUtilities.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(UNUtilities.plugin, () -> {
            Logger.info("&eChecking for updates...");
            try {
                if (GitHubLatestCommit.check("UniverseNetwork", "UNUtilities")
                        .equalsIgnoreCase(UNUtilities.commit))
                    Logger.info("&aThis plugin is already using the latest version!");
                else
                    Logger.warning("&eUpdates found! &6Immediately report this to &bARVIN&a3108 &cI&fD &6to update it.");
            } catch (IOException ignore) {
            }
        }, 0, 300000);
    }
}