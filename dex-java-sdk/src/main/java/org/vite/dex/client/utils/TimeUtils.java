package org.vite.dex.client.utils;

public class TimeUtils {
    private static final long SECOND = 1;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;

    public static long getSeconds(long seconds) {
        return SECOND;
    }

    public static long getMinutes(long seconds) {
        return seconds / MINUTE;
    }

    public static long getSecondsOfMinutes(long minutes) {
        return minutes * MINUTE;
    }

    public static long getMinute30(long seconds) {
        return seconds / (30 * MINUTE);
    }

    public static long getSecondsOfMinutes30(long minutes) {
        return minutes * (30 * MINUTE);
    }

    public static long getHours6(long seconds) {
        return seconds / (6 * HOUR);
    }

    public static long getSecondsOfHours6(long hours) {
        return hours * (6 * HOUR);
    }

    public static long getHours12(long seconds) {
        return seconds / (12 * HOUR);
    }

    public static long getSecondsOfHours12(long hours) {
        return hours * (12 * HOUR);
    }

    public static long getHours(long seconds) {
        return seconds / (HOUR);
    }

    public static long getSecondsOfHours(long hours) {
        return hours * HOUR;
    }

    public static long getDays(long seconds) {
        return seconds / (DAY);
    }

    public static long getSecondsOfDays(long days) {
        return days * DAY;
    }

    public static long getDays7(long seconds) {
        return seconds / (7 * DAY);
    }

    public static long getSecondsOfDays7(long days) {
        return days * (7 * DAY);
    }

}
