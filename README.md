# Spring AOP – Day 1: JDK Dynamic Proxy

A small plain-Java (no Spring yet) exercise that demonstrates the idea behind AOP: adding cross-cutting behavior (logging) to a service **without modifying it**, using a JDK dynamic proxy.

## Task

Wrap a `NotificationService` with a proxy that logs a message before and after each call to `sendEmail` / `sendSms`.

## Project Structure

| File | Role |
|------|------|
| `NotificationService` | Interface with `sendEmail(to, message)` and `sendSms(to, message)` |
| `NotificationServiceImpl` | Real implementation (the *target*); prints the send action |
| `TestService` | Empty marker interface, shows a proxy can implement multiple interfaces |
| `LoggingHandler` | `InvocationHandler` (the *advice*): prints `Entering ...` / `Leaving ...` around the delegated call |
| `MainProxy` | Entry point: builds the proxy with `Proxy.newProxyInstance` and calls its methods |

## How It Works

1. `MainProxy` creates the real `NotificationServiceImpl`.
2. `Proxy.newProxyInstance(...)` creates a proxy implementing `NotificationService` and `TestService`, routing every call to `LoggingHandler`.
3. `LoggingHandler.invoke` prints "Entering", calls the real method via reflection (`method.invoke(delegate, args)`), then prints "Leaving".

## Expected Output

```
Entering sendEmail method
Sending email to Kareem Emad : Welcome to Proxy
Leaving sendEmail method

Entering sendSms method
Sending email to Kareem Emad : Welcome to Proxy
Leaving sendSms method
```

## Concepts Covered

- Proxy pattern and JDK dynamic proxies (`java.lang.reflect.Proxy`)
- `InvocationHandler` as the interception point (before/after advice)
- Reflection-based method invocation
- Cross-cutting concerns (logging) separated from business logic
- Foundation for Spring AOP, which uses JDK proxies (interface-based) or CGLIB (class-based)

## Notes / Known Issues

- `LoggingHandler` only handles `sendSms` and `sendEmail`; any other method (e.g. `toString`) returns `null` without being invoked.
- `NotificationServiceImpl.sendSms` prints "Sending email" instead of "Sending SMS".
- `MainProxy.main()` uses an instance-less `static void main()` (requires a recent JDK with that preview/feature enabled).
