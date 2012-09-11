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
