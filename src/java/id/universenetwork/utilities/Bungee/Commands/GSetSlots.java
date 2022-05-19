package id.universenetwork.utilities.Bungee.Commands;

import id.universenetwork.utilities.Bungee.Manager.Commands;
import id.universenetwork.utilities.Bungee.Manager.Settings;
import id.universenetwork.utilities.Universal.Annotations.CommandProperties;
import net.md_5.bungee.api.CommandSender;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

import static id.universenetwork.utilities.Bungee.Enums.MaxPlayerChangerCommand.*;
import static id.universenetwork.utilities.Bungee.UNUtilities.plugin;

@CommandProperties(Name = "GSetSlots", Permission = "unutilities.command.changeslots", PlayerOnly = false, Aliases = {"GChangeSlots"})
public class GSetSlots extends Commands {
    @Override
    public void Execute(CommandSender Sender, String[] Args) {
        if (Settings.MPCCBoolean(ENABLED)) {
            if (Args.length == 1) {
                try {
                    int s = Integer.parseInt(Args[0]);
                    changeSlots(s);
                    if (Settings.MPCCBoolean(SOR)) updateBungeeConfig(s);
                    Sender.sendMessage(StringUtils.replace(Settings.MPCCString(SUCCESSMSG), "%n%", Args[0]));
                } catch (NumberFormatException e) {
                    Sender.sendMessage(Settings.MPCCString(NONUMMSG));
                } catch (ReflectiveOperationException e) {
                    Sender.sendMessage(Settings.MPCCString(ERRMSG));
                }
            } else Sender.sendMessage(Settings.MPCCString(NOARGMSG));
        } else Sender.sendMessage(Settings.MPCCString(DISABLEDMSG));
    }

    @Override
    public Iterable<String> TabComplete(CommandSender Sender, String[] Args) {
        return Collections.emptyList();
    }

    void changeSlots(int slots) throws ReflectiveOperationException {
        Class<?> configClass = plugin.getProxy().getConfig().getClass();
        if (!configClass.getSuperclass().equals(Object.class)) configClass = configClass.getSuperclass();
        Field playerLimitField = configClass.getDeclaredField("playerLimit");
        playerLimitField.setAccessible(true);
        playerLimitField.setInt(plugin.getProxy().getConfig(), slots);
    }

    void updateBungeeConfig(int slots) throws ReflectiveOperationException {
        Method setMethod = plugin.getProxy().getConfigurationAdapter().getClass().getDeclaredMethod("set", String.class, Object.class);
        setMethod.setAccessible(true);
        setMethod.invoke(plugin.getProxy().getConfigurationAdapter(), "player_limit", slots);
    }
}