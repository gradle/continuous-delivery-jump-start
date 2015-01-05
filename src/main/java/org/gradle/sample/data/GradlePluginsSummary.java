package org.gradle.sample.data;

public class GradlePluginsSummary {
    private Long count;
    private String exceptionMessage;
    
    public void setCount(Long count) {
        this.count = count;
    }
    
    public Long getCount() {
        return count;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradlePluginsSummary that = (GradlePluginsSummary) o;

        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (exceptionMessage != null ? !exceptionMessage.equals(that.exceptionMessage) : that.exceptionMessage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = count != null ? count.hashCode() : 0;
        result = 31 * result + (exceptionMessage != null ? exceptionMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GradlePluginsSummary{" +
                "count=" + count +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }
}