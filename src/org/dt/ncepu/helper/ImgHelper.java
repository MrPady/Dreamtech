package org.dt.ncepu.helper;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImgHelper {

    private static final Log loggers =
            LogFactory.getLog(ImgHelper.class);

    /**
     *
     * @param f 图片所在的文件夹路径
     * @param filelist 图片路径
     * @param ext 扩展名
     * @param n 图片名
     * @param w 目标宽
     * @param h 目标高
     */
    public static void  Tosmallerpic(String f,File filelist,String ext,String n,int w,int h){
        Image src;
        try {
            src = javax.imageio.ImageIO.read(filelist); //构造Image对象
            String img_midname=f+n.substring(0,n.indexOf("."))+ext+n.substring(n.indexOf("."));
            int old_w=src.getWidth(null); //得到源图宽
            int old_h=src.getHeight(null);//得到源图长
            int new_w=0;
            int new_h=0;

            double w2=(old_w*1.00)/(w*1.00);
            double h2=(old_h*1.00)/(h*1.00);

            //根据长宽3：2切割
            BufferedImage oldpic;
            int length;
            if(old_w/3>old_h/2)
            {
                length=old_h/2;
            }else{
                length=old_w/3;
            }
            Image image = src.getScaledInstance(length*3, length*2,
                    Image.SCALE_DEFAULT);
            ImageFilter cropFilter = new CropImageFilter(0, 0, length*3, length*2);
            Image img = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(image.getSource(),
                            cropFilter));
            BufferedImage tag = new BufferedImage(3*length, 2*length, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(img, 0, 0, 3*length, 2*length, null); // 绘制切割后的图
            g.dispose();

            FileOutputStream newimage=new FileOutputStream(img_midname); //输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            JPEGEncodeParam jep=JPEGCodec.getDefaultJPEGEncodeParam(tag);
                /* 压缩质量 */
            jep.setQuality((float) 0.5, true);
            encoder.encode(tag, jep);
            //encoder.encode(tag); //近JPEG编码
            newimage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 图像切割(按指定起点坐标和宽高切割)
     * @param srcImageFile 源图像地址
     * @param result 切片后的图像地址
     * @param x 目标切片起点坐标X
     * @param y 目标切片起点坐标Y
     * @param width 目标切片宽度
     * @param height 目标切片高度
     */
    public  static void cut(String srcImageFile, String result,
                                 int x, int y, int width, int height) {
        try {
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight(); // 源图宽度
            int srcHeight = bi.getWidth(); // 源图高度
            if (srcWidth > 0 && srcHeight > 0) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);
                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
                Image img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(),
                                cropFilter));
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
                g.dispose();
                // 输出为文件
                ImageIO.write(tag, "JPEG", new File(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图像切割
     * @param srcImageFile 源图像地址
     * @param descDir      切片目标文件夹
     * @param destWidth    目标切片宽度
     * @param destHeight   目标切片高度
     */
    public static void cut(String srcImageFile, String descDir, int destWidth, int destHeight)
    {
        try
        {
            Image img;
            ImageFilter cropFilter;
            String dir = null;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight(); // 源图宽度
            int srcHeight = bi.getWidth(); // 源图高度
            if (srcWidth > destWidth && srcHeight > destHeight)
            {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                destWidth = 300; // 切片宽度
                destHeight = 300; // 切片高度
                int cols = 0; // 切片横向数量
                int rows = 0; // 切片纵向数量
                // 计算切片的横向和纵向数量
                if (srcWidth % destWidth == 0)
                {
                    cols = srcWidth / destWidth;
                }
                else
                {
                    cols = (int) Math.floor(srcWidth / destWidth) + 1;
                }
                if (srcHeight % destHeight == 0)
                {
                    rows = srcHeight / destHeight;
                }
                else
                {
                    rows = (int) Math.floor(srcHeight / destHeight) + 1;
                }
                // 循环建立切片
                for (int i = 0; i < rows; i++)
                {
                    for (int j = 0; j < cols; j++)
                    {
                        // 四个参数分别为图像起点坐标和宽高
                        // 即: CropImageFilter(int x,int y,int width,int height)
                        cropFilter = new CropImageFilter(j * 300, i * 300, destWidth, destHeight);
                        img = Toolkit.getDefaultToolkit().createImage(
                                new FilteredImageSource(image.getSource(), cropFilter));
                        BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                        Graphics g = tag.getGraphics();
                        g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                        g.dispose();
                        // 输出为文件
                        dir = descDir + "cut_image_" + i + "_" + j + ".jpg";
                        File f = new File(dir);
                        ImageIO.write(tag, "JPEG",f);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 剪切图片,没有处理图片后缀名是否正确,还有gif动态图片
     * @param sourcePath 源路径(包含图片)
     * @param targetPath 目标路径 null则默认为源路径
     * @param x 起点x坐标
     * @param y 起点y左边
     * @param width 剪切宽度
     * @param height 剪切高度
     * @return 目标路径
     * @throws IOException if sourcePath image doesn't exist
     */
    public static String cutImage(String sourcePath,String targetPath,int x,int y,int width,int height) throws IOException{
        File imageFile = new File(sourcePath);
        if(!imageFile.exists()){
            throw new IOException("Not found the images:"+sourcePath);
        }
        if(targetPath==null || targetPath.isEmpty()) targetPath = sourcePath;
        String format = sourcePath.substring(sourcePath.lastIndexOf(".")+1,sourcePath.length());
        BufferedImage image = ImageIO.read(imageFile);
        image = image.getSubimage(x, y, width, height);
        ImageIO.write(image, format, new File(targetPath));
        return targetPath;
    }

    /**
     * 压缩图片
     * @param sourcePath 源路径(包含图片)
     * @param targetPath 目标路径 null则默认为源路径
     * @param width 压缩后宽度
     * @param height 压缩后高度
     * @return 目标路径
     * @throws IOException if sourcePath image does not exist
     */
    public static String zoom(String sourcePath,String targetPath,int width,int height) throws IOException{
        File imageFile = new File(sourcePath);
        if(!imageFile.exists()){
            throw new IOException("Not found the images:"+sourcePath);
        }
        if(targetPath==null || targetPath.isEmpty()) targetPath = sourcePath;
        String format = sourcePath.substring(sourcePath.lastIndexOf(".")+1,sourcePath.length());
        BufferedImage image = ImageIO.read(imageFile);
        image = zoom(image,width,height);
        ImageIO.write(image, format, new File(targetPath));
        return targetPath;
    }

    /**
     * 压缩图片
     * @param sourceImage    待压缩图片
     * @param width          压缩图片高度
     * @param height          压缩图片宽度
     */
    private static BufferedImage zoom(BufferedImage sourceImage , int width , int height){
        BufferedImage zoomImage = new BufferedImage(width, height, sourceImage.getType());
        Image image = sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Graphics gc = zoomImage.getGraphics();
        gc.setColor(Color.WHITE);
        gc.drawImage( image , 0, 0, null);
        return zoomImage;
    }

    public static void PadyCutAndZoomImage(String sourcePath,File targetFile,int width,int height) throws IOException {
        File imageFile = new File(sourcePath);
        String format = sourcePath.substring(sourcePath.lastIndexOf(".")+1,sourcePath.length());
        BufferedImage image = ImageIO.read(imageFile);
        int old_w=image.getWidth();
        int old_h=image.getHeight();
        int length;
        if(old_w/width>old_h/height){
            length=old_h/height;
            image=image.getSubimage(0,0,length*width,length*height);
        }else{
            length=old_w/width;
            image=image.getSubimage(0,0,length*width,length*height);
        }
        image=zoom(image,width,height);

        FileOutputStream newimage=new FileOutputStream(targetFile); //输出到文件流
        ImageIO.write(image, format, newimage);
    }

}
