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
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
        InputStreamReader in = null;
        HttpResponse httpResponse = null;

        StringBuffer data = new StringBuffer();
        if (parameters != null) {
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


//            int length = (int) connection.getLength();
//            byte[] responseData = null;
//            if (length != -1) {
//                responseData = new byte[length];
//                in = new  DataInputStream(connection.openInputStream());
//                in.readFully(responseData);
//
//            } else {
//                // If content length is not given, read in chunks.
//                int chunkSize = 1024;
//                int index = 0;
//                int readLength = 0;
//                in = new DataInputStream(connection.openInputStream());
//                responseData = new byte[chunkSize];
//                do {
//                    if (responseData.length < index + chunkSize) {
//                        byte[] newData = new byte[index + chunkSize];
//                        System.arraycopy(responseData, 0, newData, 0, responseData.length);
//                        responseData = newData;
//                    }
//                    readLength = in.read(responseData, index, chunkSize);
//                    index += readLength;
//                } while (readLength == chunkSize);
//                length = index;
//            }
//            if (length < responseData.length) {
//                byte[] newData = new byte[length];
//                System.arraycopy(responseData, 0, newData, 0, length);
//                responseData = newData;
//            }
//            httpResponse = new HttpResponse(responseData);
            StringBuffer buffer = new StringBuffer();
            try {
                in = new InputStreamReader(connection.openInputStream(), "UTF-8");
            } catch (UnsupportedEncodingException exception) {
                in = new InputStreamReader(connection.openInputStream());
            }
            int readedChar;
            while ((readedChar = in.read()) != -1) {
                buffer.append((char) readedChar);
            }

            httpResponse = new HttpResponse(buffer.toString());


        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
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

    public static HttpResponse makeHttpPostRequest(
            String url,
            String username,
            String password,
            Hashtable parameters) throws IOException {
        return makeHttpRequest(HttpConnection.POST, url, username, password, parameters);
    }
}
