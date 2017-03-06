package jcrusader.pl.cytatdnia.service;

/**
 * Created by bogumil on 3/6/17.
 */

public interface HttpDownloadService {
    /**
     * Gets content from Given url. Content is returned as String and it contains HTML of given URL.
     *
     * @param url address from which content will be download
     * @return String with HTML content of given address, or null if HTML content cannot be downloaded
     */
    String getUrl(String url);
}
