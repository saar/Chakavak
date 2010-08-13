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

import http.HttpResponse;
import java.io.IOException;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;
import twitterbase.api.ParserException;
import twitterbase.api.UserParsingException;

/**
 *
 * @author Ramin Gomari
 */
public class User extends twitter.User {

    private String statusnet_profile_url = null;
    private boolean statusnet_blocking = false;

    public User(HttpResponse hr)
            throws ParserException,
            UserParsingException {
        super(hr);
    }

    public User(KXmlParser parser) throws XmlPullParserException, IOException{
        super(parser);
    }

    public boolean set(String key, Object value)
            throws XmlPullParserException,
            IOException {
        if (super.set(key, value)) {
        } else if (key.compareTo("statusnet:profile_url") == 0) {
            setStatusnet_profile_url((String) value);
        } else if (key.compareTo("statusnet:blocking") == 0) {
            setStatusnet_blocking(((String) value).compareTo("true") == 0);
        } else {
            return false;
        }
        return true; //else else ;)
    }

    /**
     * @return the statusnet_profile_url
     */
    public String getStatusnet_profile_url() {
        return statusnet_profile_url;
    }

    /**
     * @param statusnet_profile_url the statusnet_profile_url to set
     */
    public void setStatusnet_profile_url(String statusnet_profile_url) {
        this.statusnet_profile_url = statusnet_profile_url;
    }

    /**
     * @return the statusnet_blocking
     */
    public boolean isStatusnet_blocking() {
        return statusnet_blocking;
    }

    /**
     * @param statusnet_blocking the statusnet_blocking to set
     */
    public void setStatusnet_blocking(boolean statusnet_blocking) {
        this.statusnet_blocking = statusnet_blocking;
    }
}
