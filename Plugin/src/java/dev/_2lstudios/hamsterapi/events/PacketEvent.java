package dev._2lstudios.hamsterapi.events;

import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayer;
import io.netty.channel.ChannelHandlerContext;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

class PacketEvent extends Event implements Cancellable {
    static final HandlerList handlers = new HandlerList();
    final ChannelHandlerContext channelHandlerContext;
    final HamsterPlayer hamsterPlayer;
    boolean cancelled = false;

    public PacketEvent(ChannelHandlerContext channelHandlerContext, HamsterPlayer hamsterPlayer, boolean async) {
        super(async);
        this.channelHandlerContext = channelHandlerContext;
        this.hamsterPlayer = hamsterPlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public void setCancelled(boolean result) {
        cancelled = result;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public HamsterPlayer getHamsterPlayer() {
        return hamsterPlayer;
    }
}