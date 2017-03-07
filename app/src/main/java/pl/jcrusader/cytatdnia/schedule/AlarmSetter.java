package pl.jcrusader.cytatdnia.schedule;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by bogumil on 3/7/17.
 */

public class AlarmSetter {

    private final static Integer INTERVAL_IN_HOURS = 4;
    private final static Integer MILISECONDS_IN_THIRTY_SECONDS = 30 * 1000;

    /**
     * Starts (schedules) new alarm. Alarm will be broadcasted to {@link AlarmReceiver} class.
     * It is set to {@link AlarmManager#ELAPSED_REALTIME} type with first invocation 30 seconds after.
     * Next invocations will have 4 hours interval.
     *
     * @param context context of intent, that starts the alarm
     */
    public static void startAlarm(Context context) {
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int interval = getMiliseconds(INTERVAL_IN_HOURS);
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + MILISECONDS_IN_THIRTY_SECONDS, interval, pendingIntent);
    }

    /**
     * Gets number of miliseconds in given amount of hours.
     *
     * @param hours number of hours
     * @return number of miliseconds in given amount of hours
     */
    private static int getMiliseconds(int hours) {
        return 1000 * 60 * 60 * hours;
    }
}
