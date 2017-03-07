package pl.jcrusader.cytatdnia;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pl.jcrusader.cytatdnia.dto.QuoteDto;
import pl.jcrusader.cytatdnia.task.DownloadQuoteTask;

public class QuoteActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private TextView authorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        assignViewsToObjects();
        downloadAndDisplayQuote();
        showNewQuoteNotification();
    }

    private void assignViewsToObjects() {
        quoteTextView = (TextView) findViewById(R.id.quoteTextView);
        authorTextView = (TextView) findViewById(R.id.authorTextView);
    }

    private void downloadAndDisplayQuote() {
        new DownloadQuoteTask(this).execute();
    }

    public void updateQuoteViews(QuoteDto quoteDto) {
        if (quoteDto != null) {
            quoteTextView.setText(quoteDto.getQuote());
            authorTextView.setText(quoteDto.getAuthor());
        } else {
            showErrorAlert();
        }
    }

    private void showErrorAlert() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(getString(R.string.alertDialogTitle))
                .setMessage(R.string.alertDialogMessage)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
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
