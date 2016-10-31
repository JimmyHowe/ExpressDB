import com.jimmyhowe.db.queries.QueryBuilderTest;
import com.jimmyhowe.db.queries.components.OrderByTest;
import com.jimmyhowe.db.queries.components.WhereGroupTest;
import com.jimmyhowe.db.queries.components.WhereTest;
import com.jimmyhowe.db.tables.columns.ColumnTest;
import com.jimmyhowe.db.tables.columns.ColumnsTest;
import com.jimmyhowe.db.tables.rows.RowsTest;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Unit Tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        QueryBuilderTest.class,
        ColumnTest.class,
        ColumnsTest.class,
        RowsTest.class,
        WhereTest.class,
        WhereGroupTest.class,
        OrderByTest.class
})

public class UnitTestSuite extends TestSuite
{

}
