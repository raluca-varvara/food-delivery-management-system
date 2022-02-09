package dataLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWr {
    private File f;
    private FileWriter fr;

    public FileWr(String fileName) throws IOException {
        f = new File(fileName);
        fr = new FileWriter(f);
    }

    public FileWriter getFr() {
        return fr;
    }
}
