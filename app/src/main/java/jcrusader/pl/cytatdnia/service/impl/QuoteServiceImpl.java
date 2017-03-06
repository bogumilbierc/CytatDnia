package jcrusader.pl.cytatdnia.service.impl;

import jcrusader.pl.cytatdnia.dto.QuoteDto;
import jcrusader.pl.cytatdnia.service.HttpDownloadService;
import jcrusader.pl.cytatdnia.service.QuoteExtractorService;
import jcrusader.pl.cytatdnia.service.QuoteService;

/**
 * Created by bogumil on 3/6/17.
 */

public class QuoteServiceImpl implements QuoteService {

    private final static String QUOTE_PAGE_URL = "http://www.cytatdnia.pl/";
    private HttpDownloadService httpDownloadService;
    private QuoteExtractorService quoteExtractorService;

    public QuoteServiceImpl() {
        this.httpDownloadService = new HttpDownloadServiceImpl();
        this.quoteExtractorService = new QuoteExtractorServiceImpl();
    }

    @Override
    public QuoteDto downloadQuoteForToday() {
        String quotePageHtml = httpDownloadService.getUrl(QUOTE_PAGE_URL);
        QuoteDto quoteDto = quoteExtractorService.extractQuoteFromPageHtml(quotePageHtml);
        return quoteDto;
    }
}
