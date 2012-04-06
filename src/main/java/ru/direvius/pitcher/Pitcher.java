/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
