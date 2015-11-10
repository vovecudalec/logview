package ru.bug4j.weblog.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.bug4j.weblog.AppProperties;
import ru.bug4j.weblog.WeblogException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component("logFile")
@Scope("prototype")
public class LogFile {
    private String name;
    private String path;
    List<String> content = new ArrayList<>();
    private Object hash;

    public LogFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public LogFile() {
//        this.path = AppProperties.get("root.folder");
        this.name = "alfresco.log";
        this.path = "/Alfresco/log";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getLast100() throws WeblogException {
//        return getX(100);
//    }

    private void invalidate() throws WeblogException {

        String fileName = path + System.getProperty("file.separator") + name;
        try (FileReader fileReader = new FileReader(fileName)) {

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            if (Objects.equals(bufferedReader.hashCode(), hash)) {
                return;
            }

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
