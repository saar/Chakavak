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

import http.HttpRequest;
import http.HttpResponse;
import java.io.IOException;
import java.util.Hashtable;
import twitterbase.api.Geo;
import twitterbase.api.ParserException;
import twitterbase.api.StatusParsingException;

/**
 *
 * @author Ramin Gomari
 */
public class StatusnetApi extends twitterbase.api.Api {

    private String apiRoot;
    private String sourceApp;
    private String username;
    private String password;
    private static final String PUBLIC_TIMELINE = "/api/statuses/public_timeline.xml";
    private static final String FRIENDS_TIMELINE = "/api/statuses/home_timeline.xml";
    private static final String HOME_TIMELINE = "/api/statuses/home_timeline.xml";
    private static final String USER_TIMELINE = "/api/statuses/user_timeline.xml";
    private static final String RESPONSES_TIMELINE = "/api/statuses/replies.xml";
    private static final String STATUS_UPDATE = "/api/statuses/update.xml";
    private static final String STATUS_DESTROY = "/api/statuses/destroy/"; //destroy/:id.format
    private static final String STATUS_RETWEET = "/api/statuses/retweet/"; //retweet/:id.format
    private static final String STATUS_SHOW = "/api/statuses/show/"; //retweet/:id.format
    private static final String DIRECT_TIMELINE = "/api/direct_messages.xml";
    private static final String FRIENDS = "/api/statuses/friends.xml";

    public StatusnetApi(String apiRoot, String sourceApp, String username, String password) {
        this.apiRoot = apiRoot;
        this.sourceApp = sourceApp;
        this.username = username;
        this.password = password;
    }

    public twitterbase.api.Status update(String status)
            throws IOException,
            ParserException,
            StatusParsingException {
        return update(status, -1, null);
    }

    public twitterbase.api.Status update(
            String status,
            long in_reply_to_status_id)
            throws IOException,
            ParserException,
            StatusParsingException {
        return update(status, in_reply_to_status_id, null);
    }

    public twitterbase.api.Status update(
            String status,
            long in_reply_to_status_id,
            twitterbase.api.Geo geo)
            throws IOException,
            ParserException,
            StatusParsingException {

        HttpResponse response;
        Hashtable table = new Hashtable();
        table.put("status", status);
        if (in_reply_to_status_id != -1) {
            table.put("in_reply_to_status_id", new Long(in_reply_to_status_id));
        }
        if (geo != null) {
            table.put("lat", new Float(geo.getLatitude()));
            table.put("long", new Float(geo.getLongitude()));
        }
        if (sourceApp != null && sourceApp.length() != 0) {
            table.put("source", sourceApp);
        }
        response = HttpRequest.makeHttpPostRequest(
                apiRoot + STATUS_UPDATE,
                username,
                password,
                table);
        return new Status(response);
    }

    public twitterbase.api.Status destroy(long id)
            throws IOException,
            StatusParsingException,
            ParserException {
        HttpResponse response;
        Hashtable table = new Hashtable();
        if (sourceApp != null && sourceApp.length() != 0) {
            table.put("source", sourceApp);
        }
        response = HttpRequest.makeHttpPostRequest(
                apiRoot + STATUS_DESTROY + Long.toString(id) + ".xml",
                username,
                password,
                table);
        return new Status(response);
    }

    public twitterbase.api.Status redent(long id)
            throws IOException,
            StatusParsingException,
            ParserException {
        return retweet(id);
    }

    public twitterbase.api.Status retweet(long id)
            throws IOException,
            StatusParsingException,
            ParserException {
        HttpResponse response;
        Hashtable table = new Hashtable();
        if (sourceApp != null && sourceApp.length() != 0) {
            table.put("source", sourceApp);
        }
        response = HttpRequest.makeHttpPostRequest(
                apiRoot + STATUS_RETWEET + Long.toString(id) + ".xml",
                username,
                password,
                table);
        return new Status(response);
    }

    public twitterbase.api.Status show(long id)
            throws IOException,
            StatusParsingException,
            ParserException {
        HttpResponse response;
        response = HttpRequest.makeHttpGetRequest(
                apiRoot + STATUS_SHOW + Long.toString(id) + ".xml",
                null,
                null);
        return new Status(response);
    }

    public twitterbase.api.Statuses home_timeline()
            throws IOException,
            StatusParsingException,
            ParserException {
        return home_timeline(-1, -1, -1);
    }

    public twitterbase.api.Statuses home_timeline(long since_id,
            long max_id,
            int count)
            throws IOException,
            StatusParsingException,
            ParserException {
        return getTimeline(since_id, max_id, count, apiRoot + HOME_TIMELINE);
    }

    public twitterbase.api.Statuses friends_timeline()
            throws IOException,
            StatusParsingException,
            ParserException {
        return friends_timeline(-1, -1, -1);
    }

    public twitterbase.api.Statuses friends_timeline(long since_id,
            long max_id,
            int count)
            throws IOException,
            StatusParsingException,
            ParserException {
        return getTimeline(since_id, max_id, count, apiRoot + FRIENDS_TIMELINE);
    }

    public twitterbase.api.Statuses getTimeline(long since_id,
            long max_id,
            int count,
            String address)
            throws IOException,
            StatusParsingException,
            ParserException {
        Hashtable table = new Hashtable();
        if (since_id != -1) {
            table.put("since_id", new Long(since_id));
        }

        if (max_id != -1) {
            table.put("max_id", new Long(max_id));
        }

        if (count != -1) {
            table.put("count", new Integer(count));
        }

        HttpResponse response;
        response = HttpRequest.makeHttpGetRequest(
                address,
                username,
                password, table);
        return new Statuses(response);
    }
}
