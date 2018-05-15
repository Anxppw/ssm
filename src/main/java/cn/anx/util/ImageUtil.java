package cn.anx.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    private static Random r = new Random();
    private static String basePath = PathUtil.getImgBasePath();

    /*
    * deal with thumbnail,return absolute path of new picture
    *
    * @param thumbnail
    * @param target address
    * @return
    * */
    public static String generateThumbnail(InputStream thumbnailInuptStream,String fileName, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnailInuptStream).size(600, 326).watermark(Positions.BOTTOM_RIGHT,
                    ImageIO.read(new File(basePath + "@ANX.png")), 0.25f).outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }

    //create directory if null
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String getRandomFileName() {
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = DATE_FORMAT.format(new Date());
        return nowTimeStr + rannum;
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File(basePath + "miku.jpg"))
                .size(600, 326).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath + "@ANX.png")), 0.25f).outputQuality(0.8f)
                .toFile(basePath + "new_miku.jpg");
    }
}