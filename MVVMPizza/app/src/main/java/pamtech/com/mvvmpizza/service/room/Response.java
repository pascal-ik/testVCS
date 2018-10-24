package pamtech.com.mvvmpizza.service.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "response_table")
public class Response {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String response;

    public Response(String response) {
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    public void setId(int id) {
        this.id = id;
    }
}
