package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SMG;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class SimpleMaterialGenerator {
    public SimpleMaterialGenerator() {
        if (Enabled("SMG")) {
            SMGItemSetup.setup();
            System.out.println(prefix + " §bSuccessfully Registered §dSMG §bAddon");
        }
    }
}
