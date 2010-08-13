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
import java.util.Date;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;
import twitterbase.api.ParserException;
import twitterbase.api.StatusParsingException;
import utilities.DateTools;
import utilities.Setter;
import utilities.XmlParser;

/**
 *
 * @author Ramin Gomari
 */
public class Status implements Setter, twitterbase.api.Status {

    private long id = -1;
    private String text = null;
    private boolean truncated = false;
    private Date create_at = null;
    private long in_reply_to_status_id = -1;
    private String source = null;
    private twitterbase.api.User user = null;
    private long in_reply_to_user_id = -1;
    private String in_reply_to_screen_name = null;
    private twitterbase.api.Geo geo = null;
    private boolean favorited = false;

    public Status(KXmlParser parser) throws XmlPullParserException, IOException {
        XmlParser.parse(parser, "status", this);

    }

    public Status(HttpResponse hr) throws ParserException, StatusParsingException {
        KXmlParser parser = null;
        try {
            parser = XmlParser.getParser(hr);
            XmlParser.parse(parser, "status", this);
        } catch (XmlPullParserException ex) {
            if (parser != null) {
                throw new StatusParsingException(parser);
            } else {
                throw new ParserException(ex.getMessage());
            }
        } catch (IOException ex) {
            throw new ParserException(ex.getMessage());
        } finally {
            hr.closeStream();
        }
    }

    public boolean set(String key, Object value)
            throws XmlPullParserException,
            IOException {
        if (key.compareTo("text") == 0) {
            setText((String) value);
        } else if (key.compareTo("id") == 0) {
            setId(Long.parseLong((String) value));
        } else if (key.compareTo("created_at") == 0) {
            setCreate_at(DateTools.parseDate((String) value));
        } else if (key.compareTo("truncated") == 0) {
            setTruncated(((String) value).compareTo("true") == 0);
        } else if (key.compareTo("favorited") == 0) {
            setTruncated(((String) value).compareTo("true") == 0);
        } else if (key.compareTo("in_reply_to_status_id") == 0) {
            setIn_reply_to_status_id(Long.parseLong((String) value));
        } else if (key.compareTo("in_reply_to_user_id") == 0) {
            setIn_reply_to_user_id(Long.parseLong((String) value));
        } else if (key.compareTo("in_reply_to_screen_name") == 0) {
            setIn_reply_to_screen_name((String) value);
        } else if (key.compareTo("source") == 0) {
            setSource((String) value);
        } else if (key.compareTo("user") == 0) {
            setUser(new User((KXmlParser) value));
        } else if (key.compareTo("geo") == 0) {
            KXmlParser parser = (KXmlParser) value;
            parser.require(KXmlParser.START_TAG, null, "georss:point");
            if (parser.next() == KXmlParser.TEXT && parser.isWhitespace()) {
                parser.next();
            }
            Geo g = new Geo(parser.getText());
            if (g.getLatitude() != -1 && g.getLongitude() != -1) {
                setGeo(g);
            }
        } else {
            return false;
        }
        return true;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public long getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    public void setIn_reply_to_status_id(long in_reply_to_status_id) {
        this.in_reply_to_status_id = in_reply_to_status_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public twitterbase.api.User getUser() {
        return user;
    }

    public void setUser(twitterbase.api.User user) {
        this.user = user;
    }

    public long getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    public void setIn_reply_to_user_id(long in_reply_to_user_id) {
        this.in_reply_to_user_id = in_reply_to_user_id;
    }

    public String getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
        this.in_reply_to_screen_name = in_reply_to_screen_name;
    }

    public twitterbase.api.Geo getGeo() {
        return geo;
    }

    public void setGeo(twitterbase.api.Geo geo) {
        this.geo = geo;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public String toString() {
        return getText();
    }
}
