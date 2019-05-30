package scorekeeper.scorekeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ScoreKeeperOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ScoreKeeper.db";
    public static final int DATABASE_VERSION = 1;

    public ScoreKeeperOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
  //      sqLiteDatabase.execSQL(ScoreKeeperDatabaseContract.GamesEntry.SQL_CREATE_TABLE);
  //      sqLiteDatabase.execSQL(ScoreKeeperDatabaseContract.PlayerEntry.SQL_CREATE_TABLE);

  //      DatabaseDataWorker worker = new DatabaseDataWorker(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
