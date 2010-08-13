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

import java.util.Date;

/**
 *
 * @author Ramin Gomari
 */
public interface User {

    /**
     * @return the id
     */
    public long getId();

    /**
     * @param id the id to set
     */
    public void setId(long id);

    /**
     * @return the name
     */
    public String getName();

    /**
     * @param name the name to set
     */
    public void setName(String name);

    /**
     * @return the screen_name
     */
    public String getScreen_name();

    /**
     * @param screen_name the screen_name to set
     */
    public void setScreen_name(String screen_name);

    /**
     * @return the location
     */
    public String getLocation();

    /**
     * @param location the location to set
     */
    public void setLocation(String location);

    /**
     * @return the description
     */
    public String getDescription();

    /**
     * @param description the description to set
     */
    public void setDescription(String description);

    /**
     * @return the profile_image_url
     */
    public String getProfile_image_url();

    /**
     * @param profile_image_url the profile_image_url to set
     */
    public void setProfile_image_url(String profile_image_url);

    /**
     * @return the url
     */
    public String getUrl();

    /**
     * @param url the url to set
     */
    public void setUrl(String url);

    /**
     * @return the Protected
     */
    public boolean isProtected();

    /**
     * @param Protected the Protected to set
     */
    public void setProtected(boolean Protected);

    /**
     * @return the followers_count
     */
    public int getFollowers_count();

    /**
     * @param followers_count the followers_count to set
     */
    public void setFollowers_count(int followers_count);

    /**
     * @return the profile_background_color
     */
    public String getProfile_background_color();

    /**
     * @param profile_background_color the profile_background_color to set
     */
    public void setProfile_background_color(String profile_background_color);

    /**
     * @return the profile_text_color
     */
    public String getProfile_text_color();

    /**
     * @param profile_text_color the profile_text_color to set
     */
    public void setProfile_text_color(String profile_text_color);

    /**
     * @return the profile_link_color
     */
    public String getProfile_link_color();

    /**
     * @param profile_link_color the profile_link_color to set
     */
    public void setProfile_link_color(String profile_link_color);

    /**
     * @return the profile_sidebar_fill_color
     */
    public String getProfile_sidebar_fill_color();

    /**
     * @param profile_sidebar_fill_color the profile_sidebar_fill_color to set
     */
    public void setProfile_sidebar_fill_color(String profile_sidebar_fill_color);

    /**
     * @return the profile_sidebar_border_color
     */
    public String getProfile_sidebar_border_color();

    /**
     * @param profile_sidebar_border_color the profile_sidebar_border_color to set
     */
    public void setProfile_sidebar_border_color(String profile_sidebar_border_color);

    /**
     * @return the friends_count
     */
    public int getFriends_count();

    /**
     * @param friends_count the friends_count to set
     */
    public void setFriends_count(int friends_count);

    /**
     * @return the created_at
     */
    public Date getCreated_at();

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(Date created_at);

    /**
     * @return the favourites_count
     */
    public int getFavourites_count();

    /**
     * @param favourites_count the favourites_count to set
     */
    public void setFavourites_count(int favourites_count);

    /**
     * @return the utc_offset
     */
    public long getUtc_offset();

    /**
     * @param utc_offset the utc_offset to set
     */
    public void setUtc_offset(long utc_offset);

    /**
     * @return the time_zone
     */
    public String getTime_zone();

    /**
     * @param time_zone the time_zone to set
     */
    public void setTime_zone(String time_zone);

    /**
     * @return the profile_background_image_url
     */
    public String getProfile_background_image_url();

    /**
     * @param profile_background_image_url the profile_background_image_url to set
     */
    public void setProfile_background_image_url(String profile_background_image_url);

    /**
     * @return the profile_background_tile
     */
    public boolean isProfile_background_tile();

    /**
     * @param profile_background_tile the profile_background_tile to set
     */
    public void setProfile_background_tile(boolean profile_background_tile);

    /**
     * @return the statuses_count
     */
    public int getStatuses_count();

    /**
     * @param statuses_count the statuses_count to set
     */
    public void setStatuses_count(int statuses_count);

    /**
     * @return the following
     */
    public boolean isFollowing();

    /**
     * @param following the following to set
     */
    public void setFollowing(boolean following);

    /**
     * @return the notifications
     */
    public boolean isNotifications();

    /**
     * @param notifications the notifications to set
     */
    public void setNotifications(boolean notifications);
}
