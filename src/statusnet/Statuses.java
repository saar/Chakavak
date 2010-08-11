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
package statusnet;

import com.exploringxml.xml.Node;
import com.exploringxml.xml.Xparse;
import twitterbase.api.ParserException;
import twitterbase.api.StatusParsingException;

/**
 *
 * @author Ramin Gomari
 */
public class Statuses extends twitterbase.api.Statuses {

    public Statuses(String xml)
            throws ParserException,
            StatusParsingException {
        super(xml);
    }

    public void pars(String xml) throws ParserException, StatusParsingException {
        int occur[] = {1};
        Node root = new Xparse().parse(xml).find("statuses", occur);
        if (root == null) {
            throw new ParserException("Can not parse \n" + xml);
        }
        occur = new int[2];
        occur[0] = occur[1] = 1;
        Node status = null;

        while ((status = root.find("status", occur)) != null) {
            addStatus(new Status(status));
        }


    }
}
