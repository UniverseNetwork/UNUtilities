package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Enums.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Config.MPCCMessage;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class SetSlots extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    Field maxPlayersField;

    public SetSlots() {
        super("SetSlots", "Max Player Changer Command Features", "unutilities.command.setslots", false, "changeslots");
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (Config.MPCCBoolean(MaxPlayerChangerCommand.ENABLED)) {
            if (args.length == 1) {
                try {
                    changeSlots(Integer.parseInt(args[0]));
                    sender.sendMessage(org.apache.commons.lang.StringUtils.replace(MPCCMessage(MaxPlayerChangerCommand.SUCCESSMSG), "%n%", args[0]));
                } catch (NumberFormatException e) {
                    sender.sendMessage(MPCCMessage(MaxPlayerChangerCommand.NONUMMSG));
                } catch (ReflectiveOperationException e) {
                    sender.sendMessage(MPCCMessage(MaxPlayerChangerCommand.ERRMSG));
                    Bukkit.getLogger().log(java.util.logging.Level.SEVERE, id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §cAn error occurred while updating max players", e);
                }
            } else sender.sendMessage(MPCCMessage(MaxPlayerChangerCommand.NOARGMSG));
        } else sender.sendMessage(MPCCMessage(MaxPlayerChangerCommand.DISABLEDMSG));
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        return java.util.Collections.emptyList();
    }

    void changeSlots(int slots) throws ReflectiveOperationException {
        Method serverGetHandle = plugin.getServer().getClass().getDeclaredMethod("getHandle");
        Object playerList = serverGetHandle.invoke(plugin.getServer());
        if (maxPlayersField == null) maxPlayersField = getMaxPlayersField(playerList);
        maxPlayersField.setInt(playerList, slots);
    }

    Field getMaxPlayersField(Object playerList) throws ReflectiveOperationException {
        Class<?> playerListClass = playerList.getClass().getSuperclass();
        try {
            Field field = playerListClass.getDeclaredField("maxPlayers");
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            for (Field field : playerListClass.getDeclaredFields()) {
                if (field.getType() != int.class) continue;
                field.setAccessible(true);
                if (field.getInt(playerList) == Bukkit.getMaxPlayers()) return field;
            }
            throw new NoSuchFieldException("Unable to find maxPlayers field in " + playerListClass.getName());
        }
    }
}