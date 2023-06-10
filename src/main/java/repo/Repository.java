package repo;

import model.User;
import model.chat.Chat;
import view.general.GeneralView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Repository {

    public static User currentUser;

    public static Chat singleChat;

    public static Map map = new HashMap();

    public static GeneralView generalView;

    public static byte[] convertImageToByte(File file) {
        if (Objects.isNull(file))
            return null;

        byte[] bFile = new byte[(int) file.length()];

        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
            return bFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage convertByteToImage(byte[] bytes){
        if (Objects.isNull(bytes))
            return null;

        try {
            InputStream is = new ByteArrayInputStream(bytes);
            BufferedImage newBi = ImageIO.read(is);
            return newBi;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
