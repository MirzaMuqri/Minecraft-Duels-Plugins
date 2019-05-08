package xyz.mukri.duels.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import xyz.mukri.duels.Core;
import xyz.mukri.duels.arena.Arena;

public class CommandProccessEvents implements Listener {

    @EventHandler
    public void onCommandsProccess(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();
        Arena arena = Core.getInstance().arenaManager.getPlayersArena(p.getUniqueId());

        if (arena != null) {
            if (msg.startsWith("duels")) {
                return;
            }

            if (!p.hasPermission("duels.admin")) {
                e.setCancelled(true);
            }
        }

    }
}
