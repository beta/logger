package net.kyouko.logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for logging.
 */
public class Logger {

    private Logger() {
    }

    private static Set<LogReceiver> receivers = new HashSet<>();

    /**
     * Add a new log receiver.
     *
     * @param receiver instance of {@link LogReceiver} for receiving logs
     */
    public static void addReceiver(LogReceiver receiver) {
        receivers.add(receiver);
    }

    private static StandardOutputLogReceiver standardOutputLogReceiver = new StandardOutputLogReceiver();

    /**
     * Enables printing logs into standard output.
     * <p>
     * The lowest level of logs is {@link Log.Level#INFO} by default. For a custom level, use
     * {@link #enableStandardOutput(Log.Level)} instead.
     */
    public static void enableStandardOutput() {
        enableStandardOutput(Log.Level.INFO);
    }

    /**
     * Enables printing logs into standard output, with the lowest level as {@code lowestLevel}.
     *
     * @param lowestLevel lowest level of logs to be printed into standard output
     */
    public static void enableStandardOutput(Log.Level lowestLevel) {
        disableStandardOutput();

        standardOutputLogReceiver.setLowestLevel(lowestLevel);
        receivers.add(standardOutputLogReceiver);
    }

    /**
     * Disables all logs from being printed into standard output.
     */
    public static void disableStandardOutput() {
        receivers.removeIf(receiver -> receiver instanceof StandardOutputLogReceiver);
    }

    /**
     * Sends a piece of log to the receivers.
     *
     * @param level   log level
     * @param client  log client, usually name of the class/module that sends the log
     * @param content log content
     */
    public static void log(Log.Level level, String client, String content) {
        Log log = new Log(level, client, content);
        receivers.stream()
                .filter(receiver -> level.notLowerThan(receiver.getLowestLevel()))
                .forEach(receiver -> receiver.receive(log));
    }

    /**
     * Sends a log with type as debug.
     *
     * @see #log(Log.Level, String, String)
     */
    public static void debug(String client, String content) {
        log(Log.Level.DEBUG, client, content);
    }

    /**
     * Shortcut for {@link #debug(String, String)}
     *
     * @see #debug(String, String)
     */
    public static void d(String client, String content) {
        debug(client, content);
    }

    /**
     * Sends a log with type as info.
     *
     * @see #log(Log.Level, String, String)
     */
    public static void info(String client, String content) {
        log(Log.Level.INFO, client, content);
    }

    /**
     * Shortcut for {@link #info(String, String)}
     *
     * @see #info(String, String)
     */
    public static void i(String client, String content) {
        info(client, content);
    }

    /**
     * Sends a log with type as warning.
     *
     * @see #log(Log.Level, String, String)
     */
    public static void warning(String client, String content) {
        log(Log.Level.WARNING, client, content);
    }

    /**
     * Shortcut for {@link #warning(String, String)}
     *
     * @see #warning(String, String)
     */
    public static void w(String client, String content) {
        warning(client, content);
    }

    /**
     * Sends a log with type as error.
     *
     * @see #log(Log.Level, String, String)
     */
    public static void error(String client, String content) {
        log(Log.Level.ERROR, client, content);
    }

    /**
     * Shortcut for {@link #error(String, String)}
     *
     * @see #error(String, String)
     */
    public static void e(String client, String content) {
        error(client, content);
    }

}
