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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Ramin Gomari
 */
public class HttpResponse {

    private String text;
    private InputStream in;

    public HttpResponse(InputStream in) {
        this.in = in;
    }

    public String readText() throws IOException {
        if (this.text == null) {
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
            InputStreamReader isr = getReader();
            int readedChar;
            while ((readedChar = isr.read()) != -1) {
                buffer.append((char) readedChar);
            }
            this.text = buffer.toString();
        }
        return this.text;
    }

    public InputStreamReader getReader() {
        InputStreamReader isr;
        try {
            isr = new InputStreamReader(getInputStream(), "UTF-8");
        } catch (UnsupportedEncodingException exception) {
            isr = new InputStreamReader(getInputStream());
        }
        return isr;
    }

    public InputStream getInputStream() {
        return in;
    }
    
    public void closeStream() {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
        }
        in = null;
    }
}
