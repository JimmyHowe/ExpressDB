package com.jimmyhowe.db.contracts;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * MySQL Data Types
 */
public interface MySQLTypes
{
    /**
     * TYPES for converting MySQL columnNames to Java Objects
     */

    default Map<String, Class> TYPES()
    {
        final Map<String, Class> TYPES = new HashMap<>();

        TYPES.put("INT UNSIGNED", Long.class);
        TYPES.put("INTEGER", Integer.class);
        TYPES.put("TINYINT", Byte.class);
        TYPES.put("SMALLINT", Short.class);
        TYPES.put("BIGINT", Long.class);
        TYPES.put("REAL", Float.class);
        TYPES.put("FLOAT", Double.class);
        TYPES.put("DOUBLE", Double.class);
        TYPES.put("DECIMAL", BigDecimal.class);
        TYPES.put("NUMERIC", BigDecimal.class);
        TYPES.put("BOOLEAN", Boolean.class);
        TYPES.put("CHAR", String.class);
        TYPES.put("VARCHAR", String.class);
        TYPES.put("LONG VARCHAR", String.class);
        TYPES.put("DATE", Date.class);
        TYPES.put("TIME", Time.class);
        TYPES.put("TIMESTAMP", Timestamp.class);
        TYPES.put("SERIAL", Integer.class);

        return Collections.unmodifiableMap(TYPES);
    }
}
