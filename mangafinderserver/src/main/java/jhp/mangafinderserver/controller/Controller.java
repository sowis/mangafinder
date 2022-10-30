package jhp.mangafinderserver.controller;

import jhp.mangafinderserver.service.HttpRequest;
import jhp.mangafinderserver.service.UrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    private UrlManager urlManager;
    private HttpRequest httpRequest;

    @Autowired
    public Controller(UrlManager urlManager, HttpRequest httpRequest) {
        this.urlManager = urlManager;
        this.httpRequest = httpRequest;
    }

    @GetMapping("/")
    public byte[] getImage(@RequestParam final String srcUrl, @RequestParam final String pageUrl) throws Exception {
        final Map<String, String> params = urlManager.parseQuery(pageUrl);
        final String mobile_url = "https://m.dcinside.com/board/" + params.get("id") + "/" + params.get("no");

        final byte[] byteImage = httpRequest.requestImage(srcUrl, mobile_url);

        return byteImage;
    }

}
