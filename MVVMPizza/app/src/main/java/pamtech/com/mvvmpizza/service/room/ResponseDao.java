package pamtech.com.mvvmpizza.service.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

@Dao
public interface ResponseDao {

    @Insert
    void insert(Response response);
    @Update
    void update(Response response);
    @Delete
    void delete(Response response);

}
