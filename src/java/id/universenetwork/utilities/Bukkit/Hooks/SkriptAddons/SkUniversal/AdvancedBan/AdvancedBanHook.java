package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan;

import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.manager.UUIDManager;

public class AdvancedBanHook {
    public final static PunishmentManager punishmentManager = PunishmentManager.get();
    public final static UUIDManager uuidManager = UUIDManager.get();

    static {
        ch.njol.skript.Skript.registerEvent("AdvancedBan - Punishment", ch.njol.skript.lang.util.SimpleEvent.class, me.leoko.advancedban.bukkit.event.PunishmentEvent.class, "[AdvancedBan] [player] punish[ment]").description("Called when a player is punished.\n\n" + "**Event Expressions:**\n" + "`[the] [AdvancedBan] punished player`\n" + "`[the] [AdvancedBan] punisher`\n" + "`[the] [AdvancedBan] punish[ment] type`\n" + "`[the] [AdvancedBan] punish[ment] reason`\n" + "`[the] [AdvancedBan] punish[ment] (length|duration)`").examples("on punishment:", "\tif punish type is \"mute\":", "\t\tbroadcast \"%punished player% has been muted by %punisher% for %punish duration% with reason %punish reason%!\"");
    }
}