package $10_nio;

import java.io.File;
import java.io.IOException;

public class BufferTime {

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir")); // 현재 디렉터리 출력



        String filePath =  "8.pptx";
        // String filePath = "8.pptx";
        System.out.println(filePath);


        // String filePath = getClass().getResource("/8.pdf").getPath();
        // String filePath = "C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\src\\$10_nio\\8.pptx";
        // String filePath = "./src/$10_nio/8.pdf";
        File originalFile = new File(filePath);

        new CopyNonBuf(originalFile, "COPY_n_" + filePath);
        new CopySmallBuf(originalFile, "COPY_s_" + filePath);
        new CopyLargeBuf(originalFile, "COPY_l_" + filePath);
    }
}
