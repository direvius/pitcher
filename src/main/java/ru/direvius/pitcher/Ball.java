/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.direvius.pitcher;

/**
 *
 * @author direvius
 */
public interface Ball extends Runnable {
    public void beforeStart();
    public void afterFinished();
}
