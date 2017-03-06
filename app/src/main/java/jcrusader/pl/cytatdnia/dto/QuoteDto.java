package jcrusader.pl.cytatdnia.dto;

/**
 * Created by bogumil on 3/6/17.
 * Data Transfer Object for Quote.
 */
public class QuoteDto {
    private String quote;
    private String author;

    public QuoteDto(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}
