import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Timeout(value = 22)
    public void mainTestTimeout() throws Exception {
        Main.main();
    }
}
