package serveur;

import java.io.IOException;
import java.net.Socket;

import application.MediathequeBD;

public abstract class Service implements Runnable{
    private Socket socket;
    private MediathequeBD mediatheque;

    
	public Service() {}
    
    protected Socket getSocket(){
        return socket;
    }

    public void setSocket(java.net.Socket newSocket) {
        socket = newSocket;
    }
    
    public MediathequeBD getMediatheque() {
		return mediatheque;
	}

	public void setMediatheque(MediathequeBD mediatheque) {
		this.mediatheque = mediatheque;
	}
    public void close(){
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract void run();
}