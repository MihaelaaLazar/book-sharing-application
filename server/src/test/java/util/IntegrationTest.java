package util;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

/**
 * Target - defines custom annotation that are an amalgamation of several other annotations combines into one.
 * Retention - the annotations are available at runtime and can be accessed in our program during runtime.
 * Inherited - the annotation is inherited by subclasses.
 * ActiveProfiles - is used to activate profiles while loading ApplicationContext in Spring integration tests.
 * */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(TestLogger.class)
public @interface IntegrationTest {
}
