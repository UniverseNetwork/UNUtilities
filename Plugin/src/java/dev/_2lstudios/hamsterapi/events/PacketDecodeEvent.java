package dev._2lstudios.hamsterapi.events;

import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayer;
import dev._2lstudios.hamsterapi.wrappers.ByteBufWrapper;
import io.netty.channel.ChannelHandlerContext;

public class PacketDecodeEvent extends PacketEvent {
    final ByteBufWrapper byteBuf;

    public PacketDecodeEvent(ChannelHandlerContext channelHandlerContext, HamsterPlayer hamsterPlayer, ByteBufWrapper byteBuf, boolean async) {
        super(channelHandlerContext, hamsterPlayer, async);
        this.byteBuf = byteBuf;
    }

    public ByteBufWrapper getByteBuf() {
        return byteBuf;
    }
}