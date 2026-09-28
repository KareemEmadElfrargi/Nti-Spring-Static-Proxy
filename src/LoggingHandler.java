import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingHandler implements InvocationHandler {
    private final Object delegate;


    public LoggingHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        switch (method.getName()) {
            case "sendSms", "sendEmail":
                System.out.printf("Entering %s method%n", method.getName());
                result = method.invoke(delegate, args);
                System.out.printf("Leaving %s method%n%n", method.getName());
                break;
        }

        return result;
    }
}
