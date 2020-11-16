package nl.parrotlync.homes.manager;

import nl.parrotlync.homes.Homes;
import nl.parrotlync.homes.model.Home;
import nl.parrotlync.homes.util.DataUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HomeManager {
    private HashMap<UUID, HashMap<String, Home>> homes = new HashMap<>();

    private void checkList(Player player) {
        homes.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>());
    }

    public void addHome(Player player, String name) {
        checkList(player);
        Home home = new Home(name, player.getLocation());
        homes.get(player.getUniqueId()).put(name, home);
    }

    public void setBed(Player player, Location location) {
        checkList(player);
        Home home = new Home("§oBed", location);
        homes.get(player.getUniqueId()).put("§oBed", home);
    }

    public Home removeHome(Player player, String name) {
        checkList(player);
        Home home = homes.get(player.getUniqueId()).get(name);
        if (home != null) {
            homes.get(player.getUniqueId()).remove(name);
        }
        return home;
    }

    public Home getHome(Player player, String name) {
        checkList(player);
        return homes.get(player.getUniqueId()).get(name);
    }

    public List<Home> getHomes(Player player) {
        checkList(player);
        return new ArrayList<>(this.homes.get(player.getUniqueId()).values());
    }

    public boolean hasHome(Player player, String name) {
        checkList(player);
        return homes.get(player.getUniqueId()).containsKey(name);
    }

    public void save() {
        DataUtil.saveObjectToPath(homes, "plugins/Homes/homes.data");
        Homes.getInstance().getLogger().info("Saved home data to file.");
    }

    public void load() {
        HashMap<UUID, HashMap<String, Home>> storedHomes = DataUtil.loadObjectFromPath("plugins/Homes/homes.data");
        if (storedHomes != null) {
            homes = storedHomes;
            Homes.getInstance().getLogger().info("Loaded homes from storage.");
        }
    }
}
