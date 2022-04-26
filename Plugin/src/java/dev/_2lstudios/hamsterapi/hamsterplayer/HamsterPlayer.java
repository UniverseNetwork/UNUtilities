package dev._2lstudios.hamsterapi.hamsterplayer;

import dev._2lstudios.hamsterapi.HamsterAPI;
import dev._2lstudios.hamsterapi.handlers.HamsterChannelHandler;
import dev._2lstudios.hamsterapi.handlers.HamsterDecoderHandler;
import dev._2lstudios.hamsterapi.utils.Reflection;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.ClosedChannelException;
import java.util.UUID;

import static dev._2lstudios.hamsterapi.enums.HamsterHandler.HAMSTER_CHANNEL;
import static dev._2lstudios.hamsterapi.enums.HamsterHandler.HAMSTER_DECODER;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class HamsterPlayer {
    final Player player;
    final HamsterAPI hamsterAPI;
    Object playerConnection;
    Object networkManager;
    Channel channel;
    Class<?> iChatBaseComponentClass;
    Method toChatBaseComponent;
    Method sendPacketMethod;
    boolean setup = false;
    boolean injected = false;

    HamsterPlayer(Player player) {
        this.player = player;
        hamsterAPI = HamsterAPI.getInstance();
    }

    public Player getPlayer() {
        return player;
    }

    public void sendActionbarPacketOld(String text) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
        Reflection reflection = hamsterAPI.getReflection();
        Object chatAction = toChatBaseComponent.invoke(null, "{ \"text\":\"" + text + "\" }");
        Object packet = reflection.getPacketPlayOutChat().getConstructor(iChatBaseComponentClass, byte.class).newInstance(chatAction, (byte) 2);
        sendPacket(packet);
    }

    public void sendActionbarPacketNew(String text) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
        Reflection reflection = hamsterAPI.getReflection();
        Object chatAction = toChatBaseComponent.invoke(null, "{ \"text\":\"" + text + "\" }");
        Class<?> chatMessageTypeClass = reflection.getChatMessageType();
        Object[] enumConstants = chatMessageTypeClass.getEnumConstants();
        Object packet = reflection.getPacketPlayOutChat().getConstructor(iChatBaseComponentClass, chatMessageTypeClass, UUID.class).newInstance(chatAction, enumConstants[2], player.getUniqueId());
        sendPacket(packet);
    }

    // Sends an ActionBar to the HamsterPlayer
    public void sendActionbar(String text) {
        try {
            sendActionbarPacketNew(text);
        } catch (Exception e1) {
            try {
                sendActionbarPacketOld(text);
            } catch (Exception e2) {
                getLogger().info(prefix + " §fFailed to send actionbar packet to player " + player.getName() + "!");
            }
        }
    }

    public void sendTitlePacketOld(String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, NoSuchFieldException {
        Reflection reflection = hamsterAPI.getReflection();
        Object chatTitle = toChatBaseComponent.invoke(null, "{ \"text\":\"" + title + "\" }");
        Object chatSubTitle = toChatBaseComponent.invoke(null, "{ \"text\":\"" + subtitle + "\" }");
        Class<?> enumTitleActionClass = reflection.getPacketPlayOutTitle().getDeclaredClasses()[0];
        Constructor<?> titleConstructor = reflection.getPacketPlayOutTitle().getConstructor(enumTitleActionClass, iChatBaseComponentClass, int.class, int.class, int.class);
        Object titlePacket = titleConstructor.newInstance(enumTitleActionClass.getDeclaredField("TITLE").get(null), chatTitle, fadeInTime, showTime, fadeOutTime);
        Object subtitlePacket = titleConstructor.newInstance(enumTitleActionClass.getDeclaredField("SUBTITLE").get(null), chatSubTitle, fadeInTime, showTime, fadeOutTime);
        sendPacket(titlePacket);
        sendPacket(subtitlePacket);
    }

    public void sendTitlePacketNew(String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
        Reflection reflection = hamsterAPI.getReflection();
        Constructor<?> timingTitleConstructor = reflection.getClientboundSetTitlesAnimationPacket().getConstructor(int.class, int.class, int.class);
        Object timingPacket = timingTitleConstructor.newInstance(fadeInTime, showTime, fadeOutTime);
        Object chatTitle = toChatBaseComponent.invoke(null, "{ \"text\":\"" + title + "\" }");
        Constructor<?> titleConstructor = reflection.getClientboundSetTitleTextPacket().getConstructor(iChatBaseComponentClass);
        Object titlePacket = titleConstructor.newInstance(chatTitle);
        Object chatSubTitle = toChatBaseComponent.invoke(null, "{ \"text\":\"" + subtitle + "\" }");
        Constructor<?> subTitleConstructor = reflection.getClientboundSetSubtitleTextPacket().getConstructor(iChatBaseComponentClass);
        Object subTitlePacket = subTitleConstructor.newInstance(chatSubTitle);
        sendPacket(timingPacket);
        sendPacket(titlePacket);
        sendPacket(subTitlePacket);
    }

    // Sends a Title to the HamsterPlayer
    public void sendTitle(String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime) {
        try {
            sendTitlePacketNew(title, subtitle, fadeInTime, showTime, fadeOutTime);
        } catch (Exception e1) {
            try {
                sendTitlePacketOld(title, subtitle, fadeInTime, showTime, fadeOutTime);
            } catch (Exception e2) {
                getLogger().info(prefix + " §fFailed to send title packet to player " + player.getName() + "!");
            }
        }
    }

    // Sends the HamsterPlayer to another Bungee server
    public void sendServer(String serverName) {
        hamsterAPI.getBungeeMessenger().sendPluginMessage("ConnectOther", player.getName(), serverName);
    }

    // Forcibly closes the player connection
    public void closeChannel() {
        if (channel != null && channel.isActive()) channel.close();
    }

    // Disconnect the HamsterPlayer with packets
    public void disconnect(String reason) {
        Reflection reflection = hamsterAPI.getReflection();
        Server server = getServer();
        try {
            Object chatKick = toChatBaseComponent.invoke(null, "{\"text\":\"" + reason + "\"}");
            Object packet = reflection.getPacketPlayOutKickDisconnect().getConstructor(iChatBaseComponentClass).newInstance(chatKick);
            sendPacket(packet);
        } catch (Exception e) {
            getLogger().info(" §fFailed to send disconnect packet to player " + player.getName() + "!");
        }
        hamsterAPI.getBungeeMessenger().sendPluginMessage("kickPlayer", player.getName(), reason);
        closeChannel();
    }

    public void sendPacket(Object packet) {
        try {
            sendPacketMethod.invoke(playerConnection, packet);
        } catch (Exception e) {
            getLogger().info(prefix + " §fFailed to send packet to player " + player.getName() + "!");
        }
    }

    public Object getPlayerConnection() {
        return playerConnection;
    }

    public Object getNetworkManager() {
        return networkManager;
    }

    public Channel getChannel() {
        return channel;
    }

    // Removes handlers from the player pipeline
    public void uninject() {
        if (injected && channel != null && channel.isActive()) {
            ChannelPipeline pipeline = channel.pipeline();
            if (pipeline.get(HAMSTER_DECODER) != null) pipeline.remove(HAMSTER_DECODER);
            if (pipeline.get(HAMSTER_CHANNEL) != null) pipeline.remove(HAMSTER_CHANNEL);
        }
    }

    // Sets variables to simplify packet handling and inject
    public void setup() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        if (!setup) {
            Reflection reflection = hamsterAPI.getReflection();
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            playerConnection = reflection.getField(handle, reflection.getPlayerConnection());
            networkManager = reflection.getField(playerConnection, reflection.getNetworkManager());
            channel = (Channel) reflection.getField(networkManager, Channel.class);
            iChatBaseComponentClass = reflection.getIChatBaseComponent();
            sendPacketMethod = playerConnection.getClass().getMethod("sendPacket", reflection.getPacket());
            toChatBaseComponent = iChatBaseComponentClass.getDeclaredClasses()[0].getMethod("a", String.class);
            setup = true;
        }
    }

    // Injects handlers to the player pipeline with NMS
    public void inject() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, ClosedChannelException {
        if (!injected) {
            setup();
            if (!channel.isActive()) throw new ClosedChannelException();
            ChannelPipeline pipeline = channel.pipeline();
            ByteToMessageDecoder hamsterDecoderHandler = new HamsterDecoderHandler(this);
            ChannelDuplexHandler hamsterChannelHandler = new HamsterChannelHandler(this);
            if (pipeline.get("decompress") != null)
                pipeline.addAfter("decompress", HAMSTER_DECODER, hamsterDecoderHandler);
            else if (pipeline.get("splitter") != null)
                pipeline.addAfter("splitter", HAMSTER_DECODER, hamsterDecoderHandler);
            else
                throw new IllegalAccessException("No ChannelHandler was found on the pipeline to inject " + HAMSTER_DECODER);
            if (pipeline.get("decoder") != null)
                pipeline.addAfter("decoder", HAMSTER_CHANNEL, hamsterChannelHandler);
            else
                throw new IllegalAccessException("No ChannelHandler was found on the pipeline to inject " + hamsterChannelHandler);
            injected = true;
        }
    }

    // Injects but instead of returning an exception returns sucess (Boolean)
    public boolean tryInject() {
        try {
            setup();
            inject();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 NoSuchFieldException | ClosedChannelException e) {
            return false;
        }
        return true;
    }
}