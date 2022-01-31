package id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Listeners;

public class BounceListener implements org.bukkit.event.Listener {
    @org.bukkit.event.EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent e) {
        if (e.getTo().getY() >= e.getFrom().getY()) return;
        org.bukkit.entity.Player p = e.getPlayer();
        if (com.viaversion.viaversion.api.Via.getAPI().getPlayerVersion(p) > 5) return;
        if (Math.floor(e.getTo().getY()) + 0.01 < e.getTo().getY()) return;
        if (p.isSneaking()) return;
        org.bukkit.block.Block b = e.getTo().clone().add(0, -0.1, 0).getBlock();
        if (b.getType() != org.bukkit.Material.SLIME_BLOCK) return;
        org.bukkit.util.Vector v = p.getVelocity();
        double motY = (e.getTo().getY() - e.getFrom().getY());
        if (motY > -0.11) return;
        v.setY(-motY * 1.05);
        p.setVelocity(v);
    }
}