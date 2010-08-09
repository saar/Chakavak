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

/**
 *
 * @author Ramin Gomari
 */
public class Geo {

    private float latitude;
    private float longitude;

    public Geo(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Geo() {
    }

    /**
     * Create a geo from comma or space seprated string
     * @param geo comma or space seprated of lat and long
     */
    public Geo(String geo) {
        StringBuffer sb[] = new StringBuffer[2];
        int index = 0;
        sb[index] = new StringBuffer();
        char c;
        for (int i = 0; i < geo.length(); i++) {
            c = geo.charAt(i);
            if (c == ' ' || c == ',') {
                sb[++index] = new StringBuffer();
            } else {
                sb[index].append(c);
            }
        }
        latitude = Float.parseFloat(sb[0].toString());
        longitude = Float.parseFloat(sb[1].toString());
    }

    /**
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

}
