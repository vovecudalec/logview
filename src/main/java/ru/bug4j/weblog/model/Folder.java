package ru.bug4j.weblog.model;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("folder")
public class Folder {

    private File path;

    private List<String> contentFilesNames = new ArrayList<>();

    public Folder() {
    }

    {
        path = new File("/Alfresco/log");
        setContentFilesNames();
    }

//    public Folder(String path) {
//        this.path = new File(path);
//    }

    public String getPath() {
        return path.getPath();
    }

    public List<String> getContentFilesNames() {
        return contentFilesNames;
    }

    private void setContentFilesNames() {

        List<File> files = Arrays.asList(path.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".log");
            }
        }));
        for(File file : files){
            contentFilesNames.add(file.getName());
        }

    }
}
