package nl.parrotlync.homes.model;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;
import java.util.Objects;

public class Home implements Serializable {
    private final String name;
    private final double x;
    private final double y;
    private final double z;
    private final float pitch;
    private final float yaw;
    private final String world;

    public Home(String name, Location location) {
        this.name = name;
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
        this.world = Objects.requireNonNull(location.getWorld()).getName();
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        World world = Bukkit.getWorld(this.world);
        return new Location(world, x, y, z, yaw, pitch);
    }
}
