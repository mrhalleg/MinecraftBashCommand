package main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin implements CommandExecutor {

    private Map<CommandSender, Session> map;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "You are not a Player!");
            return false;
        }
        Player player = (Player) sender;
        if(!player.isOp()){
            player.sendMessage(ChatColor.RED + "You are not OP!");
            return false;
        }

        if("bash".equalsIgnoreCase(label)){
            if(map.containsKey(player)){
                sender.sendMessage(ChatColor.RED + "Bash is already active, use /tern to terminate it.");
                return true;
            }

            try {
                map.put(player, new Session(this,player));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }else if("term".equalsIgnoreCase(label)){
            if(!map.containsKey(player)){
                sender.sendMessage(ChatColor.RED + "Bash is not active. use /bash.");
                return true;
            }
            map.get(player).terminate();
        }

        return false;
    }

    @Override
    public void onEnable(){
        map = new HashMap<>();
    }

    public Map<CommandSender, Session> getMap() {
        return map;
    }
}