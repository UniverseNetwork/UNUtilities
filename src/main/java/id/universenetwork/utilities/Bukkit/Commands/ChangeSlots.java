package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Enums.Features.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Config.MPCCMessage;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class ChangeSlots extends Commands {
    private Field maxPlayersField;

    public ChangeSlots() {
        super("changeslots", "unutilities.command.changeslots", false);
    }

    @Override
    public void Execute(CommandSender sender, Command command, String[] args) {
        if (Config.MPCCBoolean(MaxPlayerChangerCommand.ENABLED)) {
            if (args.length == 1) {
                try {
                    changeSlots(Integer.parseInt(args[0]));
                    sender.sendMessage(MPCCMessage(MaxPlayerChangerCommand.SUCCESSMSG).replaceAll("%n%", args[0]));
                } catch (NumberFormatException v6) {
                    sender.sendMessage(MPCCMessage(MaxPlayerChangerCommand.NONUMMSG));
                } catch (ReflectiveOperationException v7) {
                    sender.sendMessage(MPCCMessage(MaxPlayerChangerCommand.ERRMSG));
                }
            } else {
                sender.sendMessage(MPCCMessage(MaxPlayerChangerCommand.NOARGMSG));
            }
        } else {
            sender.sendMessage(MPCCMessage(MaxPlayerChangerCommand.DISABLEDMSG));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, Command command, String str, String[] args) {
        return Collections.emptyList();
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
