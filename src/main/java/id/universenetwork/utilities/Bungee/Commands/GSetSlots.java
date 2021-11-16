package id.universenetwork.utilities.Bungee.Commands;

import id.universenetwork.utilities.Bungee.Manager.Config;
import net.md_5.bungee.api.CommandSender;

import static id.universenetwork.utilities.Bungee.Enums.Features.MaxPlayerChangerCommand.*;
import static id.universenetwork.utilities.Bungee.UNUtilities.plugin;

public class GSetSlots extends id.universenetwork.utilities.Bungee.Manager.Commands {
    public GSetSlots() {
        super("GSetSlots", "unutilities.command.changeslots", false, "GChangeSlots");
    }

    @Override
    public void Execute(CommandSender Sender, String[] Args) {
        if (Config.MPCCBoolean(ENABLED)) {
            if (Args.length == 1) {
                try {
                    int s = Integer.parseInt(Args[0]);
                    changeSlots(s);
                    if (Config.MPCCBoolean(SOR)) updateBungeeConfig(s);
                    Sender.sendMessage(Config.MPCCString(SUCCESSMSG).replaceAll("%n%", Args[0]));
                } catch (NumberFormatException e) {
                    Sender.sendMessage(Config.MPCCString(NONUMMSG));
                } catch (ReflectiveOperationException e) {
                    Sender.sendMessage(Config.MPCCString(ERRMSG));
                }
            } else Sender.sendMessage(Config.MPCCString(NOARGMSG));
        } else Sender.sendMessage(Config.MPCCString(DISABLEDMSG));
    }

    @Override
    public Iterable<String> TabComplete(CommandSender Sender, String[] Args) {
        return java.util.Collections.emptyList();
    }

    void changeSlots(int slots) throws ReflectiveOperationException {
        Class<?> configClass = plugin.getProxy().getConfig().getClass();
        if (!configClass.getSuperclass().equals(Object.class)) configClass = configClass.getSuperclass();
        java.lang.reflect.Field playerLimitField = configClass.getDeclaredField("playerLimit");
        playerLimitField.setAccessible(true);
        playerLimitField.setInt(plugin.getProxy().getConfig(), slots);
    }

    void updateBungeeConfig(int slots) throws ReflectiveOperationException {
        java.lang.reflect.Method setMethod = plugin.getProxy().getConfigurationAdapter().getClass().getDeclaredMethod("set", String.class, Object.class);
        setMethod.setAccessible(true);
        setMethod.invoke(plugin.getProxy().getConfigurationAdapter(), "player_limit", slots);
    }
}