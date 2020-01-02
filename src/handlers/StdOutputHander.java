package handlers;

import main.Session;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class StdOutputHander implements Listener {

    private BufferedWriter writer;
    private Session handler;

    public StdOutputHander(Session handler) {
        this.handler = handler;
        handler.getPlugin().getServer().getPluginManager().registerEvents(this, handler.getPlugin());
        writer = new BufferedWriter(new OutputStreamWriter(handler.getProcess().getOutputStream()));
    }

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event){
        if(event.getPlayer().getUniqueId() != handler.getPlayer().getUniqueId()){
            return;
        }

        if(!handler.isActive()){
            return;
        }
        String message = event.getMessage();
        event.setCancelled(true);
        try {
            writer.write(message+"\n");
            writer.flush();
        } catch(IOException e){
            handler.terminate();
            AsyncPlayerChatEvent.getHandlerList().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.getPlayer().sendMessage(ChatColor.BLUE+"> "+ message);
    }
}