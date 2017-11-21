package net.kyouko.logger;

/**
 * A log receiver that prints received logs into standard output.
 */
class StandardOutputLogReceiver implements LogReceiver {

    private Log.Level lowestLevel = Log.Level.INFO;

    StandardOutputLogReceiver() {
    }

    StandardOutputLogReceiver(Log.Level lowestLevel) {
        this.lowestLevel = lowestLevel;
    }

    void setLowestLevel(Log.Level lowestLevel) {
        this.lowestLevel = lowestLevel;
    }

    @Override
    public Log.Level getLowestLevel() {
        return lowestLevel;
    }

    @Override
    public void receive(Log log) {
        String output = String.format("[%s] %-7s [%s]: %s", log.getTimeString(), log.getLevel().toString(),
                log.getClient(), log.getContent());
        if (log.getLevel().is(Log.Level.ERROR)) {
            System.err.println(output);
        } else {
            System.out.println(output);
        }
    }

}
