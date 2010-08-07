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
public class Status extends twitterbase.api.Status {

    private String statusnet_html;

    public Status(String text) {
        super(text);
    }

    /**
     * @return the statusnet_html
     */
    public String getStatusnet_html() {
        return statusnet_html;
    }

    /**
     * @param statusnet_html the statusnet_html to set
     */
    public void setStatusnet_html(String statusnet_html) {
        this.statusnet_html = statusnet_html;
    }

    protected void pars(String text) {
        System.out.println(text);
    }
}

