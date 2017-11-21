package net.kyouko.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Information related to a piece of log.
 */
public class Log {

    /**
     * Level for logs.
     * <p>
     * Available levels are debug, info, warning and error.
     */
    public static class Level {

        private final static int VALUE_DEBUG = 0;
        private final static int VALUE_INFO = 1;
        private final static int VALUE_WARNING = 2;
        private final static int VALUE_ERROR = 3;

        public final static Level DEBUG = new Level(VALUE_DEBUG);
        public final static Level INFO = new Level(VALUE_INFO);
        public final static Level WARNING = new Level(VALUE_WARNING);
        public final static Level ERROR = new Level(VALUE_ERROR);

        private int value;

        /**
         * Private constructor for preventing new levels from being created.
         *
         * @param value integer value of the level
         */
        private Level(int value) {
            assert value >= VALUE_DEBUG && value <= VALUE_ERROR;
            this.value = value;
        }

        public String toString() {
            switch (value) {
                case VALUE_DEBUG:
                    return "DEBUG";
                case VALUE_INFO:
                    return "INFO";
                case VALUE_WARNING:
                    return "WARNING";
                case VALUE_ERROR:
                    return "ERROR";
                default:
                    assert false;
                    return "";
            }
        }

        public boolean higherThan(Level level) {
            return value > level.value;
        }

        public boolean lowerThan(Level level) {
            return value < level.value;
        }

        public boolean notHigherThan(Level level) {
            return value <= level.value;
        }

        public boolean notLowerThan(Level level) {
            return value >= level.value;
        }

        public boolean is(Level level) {
            return value == level.value;
        }

        public boolean equals(Level level) {
            return value == level.value;
        }

        @Override
        public boolean equals(Object object) {
            return object instanceof Level && equals((Level) object);
        }

    }

    private Level level;
    private String client;
    private String content;
    private Date time;

    /**
     * Returns a new {@code Log} instance with the given params.
     *
     * @param level   level of the log
     * @param client  client of the log
     * @param content content of the log
     */
    public Log(Level level, String client, String content) {
        this.level = level;
        this.client = client;
        this.content = content;
        this.time = new Date();
    }

    /**
     * Returns a new {@code Log} instance with the given params.
     * <p>
     * Type of the log is set to {@link Level#INFO} by default.
     *
     * @see #Log(Level, String, String)
     */
    public Log(String client, String content) {
        this(Level.INFO, client, content);
    }

    public Level getLevel() {
        return level;
    }

    public String getClient() {
        return client;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    /**
     * Returns a string representation of the log time.
     * <p>
     * Format of the time string will be "yyyy-MM-dd HH:mm:ss". For custom formats, use {@link #getTimeString(String)}
     * or {@link #getTimeString(DateFormat)} instead.
     *
     * @return string representation of the log time
     */
    public String getTimeString() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(time);
    }

    /**
     * Returns a string representation of the log time, with the provided format.
     * <p>
     * Parameter {@code dateFormat} should be a pattern string acceptable by
     * {@link SimpleDateFormat#SimpleDateFormat(String)}, e.g. "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateFormat pattern string of the date format
     * @return string representation of the log time
     */
    public String getTimeString(String dateFormat) {
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(time);
    }

    /**
     * Returns a string representation of the log time, generated with the provided formatter.
     *
     * @param formatter a {@link DateFormat} instance used for generating the time string
     * @return string representation of the log time
     */
    public String getTimeString(DateFormat formatter) {
        return formatter.format(time);
    }

}
