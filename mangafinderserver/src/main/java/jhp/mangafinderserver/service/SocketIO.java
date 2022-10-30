package jhp.mangafinderserver.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
public class SocketIO {

    private final int buffer_size = 1 * 1024 * 1024;
    private byte[] buffer = new byte[buffer_size];

    public byte[] inputSteamToByteArray(InputStream is) throws Exception {
        int length;
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

        while ((length = is.read(buffer, 0, buffer.length)) != -1) {
            byteOutputStream.write(buffer, 0, length);
        }

        return byteOutputStream.toByteArray();
    }

}
