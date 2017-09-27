package utils;

import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.ByteArrayOutputStream;

public class AllureReportUtils {

    @Attachment(value = "Request content")
    public static byte[] logRequest(ByteArrayOutputStream stream) {
        return attachByteArray(stream);
    }

    @Attachment(value = "Response content")
    public static byte[] logResponse(ByteArrayOutputStream stream) {
        return attachByteArray(stream);
    }

    private static byte[] attachByteArray(ByteArrayOutputStream log) {
        byte[] array = log.toByteArray();
        log.reset();
        return array;
    }

}
