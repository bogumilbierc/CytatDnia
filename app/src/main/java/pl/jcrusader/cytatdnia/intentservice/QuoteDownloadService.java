package pl.jcrusader.cytatdnia.intentservice;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import pl.jcrusader.cytatdnia.QuoteActivity;
import pl.jcrusader.cytatdnia.R;
import pl.jcrusader.cytatdnia.dto.QuoteDto;
import pl.jcrusader.cytatdnia.service.QuoteService;
import pl.jcrusader.cytatdnia.service.impl.QuoteServiceImpl;

/**
 * Created by bogumil on 3/7/17.
 */

public class QuoteDownloadService extends IntentService {

    private QuoteService quoteService;

    public QuoteDownloadService() {
        super(QuoteDownloadService.class.toString());
        this.quoteService = new QuoteServiceImpl();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        QuoteDto quoteDto = quoteService.downloadQuoteForToday();
        showNewQuoteNotification();
    }

    private void showNewQuoteNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.notificationTitle));


        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, QuoteActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(QuoteActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        notificationBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // mId allows you to update the notification later on.
        mNotificationManager.notify(0, notificationBuilder.build());
    }
}
