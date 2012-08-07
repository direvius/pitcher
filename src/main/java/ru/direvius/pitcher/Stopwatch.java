/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.direvius.pitcher;

/**
 *
 * @author direvius
 */
public class Stopwatch {
    private final long startMillis;
    public Stopwatch(){
        this.startMillis = System.currentTimeMillis();
    }
    public long measure(){
        return System.currentTimeMillis() - startMillis;
    }
}
