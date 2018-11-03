package by.wink.core.rest.multipart;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileData {

    private String fileName;
    private byte[] content;
    private String type;

    /**
     * Constructor with data.
     *
     * @param name label of data
     * @param data byte data
     */
    public FileData(String name, byte[] data) {
        fileName = name;
        content = data;
    }

    /**
     * Constructor with mime data type.
     *
     * @param name     label of data
     * @param data     byte data
     * @param mimeType mime data like "image/jpeg"
     */
    public FileData(String name, byte[] data, String mimeType) {
        this(name, data);
        type = mimeType;
    }


    /**
     * Constructor with mime data type.
     *
     * @param name     label of data
     * @param file file data
     */
    public FileData(String name, File file) throws IOException {
        this(name, read(file));
    }

    /**
     * Constructor with mime data type.
     *
     * @param name     label of data
     * @param file file data
     * @param mimeType mime data like "image/jpeg"
     */
    public FileData(String name, File file, String mimeType) throws IOException {
        this(name, read(file), mimeType);
    }


    /**
     * Getter file name.
     *
     * @return file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Getter content.
     *
     * @return byte file data
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Getter mime type.
     *
     * @return mime type
     */
    public String getType() {
        return type;
    }


    private static byte[] read(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
        }finally {
            try {
                if (ous != null)
                    ous.close();
            } catch (IOException e) {
            }

            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }
        return ous.toByteArray();
    }
}
