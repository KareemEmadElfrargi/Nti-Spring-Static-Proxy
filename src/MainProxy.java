import java.lang.reflect.Proxy;

public class MainProxy {
    static void main() {
        NotificationService notificationService = new NotificationServiceImpl();

        NotificationService proxy = (NotificationService)Proxy.newProxyInstance(
                NotificationService.class.getClassLoader(),
                new Class[]{NotificationService.class,TestService.class},
                new LoggingHandler(notificationService)
        );

        proxy.sendEmail("Kareem Emad","Welcome to Proxy");
        proxy.sendSms("Kareem Emad","Welcome to Proxy");
    }
}
