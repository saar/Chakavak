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

import java.io.IOException;

/**
 *
 * @author Ramin Gomari
 */
public abstract class Api {

    abstract public Status update(
            String status) throws IOException;

    abstract public Status update(
            String status,
            long in_reply_to_status_id) throws IOException;

    abstract public Status update(
            String status,
            long in_reply_to_status_id,
            Geo geo) throws IOException;
}
