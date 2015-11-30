package ru.bug4j.weblog.model;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.bug4j.weblog.WeblogException;

import java.io.*;

@Component("logFile")
@Scope("prototype")
public class LogFile {

    @Autowired
    private Folder folder;

    private String name;

    public LogFile() {
    }

    public String getFullName() {
        return folder.getPath() + System.getProperty("file.separator") + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getX(int length) throws WeblogException {
        return getLastNLogLines(new File(getFullName()), length);
    }

    public String getLastNLogLines(File file, int nLines) {
        StringBuilder bilder = new StringBuilder();
        try (ReversedLinesFileReader fileReader = new ReversedLinesFileReader(file)) {
            for (int line = 0; line < nLines; line++) {
                bilder.insert(0, fileReader.readLine() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return bilder.toString();
        }
    }
}
