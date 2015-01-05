package org.gradle.sample.utils;

import java.util.Date;

public class BuildInfo {
    private String version;
    private Date timestamp;

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuildInfo buildInfo = (BuildInfo) o;

        if (timestamp != null ? !timestamp.equals(buildInfo.timestamp) : buildInfo.timestamp != null) return false;
        if (version != null ? !version.equals(buildInfo.version) : buildInfo.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BuildInfo{" +
                "version='" + version + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}