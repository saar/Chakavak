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
package http;

import http.encoder.Encoder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Ramin Gomari
 */
public class HttpRequest {

    public static HttpResponse makeHttpRequest(
            String method,
            String url,
            String username,
            String password,
            Hashtable parameters) throws IOException {

        OutputStream out = null;
        HttpConnection connection = null;
        HttpResponse httpResponse = null;

        StringBuffer data = new StringBuffer();
        if (parameters != null && !parameters.isEmpty()) {
            if (method.equals(HttpConnection.GET)) {
                data.append("?");
            }
            Enumeration keys = parameters.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                data.append(key);
                data.append("=");
                data.append(Encoder.percentEncoder(parameters.get(key).toString()));
                if (keys.hasMoreElements()) {
                    data.append("&");
                }

            }
            if (method.equals(HttpConnection.GET)) {
                url += data.toString();
            }
        }

        String authorization = null;
        if (username != null
                && password != null
                && username.length() != 0
                && password.length() != 0) {
            authorization = Encoder.base64Encode(username.concat(":".concat(password)));
        }

        try {
            connection = (HttpConnection) Connector.open(
                    url,
                    Connector.READ_WRITE,
                    true);

            connection.setRequestMethod(method);
            if (authorization != null) {
                connection.setRequestProperty("Authorization", "Basic " + authorization);
            }
            if (method.equals(HttpConnection.POST) && data.length() != 0) {

                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length",
                        Integer.toString(data.length()));
                out = connection.openOutputStream();
                out.write(data.toString().getBytes());
            }

            httpResponse = new HttpResponse(connection.openInputStream());


        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException ioe) {
            }
        }
        return httpResponse;
    }

    public static HttpResponse makeHttpGetRequest(
            String url,
            String username,
            String password) throws IOException {
        return makeHttpRequest(HttpConnection.GET, url, username, password, null);
    }

    public static HttpResponse makeHttpGetRequest(
            String url,
            String username,
            String password,
            Hashtable parameters) throws IOException {
        return makeHttpRequest(HttpConnection.GET, url, username, password, parameters);
    }

    public static HttpResponse makeHttpPostRequest(
            String url,
            String username,
            String password,
            Hashtable parameters) throws IOException {
        return makeHttpRequest(HttpConnection.POST, url, username, password, parameters);
    }
}
