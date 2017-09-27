package utils;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RestAssuredEventsWatcher extends TestWatcher {

    private ByteArrayOutputStream request = new ByteArrayOutputStream();
    private ByteArrayOutputStream response = new ByteArrayOutputStream();

    private PrintStream requestVar = new PrintStream(request, true);
    private PrintStream responseVar = new PrintStream(response, true);

    private boolean onFailedTest = false;
    private boolean onSucceededTest = false;

    public RestAssuredEventsWatcher onFailedTest(boolean onFailedTest) {
        this.onFailedTest = onFailedTest;
        return this;
    }

    public RestAssuredEventsWatcher onSucceededTest(boolean onSucceededTest) {
        this.onSucceededTest = onSucceededTest;
        return this;
    }

    @Override
    protected void succeeded(Description description) {
        if (onSucceededTest) {
            AllureReportUtils.logRequest(request);
            AllureReportUtils.logResponse(response);
        }
    }

    @Override
    protected void starting(Description description) {
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL, responseVar),
                new RequestLoggingFilter(LogDetail.ALL, requestVar));
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (onFailedTest) {
            this.succeeded(description);
        }
    }

}
