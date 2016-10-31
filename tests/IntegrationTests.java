import com.jimmyhowe.db.DBTest;
import com.jimmyhowe.db.migrations.MigratorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Integration Tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DBTest.class,
        MigratorTest.class,
})

public class IntegrationTests
{
}
