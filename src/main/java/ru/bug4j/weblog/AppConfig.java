package ru.bug4j.weblog;

import org.aeonbits.owner.Config;

public interface AppConfig extends Config {

    @Key("root.folder")
    @DefaultValue("/Alfresco/log")
    String getRootFolder();
}
