package pamtech.com.mvvmpizza.service.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("AverageRating")
    @Expose
    private String averageRating;
    @SerializedName("TotalRatings")
    @Expose
    private String totalRatings;
    @SerializedName("TotalReviews")
    @Expose
    private String totalReviews;
    @SerializedName("LastReviewDate")
    @Expose
    private Object lastReviewDate;
    @SerializedName("LastReviewIntro")
    @Expose
    private Object lastReviewIntro;

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(String totalRatings) {
        this.totalRatings = totalRatings;
    }

    public String getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(String totalReviews) {
        this.totalReviews = totalReviews;
    }

    public Object getLastReviewDate() {
        return lastReviewDate;
    }

    public void setLastReviewDate(Object lastReviewDate) {
        this.lastReviewDate = lastReviewDate;
    }

    public Object getLastReviewIntro() {
        return lastReviewIntro;
    }

    public void setLastReviewIntro(Object lastReviewIntro) {
        this.lastReviewIntro = lastReviewIntro;
    }

}
