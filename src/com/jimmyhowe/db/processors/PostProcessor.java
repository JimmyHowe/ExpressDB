package com.jimmyhowe.db.processors;

import com.jimmyhowe.support.collections.GeneralisedCollection;

import java.sql.ResultSet;

/**
 * Abstract Post Processor
 *
 * The Post Processor is used to process the results of the ResultSet into a single object
 * or a collection of objects for multiple rows.
 *
 * @param <S>    Single Row Object
 * @param <C>    Collection Object
 */
public abstract class PostProcessor<S, C extends GeneralisedCollection<S>>
{
    /**
     * Returns a single record
     *
     * @param resultSet Result Set
     *
     * @return Single Instance
     */
    public abstract S single(ResultSet resultSet);

    /**
     * Returns a collection of records
     *
     * @param resultSet Result Set
     *
     * @return Collection Instance
     */
    public abstract C collection(ResultSet resultSet);
}
