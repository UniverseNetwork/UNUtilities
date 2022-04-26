package dev._2lstudios.hamsterapi.wrappers;

import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class EventWrapper implements Cancellable {
    final PacketWrapper packet;
    final ByteBuf byteBuf;
    final ChannelHandlerContext channelHandlerContext;
    final HamsterPlayer hamsterPlayer;
    final Player player;
    boolean cancelled = false;
    boolean closed = false;

    public EventWrapper(HamsterPlayer hamsterPlayer, ChannelHandlerContext channelHandlerContext, PacketWrapper packet) {
        this.packet = packet;
        this.channelHandlerContext = channelHandlerContext;
        this.hamsterPlayer = hamsterPlayer;
        player = hamsterPlayer.getPlayer();
        byteBuf = null;
    }

    public EventWrapper(HamsterPlayer hamsterPlayer, ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        this.channelHandlerContext = channelHandlerContext;
        this.hamsterPlayer = hamsterPlayer;
        player = hamsterPlayer.getPlayer();
        packet = null;
        this.byteBuf = byteBuf;
    }

    @Override
    public void setCancelled(boolean result) {
        cancelled = result;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public ChannelPipeline getPipeline() {
        return channelHandlerContext.pipeline();
    }

    public PacketWrapper getPacket() {
        return packet;
    }

    public Player getPlayer() {
        return player;
    }

    public HamsterPlayer getHamsterPlayer() {
        return hamsterPlayer;
    }

    public void close() {
        channelHandlerContext.close();
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    public ByteBuf getByteBuf() {
        return byteBuf;
    }

    public ByteBufWrapper getByteWrapper() {
        return new ByteBufWrapper(byteBuf);
    }
}