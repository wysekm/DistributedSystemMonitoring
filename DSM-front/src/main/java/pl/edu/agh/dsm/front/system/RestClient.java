package pl.edu.agh.dsm.front.system;

import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sakushu on 2014-04-26.
 */

@Component
public class RestClient {
    Logger logger = Logger.getLogger("frontend.rest.client");

    private RestTemplate restTemplate = new RestTemplate();

    {
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    private boolean asIs = false;
    private String url = "";
    private HttpMethod httpMethod = HttpMethod.GET;

    public void asIs(boolean b) {
        this.asIs = b;
    }

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
        String nUrl = "";
        for (Map.Entry<String, String> e : parameters.entrySet()) {
            nUrl += "&" + e.getKey() + "={" + e.getKey() + "}";
        }
        nUrl = nUrl.replaceFirst("&", "?");
        nUrl = url + nUrl;
        ResponseEntity<HashMap> response = restTemplate.exchange(
                nUrl,
                httpMethod,
                new HttpEntity<HashMap>(mainTemplate, new HttpHeaders() {{
                    set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
                }}),
                HashMap.class,
                parameters
        );
        if (response.getBody().containsKey("_embedded") && !asIs) {
            callback.onCallback((HashMap) response.getBody().get("_embedded"));
        } else {
            callback.onCallback(response.getBody());
        }
    }

    public interface RestCallback {
        public void onCallback(HashMap data);
    }
}
