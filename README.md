# Logger

A utility library for writing logs conveniently.

## Installation

Logger is published to [JitPack]. For Gradle users, add the JitPack repository to your *root* build.gradle:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then add the dependency to the *module* build.gradle:

```gradle
dependencies {
    compile 'com.github.beta:logger:v0.1'
}
```

## Usage

```java
Logger.log(Log.Level.WARNING, "Some Client", "This is a warning log.");
```

Four log levels are available which are `DEBUG`, `INFO`, `WARNING` and `ERROR`. You can use a list of shortcuts for these levels:

```java
Logger.debug("Some Client", "Debug");
Logger.d("Some Client", "Debug");
Logger.info("Some Client", "Info");
Logger.i("Some Client", "Info");
Logger.warning("Some Client", "Warning");
Logger.w("Some Client", "Warning");
Logger.error("Some Client", "Error");
Logger.e("Some Client", "Error");
```

By default there is no log receiver, so your logs will be sent to nowhere. To see your logs in the standard output, use `Logger.enableStandardOutput()`. You can specify a lowest level for filtering logs. Example:

```java
Logger.enableStandardOutput(Log.Level.WARNING); // only WARNING and ERROR logs will be shown
```

Disable the standard output with `Logger.disableStandardOutput()`.

You can also make your own log receiver by implementing the `LogReceiver` interface. E.g. a receiver that only receives ERROR logs:

```java
Logger.addReceiver(new LogReceiver() {
    public Log.Level getLowestLevel() {
        return Log.Level.ERROR;
    }

    public void receive(Log log) {
        // do something to the log
    }
});
```

## License

MIT.

[JitPack]: https://jitpack.io/
