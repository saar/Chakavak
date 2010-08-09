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

import com.exploringxml.xml.Node;
import java.util.Date;

/**
 *
 * @author Ramin Gomari
 */
public abstract class User {

    private long id;
    private String name = "";
    private String screen_name = "";
    private String location = "";
    private String description = "";
    private String profile_image_url = "";
    private String url = "";
    private boolean Protected = false;
    private int followers_count = -1;
    private String profile_background_color = "";
    private String profile_text_color = "";
    private String profile_link_color = "";
    private String profile_sidebar_fill_color = "";
    private String profile_sidebar_border_color = "";
    private int friends_count = -1;
    private Date created_at = null;
    private int favourites_count = -1;
    private long utc_offset = -1;
    private String time_zone = "";
    private String profile_background_image_url = "";
    private boolean profile_background_tile = false;
    private int statuses_count = -1;
    private boolean following = false;
    private boolean notifications = false;

    public User(String name) {
        setName(name);
    }

    public User(Node node) {
        parseUserXML(node);
    }

    abstract protected void parseUserXML(Node node);

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the screen_name
     */
    public String getScreen_name() {
        return screen_name;
    }

    /**
     * @param screen_name the screen_name to set
     */
    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the profile_image_url
     */
    public String getProfile_image_url() {
        return profile_image_url;
    }

    /**
     * @param profile_image_url the profile_image_url to set
     */
    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the Protected
     */
    public boolean isProtected() {
        return Protected;
    }

    /**
     * @param Protected the Protected to set
     */
    public void setProtected(boolean Protected) {
        this.Protected = Protected;
    }

    /**
     * @return the followers_count
     */
    public int getFollowers_count() {
        return followers_count;
    }

    /**
     * @param followers_count the followers_count to set
     */
    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    /**
     * @return the profile_background_color
     */
    public String getProfile_background_color() {
        return profile_background_color;
    }

    /**
     * @param profile_background_color the profile_background_color to set
     */
    public void setProfile_background_color(String profile_background_color) {
        this.profile_background_color = profile_background_color;
    }

    /**
     * @return the profile_text_color
     */
    public String getProfile_text_color() {
        return profile_text_color;
    }

    /**
     * @param profile_text_color the profile_text_color to set
     */
    public void setProfile_text_color(String profile_text_color) {
        this.profile_text_color = profile_text_color;
    }

    /**
     * @return the profile_link_color
     */
    public String getProfile_link_color() {
        return profile_link_color;
    }

    /**
     * @param profile_link_color the profile_link_color to set
     */
    public void setProfile_link_color(String profile_link_color) {
        this.profile_link_color = profile_link_color;
    }

    /**
     * @return the profile_sidebar_fill_color
     */
    public String getProfile_sidebar_fill_color() {
        return profile_sidebar_fill_color;
    }

    /**
     * @param profile_sidebar_fill_color the profile_sidebar_fill_color to set
     */
    public void setProfile_sidebar_fill_color(String profile_sidebar_fill_color) {
        this.profile_sidebar_fill_color = profile_sidebar_fill_color;
    }

    /**
     * @return the profile_sidebar_border_color
     */
    public String getProfile_sidebar_border_color() {
        return profile_sidebar_border_color;
    }

    /**
     * @param profile_sidebar_border_color the profile_sidebar_border_color to set
     */
    public void setProfile_sidebar_border_color(String profile_sidebar_border_color) {
        this.profile_sidebar_border_color = profile_sidebar_border_color;
    }

    /**
     * @return the friends_count
     */
    public int getFriends_count() {
        return friends_count;
    }

    /**
     * @param friends_count the friends_count to set
     */
    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    /**
     * @return the created_at
     */
    public Date getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    /**
     * @return the favourites_count
     */
    public int getFavourites_count() {
        return favourites_count;
    }

    /**
     * @param favourites_count the favourites_count to set
     */
    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    /**
     * @return the utc_offset
     */
    public long getUtc_offset() {
        return utc_offset;
    }

    /**
     * @param utc_offset the utc_offset to set
     */
    public void setUtc_offset(long utc_offset) {
        this.utc_offset = utc_offset;
    }

    /**
     * @return the time_zone
     */
    public String getTime_zone() {
        return time_zone;
    }

    /**
     * @param time_zone the time_zone to set
     */
    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    /**
     * @return the profile_background_image_url
     */
    public String getProfile_background_image_url() {
        return profile_background_image_url;
    }

    /**
     * @param profile_background_image_url the profile_background_image_url to set
     */
    public void setProfile_background_image_url(String profile_background_image_url) {
        this.profile_background_image_url = profile_background_image_url;
    }

    /**
     * @return the profile_background_tile
     */
    public boolean isProfile_background_tile() {
        return profile_background_tile;
    }

    /**
     * @param profile_background_tile the profile_background_tile to set
     */
    public void setProfile_background_tile(boolean profile_background_tile) {
        this.profile_background_tile = profile_background_tile;
    }

    /**
     * @return the statuses_count
     */
    public int getStatuses_count() {
        return statuses_count;
    }

    /**
     * @param statuses_count the statuses_count to set
     */
    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    /**
     * @return the following
     */
    public boolean isFollowing() {
        return following;
    }

    /**
     * @param following the following to set
     */
    public void setFollowing(boolean following) {
        this.following = following;
    }

    /**
     * @return the notifications
     */
    public boolean isNotifications() {
        return notifications;
    }

    /**
     * @param notifications the notifications to set
     */
    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }
}
