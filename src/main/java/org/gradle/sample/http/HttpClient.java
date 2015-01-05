package org.gradle.sample.http;

public interface HttpClient {
    HttpResponse get(String url);
}