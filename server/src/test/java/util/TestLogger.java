package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class TestLogger implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        log.info("Test {} finished", extensionContext.getDisplayName());
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        log.info("Test {} started", extensionContext.getDisplayName());
    }
}
