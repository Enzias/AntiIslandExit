package fr.enzias.antiislandexit.events;

import com.wasteofplastic.askyblock.events.IslandExitEvent;
import fr.enzias.antiislandexit.AntiIslandExit;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class IslandLeaveEvent implements Listener {

    private final AntiIslandExit plugin;
    public IslandLeaveEvent(AntiIslandExit plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onIslandLeave(IslandExitEvent event) {

        if (plugin.getConfig().getBoolean("antiislandexit.enable")) {

            Player player = Bukkit.getPlayer(event.getPlayer());

            Location exit = event.getLocation();
            Location spawn = event.getIslandLocation();

            IntRange rangePositiveX = new IntRange(spawn.getX() + (event.getProtectionSize() / 2) + 1, spawn.getX() + (event.getProtectionSize() / 2) - 1);
            IntRange rangeNegativeX = new IntRange(spawn.getX() - (event.getProtectionSize() / 2) + 1, spawn.getX() - (event.getProtectionSize() / 2) - 1);
            IntRange rangePositiveZ = new IntRange(spawn.getZ() + (event.getProtectionSize() / 2) + 1, spawn.getZ() + (event.getProtectionSize() / 2) - 1);
            IntRange rangeNegativeZ = new IntRange(spawn.getZ() - (event.getProtectionSize() / 2) + 1, spawn.getZ() - (event.getProtectionSize() / 2) - 1);

            if (!player.hasPermission("islandleave.bypass")) {
                if (rangePositiveX.containsInteger(exit.getX())) {
                    player.teleport(new Location(player.getWorld(), exit.getX() - 1, exit.getY(), exit.getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
                } else if (rangeNegativeX.containsInteger(exit.getX())) {
                    player.teleport(new Location(player.getWorld(), exit.getX() + 1, exit.getY(), exit.getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
                } else if (rangePositiveZ.containsInteger(exit.getZ())) {
                    player.teleport(new Location(player.getWorld(), exit.getX(), exit.getY(), exit.getZ() - 1, player.getLocation().getYaw(), player.getLocation().getPitch()));
                } else if (rangeNegativeZ.containsInteger(exit.getZ())) {
                    player.teleport(new Location(player.getWorld(), exit.getX(), exit.getY(), exit.getZ() + 1, player.getLocation().getYaw(), player.getLocation().getPitch()));
                }
                else if(plugin.getConfig().getBoolean("antiislandexit.lastchance")){
                    player.teleport(event.getIslandLocation());
                }
                if(plugin.getConfig().getBoolean("antiislandexit.messages.antiislandexit.enable")) {
                    String message = plugin.getConfig().getString("antiislandexit.messages.antiislandexit.message");
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
                }
            } else if(plugin.getConfig().getBoolean("antiislandexit.messages.bypass-message.enable")){
                String message = plugin.getConfig().getString("antiislandexit.messages.bypass-message.message");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',message));
            }

        }
    }

}
