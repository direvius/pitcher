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
