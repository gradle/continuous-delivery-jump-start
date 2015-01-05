package org.gradle.sample.http;

public class HttpClientException extends RuntimeException {
    public HttpClientException() {}

    public HttpClientException(Throwable cause) {
        super(cause);
    }
}