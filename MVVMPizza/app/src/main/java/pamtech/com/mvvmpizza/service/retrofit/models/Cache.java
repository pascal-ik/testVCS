package pamtech.com.mvvmpizza.service.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cache {

    @SerializedName("execution-start-time")
    @Expose
    private String executionStartTime;
    @SerializedName("execution-stop-time")
    @Expose
    private String executionStopTime;
    @SerializedName("execution-time")
    @Expose
    private String executionTime;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("content")
    @Expose
    private String content;

    public String getExecutionStartTime() {
        return executionStartTime;
    }

    public void setExecutionStartTime(String executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    public String getExecutionStopTime() {
        return executionStopTime;
    }

    public void setExecutionStopTime(String executionStopTime) {
        this.executionStopTime = executionStopTime;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
