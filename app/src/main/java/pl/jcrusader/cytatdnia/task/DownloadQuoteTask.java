package pl.jcrusader.cytatdnia.task;

import android.os.AsyncTask;

import pl.jcrusader.cytatdnia.QuoteActivity;
import pl.jcrusader.cytatdnia.dto.QuoteDto;
import pl.jcrusader.cytatdnia.service.QuoteService;
import pl.jcrusader.cytatdnia.service.impl.QuoteServiceImpl;

/**
 * Created by bogumil on 3/6/17.
 */

public class DownloadQuoteTask extends AsyncTask<Void, Void, QuoteDto> {

    private QuoteService quoteService;
    private QuoteActivity invokingActivity;

    public DownloadQuoteTask(QuoteActivity invokingActivity) {
        this.invokingActivity = invokingActivity;
        this.quoteService = new QuoteServiceImpl();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected QuoteDto doInBackground(Void... params) {
        return quoteService.downloadQuoteForToday();
    }

    @Override
    protected void onPostExecute(QuoteDto quoteDto) {
        super.onPostExecute(quoteDto);
        invokingActivity.updateQuoteViews(quoteDto);

    }
}
