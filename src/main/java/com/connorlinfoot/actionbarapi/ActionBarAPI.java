package com.connorlinfoot.actionbarapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

import static id.universenetwork.utilities.Bukkit.Manager.API.nmsver;
import static id.universenetwork.utilities.Bukkit.Manager.API.useOldMethods;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class ActionBarAPI implements Listener {

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
                            Object[] var13 = chatMessageTypes;
                            int var14 = chatMessageTypes.length;

                            for (int var15 = 0; var15 < var14; ++var15) {
                                Object obj = var13[var15];
                                if (obj.toString().equals("GAME_INFO")) {
                                    chatMessageType = obj;
                                }
                            }

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
                } catch (Exception var18) {
                    var18.printStackTrace();
                }

            }
        }
    }

    public static void sendActionBar(final Player player, final String message, int duration) {
        sendActionBar(player, message);
        if (duration >= 0) {
            (new BukkitRunnable() {
                @Override
                public void run() {
                    ActionBarAPI.sendActionBar(player, "");
                }
            }).runTaskLater(plugin, (long) (duration + 1));
        }

        while (duration > 40) {
            duration -= 40;
            (new BukkitRunnable() {
                @Override
                public void run() {
                    ActionBarAPI.sendActionBar(player, message);
                }
            }).runTaskLater(plugin, (long) duration);
        }

    }

    public static void sendActionBarToAllPlayers(String message) {
        sendActionBarToAllPlayers(message, -1);
    }

    public static void sendActionBarToAllPlayers(String message, int duration) {
        Iterator var2 = Bukkit.getOnlinePlayers().iterator();

        while (var2.hasNext()) {
            Player p = (Player) var2.next();
            sendActionBar(p, message, duration);
        }

    }
}