import org.jim2mov.core.MovieSaveException;

import java.io.File;
import java.net.URL;

public class FilesToMovTest {
    // 主函数
    public static void main(String[] args) throws MovieSaveException {
        //读取图片文件
        URL url = FilesToMov.class.getClassLoader().getResource("images");
        File file = new File(url.getFile());

        //视频文件保存目录 请使用绝对路径
        String path = "images.mov";
        // 图片宽度
        int width = 1000;
        // 图片高度
        int height = 1000;
        new FilesToMov(file, path, width, height);
        System.out.println("生成完成");
    }

}