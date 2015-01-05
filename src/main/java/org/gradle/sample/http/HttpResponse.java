package org.gradle.sample.http;

public class HttpResponse {
    private String body;
    private int statusCode;
    private String reasonPhrase;
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public String getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public boolean isOK() {
        return statusCode == 200;
    }
}