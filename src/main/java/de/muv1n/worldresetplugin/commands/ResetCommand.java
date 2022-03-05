package de.muv1n.worldresetplugin.commands;

import de.muv1n.worldresetplugin.WorldResetPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String PREFIX = "§7[§bSystem§7] ";
        String USSAGE = PREFIX + "§7Please perform §b</reset confirm> §7to reset the world!";
        String NAME = sender.getName();
        String KICK_MESSAGE = "§c---------------------------------------------------\n" +
                              "§bYou was kicked because the Server resets the world!\n" +
                              "§5The Command Trigered by " + NAME + "\n" +
                              "§2You can rejoin in 10 Seconds\n" +
                              "§c---------------------------------------------------\n";
        if (args.length == 1){
            if (args[0].equals("confirm")) {
                Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer(KICK_MESSAGE));
                WorldResetPlugin.instance.getConfig().set("isReset", true);
                WorldResetPlugin.instance.saveConfig();
                Bukkit.spigot().restart();
            }else {
                sender.sendMessage(USSAGE);
            }
        }else{
            sender.sendMessage(USSAGE);
        }
        return false;
    }
}
