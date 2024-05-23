package tobyspring.myboot;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

class HelloControllerTest {

    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(new HelloService() {
            @Override
            public String sayHello(String name) {
                return name;
            }
        });

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                helloController.hello("");
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }
}