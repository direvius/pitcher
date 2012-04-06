/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.direvius.pitcher;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

/**
 *
 * @author direvius
 */
class JettyBall implements Ball {
    private final String url;

    public JettyBall(String url) {
        this.url = url;
    }
    HttpClient client;
    {
        client = new HttpClient();
        client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
        try {
            client.start();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        HttpExchange exchange = new SamplerHttpExchange();
        exchange.setURL(url);
        try {
            client.send(exchange);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void beforeStart() {
    }

    public void afterFinished() {
    }
    
}
