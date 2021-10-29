package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Enums.Features.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Config.MPCCMessage;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class ChangeSlots extends Commands {
    Field maxPlayersField;

    public ChangeSlots() {
        super("ChangeSlots", "Max Player Changer Command Features", "unutilities.command.changeslots", false, "setslots");
        List<String> alias = new ArrayList<>();
        alias.add("setslots");
        setAliases(alias);
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
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
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        return java.util.Collections.emptyList();
    }

    void changeSlots(int slots) throws ReflectiveOperationException {
        Method serverGetHandle = plugin.getServer().getClass().getDeclaredMethod("getHandle");
        Object playerList = serverGetHandle.invoke(plugin.getServer());
        if (maxPlayersField == null) {
            maxPlayersField = getMaxPlayersField(playerList);
        }
        maxPlayersField.setInt(playerList, slots);
    }

    Field getMaxPlayersField(Object playerList) throws ReflectiveOperationException {
        Class playerListClass = playerList.getClass().getSuperclass();
        try {
            Field field = playerListClass.getDeclaredField("maxPlayers");
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException v8) {
            Field[] v4 = playerListClass.getDeclaredFields();
            int v5 = v4.length;

            for (Field field : v4) {
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