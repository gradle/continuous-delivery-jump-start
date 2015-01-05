package org.gradle.sample.parser;

public class ApiParserException extends RuntimeException {
    public ApiParserException() {}

    public ApiParserException(Throwable cause) {
        super(cause);
    }
}