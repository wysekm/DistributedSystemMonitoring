package pl.edu.agh.dsm.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sakushu on 2014-04-26.
 */

@Controller
@RequestMapping("/example/")
public class ExampleController {
    @RequestMapping(value = "graph", method = RequestMethod.GET)
    @ResponseBody
    public String graphData() {
        return "{\n" +
                "  \"_embedded\" : {\n" +
                "    \"measurements\" : [ {\n" +
                "      \"resource\" : \"zeus_1F\",\n" +
                "      \"metric\" : \"cpuUsage\",\n" +
                "      \"unit\": \"%\",\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"http://monitor1/measurements/c40fac9f-8c96-4476-b435-aa52e4b17e08\"\n" +
                "        },\n" +
                "        \"data\" : {\n" +
                "          \"href\" : \"http://127.0.0.1:8082/DSM-front/example/graph/usage/\"\n" +
                "        }\n" +
                "      }\n" +
                "    }, {\n" +
                "      \"resource\" : \"atena_2F\",\n" +
                "      \"metric\" : \"memUsage\",\n" +
                "      \"unit\": \"%\",\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"http://monitor1/measurements/bffb94a2-2152-4559-992e-f9348da19619\"\n" +
                "        },\n" +
                "        \"data\" : {\n" +
                "          \"href\" : \"http://127.0.0.1:8082/DSM-front/example/graph/usage/\"\n" +
                "        }\n" +
                "      }\n" +
                "    } ]\n" +
                "  }\n" +
                "}\n";
    }

    @RequestMapping(value = "graph/usage/", method = RequestMethod.GET)
    @ResponseBody
    public String graphUsageData() {
        return "{\n" +
                "  \"_embedded\": {\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"timestamp\": 1398071005,\n" +
                "        \"data\": 30\n" +
                "      },\n" +
                "      {\n" +
                "        \"timestamp\": 1398072005,\n" +
                "        \"data\": 76\n" +
                "      },\n" +
                "      {\n" +
                "        \"timestamp\": 1398075305,\n" +
                "        \"data\": 34\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    @RequestMapping(value = {"monitors/", "RST_KT_MSL/"}, method = RequestMethod.GET)
    @ResponseBody
    public String getMonitors() {
        return "{\n" +
                "  \"_embedded\" : {\n" +
                "    \"measurements\" : [ {\n" +
                "      \"resource\" : \"atena_1A\",\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"http://127.0.0.1:8082/DSM-front/example/resources/\"\n" +
                "        },\n" +
                "        \"details\" : {\n" +
                "          \"href\" : \"http://127.0.0.1:8082/DSM-front/example/resources/\"\n" +
                "        }\n" +
                "      }\n" +
                "    }, {\n" +
                "      \"resource\" : \"zeus_2B\",\n" +
                "      \"metric\" : \"memUsage\",\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"http://127.0.0.1:8082//DSM-front/example/resources/\"\n" +
                "        },\n" +
                "        \"details\" : {\n" +
                "          \"href\" : \"http://127.0.0.1:8082//DSM-front/example/resources/\"\n" +
                "        }\n" +
                "      }\n" +
                "    }, {\n" +
                "      \"resource\" : \"atena_1C\",\n" +
                "      \"metric\" : \"cpuUsage\",\n" +
                "      \"_links\" : {\n" +
                "        \"self\" : {\n" +
                "          \"href\" : \"http://127.0.0.1:8082//DSM-front/example/resources/\"\n" +
                "        },\n" +
                "        \"details\" : {\n" +
                "          \"href\" : \"http://127.0.0.1:8082/DSM-front/example/resources/\"\n" +
                "        }\n" +
                "      }\n" +
                "    } ]\n" +
                "  }\n" +
                "}";
    }

    @RequestMapping(value = "resources/", method = RequestMethod.GET)
    @ResponseBody
    public String getResources() {
        return "{\n" +
                "  \"resource\": \"zeus_1F\",\n" +
                "  \"metric\": \"cpuUsage\",\n" +
                "  \"unit\": \"%\",\n" +
                "  \"_links\": {\n" +
                "    \"self\": {\n" +
                "      \"href\": \"http://127.0.0.1:8082/DSM-front/example/resources/\"\n" +
                "    },\n" +
                "    \"data\": {\n" +
                "      \"href\": \"http://127.0.0.1:8082/DSM-front/example/graph/usage/\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    @RequestMapping(value = "rst_kt_rsl", method = RequestMethod.GET)
    @ResponseBody
    public String get_RST_KT_RSL() {
        return "{\n" +
                "  \"_embedded\": {\n" +
                "    \"resources\": [\n" +
                "      {\n" +
                "        \"name\": \"atena_1A\",\n" +
                "        \"_links\": {\n" +
                "          \"measurements\": {\n" +
                "            \"href\": \"http://127.0.0.1:8082/DSM-front/example/RST_KT_MSL/?resource=atena_1A\"\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"zeus_2F\",\n" +
                "        \"_links\": {\n" +
                "          \"measurements\": {\n" +
                "            \"href\": \"http://127.0.0.1:8082/DSM-front/example/RST_KT_MSL/?resource=zeus_2F\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }
}
