package jhp.mangafinderserver.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UrlManagerTest {

    private UrlManager urlManager = new UrlManager();

    @Test
    void parseQueryTest() throws Exception {
        final String url = "https://gall.dcinside.com/board/view/?id=comic_new3&no=6550090&page=1";

        urlManager.parseQuery(url);
    }
}