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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author direvius
 */
public class Pitcher {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
    private final Ball ball;
    private ScheduledFuture future;
    public Pitcher(Ball ball){
        this.ball = ball;
    }
    public void begin(double rps){
        ball.beforeStart();
        start(rps);
    }
    public void finish(){
        stop();
        ball.afterFinished();
    }
    public void setRate(double rate){
        stop();
        start(rate);
    }
    public void start(double rate){
        if(future == null || future.isDone()){
            future = scheduler.scheduleAtFixedRate(ball, r2d(rate), r2d(rate), TimeUnit.MICROSECONDS);
        }
    }
    public void stop(){
        if(future!=null)future.cancel(false);
    }
    private long r2d(double rate){
        return (long)(1000000/rate);
    }
}
