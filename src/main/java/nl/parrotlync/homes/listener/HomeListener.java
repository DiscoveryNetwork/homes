package nl.parrotlync.homes.listener;

import nl.parrotlync.homes.Homes;
import nl.parrotlync.homes.util.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class HomeListener implements Listener {

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        Homes.getInstance().getHomeManager().setBed(event.getPlayer(), event.getBed().getLocation());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (Homes.getInstance().getHomeManager().hasHome(player, "§oBed")) {
            event.setRespawnLocation(Homes.getInstance().getHomeManager().getHome(player, "bed").getLocation());
        }
    }
}
