package dev._2lstudios.hamsterapi.events;

import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayer;
import dev._2lstudios.hamsterapi.wrappers.PacketWrapper;
import io.netty.channel.ChannelHandlerContext;

public class PacketReceiveEvent extends PacketEvent {
    final PacketWrapper packet;

    public PacketReceiveEvent(ChannelHandlerContext channelHandlerContext, HamsterPlayer hamsterPlayer, PacketWrapper packet, boolean async) {
        super(channelHandlerContext, hamsterPlayer, async);
        this.packet = packet;
    }

    public PacketWrapper getPacket() {
        return packet;
    }
}