# Database
Java Database Package

Provides fluent interface around the JCDB Driver

Set the DB Connection
    
    DB.connectWith(new MySQLAdapter("db_name"))

Getting single records

    Row firstUser = DB.table("users").first();

Getting collections

    Rows allUsers = DB.table("users").get();
    
Complex Queries
    
    Rows allUsers = DB.table("users").where("status", "=", "active").orderBy("popularity").get();
