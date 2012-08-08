/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.direvius.pitcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author direvius
 */
public class TransactionLogger {
    private final static Logger logger = LoggerFactory.getLogger(TransactionLogger.class);
    private final static String prefix = "one_sec.load.";
    private TransactionLogger(){}
    private static class TransactionLoggerHolder{
        private static final TransactionLogger instanse = new TransactionLogger();
    }
    public static TransactionLogger get(){ return TransactionLoggerHolder.instanse; }
    public void log(String transactionID, long milliseconds, String status){
        String logEntry = System.currentTimeMillis()/1000+"\t"+prefix+transactionID+"."+status+"\t"+milliseconds;
        logger.info(logEntry);
    }
}
