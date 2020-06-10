package com.lge.ex23;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Try with Resources -> AutoClosable 인터페이스만 구현합니다.
class Resource implements AutoCloseable {
    // File, Socket
    @Override
    public void close() throws IOException {
        System.out.println("Resource 파괴되었음!");
    }
}

public class Sample {
    /*
    public static void main(String[] args) throws Exception {
        // a.txt
        // "Hello"
        FileOutputStream fos = new FileOutputStream("a.txt");
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeUTF("Hello");
        dos.close();
        fos.close();
    }
    */
    // Java 7: Try with Resources
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("a.txt");
             DataOutputStream dos = new DataOutputStream(fos);
             Resource resource = new Resource()) {

            dos.writeUTF("Hello");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // OS 리소스(비메모리 자원)
    // => GC가 처리하지 않으므로, 명시적인 종료 메소드를 호출해서 정리해야 한다.
    // => Java 6
    /*
    public static void main(String[] args) {
        // a.txt
        // "Hello"
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream("a.txt");
            dos = new DataOutputStream(fos);
            dos.writeUTF("Hello");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */
}
