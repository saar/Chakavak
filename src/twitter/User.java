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
import twitterbase.api.UserParsingException;
import utilities.DateTools;
import utilities.Setter;
import utilities.XmlParser;

/**
 *
 * @author Ramin Gomari
 */
public class User implements Setter, twitterbase.api.User {

    private long id = -1;
    private String name = null;
    private String screen_name = null;
    private String location = null;
    private String description = null;
    private String profile_image_url = null;
    private String url = null;
    private boolean Protected = false;
    private int followers_count = -1;
    private String profile_background_color = null;
    private String profile_text_color = null;
    private String profile_link_color = null;
    private String profile_sidebar_fill_color = null;
    private String profile_sidebar_border_color = null;
    private int friends_count = -1;
    private Date created_at = null;
    private int favourites_count = -1;
    private long utc_offset = -1;
    private String time_zone = null;
    private String profile_background_image_url = null;
    private boolean profile_background_tile = false;
    private int statuses_count = -1;
    private boolean following = false;
    private boolean notifications = false;

    public User(KXmlParser parser) throws XmlPullParserException, IOException {

        XmlParser.parse(parser, "user", this);
    }

    public User(HttpResponse hr) throws ParserException, UserParsingException {
        KXmlParser parser = null;
        try {
            parser = XmlParser.getParser(hr);
            XmlParser.parse(parser, "user", this);
        } catch (XmlPullParserException ex) {
            if (parser != null) {
                throw new UserParsingException(parser);
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
        if (key.compareTo("id") == 0) {
            setId(Long.parseLong((String) value));
        } else if (key.compareTo("name") == 0) {
            setName((String) value);
        } else if (key.compareTo("screen_name") == 0) {
            setScreen_name((String) value);
        } else if (key.compareTo("location") == 0) {
            setLocation((String) value);
        } else if (key.compareTo("description") == 0) {
            setDescription((String) value);
        } else if (key.compareTo("profile_image_url") == 0) {
            setProfile_image_url((String) value);
        } else if (key.compareTo("url") == 0) {
            setUrl((String) value);
        } else if (key.compareTo("followers_count") == 0) {
            setFollowers_count(Integer.parseInt((String) value));
        } else if (key.compareTo("friends_count") == 0) {
            setFriends_count(Integer.parseInt((String) value));
        } else if (key.compareTo("favourites_count") == 0) {
            setFavourites_count(Integer.parseInt((String) value));
        } else if (key.compareTo("statuses_count") == 0) {
            setStatuses_count(Integer.parseInt((String) value));
        } else if (key.compareTo("profile_background_color") == 0) {
            setProfile_background_color((String) value);
        } else if (key.compareTo("profile_text_color") == 0) {
            setProfile_text_color((String) value);
        } else if (key.compareTo("profile_link_color") == 0) {
            setProfile_link_color((String) value);
        } else if (key.compareTo("profile_sidebar_fill_color") == 0) {
            setProfile_sidebar_fill_color((String) value);
        } else if (key.compareTo("profile_sidebar_border_color") == 0) {
            setProfile_sidebar_border_color((String) value);
        } else if (key.compareTo("time_zone") == 0) {
            setTime_zone((String) value);
        } else if (key.compareTo("utc_offset") == 0) {
            setUtc_offset(Long.parseLong((String) value));
        } else if (key.compareTo("profile_background_image_url") == 0) {
            setProfile_background_image_url((String) value);
        } else if (key.compareTo("profile_background_tile") == 0) {
            setProfile_background_tile(((String) value).compareTo("true") == 0);
        } else if (key.compareTo("protected") == 0) {
            setProtected(((String) value).compareTo("true") == 0);
        } else if (key.compareTo("following") == 0) {
            setFollowing(((String) value).compareTo("true") == 0);
        } else if (key.compareTo("notifications") == 0) {
            setFollowing(((String) value).compareTo("true") == 0);
        } else if (key.compareTo("created_at") == 0) {
            setCreated_at(DateTools.parseDate((String) value));
        } else {
            return false;
        }
        return true; //else else ;)
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isProtected() {
        return Protected;
    }

    public void setProtected(boolean Protected) {
        this.Protected = Protected;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public String getProfile_background_color() {
        return profile_background_color;
    }

    public void setProfile_background_color(String profile_background_color) {
        this.profile_background_color = profile_background_color;
    }

    public String getProfile_text_color() {
        return profile_text_color;
    }

    public void setProfile_text_color(String profile_text_color) {
        this.profile_text_color = profile_text_color;
    }

    public String getProfile_link_color() {
        return profile_link_color;
    }

    public void setProfile_link_color(String profile_link_color) {
        this.profile_link_color = profile_link_color;
    }

    public String getProfile_sidebar_fill_color() {
        return profile_sidebar_fill_color;
    }

    public void setProfile_sidebar_fill_color(String profile_sidebar_fill_color) {
        this.profile_sidebar_fill_color = profile_sidebar_fill_color;
    }

    public String getProfile_sidebar_border_color() {
        return profile_sidebar_border_color;
    }

    public void setProfile_sidebar_border_color(String profile_sidebar_border_color) {
        this.profile_sidebar_border_color = profile_sidebar_border_color;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    public long getUtc_offset() {
        return utc_offset;
    }

    public void setUtc_offset(long utc_offset) {
        this.utc_offset = utc_offset;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public String getProfile_background_image_url() {
        return profile_background_image_url;
    }

    public void setProfile_background_image_url(String profile_background_image_url) {
        this.profile_background_image_url = profile_background_image_url;
    }

    public boolean isProfile_background_tile() {
        return profile_background_tile;
    }

    public void setProfile_background_tile(boolean profile_background_tile) {
        this.profile_background_tile = profile_background_tile;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }
}
