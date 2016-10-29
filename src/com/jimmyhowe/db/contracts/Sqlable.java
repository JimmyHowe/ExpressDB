package com.jimmyhowe.db.contracts;

/**
 * Created by Jimmy on 29/10/2016.
 */
public interface Sqlable
{
    /**
     * @return SQL Statement / Fragment
     */
    String toSql();
}
