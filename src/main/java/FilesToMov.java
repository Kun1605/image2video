import org.jim2mov.core.*;
import org.jim2mov.utils.MovieUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * @Author YangKun
 * @Date 2018/11/7
 */

public class FilesToMov implements ImageProvider, FrameSavedListener {
    // 文件数组
    private ArrayList<String> fileArray = null;
    // 文件类型
    private int type = MovieInfoProvider.TYPE_QUICKTIME_JPEG;


    /**
     * 图片转视频
     *
     * @param filePaths 文件路径数组
     * @param type      格式
     * @param path      文件名
     * @throws MovieSaveException
     */
    public FilesToMov(File file, String path, int width, int height) throws MovieSaveException {
        File[] listFiles = file.listFiles();
        fileArray = new ArrayList();
        for (int i = 0; i < listFiles.length; i++) {
            fileArray.add(listFiles[i].getAbsolutePath());
        }
        // 指定生成视频帧图片格式
        this.type = MovieInfoProvider.TYPE_QUICKTIME_JPEG;
        DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider(path);
        // 设置帧频率 为了演示效果设定 1帧/秒
        dmip.setFPS(0.5F);
        // 设置帧数 也就是图片的数量
        dmip.setNumberOfFrames(fileArray.size());
        // 设置视频宽度
        dmip.setMWidth(width);
        // 设置视频高度
        dmip.setMHeight(height);
        //开始转换
        new Jim2Mov(this, dmip, this).saveMovie(this.type);
    }

    @Override
    public void frameSaved(int frameNumber) {
        System.out.println("正在生成index->" + frameNumber);
    }


    @Override
    public byte[] getImage(int frame) {
        try {
            //设置压缩比
            return MovieUtils.convertImageToJPEG(new File(fileArray.get(frame)), 1.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

