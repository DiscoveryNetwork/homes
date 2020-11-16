package nl.parrotlync.homes.command;

import nl.parrotlync.homes.Homes;
import nl.parrotlync.homes.model.Home;
import nl.parrotlync.homes.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ListHomesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ChatUtil.sendConfigMessage(sender, "players-only");
            return false;
        }
        Player player = (Player) sender;

        List<Home> homes = Homes.getInstance().getHomeManager().getHomes(player);
        StringBuilder msg = new StringBuilder("§6Homes: §f");
        for (Home home : homes) {
            msg.append(home.getName());
            if (homes.indexOf(home) != homes.size() - 1) { msg.append(", "); }
        }
        ChatUtil.sendMessage(player, msg.toString());
        return true;
    }
}
