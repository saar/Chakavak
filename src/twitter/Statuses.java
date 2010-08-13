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
package twitter;

import http.HttpResponse;
import java.io.IOException;
import java.util.Vector;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;
import twitterbase.api.ParserException;
import twitterbase.api.StatusParsingException;
import utilities.Setter;
import utilities.XmlParser;

/**
 *
 * @author Ramin Gomari
 */
public class Statuses extends Vector implements Setter, twitterbase.api.Statuses {

    public Statuses(HttpResponse hr) throws ParserException, StatusParsingException {
        KXmlParser parser = null;
        try {
            parser = XmlParser.getParser(hr);
            XmlParser.parse(parser, "statuses", this);
            hr.closeStream();
        } catch (XmlPullParserException ex) {
//            ex.printStackTrace();
            if (parser != null) {
                throw new StatusParsingException(parser);
            } else {
                throw new ParserException(ex.getMessage());
            }
        } catch (IOException ex) {
            throw new ParserException(ex.getMessage());
        }
    }

    public synchronized void addStatus(twitterbase.api.Status status) {
        super.addElement(status);
    }

    public void addStatuses(final twitterbase.api.Statuses s) {
        for (int i = 0; i < s.size(); i++) {
            addStatus(s.statusAt(i));
        }
    }

    public twitterbase.api.Status statusAt(int index) {
        return (Status) super.elementAt(index);
    }

    public synchronized String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < size(); i++) {
            buffer.append(statusAt(i).toString()).append("\n");
        }
        return buffer.toString();
    }

    public boolean set(String key, Object value) throws XmlPullParserException, IOException {
        if (key.compareTo("status") == 0) {
            addStatus(new Status((KXmlParser) value));
        } else {
            return false;
        }
        return true;
    }

    public int size() {
        return super.size();
    }
}
