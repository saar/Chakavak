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
import utilities.DateTools;

/**
 *
 * @author Ramin Gomari
 */
public class User extends twitterbase.api.User {

    private String statusnet_profile_url = "";
    private boolean statusnet_blocking = false;

    public User(String name) {
        super(name);
    }

    public User(Node node) {
        super(node);
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

    protected void parseUserXML(Node node) {
        int occur[] = {1};
        Node tempNode = node.find("id", occur);
        if (tempNode != null && tempNode.getCharacters().length() != 0) {
            setId(Long.parseLong(tempNode.getCharacters()));
        } else {
            setId(-1);
        }

        tempNode = node.find("name", occur);
        if (tempNode != null) {
            setName(tempNode.getCharacters());
        }

        tempNode = node.find("screen_name", occur);
        if (tempNode != null) {
            setScreen_name(tempNode.getCharacters());
        }

        tempNode = node.find("location", occur);
        if (tempNode != null) {
            setLocation(tempNode.getCharacters());
        }

        tempNode = node.find("description", occur);
        if (tempNode != null) {
            setDescription(tempNode.getCharacters());
        }


        tempNode = node.find("profile_image_url", occur);
        if (tempNode != null) {
            setProfile_image_url(tempNode.getCharacters());
        }

        tempNode = node.find("url", occur);
        if (tempNode != null) {
            setUrl(tempNode.getCharacters());
        }

        tempNode = node.find("followers_count", occur);
        if (tempNode != null) {
            setFollowers_count(Integer.parseInt(tempNode.getCharacters()));
        }

        tempNode = node.find("friends_count", occur);
        if (tempNode != null) {
            setFriends_count(Integer.parseInt(tempNode.getCharacters()));
        }

        tempNode = node.find("favourites_count", occur);
        if (tempNode != null) {
            setFavourites_count(Integer.parseInt(tempNode.getCharacters()));
        }

        tempNode = node.find("statuses_count", occur);
        if (tempNode != null) {
            setStatuses_count(Integer.parseInt(tempNode.getCharacters()));
        }

        tempNode = node.find("profile_background_color", occur);
        if (tempNode != null) {
            setProfile_background_color(tempNode.getCharacters());
        }

        tempNode = node.find("profile_text_color", occur);
        if (tempNode != null) {
            setProfile_text_color(tempNode.getCharacters());
        }

        tempNode = node.find("profile_link_color", occur);
        if (tempNode != null) {
            setProfile_link_color(tempNode.getCharacters());
        }

        tempNode = node.find("profile_sidebar_fill_color", occur);
        if (tempNode != null) {
            setProfile_sidebar_fill_color(tempNode.getCharacters());
        }

        tempNode = node.find("profile_sidebar_border_color", occur);
        if (tempNode != null) {
            setProfile_sidebar_border_color(tempNode.getCharacters());
        }

        tempNode = node.find("time_zone", occur);
        if (tempNode != null) {
            setTime_zone(tempNode.getCharacters());
        }

        tempNode = node.find("utc_offset", occur);
        if (tempNode != null) {
            setUtc_offset(Long.parseLong(tempNode.getCharacters()));
        }

        tempNode = node.find("profile_background_image_url", occur);
        if (tempNode != null) {
            setProfile_background_image_url(tempNode.getCharacters());
        }

        tempNode = node.find("statusnet:profile_url", occur);
        if (tempNode != null) {
            setStatusnet_profile_url(tempNode.getCharacters());
        }



        tempNode = node.find("profile_background_tile", occur);
        if (tempNode != null) {
            setProfile_background_tile(tempNode.getCharacters().equalsIgnoreCase("true") ? true : false);
        }


        tempNode = node.find("protected", occur);
        if (tempNode != null) {
            setProtected(tempNode.getCharacters().equalsIgnoreCase("true") ? true : false);
        }

        tempNode = node.find("statusnet:blocking", occur);
        if (tempNode != null) {
            setStatusnet_blocking(tempNode.getCharacters().equalsIgnoreCase("true") ? true : false);
        }

        tempNode = node.find("following", occur);
        if (tempNode != null) {
            setFollowing(tempNode.getCharacters().equalsIgnoreCase("true") ? true : false);
        }

        tempNode = node.find("notifications", occur);
        if (tempNode != null) {
            setFollowing(tempNode.getCharacters().equalsIgnoreCase("true") ? true : false);
        }

        tempNode = node.find("created_at", occur);
        if (tempNode != null) {
            setCreated_at(DateTools.parseDate(tempNode.getCharacters()));
        }
    }

}
