package handlers;

import main.Session;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StdInputHandler extends InputHandler{
    public StdInputHandler(Session session){
        super(session,session.getProcess().getInputStream());
    }

    @Override
    protected void sendMessage(String line) {
        session.getPlayer().sendMessage(ChatColor.WHITE+line);
    }
}
