package $10_nio;

import java.io.*;

public class CopySmallBuf {
    public CopySmallBuf(File originFile, String copyFile) throws IOException {

        long startTime = System.currentTimeMillis();
        System.out.println(System.getProperty("user.dir"));

        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try{
            inputStream = new FileInputStream(originFile);
            bis = new BufferedInputStream(inputStream, 1024);
            outputStream = new FileOutputStream(copyFile);
            bos = new BufferedOutputStream(outputStream, 1024);

            while(true){
                int byteData = bis.read();
                if(byteData == -1){ break; }
                bos.write(byteData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
        }

        long endTime = System.currentTimeMillis();
        int elapsedTime = (int) (endTime - startTime);
        System.out.println("Small-Buffer tome = " + elapsedTime + " milli seconds");

    }
}
