package pamtech.com.mvvmpizza.service.retrofit.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Diagnostics {

    @SerializedName("publiclyCallable")
    @Expose
    private String publiclyCallable;
    @SerializedName("cache")
    @Expose
    private List<Cache> cache = null;
    @SerializedName("url")
    @Expose
    private Url url;
    @SerializedName("query")
    @Expose
    private Query_ query;
    @SerializedName("javascript")
    @Expose
    private Javascript javascript;
    @SerializedName("user-time")
    @Expose
    private String userTime;
    @SerializedName("service-time")
    @Expose
    private String serviceTime;
    @SerializedName("build-version")
    @Expose
    private String buildVersion;

    public String getPubliclyCallable() {
        return publiclyCallable;
    }

    public void setPubliclyCallable(String publiclyCallable) {
        this.publiclyCallable = publiclyCallable;
    }

    public List<Cache> getCache() {
        return cache;
    }

    public void setCache(List<Cache> cache) {
        this.cache = cache;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public Query_ getQuery() {
        return query;
    }

    public void setQuery(Query_ query) {
        this.query = query;
    }

    public Javascript getJavascript() {
        return javascript;
    }

    public void setJavascript(Javascript javascript) {
        this.javascript = javascript;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

}
