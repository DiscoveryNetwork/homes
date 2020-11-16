package nl.parrotlync.homes;

import nl.parrotlync.homes.command.HomeCommand;
import nl.parrotlync.homes.command.ListHomesCommand;
import nl.parrotlync.homes.command.RemoveHomeCommand;
import nl.parrotlync.homes.command.SetHomeCommand;
import nl.parrotlync.homes.listener.HomeListener;
import nl.parrotlync.homes.manager.HomeManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Homes extends JavaPlugin {
    private static Homes instance;
    private final HomeManager homeManager;

    public Homes() {
        instance = this;
        homeManager = new HomeManager();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        homeManager.load();
        getServer().getPluginManager().registerEvents(new HomeListener(), this);
        Objects.requireNonNull(getCommand("home")).setExecutor(new HomeCommand());
        Objects.requireNonNull(getCommand("sethome")).setExecutor(new SetHomeCommand());
        Objects.requireNonNull(getCommand("delhome")).setExecutor(new RemoveHomeCommand());
        Objects.requireNonNull(getCommand("homes")).setExecutor(new ListHomesCommand());
        getLogger().info("Homes has now been enabled!");
    }

    @Override
    public void onDisable() {
        homeManager.save();
        getLogger().info("Homes has now been disabled!");
    }

    public HomeManager getHomeManager() {
        return homeManager;
    }

    public static Homes getInstance() {
        return instance;
    }
}
