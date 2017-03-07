package pl.jcrusader.cytatdnia.service.impl;


import java.io.IOException;

import pl.jcrusader.cytatdnia.service.HttpDownloadService;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bogumil on 3/6/17.
 */

public class HttpDownloadServiceImpl implements HttpDownloadService {

    OkHttpClient client = new OkHttpClient();

    @Override
    public String getUrl(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Call call = client.newCall(request);
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            return null;
        }
    }
}
