package nl.parrotlync.homes.util;

import nl.parrotlync.homes.Homes;
import nl.parrotlync.homes.model.Home;
import org.bukkit.command.CommandSender;

public class ChatUtil {

    public static void sendMessage(CommandSender sender, String msg) {
        sender.sendMessage(msg);
    }

    public static void sendConfigMessage(CommandSender sender, String shortcode, Home home) {
        String message = Homes.getInstance().getConfig().getString("messages." + shortcode);
        if (message != null) {
            sender.sendMessage(message.replace("{home}", home.getName()));
        }
    }

    public static void sendConfigMessage(CommandSender sender, String shortcode) {
        String message = Homes.getInstance().getConfig().getString("messages." + shortcode);
        if (message != null) {
            sender.sendMessage(message);
        }
    }
}
