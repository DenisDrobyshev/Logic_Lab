package space.drobyshev.logiclab;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";
    public static final String USERS_TABLE = "users";
    public static final String USER_DATA_TABLE = "user_data";



    public DBHelper(Context context) {
        super(context, "Login.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USERS_TABLE + "(name TEXT, email TEXT primary key, password TEXT, game1 BOOLEAN, game2 BOOLEAN, game3 BOOLEAN, game4 BOOLEAN, game5 BOOLEAN )");}

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS users_data");
    }

    public Boolean insertData(String name, String email, String password, Boolean game1, Boolean game2, Boolean game3, Boolean game4, Boolean game5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        values.put("game1", game1);
        values.put("game2", game2);
        values.put("game3", game3);
        values.put("game4", game4);
        values.put("game5", game5);

        long result = db.insert(USERS_TABLE, null, values);

        if (result == -1) return false;
        else
            return true;
    }

    public void updateUserGameProgress(String email, Boolean game1, Boolean game2, Boolean game3, Boolean game4, Boolean game5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("game1", game1);
        values.put("game2", game2);
        values.put("game3", game3);
        values.put("game4", game4);
        values.put("game5", game5);

        int result = db.update(USERS_TABLE, values, "email = ?", new String[] { email });

        if (result > 0) {
            Log.d("DBHelper", "User game progress updated");
        } else {
            Log.e("DBHelper", "Failed to update user game progress");
        }
    }


    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email=?", new String[] {email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email=? and password=?", new String[] {email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    @SuppressLint("Range")
    public String getNameByEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String name = null;

        // Запрос для выборки имени по электронной почте
        String query = "SELECT name FROM " + USERS_TABLE + " WHERE email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
        }
        cursor.close();

        return name;
    }

    public  List<Boolean> getAllGameResultsByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Boolean> gameResults = new ArrayList<>();

        Cursor cursor = db.query(
                USERS_TABLE,
                new String[] {"game1", "game2", "game3", "game4", "game5"},
                "email = ?",
                new String[] {email},
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            // Получаем индексы столбцов для каждой игры
            int game1Index = cursor.getColumnIndex("game1");
            int game2Index = cursor.getColumnIndex("game2");
            int game3Index = cursor.getColumnIndex("game3");
            int game4Index = cursor.getColumnIndex("game4");
            int game5Index = cursor.getColumnIndex("game5");

            // Получаем результаты игры и сохраняем их в список
            boolean game1Result = cursor.getInt(game1Index) > 0;
            boolean game2Result = cursor.getInt(game2Index) > 0;
            boolean game3Result = cursor.getInt(game3Index) > 0;
            boolean game4Result = cursor.getInt(game4Index) > 0;
            boolean game5Result = cursor.getInt(game5Index) > 0;

            gameResults.add(game1Result);
            gameResults.add(game2Result);
            gameResults.add(game3Result);
            gameResults.add(game4Result);
            gameResults.add(game5Result);
        }

        if (cursor != null) {
            cursor.close();
        }

        return gameResults;
    }


}