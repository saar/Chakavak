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

/**
 *
 * @author Ramin Gomari
 */
public class User extends twitterbase.api.User {

    private String statusnet_profile_url;

    public User(String name) {
        super(name);
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
}
