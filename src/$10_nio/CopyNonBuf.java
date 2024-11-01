package $10_nio;

import java.io.*;

public class CopyNonBuf {
    public CopyNonBuf(File originFile, String copyFile) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println(System.getProperty("user.dir"));

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try{
            inputStream = new FileInputStream(originFile);
            outputStream = new FileOutputStream(copyFile);

            while(true){
                int byteData = inputStream.read();
                if(byteData == -1) break;
                outputStream.write(byteData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) inputStream.close();
            if(outputStream != null) outputStream.close();
        }

        long endTime = System.currentTimeMillis();
        int elapsedTime = (int) (endTime - startTime);
        System.out.println("Non-Buffer time = "+elapsedTime + " milli seconds");
    }
}
