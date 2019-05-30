package scorekeeper.scorekeeper;

import android.provider.BaseColumns;

public final class ScoreKeeperDatabaseContract {
    private ScoreKeeperDatabaseContract()
    {

    }
    public static final class PlayerEntry implements BaseColumns {
        public static final String TABLE_NAME = "player_info";
        public static final String COLUMN_PLAYER_NAME = "player_name";
        public static final String COLUMN_PLAYER_VICTORY_POINTS = "player_victory_points";
        public static final String COLUMN_PLAYER_COMMAND_POINTS = "player_command_points";
        public static final String COLUMN_PLAYER_FIRST_BLOOD = "player_first_blood";
        public static final String COLUMN_PLAYER_WARLORD = "player_warlord";
        public static final String COLUMN_PLAYER_BEHIND_LINES = "player_behind_lines";

        //CREATE TABLE player_info (player_id, player_name, player_victory_points, player_command_points, player_first_blood, player_warlord, player_behind_lines)

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_PLAYER_NAME + " TEXT NOT NULL, " +
                        COLUMN_PLAYER_VICTORY_POINTS + " INTEGER NOT NULL, " +
                        COLUMN_PLAYER_COMMAND_POINTS + " INTEGER NOT NULL, " +
                        COLUMN_PLAYER_FIRST_BLOOD + " BOOLEAN NOT NULL, " +
                        COLUMN_PLAYER_WARLORD + " BOOLEAN NOT NULL, " +
                        COLUMN_PLAYER_BEHIND_LINES + " BOOLEAN NOT NULL)";

    }

    public static final class GamesEntry  implements BaseColumns {
        public static final String TABLE_NAME = "game_info";
        public static final String COLUMN_GAME_NAME = "game_name";
        public static final String COLUMN_PLAYER1_ID = "player1_id";
        public static final String COLUMN_PLAYER2_ID = "player2_id";
        public static final String COLUMN_TURN = "game_turn";

        //CREATE TABLE game_info (game_id, game_name, turn, player1_id, player2_id)

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_GAME_NAME + " TEXT NOT NULL, " +
                        COLUMN_TURN + " INTEGER NOT NULL, " +
                        COLUMN_PLAYER1_ID + " INTEGER NOT NULL, " +
                        COLUMN_PLAYER2_ID + " INTEGER NOT NULL)";
    }
}
