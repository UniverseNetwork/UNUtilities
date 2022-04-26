package dev._2lstudios.hamsterapi.handlers;

import dev._2lstudios.hamsterapi.events.PacketDecodeEvent;
import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayer;
import dev._2lstudios.hamsterapi.wrappers.ByteBufWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

import java.util.List;

public class HamsterDecoderHandler extends ByteToMessageDecoder {
    final Server server;
    final PluginManager pluginManager;
    final HamsterPlayer hamsterPlayer;

    public HamsterDecoderHandler(HamsterPlayer hamsterPlayer) {
        server = hamsterPlayer.getPlayer().getServer();
        pluginManager = server.getPluginManager();
        this.hamsterPlayer = hamsterPlayer;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf bytebuf, List<Object> list) {
        ByteBufWrapper byteBufWrapper = new ByteBufWrapper(bytebuf);
        boolean async = !server.isPrimaryThread();
        PacketDecodeEvent event = new PacketDecodeEvent(channelHandlerContext, hamsterPlayer, byteBufWrapper, async);
        try {
            pluginManager.callEvent(event);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (!event.isCancelled()) list.add(bytebuf.readBytes(bytebuf.readableBytes()));
        else bytebuf.skipBytes(bytebuf.readableBytes());
    }
}