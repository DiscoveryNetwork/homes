package nl.parrotlync.homes.command;

import nl.parrotlync.homes.Homes;
import nl.parrotlync.homes.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ChatUtil.sendConfigMessage(sender, "players-only");
            return false;
        }
        Player player = (Player) sender;

        if (args.length == 1 && !args[0].equals("") ) {
            Homes.getInstance().getHomeManager().addHome(player, args[0]);
            ChatUtil.sendConfigMessage(player, "home-set");
            return true;
        }

        return false;
    }
}
