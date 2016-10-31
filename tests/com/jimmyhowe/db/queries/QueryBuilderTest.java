package com.jimmyhowe.db.queries;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.TestCase;
import com.jimmyhowe.db.queries.components.AndWhere;
import com.jimmyhowe.db.queries.components.OrWhere;
import com.jimmyhowe.db.queries.components.Where;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 09/09/2016.
 */
public class QueryBuilderTest extends TestCase
{
    private QueryBuilder queryBuilder;

    public QueryBuilderTest()
    {
        ConnectsWithDatabase();
    }

    @Before
    public void setUp() throws Exception
    {
        this.queryBuilder = new QueryBuilder("test");
    }

    @Test
    public void it_can_return_table_name() throws Exception
    {
        assertEquals("test", this.queryBuilder.getTableName());
    }

    @Test
    public void it_can_generate_a_simple_select_all() throws Exception
    {
        assertEquals("SELECT * FROM test", this.queryBuilder.get());
    }

    @Test
    public void it_can_generate_a_simple_select_with_columns() throws Exception
    {
        assertEquals("SELECT id, name, email FROM test", this.queryBuilder.get("id", "name", "email"));
    }

    @Test
    public void it_can_generate_a_simple_select_first() throws Exception
    {
        assertEquals("SELECT * FROM test LIMIT 1", this.queryBuilder.first());
    }

    @Test
    public void it_can_generate_a_simple_select_first_with_columns() throws Exception
    {
        assertEquals("SELECT id, name, email FROM test LIMIT 1", this.queryBuilder.first("id", "name", "email"));
    }

    @Test
    public void it_can_generate_a_list_using_default_primary_key() throws Exception
    {
        assertEquals("SELECT id, name FROM test", this.queryBuilder.list("name"));
    }

    @Test
    public void it_can_generate_a_list_using_primary_key() throws Exception
    {
        assertEquals("SELECT user_id, name FROM test", this.queryBuilder.list("user_id", "name"));
    }

    @Test
    public void it_can_generate_a_select_all_with_a_where_statement() throws Exception
    {
        assertEquals("SELECT * FROM test WHERE name = 'Jimmy'", this.queryBuilder.where("name", "Jimmy").get());
    }

    @Test
    public void it_can_generate_a_select_all_with_a_where_not_statement() throws Exception
    {
        assertEquals("SELECT * FROM test WHERE name != 'Jimmy'", this.queryBuilder.whereNot("name", "Jimmy").get());
    }

    @Test
    public void it_can_generate_a_select_all_with_a_where_statement_with_operator() throws Exception
    {
        assertEquals("SELECT * FROM test WHERE name != 'Jimmy'", this.queryBuilder.where("name", "!=", "Jimmy").get());
    }

    @Test
    public void it_can_generate_a_select_all_with_an_order_statement() throws Exception
    {
        assertEquals("SELECT * FROM test ORDER BY name ASC", this.queryBuilder.orderBy("name").get());
    }

    @Test
    public void it_can_generate_a_select_all_with_an_order_desc_statement() throws Exception
    {
        assertEquals("SELECT * FROM test ORDER BY name DESC", this.queryBuilder.orderByDesc("name").get());
    }

    @Test
    public void it_can_generate_a_select_all_with_a_specified_order_statement() throws Exception
    {
        assertEquals("SELECT * FROM test ORDER BY name DESC", this.queryBuilder.orderBy("name", "DESC").get());
    }

    @Test
    public void it_can_generate_a_select_all_with_an_order_by_and_a_where_statement() throws Exception
    {
        String expected = "SELECT * FROM test WHERE name = 'Jimmy' ORDER BY name DESC";

        Object actual = this.queryBuilder.where("name", "Jimmy").orderBy("name", "DESC").get();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_a_select_first_with_an_order_by_and_a_where_statement() throws Exception
    {
        String expected = "SELECT * FROM test WHERE name = 'Jimmy' ORDER BY name DESC LIMIT 1";

        Object actual = this.queryBuilder.where("name", "Jimmy").orderBy("name", "DESC").first();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_a_select_distinct() throws Exception
    {
        String expected = "SELECT DISTINCT email FROM test";

        Object actual = this.queryBuilder.distinct().select("email").get();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_select_with_and_where() throws Exception
    {
        String expected = "SELECT * FROM test WHERE id = '1' AND id = '2'";

        Object actual = this.queryBuilder.where("id", 1).andWhere("id", 2).get();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_select_with_or_where() throws Exception
    {
        String expected = "SELECT * FROM test WHERE id = '1' OR id = '2'";

        Object actual = this.queryBuilder.where("id", 1).orWhere("id", 2).get();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_select_with_multiple_wheres() throws Exception
    {
        String expected = "SELECT * FROM test WHERE ( id = '1' AND name = 'Jimmy' )";

        Object actual = this.queryBuilder.where(
                new Where("id", 1),
                new AndWhere("name", "Jimmy")
        ).get();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_select_with_multiple_wheres_and_or_where() throws Exception
    {
        String expected = "SELECT * FROM test WHERE ( id = '1' AND name = 'Jimmy' ) OR id = '2'";

        Object actual = this.queryBuilder.where(
                new Where("id", 1),
                new AndWhere("name", "Jimmy")
        ).orWhere("id", 2).get();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_select_with_multiple_and_or_where() throws Exception
    {
        String expected = "SELECT * FROM test WHERE ( id = '1' OR name = 'Jimmy' ) AND ( id = '2' OR name = 'Jurij' )";

        Object actual = this.queryBuilder
                .where(new Where("id", 1), new OrWhere("name", "Jimmy"))
                .andWhere(new Where("id", 2), new OrWhere("name", "Jurij"))
                .get();

        assertEquals(expected, actual);
    }


    @Test
    public void it_can_generate_an_update_statement() throws Exception
    {
        String expected = "UPDATE test SET name = 'Jimmy' WHERE id = '1'";

        Object actual = this.queryBuilder.set("name", "Jimmy").where("id", 1).update();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_an_update_statement_with_multiple_sets() throws Exception
    {
        String expected = "UPDATE test SET name = 'Jimmy', age = '33' WHERE id = '1'";

        Object actual = this.queryBuilder.set("name", "Jimmy").set("age", 33).where("id", 1).update();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_a_delete_statement() throws Exception
    {
        String expected = "DELETE FROM test WHERE id = '1'";

        Object actual = this.queryBuilder.where("id", 1).delete();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_a_delete_statement_with_multiple_wheres() throws Exception
    {
        String expected = "DELETE FROM test WHERE id = '1' AND name = 'Jimmy'";

        Object actual = this.queryBuilder.where("id", 1).andWhere("name", "Jimmy").delete();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_a_delete_statement_with_limit() throws Exception
    {
        String expected = "DELETE FROM test WHERE age < '21' LIMIT 1";

        Object actual = this.queryBuilder.where("age", "<", 21).limit(1).delete();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_use_raw_select() throws Exception
    {
        String expected = "SELECT count(*) as user_count FROM test";

        Object actual = this.queryBuilder.select(DB.raw("count(*) as user_count")).get();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_use_raw_select_and_add_selects() throws Exception
    {
        String expected = "SELECT *, count(*) as user_count FROM test";

        Object actual = this.queryBuilder
                .select()
                .addSelect(DB.raw("count(*) as user_count"))
                .get();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_generate_an_insert_statement() throws Exception
    {
        String expected = "INSERT INTO test (name) VALUES ('Jimmy')";
        Object actual = this.queryBuilder.insertInto("name").values("Jimmy");

        assertEquals(expected, actual);
    }
}