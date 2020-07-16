package fr.enzias.antiislandexit;

import fr.enzias.antiislandexit.events.IslandLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiIslandExit extends JavaPlugin {

    @Override
    public void onEnable() {

        if(Bukkit.getPluginManager().getPlugin("ASkyBlock") != null) {

            this.getServer().getPluginManager().registerEvents(new IslandLeaveEvent(this), this);
            this.saveDefaultConfig();
            saveConfig();
            Bukkit.getServer().getLogger().info("AntiIslandExit successfully enabled.");

        }
        else{
            Bukkit.getServer().getLogger().info("AntiIslandExit needs askyblock plugin to work !");
            this.getPluginLoader().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {
        saveConfig();
        Bukkit.getServer().getLogger().info("AntiIslandExit successfully disabled.");
    }
}
