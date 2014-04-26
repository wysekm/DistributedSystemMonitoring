package pl.edu.agh.dsm.front.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sakushu on 2014-04-26.
 */

@Component
public class RestClient {
    @Autowired
    RestKeys restKeys;
    Logger logger = Logger.getLogger("frontend.rest.client");

    private RestTemplate restTemplate = new RestTemplate();

    {
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public interface RestCallback {
        public void onCallback(HashMap data);
    }


    private String url = "";
    private HttpMethod httpMethod = HttpMethod.GET;

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void send(HashMap mainTemplate, RestCallback callback) {
        send(mainTemplate, callback, new HashMap());
    }

    public void send(HashMap mainTemplate, RestCallback callback, HashMap<String, String> parameters) {
        logger.log(Level.INFO, getUrl());
        ResponseEntity<HashMap> response = restTemplate.exchange(
                url,
                httpMethod,
                new HttpEntity<HashMap>(mainTemplate, new HttpHeaders() {{
                    set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
                }}),
                HashMap.class,
                parameters
        );


        if (response.getBody().containsKey(restKeys.get_embedded())) {
            callback.onCallback((HashMap) response.getBody().get(restKeys.get_embedded()));
        }else {
            callback.onCallback(response.getBody());
        }
    }
}
