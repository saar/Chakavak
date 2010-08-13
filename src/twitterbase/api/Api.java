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

    /**
     * Updates the authenticating user's status. A status update with
     * text identical to the authenticating user's text identical to
     * the authenticating user's current status will be ignored to
     * prevent duplicates.
     * @param status The text of your status update, up to 140 characters.
     * URL encode as necessary. 
     * @return a single status
     * @throws IOException
     * @throws StatusParsingException
     * @throws ParserException
     */
    abstract public Status update(
            String status)
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     * Updates the authenticating user's status. A status update with
     * text identical to the authenticating user's text identical to
     * the authenticating user's current status will be ignored to
     * prevent duplicates.
     * @param status The text of your status update, up to 140 characters.
     * URL encode as necessary.
     * @param in_reply_to_status_id The ID of an existing status that the
     * update is in reply to.
     * @return a single status
     * @throws IOException
     * @throws StatusParsingException
     * @throws ParserException
     */
    abstract public Status update(
            String status,
            long in_reply_to_status_id)
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     * Updates the authenticating user's status. A status update with 
     * text identical to the authenticating user's text identical to 
     * the authenticating user's current status will be ignored to 
     * prevent duplicates.
     * @param status The text of your status update, up to 140 characters. 
     * URL encode as necessary. 
     * @param in_reply_to_status_id The ID of an existing status that the
     * update is in reply to. 
     * @param geo The the location this tweet refers to. 
     * @return a single status
     * @throws IOException
     * @throws StatusParsingException
     * @throws ParserException
     */
    abstract public Status update(
            String status,
            long in_reply_to_status_id,
            Geo geo)
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     * Destroys the status specified by the required ID parameter. 
     * The authenticating user must be the author of the specified status.
     * @param id The numerical ID of the desired status. 
     * @return a single status that was deleted
     * @throws IOException
     * @throws StatusParsingException
     * @throws ParserException
     */
    abstract public Status destroy(long id)
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     * Returns a single status, specified by the id parameter below.
     * The status's author will be returned inline.
     * @param id The numerical ID of the desired status. 
     * @return a single status, specified by the id parameter
     * @throws IOException
     * @throws StatusParsingException
     * @throws ParserException
     */
    abstract public Status show(long id)
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     * Retweets a tweet. Returns the original tweet with
     * retweet details embedded.
     * @param id The numerical ID of the desired status.
     * @return a single status
     * @throws IOException
     * @throws StatusParsingException
     * @throws ParserException
     */
    abstract public Status retweet(long id)
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     * Returns the 20 most recent statuses, including retweets 
     * if they exist, posted by the authenticating user and 
     * the user's they follow. This is the same timeline seen 
     * by a user when they login to twitter.com.
     * 
     * This method is identical to statuses/friends_timeline, 
     * except that this method always includes retweets.
     * 
     * This method is can only return up to 800 statuses, 
     * including retweets.

     * @return a set of statuses
     */
    abstract public Statuses home_timeline()
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     * Returns the 20 most recent statuses, including retweets
     * if they exist, posted by the authenticating user and
     * the user's they follow. This is the same timeline seen
     * by a user when they login to twitter.com.
     *
     * This method is identical to statuses/friends_timeline,
     * except that this method always includes retweets.
     *
     * This method is can only return up to 800 statuses,
     * including retweets.
     * @param since_id Returns results with an ID greater
     * than (that is, more recent than) the specified ID.
     * @param max_id Returns results with an ID less than
     * (that is, older than) or equal to the specified ID.
     * @param count Specifies the number of records to
     * retrieve. May not be greater than 200.
     * @return a set of statuses
     */
    abstract public Statuses home_timeline(long since_id,
            long max_id, int count)
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     * Returns the 20 most recent statuses posted by the authenticating
     * user and that user's friends. This is the equivalent of
     * /timeline/home on the Web.
     * @return a set of statuses
     */
    abstract public Statuses friends_timeline()
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     * Returns the 20 most recent statuses posted by the authenticating
     * user and that user's friends. This is the equivalent of
     * /timeline/home on the Web.
     * @param since_id Returns results with an ID greater
     * than (that is, more recent than) the specified ID.
     * @param max_id Returns results with an ID less than
     * (that is, older than) or equal to the specified ID.
     * @param count Specifies the number of records to
     * retrieve. May not be greater than 200.
     * @return a set of statuses
     */
    abstract public Statuses friends_timeline(long since_id,
            long max_id,
            int count)
            throws IOException,
            StatusParsingException,
            ParserException;

    /**
     *  Returns the most recent statuses on the time line.
     * @param since_id Returns results with an ID greater
     * than (that is, more recent than) the specified ID. Set it -1 to disable.
     * @param max_id Returns results with an ID less than
     * (that is, older than) or equal to the specified ID. Set it -1 to disable.
     * @param count count Specifies the number of records to
     * retrieve. May not be greater than 200. Set it -1 to disable.
     * @param address time line address
     * @return a set of statuses
     */
    abstract public twitterbase.api.Statuses getTimeline(long since_id,
            long max_id,
            int count,
            String address)
            throws IOException,
            StatusParsingException,
            ParserException;
}
