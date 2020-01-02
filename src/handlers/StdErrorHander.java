package handlers;

import main.Session;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StdErrorHander extends InputHandler{

    public StdErrorHander(Session session){
        super(session,session.getProcess().getErrorStream());
    }

    @Override
    protected void sendMessage(String line) {
        session.getPlayer().sendMessage(ChatColor.YELLOW+line);
    }
}
