package io.github.sac;

import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;

import io.github.sac.*;
import java.util.List;
import java.util.Map;
/**
 * Created by oluwapelumi.olaoye on 3/13/18.
 */

public class Wrapper {

    private Socket socket;

    public void setUrl(String url) {
        socket.seturl(url);
    }

    public void wrapSocket() {
        if(socket.getUrl().equals("")) {
            System.out.println("Please input a valid url");
            return;
        }

        socket = new Socket(socket.getUrl());

        socket.setListener(new BasicListener() {
            public void onConnected(Socket socket, Map<String, List<String>> headers) {
                System.out.println("Connected to endpoint");
            }

            public void onDisconnected(Socket socket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
                System.out.println("Disconnected from end-point");
            }

            public void onConnectError(Socket socket, WebSocketException exception) {
                System.out.println("Got connect error " + exception);
            }

            public void onSetAuthToken(String token, Socket socket) {
                System.out.println("Set auth token got called");
                socket.setAuthToken(token);
            }

            public void onAuthentication(Socket socket, Boolean status) {
                if (status) {
                    System.out.println("socket is authenticated");
                } else {
                    System.out.println("Authentication is required (optional)");
                }
            }
        });
        
    }
}
