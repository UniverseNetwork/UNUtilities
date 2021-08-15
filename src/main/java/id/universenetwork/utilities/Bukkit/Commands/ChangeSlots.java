package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Enums.Features.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static id.universenetwork.utilities.Bukkit.Manager.Config.MPCCMessage;
import static id.universenetwork.utilities.Bukkit.Manager.Sender.send;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class ChangeSlots implements CommandExecutor {
    private Field maxPlayersField;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("unutilities.command.changeslots")) {
            if (Config.MPCCSettings(MaxPlayerChangerCommand.ENABLED)) {
                if (args.length == 1) {
                    try {
                        changeSlots(Integer.parseInt(args[0]));
                        send(sender, MPCCMessage(MaxPlayerChangerCommand.SUCCESSMSG).replaceAll("%n%", args[0]));
                    } catch (NumberFormatException v6) {
                        send(sender, MPCCMessage(MaxPlayerChangerCommand.NONUMMSG));
                        return false;
                    } catch (ReflectiveOperationException v7) {
                        send(sender, MPCCMessage(MaxPlayerChangerCommand.ERRMSG));
                        return false;
                    }
                } else {
                    send(sender, MPCCMessage(MaxPlayerChangerCommand.NOARGMSG));
                    return false;
                }
            } else {
                send(sender, MPCCMessage(MaxPlayerChangerCommand.DISABLEDMSG));
                return false;
            }
        } else {
            send(sender, Config.Settings(Settings.NOPERMISSION));
            return false;
        }
        return true;
    }

    private void changeSlots(int slots) throws ReflectiveOperationException {
        Method serverGetHandle = plugin.getServer().getClass().getDeclaredMethod("getHandle");
        Object playerList = serverGetHandle.invoke(plugin.getServer());
        if (maxPlayersField == null) {
            maxPlayersField = getMaxPlayersField(playerList);
        }
        maxPlayersField.setInt(playerList, slots);
    }

    private Field getMaxPlayersField(Object playerList) throws ReflectiveOperationException {
        Class playerListClass = playerList.getClass().getSuperclass();
        try {
            Field field = playerListClass.getDeclaredField("maxPlayers");
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException v8) {
            Field[] v4 = playerListClass.getDeclaredFields();
            int v5 = v4.length;

            for (int v6 = 0; v6 < v5; ++v6) {
                Field field = v4[v6];
                if (field.getType() == Integer.TYPE) {
                    field.setAccessible(true);
                    if (field.getInt(playerList) == Bukkit.getMaxPlayers()) {
                        return field;
                    }
                }
            }
            throw new NoSuchFieldException("Unable to find maxPlayers field in " + playerListClass.getName());
        }
    }
}
