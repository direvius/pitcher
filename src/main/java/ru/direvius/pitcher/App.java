package ru.direvius.pitcher;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);
    
    public static void main( String[] args )
    {
        final Runnable cannon = new Runnable() {
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
                ContentExchange exchange = new ContentExchange(true){
                    @Override
                    protected void onResponseComplete() throws IOException{
                        int status = getResponseStatus();
                        if (status == 200){
                            Logger.getLogger(App.class.getName()).log(Level.INFO, "HTTP OK: {0}", status);
                            Logger.getLogger(App.class.getName()).log(Level.INFO, getResponseContent());
                        }else{
                            Logger.getLogger(App.class.getName()).log(Level.INFO, "HTTP error: {0}", status);
                        }  
                    }
                };
                
                exchange.setURL("http://ya.ru");
                try {
                    client.send(exchange);
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        };
        final ScheduledFuture<?> cannonHandle = scheduler.scheduleAtFixedRate(cannon, 1, 1, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable(){

            public void run() {
                cannonHandle.cancel(true);
                System.out.println( "Finished!" );
            }
            
        }, 10, TimeUnit.SECONDS);
    }
}
