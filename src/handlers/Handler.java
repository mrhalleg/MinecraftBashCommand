package handlers;

import main.Session;

public abstract class Handler extends Thread {
    protected Session session;

    public Handler(Session session){
        this.session = session;
    }
}
