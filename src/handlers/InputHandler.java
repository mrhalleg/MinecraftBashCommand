package handlers;

import handlers.Handler;
import main.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class InputHandler extends Handler implements Runnable{
    protected BufferedReader reader;

    public InputHandler(Session session, InputStream stream){
        super(session);
        reader = new BufferedReader(new InputStreamReader(stream));
    }

    @Override
    public void run(){
        try{
            String line = "";
            while(true){
                int i = reader.read();
                if(!Character.isDefined(i)){
                    session.terminate();
                    return;
                }
                String s = Character.toString(i);

                if("\n".equals(s)) {
                    sendMessage(line);
                    line = "";
                }else {
                    line  += s;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void sendMessage(String line);
}
