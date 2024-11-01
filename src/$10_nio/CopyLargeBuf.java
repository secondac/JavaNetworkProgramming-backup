package $10_nio;

import java.io.*;

public class CopyLargeBuf {

    public CopyLargeBuf(File originFile, String copyFile) throws IOException {
        long startTime = System.currentTimeMillis();

        System.out.println(System.getProperty("user.dir"));

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try{
            inputStream = new FileInputStream(originFile);
            outputStream = new FileOutputStream(copyFile);

            int fileSize = inputStream.available();
            byte[] buffer = new byte[fileSize];
            int byteData = inputStream.read(buffer);
            outputStream.write(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) { inputStream.close();}
            if (outputStream != null) {outputStream.close();}
        }

        long endTime = System.currentTimeMillis();
        int elapsedTime = (int) (endTime - startTime);
        System.out.println("Large-Buffer time :" + elapsedTime + "milliseconds");



    }
}
