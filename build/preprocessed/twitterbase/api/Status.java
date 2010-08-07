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
public abstract class Status {

    private long id;
    private String text;
    private boolean truncated;
    private Date create_at;
    private long in_reply_to_status_id;
    private String source;
    private User user;
    private long in_reply_to_user_id;
    private String in_reply_to_screen_name;
    private Geo geo;
    private boolean favorited;

    public Status(String text) {
        pars(text);
    }

    abstract protected void pars(String text);

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
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the truncated
     */
    public boolean isTruncated() {
        return truncated;
    }

    /**
     * @param truncated the truncated to set
     */
    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    /**
     * @return the create_at
     */
    public Date getCreate_at() {
        return create_at;
    }

    /**
     * @param create_at the create_at to set
     */
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    /**
     * @return the in_reply_to_status_id
     */
    public long getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    /**
     * @param in_reply_to_status_id the in_reply_to_status_id to set
     */
    public void setIn_reply_to_status_id(long in_reply_to_status_id) {
        this.in_reply_to_status_id = in_reply_to_status_id;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the in_reply_to_user_id
     */
    public long getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    /**
     * @param in_reply_to_user_id the in_reply_to_user_id to set
     */
    public void setIn_reply_to_user_id(long in_reply_to_user_id) {
        this.in_reply_to_user_id = in_reply_to_user_id;
    }

    /**
     * @return the in_reply_to_screen_name
     */
    public String getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    /**
     * @param in_reply_to_screen_name the in_reply_to_screen_name to set
     */
    public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
        this.in_reply_to_screen_name = in_reply_to_screen_name;
    }

    /**
     * @return the geo
     */
    public Geo getGeo() {
        return geo;
    }

    /**
     * @param geo the geo to set
     */
    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    /**
     * @return the favorited
     */
    public boolean isFavorited() {
        return favorited;
    }

    /**
     * @param favorited the favorited to set
     */
    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}

