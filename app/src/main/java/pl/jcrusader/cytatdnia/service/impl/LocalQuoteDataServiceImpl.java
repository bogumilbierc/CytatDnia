package pl.jcrusader.cytatdnia.service.impl;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

import pl.jcrusader.cytatdnia.R;
import pl.jcrusader.cytatdnia.dto.QuoteDto;
import pl.jcrusader.cytatdnia.service.LocalQuoteDataService;

/**
 * Created by bogumil on 3/7/17.
 */

public class LocalQuoteDataServiceImpl implements LocalQuoteDataService {

    private static final String AUTHOR_KEY = "AUTHOR";
    private static final String QUOTE_KEY = "QUOTE";
    private static final String DATE_KEY = "DATE";

    private Context context;

    public LocalQuoteDataServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public QuoteDto getLocallyStoredQuoteForToday() {
        if (wasQuoteDownloadedToday()) {
            SharedPreferences sharedPreferences = getPreferences();
            String author = sharedPreferences.getString(AUTHOR_KEY, "");
            String quote = sharedPreferences.getString(QUOTE_KEY, "");
            return new QuoteDto(author, quote);

        }
        return null;
    }

    @Override
    public Boolean wasQuoteDownloadedToday() {
        SharedPreferences sharedPreferences = getPreferences();
        String storedDate = sharedPreferences.getString(DATE_KEY, "");

        return storedDate.equals(getTodaysDateAsString());
    }

    @Override
    public void updateLocallyStoredQuote(QuoteDto quoteDto) {
        SharedPreferences sharedPreferences = getPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AUTHOR_KEY, quoteDto.getAuthor());
        editor.putString(QUOTE_KEY, quoteDto.getQuote());
        editor.putString(DATE_KEY, getTodaysDateAsString());
        editor.commit();
    }

    private String getTodaysDateAsString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

    private SharedPreferences getPreferences() {
        return context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }
}
