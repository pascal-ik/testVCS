package pamtech.com.mvvmpizza.service.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Response.class}, version = 1, exportSchema = false)
public abstract class ResponseDatabase extends RoomDatabase {
    private static ResponseDatabase instance;
    public abstract ResponseDao responseDao();

    public static synchronized ResponseDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ResponseDatabase.class, "response_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
