package jhp.mangafinderserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Service
public class HttpRequest {

    private SocketIO socketIO;
    private String lastContentType;

    @Autowired
    public HttpRequest(SocketIO socketIO) {
        this.socketIO = socketIO;
    }

    public byte[] requestImage(final String urlString, final String referer) throws Exception {
        URL url = new URL(urlString);

        URLConnection con = url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36");
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        con.setRequestProperty("Referer", referer);

        InputStream inputStream = con.getInputStream();
        lastContentType = con.getContentType();

        byte[] result =  socketIO.inputSteamToByteArray(inputStream);
        inputStream.close();

        return result;
    }

    public String getLastContentType() {
        return lastContentType;
    }
}
