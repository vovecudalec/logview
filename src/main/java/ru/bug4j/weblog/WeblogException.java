package ru.bug4j.weblog;

import java.io.IOException;

public class WeblogException extends Exception {
    public WeblogException(Exception e) {
        super(e);
    }
}
