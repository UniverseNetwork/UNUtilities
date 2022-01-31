package com.connorlinfoot.actionbarapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static id.universenetwork.utilities.Bukkit.Manager.API.nmsver;
import static id.universenetwork.utilities.Bukkit.Manager.API.useOldMethods;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class ActionBarAPI {

    public static void sendActionBar(Player player, String message) {
        if (player.isOnline()) {
            ActionBarMessageEvent actionBarMessageEvent = new ActionBarMessageEvent(player, message);
            Bukkit.getPluginManager().callEvent(actionBarMessageEvent);
            if (!actionBarMessageEvent.isCancelled()) {
                try {
                    Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
                    Object craftPlayer = craftPlayerClass.cast(player);
                    Class<?> packetPlayOutChatClass = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
                    Class<?> packetClass = Class.forName("net.minecraft.server." + nmsver + ".Packet");
                    Object packet;
                    Class chatComponentTextClass;
                    Class iChatBaseComponentClass;
                    Object chatCompontentText;
                    if (useOldMethods) {
                        chatComponentTextClass = Class.forName("net.minecraft.server." + nmsver + ".ChatSerializer");
                        iChatBaseComponentClass = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
                        Method m3 = chatComponentTextClass.getDeclaredMethod("a", String.class);
                        chatCompontentText = iChatBaseComponentClass.cast(m3.invoke(chatComponentTextClass, "{\"text\": \"" + message + "\"}"));
                        packet = packetPlayOutChatClass.getConstructor(iChatBaseComponentClass, Byte.TYPE).newInstance(chatCompontentText, 2);
                    } else {
                        chatComponentTextClass = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
                        iChatBaseComponentClass = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
                        try {
                            Class<?> chatMessageTypeClass = Class.forName("net.minecraft.server." + nmsver + ".ChatMessageType");
                            Object[] chatMessageTypes = chatMessageTypeClass.getEnumConstants();
                            Object chatMessageType = null;
                            int var14 = chatMessageTypes.length;
                            for (Object obj : chatMessageTypes)
                                if (obj.toString().equals("GAME_INFO")) chatMessageType = obj;
                            chatCompontentText = chatComponentTextClass.getConstructor(String.class).newInstance(message);
                            packet = packetPlayOutChatClass.getConstructor(iChatBaseComponentClass, chatMessageTypeClass).newInstance(chatCompontentText, chatMessageType);
                        } catch (ClassNotFoundException var17) {
                            chatCompontentText = chatComponentTextClass.getConstructor(String.class).newInstance(message);
                            packet = packetPlayOutChatClass.getConstructor(iChatBaseComponentClass, Byte.TYPE).newInstance(chatCompontentText, 2);
                        }
                    }
                    Method craftPlayerHandleMethod = craftPlayerClass.getDeclaredMethod("getHandle");
                    Object craftPlayerHandle = craftPlayerHandleMethod.invoke(craftPlayer);
                    Field playerConnectionField = craftPlayerHandle.getClass().getDeclaredField("playerConnection");
                    chatCompontentText = playerConnectionField.get(craftPlayerHandle);
                    Method sendPacketMethod = chatCompontentText.getClass().getDeclaredMethod("sendPacket", packetClass);
                    sendPacketMethod.invoke(chatCompontentText, packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendActionBar(final Player player, final String message, int duration) {
        sendActionBar(player, message);
        if (duration >= 0) (new BukkitRunnable() {
            @Override
            public void run() {
                ActionBarAPI.sendActionBar(player, "");
            }
        }).runTaskLater(plugin, duration + 1);
        while (duration > 40) {
            duration -= 40;
            (new BukkitRunnable() {
                @Override
                public void run() {
                    ActionBarAPI.sendActionBar(player, message);
                }
            }).runTaskLater(plugin, duration);
        }
    }

    public static void sendActionBarToAllPlayers(String message) {
        sendActionBarToAllPlayers(message, -1);
    }

    public static void sendActionBarToAllPlayers(String message, int duration) {
        for (Player p : Bukkit.getOnlinePlayers()) sendActionBar(p, message, duration);
    }
}