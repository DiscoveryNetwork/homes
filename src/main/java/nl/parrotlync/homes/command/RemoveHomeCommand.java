package nl.parrotlync.homes.command;

import nl.parrotlync.homes.Homes;
import nl.parrotlync.homes.model.Home;
import nl.parrotlync.homes.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class RemoveHomeCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ChatUtil.sendConfigMessage(sender, "players-only");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            Home home = Homes.getInstance().getHomeManager().removeHome(player, args[0]);
            if (home != null) {
                ChatUtil.sendConfigMessage(player, "home-removed", home);
            } else {
                ChatUtil.sendConfigMessage(player, "home-not-exists");
            }
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();
        Player player = (Player) sender;

        if (args.length == 0) {
            for (Home home : Homes.getInstance().getHomeManager().getHomes(player)) {
                suggestions.add(home.getName());
            }
            return StringUtil.copyPartialMatches(args[0], suggestions, new ArrayList<>());
        }

        return suggestions;
    }
}
