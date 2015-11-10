package ru.bug4j.weblog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bug4j.weblog.model.Folder;

@Controller
public class SelectorController {

    @Autowired
    private Folder folder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initSelector(ModelMap model) {
        model.addAttribute("fileNameList", folder.getContentFilesNames());
        return "selector";
    }
}
