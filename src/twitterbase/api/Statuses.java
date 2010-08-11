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
package twitterbase.api;

import java.util.Vector;

/**
 *
 * @author Ramin Gomari
 */
public abstract class Statuses extends Vector {

    public Statuses(String text)
            throws ParserException,
            StatusParsingException {
        pars(text);
    }

    public synchronized void addStatus(Status status) {
        super.addElement(status);
    }

    public synchronized Status statusAt(int index) {
        return (Status) super.elementAt(index);
    }

    public synchronized String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < size(); i++) {
            buffer.append(statusAt(i).toString()).append("\n");
        }
        return buffer.toString();
    }

    public abstract void pars(String text)
            throws ParserException,
            StatusParsingException;
}
