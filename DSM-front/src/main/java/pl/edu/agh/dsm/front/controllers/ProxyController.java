package pl.edu.agh.dsm.front.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.agh.dsm.front.system.RestClient;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by Sakushu on 2014-05-05.
 */

@Controller
@RequestMapping("/proxy/")
public class ProxyController {
    ObjectMapper objectMapper = new ObjectMapper();

    Logger logger = Logger.getLogger("frontend.proxy");

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap getData(@RequestParam("url") String name, @RequestParam("method") String method) {
        RestClient restClient = new RestClient();
        restClient.setUrl(name);
        restClient.setHttpMethod(HttpMethod.valueOf(method));
        restClient.asIs(true);
        final HashMap b = new HashMap();
        restClient.send(null, new RestClient.RestCallback() {
            @Override
            public void onCallback(HashMap data) {
                b.putAll(data);
            }
        });
		return b;
        //System.out.println(b);
/*       try {
            return objectMapper.writeValueAsString(b);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";*/
    }
}
