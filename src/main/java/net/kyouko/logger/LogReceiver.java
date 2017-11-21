package net.kyouko.logger;

/**
 * Interface for log receivers.
 */
public interface LogReceiver {
    /**
     * Returns the lowest level of logs this receiver receives.
     *
     * @return lowest level of logs this receiver receives
     */
    Log.Level getLowestLevel();

    /**
     * Receives a log.
     *
     * @param log log to receive, with level higher than {@link #getLowestLevel()}
     */
    void receive(Log log);
}
