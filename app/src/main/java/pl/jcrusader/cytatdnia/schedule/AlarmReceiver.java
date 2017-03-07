package pl.jcrusader.cytatdnia.schedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import pl.jcrusader.cytatdnia.intentservice.QuoteDownloadService;

/**
 * Created by bogumil on 3/7/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast received", Toast.LENGTH_SHORT).show();
        startQuoteDownloadBackgroundService(context);
    }

    private void startQuoteDownloadBackgroundService(Context context) {
        Intent quoteDownloadServiceIntent = new Intent(context, QuoteDownloadService.class);
        context.startService(quoteDownloadServiceIntent);
    }

}
