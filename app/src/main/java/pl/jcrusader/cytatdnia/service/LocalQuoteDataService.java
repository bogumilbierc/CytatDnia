package pl.jcrusader.cytatdnia.service;

import pl.jcrusader.cytatdnia.dto.QuoteDto;

/**
 * Created by bogumil on 3/7/17.
 */

public interface LocalQuoteDataService {

    /**
     * Gets locally stored quote for today (and only today). If quote is not update (if it belongs to other day), then null is returned.
     *
     * @return quote for today or null if not quote is available
     */
    QuoteDto getLocallyStoredQuoteForToday();


    /**
     * Indicates whether quote was downloaded for today.
     *
     * @return true if quote was downloaded for today, false otherwise
     */
    Boolean wasQuoteDownloadedToday();

    /**
     * Updates locally stored quote. It automatically updates date of last stored quote with todays date.
     *
     * @param quoteDto Quote to store
     */
    void updateLocallyStoredQuote(QuoteDto quoteDto);
}
