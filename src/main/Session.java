package main;

import handlers.StdErrorHander;
import handlers.StdInputHandler;
import handlers.StdOutputHander;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Session {
    private Main plugin;
    private Player player;
    private Process process;
    private StdInputHandler inputHandler;
    private StdErrorHander errorHander;
    private StdOutputHander outputHander;
    private boolean active;

    public Session(Main plugin, Player player) throws IOException {
        this.player = player;
        this.plugin = plugin;
        process = new ProcessBuilder().command("bash").start();
        outputHander = new StdOutputHander(this);
        errorHander = new StdErrorHander(this);
        inputHandler = new StdInputHandler(this);
        new Thread(errorHander).start();
        new Thread(inputHandler).start();
        active = true;
        player.sendMessage(ChatColor.GREEN+"#Bash now active#");
    }

    public Main getPlugin() {
        return plugin;
    }

    public Player getPlayer() {
        return player;
    }

    public Process getProcess() {
        return process;
    }

    public StdInputHandler getInputHandler() {
        return inputHandler;
    }

    public StdErrorHander getErrorHander() {
        return errorHander;
    }

    public StdOutputHander getOutputHander() {
        return outputHander;
    }

    public void terminate() {
        if(!active){
            return;
        }
        active = false;
        process.destroyForcibly();
        player.sendMessage(ChatColor.GREEN+"#Bash terminated#");
        plugin.getMap().remove(player);
    }

    public boolean isActive() {
        return active;
    }
}
