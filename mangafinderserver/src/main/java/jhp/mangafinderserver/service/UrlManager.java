package jhp.mangafinderserver.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UrlManager {

    public Map<String, String> parseQuery(final String url) throws Exception {
        Map<String, String> result = new HashMap<>();

        for (final String param : url.split("\\?")[1].split("&")) {
            final String[] pair = param.split("=");
            result.put(pair[0], pair[1]);
        }

        return result;
    }

}
