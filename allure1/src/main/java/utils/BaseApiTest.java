package utils;

import org.junit.Rule;

public abstract class BaseApiTest {

    @Rule
    public RestAssuredEventsWatcher restAssuredEventsWatcher = new RestAssuredEventsWatcher()
            .onFailedTest(true)
            .onSucceededTest(true);

}
