package com.jimmyhowe.db.processors;

import com.jimmyhowe.support.collections.GeneralisedCollection;

import java.sql.ResultSet;

/**
 * Created by Jimmy on 23/08/2016.
 */
public abstract class PostProcessor<S, C extends GeneralisedCollection<S>>
{
    /**
     * Get Single
     *
     * @param resultSet
     */
    public abstract S single(ResultSet resultSet);

    /**
     * Get Collection
     *
     * @param resultSet
     */
    public abstract C collection(ResultSet resultSet);
}
