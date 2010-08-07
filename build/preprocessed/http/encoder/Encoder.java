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
package http.encoder;

/**
 * Provide methods for encode any string into base64 or url UTF8  form.
 * @author Ramin Gomari
 */
public final class Encoder {

    private static final String[] hexTable;

    static {
        hexTable = new String[256];
        StringBuffer temp;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                temp = new StringBuffer();
                if (i < 10) {
                    temp.append((char) (i + '0'));
                } else {
                    temp.append((char) (i + 'a' - 10));
                }
                if (j < 10) {
                    temp.append((char) (j + '0'));
                } else {
                    temp.append((char) (j + 'a' - 10));
                }
                hexTable[i * 16 + j] = temp.toString();
            }
        }
    }

    /**
     * Computes the Base64 encoding of {@code s} string
     * @param s The string to be encoded
     * @return The encoded string
     */
    public static String base64Encode(String s) {
        StringBuffer temp = new StringBuffer();
        byte sBytes[] = s.getBytes();
        final String key =
                new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        for (int i = 0; i
                < ((int) (sBytes.length / 3)) * 3; i +=
                        3) {
            temp.append(key.charAt(sBytes[i] >> 2));
            temp.append(key.charAt(((sBytes[i] & 3) << 4) + (sBytes[i + 1] >> 4)));
            temp.append(key.charAt(((sBytes[i + 1] & 0x0F) << 2)
                    + (sBytes[i + 2] >> 6)));
            temp.append(key.charAt(sBytes[i + 2] & 0x3F));
        }

        if (sBytes.length % 3 == 1) {
            temp.append(key.charAt(sBytes[sBytes.length - 1] >> 2));
            temp.append(key.charAt((sBytes[sBytes.length - 1] & 3) << 4));
            temp.append("==");
        } else if (sBytes.length % 3 == 2) {
            temp.append(key.charAt(sBytes[sBytes.length - 2] >> 2));
            temp.append(key.charAt(((sBytes[sBytes.length - 2] & 3) << 4)
                    + (sBytes[sBytes.length - 1] >> 4)));
            temp.append(key.charAt((sBytes[sBytes.length - 1] & 0x0F) << 2));
            temp.append("=");
        }

        return temp.toString();
    }

    /**
     * Encode a string to the "percent-encoded" form.
     * Percent-encoding, also known as URL encoding, is
     * a mechanism for encoding information in a Uniform
     * Resource Identifier (URI) under certain circumstances.
     * Although it is known as URL encoding it is, in fact,
     * used more generally within the main Uniform Resource
     * Identifier (URI) set, which includes both Uniform
     * Resource Locator (URL) and Uniform Resource Name (URN)
     * . As such it is also used in the preparation of data
     * of the "application/x-www-form-urlencoded" media type,
     * as is often used in email messages and the submission
     * of HTML form data in HTTP requests.
     * from: {@link http://en.wikipedia.org/wiki/Url_encoding}
     * @param s The string to be encoded
     * @return The encoded string
     */
    public static String percentEncoder(String s) {
        StringBuffer utf8 = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ( //Unreserved chars, RFC 3986 section 2.3 Unreserved Characters (January 2005)
                    ('A' <= c && c <= 'Z')
                    || ('a' <= c && c <= 'z')
                    || ('0' <= c && c <= '9')
                    || c == '-'
                    || c == '_'
                    || c == '~'
                    || c == '.') {
                utf8.append(c);
            } else if (c == ' ') {
                utf8.append('+');
            } else if (c <= 0x007f) {
                utf8.append("%");
                utf8.append(intToHex(c));
            } else if (c <= 0x07FF) {
                /*
                 * UTF8 Encodig from wikipedia:
                 * 
                 *  from 00000000 10000000 to 00000111 11111111 (0x80 to 0x07FF)
                 *            yyy xxxxxxxx         yyy xxxxxxxx
                 * 
                 * byte 1: 110yyyxx =>  11000000 or (c >> 6)
                 *                      \______/
                 *                        0xC0
                 * 
                 * byte 2: 10xxxxxx =>  10000000 or (c and 00111111)
                 *                      \______/           \______/
                 *                        0x80               0x3F
                 * 
                 */
                utf8.append("%");
                utf8.append(intToHex(0xc0 | (c >> 6)));
                utf8.append("%");
                utf8.append(intToHex(0x80 | (c & 0x3F)));
            } else {
                /*
                 * UTF8 Encodig from wikipedia:
                 * 
                 *  from 00001000 00000000 to 11111111 11111111 (0x80 to 0x07FF)
                 *       yyyyyyyy xxxxxxxx    yyyyyyyy xxxxxxxx
                 * 
                 * byte 1: 1110yyyy =>  11000000 or (c >> 12)
                 *                      \______/
                 *                        0xE0
                 * 
                 * byte 2: 10yyyyxx =>  10000000 or ((c >> 6) and 00111111)
                 *                      \______/                  \______/
                 *                        0x80                      0x3F
                 * byte 3: 10xxxxxx =>  10000000 or (c and 00111111)
                 *                      \______/           \______/
                 *                        0x80               0x3F
                 * 
                 */
                utf8.append("%");
                utf8.append(intToHex(0xe0 | (c >> 12)));
                utf8.append("%");
                utf8.append(intToHex(0x80 | ((c >> 6) & 0x3F)));
                utf8.append("%");
                utf8.append(intToHex(0x80 | (c & 0x3F)));
            }
        }
        return utf8.toString();
    }

    /**
     * Convert an integer into an hex formated string.
     * @param value The number to be convert
     * @return The coverted string
     */
    private static String intToHex(int value) {
        if (value < 256) { //more speed and less cpu usage
            return hexTable[value];
        }
        //else:
        StringBuffer hex = new StringBuffer();
        while (value >= 16) {
            char c = (char) (value % 16);
            if (c >= 10) {
                c += 'a' - 10;
            } else {
                c += '0';
            }
            hex.append(c);
            value /= 16;
        }
        return hex.reverse().toString();
    }
}
