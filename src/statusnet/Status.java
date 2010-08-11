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

import twitterbase.api.StatusParsingException;
import twitterbase.api.ParserException;
import com.exploringxml.xml.Node;
import com.exploringxml.xml.Xparse;
import utilities.DateTools;

/**
 *
 * @author Ramin Gomari
 */
public class Status extends twitterbase.api.Status {

    private String statusnet_html = "";

    public Status(String text) throws ParserException, StatusParsingException {
        super(text);
    }
    public Status(Node status) throws ParserException, StatusParsingException {
        super(status);
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

    protected void pars(Node root) throws ParserException, StatusParsingException {
        int occur[] = {1};

        Node text = root.find("text", occur);
        Node id = root.find("id", occur);
        if (text == null || id == null) {
            throw new StatusParsingException(root);
        }
        setText(text.getCharacters());
        setId(Long.parseLong(id.getCharacters()));
        Node tempNode = root.find("created_at", occur);
        if (tempNode != null) {
            setCreate_at(DateTools.parseDate(tempNode.getCharacters()));
        }

        tempNode = root.find("truncated", occur);
        if (tempNode != null) {
            setTruncated(tempNode.getCharacters().equalsIgnoreCase("true") ? true : false);
        }

        tempNode = root.find("favorited", occur);
        if (tempNode != null) {
            setTruncated(tempNode.getCharacters().equalsIgnoreCase("true") ? true : false);
        }

        tempNode = root.find("in_reply_to_status_id", occur);
        if (tempNode != null && tempNode.getCharacters().length() != 0) {
            setIn_reply_to_status_id(Long.parseLong(tempNode.getCharacters()));
        }
        tempNode = root.find("in_reply_to_user_id", occur);
        if (tempNode != null && tempNode.getCharacters().length() != 0) {
            setIn_reply_to_user_id(Long.parseLong(tempNode.getCharacters()));
        }

        tempNode = root.find("in_reply_to_screen_name", occur);
        if (tempNode != null) {
            setIn_reply_to_screen_name(tempNode.getCharacters());
        }


        tempNode = root.find("source", occur);
        if (tempNode != null) {
            setSource(tempNode.getCharacters());
        }

        tempNode = root.find("user", occur);
        if (tempNode != null) {
            setUser(new User(tempNode));
        }

        tempNode = root.find("statusnet:html", occur);
        if (tempNode != null) {
            setStatusnet_html(tempNode.getCharacters());
        }


        tempNode = root.find("geo", occur);
        if (tempNode != null) {
            tempNode = tempNode.find("georss:point", occur);

            if (tempNode != null) {
                setGeo(new Geo(tempNode.getCharacters()));

            }
        }
    }

    protected void pars(String xml) throws ParserException, StatusParsingException {
        int occur[] = {1};
        Node root = new Xparse().parse(xml).find("status", occur);
        if (root == null) {
            throw new ParserException("Can not parse \n" + xml);
        }
        pars(root);
    }
}
