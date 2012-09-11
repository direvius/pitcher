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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author direvius
 */
public class SampleCounter implements Runnable{
    private final ScheduledExecutorService se;
    private final ScheduledFuture<?> seFuture;
    private int total;
    private int rps;
    private double avgDelay;
    private long totalDelay;
    private List<String> results;
    private SampleCounter(){
        se = Executors.newSingleThreadScheduledExecutor();
        results = new ArrayList<String>();
        this.seFuture = se.scheduleAtFixedRate(this, 1, 1, TimeUnit.SECONDS);
    }

    public void run() {
        synchronized(this){
            total += rps;
            avgDelay = (double)totalDelay/rps;
            
            //results.add(String.format("Total: %d, average: %d, rps: %d.", total, avgDelay, rps));
            results.add("Total: "+total+", rps: "+rps+", average:"+avgDelay);
            totalDelay =0;
            rps = 0;
            
        }
    }
    public synchronized void addSample(long delay){
        totalDelay += delay;
        rps++;
    }
    public List<String> getResults(){
        return results;
    }
    private static class SampleCounterHolder{
        private static final SampleCounter instance = new SampleCounter();
    }
    public static SampleCounter get(){
        return SampleCounterHolder.instance;
    }
}
