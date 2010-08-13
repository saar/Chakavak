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

import http.HttpResponse;
import java.io.IOException;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author Ramin Gomari
 */
public class XmlParser {

    public static void parse(KXmlParser parser, String tagName, Setter setter)
            throws XmlPullParserException,
            IOException {

        int n = parser.getEventType();
        String key;
        do {
            if (n == KXmlParser.START_TAG) {
                key = parser.getName();
                n = parser.next();
                if (n == KXmlParser.TEXT && parser.isWhitespace()) {
                    n = parser.next();
                }
                if (n == KXmlParser.START_TAG) {
                    if (parser.isEmptyElementTag()) {
                        continue;
                    }
                    setter.set(key, parser);
                } else if (n == KXmlParser.TEXT) {
                    setter.set(key, parser.getText());
                }
            }
        } while ((n = parser.next()) != KXmlParser.END_TAG
                || (n == KXmlParser.END_TAG && !parser.getName().equalsIgnoreCase(tagName))
                && n != KXmlParser.END_DOCUMENT);
    }

    public static KXmlParser getParser(HttpResponse hr) throws XmlPullParserException, IOException {
        KXmlParser parser = new KXmlParser();
        parser.setInput(hr.getReader());
        parser.require(KXmlParser.START_DOCUMENT, null, null);
        parser.nextTag();
        parser.next();
        return parser;
    }
}
