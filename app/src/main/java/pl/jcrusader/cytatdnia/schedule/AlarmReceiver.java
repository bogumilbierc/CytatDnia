package pl.jcrusader.cytatdnia.schedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import pl.jcrusader.cytatdnia.intentservice.QuoteDownloadService;
import pl.jcrusader.cytatdnia.service.LocalQuoteDataService;
import pl.jcrusader.cytatdnia.service.impl.LocalQuoteDataServiceImpl;

/**
 * Created by bogumil on 3/7/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LocalQuoteDataService localQuoteDataService = new LocalQuoteDataServiceImpl(context);
        if (!localQuoteDataService.wasQuoteDownloadedToday()) {
            startQuoteDownloadBackgroundService(context);
        }
    }

    private void startQuoteDownloadBackgroundService(Context context) {
        Intent quoteDownloadServiceIntent = new Intent(context, QuoteDownloadService.class);
        context.startService(quoteDownloadServiceIntent);
    }

}
