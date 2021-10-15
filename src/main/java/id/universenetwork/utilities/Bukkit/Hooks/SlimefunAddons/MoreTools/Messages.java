package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools;

import java.util.Objects;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Manager.Color.Translator;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;

public enum Messages {
    CRESCENTHAMMER_BLOCKBREAKING("Block-Breaking"),
    CRESCENTHAMMER_COOLDOWN("Cooldown"),
    CRESCENTHAMMER_DISMANTLEFAIL("Dismantle-Fail"),
    CRESCENTHAMMER_ROTATEFAIL("Rotate-Fail"),
    CRESCENTHAMMER_CHANNELCHANGEFAIL("Channel-Change-Fail"),
    CRESCENTHAMMER_CHANNELCHANGESUCCESS("Channel-Change-Success");
    final String message;

    Messages(String path) {
        message = Translator(Objects.requireNonNull(get().getString(ADDONSSETTINGS.getConfigPath() + "MoreTools.Messages.Items.Crescent-Hammer." + path)));
    }

    public String getMessage() {
        return message;
    }
}