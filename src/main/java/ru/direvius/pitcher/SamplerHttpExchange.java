/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.direvius.pitcher;

import org.eclipse.jetty.client.HttpExchange;

/**
 *
 * @author direvius
 */
public class SamplerHttpExchange extends HttpExchange{
    private long startTime;
    private long finishTime;
    @Override
    protected void onRequestComplete(){
        startTime = System.nanoTime();
    }
    
    @Override
    protected void onResponseComplete(){
        finishTime = System.nanoTime();
        long delta = (finishTime - startTime)/1000;
        //System.out.println(delta);
        SampleCounter.get().addSample(delta);
    }
}
