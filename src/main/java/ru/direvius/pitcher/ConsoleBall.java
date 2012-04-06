/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.direvius.pitcher;

/**
 *
 * @author direvius
 */
public class ConsoleBall implements Ball{

    public void beforeStart() {
        System.out.println("Before start");
    }

    public void afterFinished() {
        System.out.println("After finished");
    }

    public void run() {
        System.out.println("Run");
    }
    
}
