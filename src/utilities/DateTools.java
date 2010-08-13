/*
 *  Copyright (C) 2010 Ramin Gomari
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Ramin Gomari
 */
public class DateTools {

    public static Date parseDate(String date) {
        if (date == null || date.length() == 0) {
            return null;
        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        //for example: Sun Aug 08 18:51:29 +0000 2010
        StringBuffer tempBuffer[] = new StringBuffer[8];
        int index = 0;
        tempBuffer[index] = new StringBuffer();
        for (int i = 0; i < date.length(); i++) {
            char c = date.charAt(i);
            if (c == ' ' || c == ':') {
                tempBuffer[++index] = new StringBuffer();
            } else {
                tempBuffer[index].append(c);
            }
        }
        String tempS = tempBuffer[1].toString();
        //Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
        if (tempS.equalsIgnoreCase("jan")) {
            cal.set(Calendar.MONTH, Calendar.JANUARY);
        } else if (tempS.equalsIgnoreCase("feb")) {
            cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        } else if (tempS.equalsIgnoreCase("mar")) {
            cal.set(Calendar.MONTH, Calendar.MARCH);
        } else if (tempS.equalsIgnoreCase("apr")) {
            cal.set(Calendar.MONTH, Calendar.APRIL);
        } else if (tempS.equalsIgnoreCase("may")) {
            cal.set(Calendar.MONTH, Calendar.MAY);
        } else if (tempS.equalsIgnoreCase("jun")) {
            cal.set(Calendar.MONTH, Calendar.JUNE);
        } else if (tempS.equalsIgnoreCase("jul")) {
            cal.set(Calendar.MONTH, Calendar.JULY);
        } else if (tempS.equalsIgnoreCase("aug")) {
            cal.set(Calendar.MONTH, Calendar.AUGUST);
        } else if (tempS.equalsIgnoreCase("sep")) {
            cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
        } else if (tempS.equalsIgnoreCase("nov")) {
            cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        } else if (tempS.equalsIgnoreCase("dec")) {
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
        }
        cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(tempBuffer[2].toString()).intValue());
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(tempBuffer[3].toString()).intValue());
        cal.set(Calendar.MINUTE, Integer.valueOf(tempBuffer[4].toString()).intValue());
        cal.set(Calendar.SECOND, Integer.valueOf(tempBuffer[5].toString()).intValue());
        cal.set(Calendar.YEAR, Integer.valueOf(tempBuffer[7].toString()).intValue());
        return cal.getTime();

    }
}
