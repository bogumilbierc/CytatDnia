package pl.jcrusader.cytatdnia.service;

import pl.jcrusader.cytatdnia.dto.QuoteDto;

/**
 * Created by bogumil on 3/6/17.
 */

public interface QuoteExtractorService {

    /**
     * Extracts Quote from HTML of cytatdnia.pl page
     *
     * @param pageHtml HTML content of cytatdnia.pl page
     * @return extracted quote (quote text and author), or null if QuoteDto cannot be extracted
     */
    QuoteDto extractQuoteFromPageHtml(String pageHtml);
}
