import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static java.lang.Runtime.getRuntime;
import static java.util.Objects.isNull;

/**
 * 利用cmd转换,使用Java调用cmd完成操作
 *
 * @Author YangKun
 * @Date 2018/11/7
 */
public class FM {


    public static String FFMPEG_PATH = "D:\\Documents\\Downloads\\ffmpeg-20181105-beaa350-win64-static\\ffmpeg-20181105-beaa350-win64-static\\bin\\ffmpeg.exe";

    public static String INPUT_FILE = "C:\\Users\\Administrator\\Desktop\\item\\%d.jpg";

    public static String OUT_MP4_PATH = "D:\\referrnce\\video\\%s.mp4";

    public static String WIDTH = "1000";

    public static String HEIGHT = "1000";

    public static String HC_LOGO = "D:\\referrnce\\images\\logo\\logo.jpg";


    /**
     * 转换的方法
     *
     * @Author YangKun
     * @Date 2018/11/7
     */
    public static void main(String[] args) throws IOException {
        File file = new File(OUT_MP4_PATH);
        OUT_MP4_PATH = String.format(OUT_MP4_PATH, new DateTime().toString("yyyy年MM月dd日hh时mm分ss秒"));
//        -i iQIYI_logo.png -filter_complex overlay
        String[] params = new String[]{
                FFMPEG_PATH,
                "-r", "1",
                "-threads", "2",
                "-i", INPUT_FILE, /*"-i", "D:\\referrnce\\images\\items\\1.mp3",*/
                "-vf", "\"scale=" + WIDTH + ":" + HEIGHT + "\",\"drawtext=fontfile=C:\\Windows\\Fonts\\Consolas.ttf:text='<慧聪网>':x=0:y=0:fontsize=100:fontcolor=red:shadowy=2\"",
                "-vcodec", "libx264",
                OUT_MP4_PATH};

        ProcessBuilder pb = new ProcessBuilder(params);
        String result = "";
        Process process = pb.start();
        BufferedReader errStreamReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line = errStreamReader.readLine();
        for (; !isNull(line); line = errStreamReader.readLine()) {
            System.err.println(line);
        }
        System.out.println("转换完成!生成的视频路径-> " + OUT_MP4_PATH);
        process.destroy();

    }
}
