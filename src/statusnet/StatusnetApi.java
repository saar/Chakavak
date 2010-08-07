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
    private static final String USER_TIMELINE = "/api/statuses/user_timeline.xml";
    private static final String RESPONSES_TIMELINE = "/api/statuses/replies.xml";
    private static final String STATUS_UPDATE = "/api/statuses/update.xml";
    private static final String DIRECT_TIMELINE = "/api/direct_messages.xml";
    private static final String FRIENDS = "/api/statuses/friends.xml";

    public StatusnetApi(String apiRoot, String sourceApp, String username, String password) {
        this.apiRoot = apiRoot;
        this.sourceApp = sourceApp;
        this.username = username;
        this.password = password;
    }

    public twitterbase.api.Status update(String status) throws IOException {
        return update(status, -1, null);
    }

    public twitterbase.api.Status update(
            String status,
            long in_reply_to_status_id) throws IOException {
        return update(status, in_reply_to_status_id, null);
    }

    public twitterbase.api.Status update(
            String status,
            long in_reply_to_status_id,
            twitterbase.api.Geo geo) throws IOException {

        HttpResponse response;
        Hashtable table = new Hashtable();
        table.put("status", status);
        if (in_reply_to_status_id != -1) {
            table.put("in_reply_to_status_id", new Long(in_reply_to_status_id));
        }
        if (geo != null) {
            table.put("lat", new Long(geo.getLatitude()));
            table.put("long", new Long(geo.getLongitude()));
        }
        if (sourceApp != null && sourceApp.length() != 0) {
            table.put("source", sourceApp);
        }
        response = HttpRequest.makeHttpPostRequest(
                apiRoot + STATUS_UPDATE,
                username,
                password,
                table);
        return new Status(response.getResponse());
    }
}
