package jcrusader.pl.cytatdnia.service;

import jcrusader.pl.cytatdnia.dto.QuoteDto;

/**
 * Created by bogumil on 3/6/17.
 */
public interface QuoteService {

    /**
     * Downloads Quote for Today.
     * @return quote dto
     */
    QuoteDto downloadQuoteForToday();

}
