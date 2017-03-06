package jcrusader.pl.cytatdnia.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jcrusader.pl.cytatdnia.dto.QuoteDto;
import jcrusader.pl.cytatdnia.service.QuoteExtractorService;

/**
 * Created by bogumil on 3/6/17.
 */

public class QuoteExtractorServiceImpl implements QuoteExtractorService {

    @Override
    public QuoteDto extractQuoteFromPageHtml(String pageHtml) {
        if (pageHtml == null) {
            return null;
        }

        Element box1 = getElementWithBox1Id(pageHtml);
        if (box1 == null) {
            return null;
        }
        String quote = getQuote(box1);
        String author = getAuthor(box1);
        return new QuoteDto(quote, author);
    }

    /**
     * Gets Element which has id "box-1"
     *
     * @return element extracted from HTML or null if element cannot be extracted
     */
    private Element getElementWithBox1Id(String pageHtml) {
        Document document = Jsoup.parse(pageHtml);
        Element contentDiv = document.getElementById("content");
        if (contentDiv == null) {
            return null;
        }
        Element box_1 = contentDiv.getElementById("box-1");
        if (box_1 == null) {
            return null;
        }
        return box_1;
    }

    private String getQuote(Element box1) {
        Elements quoteElements = box1.getElementsByClass("lvl-1");
        if (quoteElements == null || quoteElements.isEmpty()) {
            return null;
        }
        Element quoteElement = quoteElements.first();
        if (quoteElement != null) {
            return quoteElement.text();
        }
        return null;
    }

    private String getAuthor(Element box1) {
        Elements authorElements = box1.getElementsByClass("lvl-2");
        if (authorElements == null || authorElements.isEmpty()) {
            return null;
        }
        Element authorElement = authorElements.first();
        if (authorElement != null) {
            return authorElement.text();
        }
        return null;
    }
}
