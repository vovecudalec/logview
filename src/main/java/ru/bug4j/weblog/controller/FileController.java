package ru.bug4j.weblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.bug4j.weblog.WeblogException;
import ru.bug4j.weblog.model.Folder;
import ru.bug4j.weblog.model.LogFile;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/file/{filename:.+}")
@Scope("singleton")
public class FileController {

    @Autowired
    private LogFile logFile;

    @Autowired
    private Folder folder;

    @RequestMapping(value = "get100", method = RequestMethod.GET)
    public String get100(ModelMap modelMap) {
            modelMap.addAttribute("fileContent", getx(100));
            modelMap.addAttribute("title", logFile.getName());
        return "hello";
    }

    @RequestMapping(value = "get500", method = RequestMethod.GET)
    public String get500(ModelMap modelMap) {
            modelMap.addAttribute("fileContent", getx(500));
            modelMap.addAttribute("title", logFile.getName());
        return "hello";
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public String getAll(ModelMap modelMap) {
            modelMap.addAttribute("fileContent", getx(0));
            modelMap.addAttribute("title", logFile.getName());
        return "hello";
    }

    public String getx(int x) {
        try {
            return logFile.getX(x);
        } catch (WeblogException e) {
            e.printStackTrace();
            return getErrorMsg() + "/n" + e.getMessage();
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String showFile(@PathVariable String filename, ModelMap modelMap) {
        logFile.setName(filename);
        try {
            modelMap.addAttribute("fileContent", logFile.getX(100));
            modelMap.addAttribute("title", logFile.getName());
        } catch (WeblogException e) {
            e.printStackTrace();
            modelMap.addAttribute("fileContent", getErrorMsg());
        }
        return "hello";
    }

    private String getErrorMsg() {
        return "Ошибка чтения файла ";
    }
}