package dev._2lstudios.hamsterapi.handlers;

import dev._2lstudios.hamsterapi.events.PacketReceiveEvent;
import dev._2lstudios.hamsterapi.events.PacketSendEvent;
import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayer;
import dev._2lstudios.hamsterapi.wrappers.PacketWrapper;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

public class HamsterChannelHandler extends ChannelDuplexHandler {
    final Server server;
    final PluginManager pluginManager;
    final HamsterPlayer hamsterPlayer;

    public HamsterChannelHandler(HamsterPlayer hamsterPlayer) {
        server = hamsterPlayer.getPlayer().getServer();
        pluginManager = server.getPluginManager();
        this.hamsterPlayer = hamsterPlayer;
    }

    @Override
    public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
        PacketWrapper packetWrapper = new PacketWrapper(packet);
        boolean async = !server.isPrimaryThread();
        PacketSendEvent event = new PacketSendEvent(channelHandlerContext, hamsterPlayer, packetWrapper, async);
        try {
            pluginManager.callEvent(event);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (!event.isCancelled()) super.write(channelHandlerContext, packetWrapper.getPacket(), channelPromise);
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
        PacketWrapper packetWrapper = new PacketWrapper(packet);
        boolean async = !server.isPrimaryThread();
        PacketReceiveEvent event = new PacketReceiveEvent(channelHandlerContext, hamsterPlayer, packetWrapper, async);
        try {
            pluginManager.callEvent(event);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (!event.isCancelled()) super.channelRead(channelHandlerContext, packetWrapper.getPacket());
    }
}