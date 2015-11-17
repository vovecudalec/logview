package ru.bug4j.weblog.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.bug4j.weblog.WeblogException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component("logFile")
@Scope("prototype")
public class LogFile {

    @Autowired
    private Folder folder;

    private String name;
    List<String> content = new ArrayList<>();
    private Integer hash;

    public LogFile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void invalidate() throws WeblogException {

        String fileName = folder.getPath() + System.getProperty("file.separator") + name;
        try (FileReader fileReader = new FileReader(fileName)) {

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String sCurrentLine;
            content.clear();
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                content.add(sCurrentLine + "\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new WeblogException(e);
        }
    }

    public List<String> getContent() throws WeblogException {
        invalidate();
        return content;
    }

    public String getX(int length) throws WeblogException {
        List<String> strings = getContent();
        int fileLength = strings.size();
        if (length == 0) {
            return StringUtils.join(strings, "");
        }
        if (fileLength <= length) {
            return StringUtils.join(strings, "");
        } else {
            return StringUtils.join(strings.subList(fileLength - length, fileLength), "");
        }
    }

}
