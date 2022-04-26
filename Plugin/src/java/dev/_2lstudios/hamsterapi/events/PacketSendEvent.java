package dev._2lstudios.hamsterapi.events;

import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayer;
import dev._2lstudios.hamsterapi.wrappers.PacketWrapper;
import io.netty.channel.ChannelHandlerContext;

public class PacketSendEvent extends PacketEvent {
    final PacketWrapper packet;

    public PacketSendEvent(ChannelHandlerContext channelHandlerContext, HamsterPlayer hamsterPlayer, PacketWrapper packet, boolean async) {
        super(channelHandlerContext, hamsterPlayer, async);
        this.packet = packet;
    }

    public PacketWrapper getPacket() {
        return packet;
    }
}