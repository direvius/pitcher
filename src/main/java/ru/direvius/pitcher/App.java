/*
*    Copyright © 2012 Alexey Lavrenuke
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU Lesser General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU Lesser General Public License for more details.
*
*    You should have received a copy of the GNU Lesser General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
    
    public static void main( String[] args )
    {
//        if(args.length<1) return;
//        final String url = args[0];
//        final Runnable cannon = new JettyBall(url);
//        final ScheduledFuture<?> cannonHandle = scheduler.scheduleAtFixedRate(cannon, 50, 50, TimeUnit.MICROSECONDS);
//        scheduler.schedule(new Runnable(){
//
//            public void run() {
//                cannonHandle.cancel(true);
//                System.out.println( "Finished!");
//                for(String result: SampleCounter.get().getResults()){
//                    System.out.println(result);
//                }
//                System.exit(0);
//            }
//            
//        }, 10, TimeUnit.SECONDS);
        
        Pitcher p = new Pitcher(new ConsoleBall());
        p.begin(1);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setRate(5);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.finish();
        System.exit(0);
    }
}
