package com.example.shane.myworkoutapp;



        import java.util.ArrayList;
        import java.util.HashMap;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class DBTools extends SQLiteOpenHelper {

    public DBTools(Context applicationContext){

        super(applicationContext, "workout.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String query = "CREATE TABLE workouts ( workoutId INTEGER PRIMARY KEY, name TEXT, " +
                "exercise1 TEXT, exercise2 TEXT, exercise3 TEXT, exercise4 TEXT)";

        database.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS workouts";

        database.execSQL(query);
        onCreate(database);

    }

    public void insertWorkout(HashMap<String, String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", queryValues.get("name"));
        values.put("exercise1", queryValues.get("exercise1"));
        values.put("exercise2", queryValues.get("exercise2"));
        values.put("exercise3", queryValues.get("exercise3"));
        values.put("exercise4", queryValues.get("exercise4"));

        database.insert("workouts", null, values);

        database.close();

    }

    public int updateWorkout(HashMap<String, String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", queryValues.get("name"));
        values.put("exercise1", queryValues.get("exercise1"));
        values.put("exercise2", queryValues.get("exercise2"));
        values.put("exercise3", queryValues.get("exercise3"));
        values.put("exercise4", queryValues.get("exercise4"));

        return database.update("workouts", values,
                "workoutId" + " = ?", new String[] {queryValues.get("workoutId") });

    }

    public void deleteWorkout(String id){

        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM workouts WHERE workoutId='" + id + "'";

        database.execSQL(deleteQuery);

    }

    public ArrayList<HashMap<String, String>> getAllWorkouts(){

        ArrayList<HashMap<String, String>> workoutArrayList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT * FROM workouts ORDER BY name";

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{

                HashMap<String, String> workoutMap = new HashMap<String, String>();

                workoutMap.put("workoutId", cursor.getString(0));
                workoutMap.put("name", cursor.getString(1));
                workoutMap.put("exercise1", cursor.getString(2));
                workoutMap.put("exercise2", cursor.getString(3));
                workoutMap.put("exercise3", cursor.getString(4));
                workoutMap.put("exercise4", cursor.getString(5));

                workoutArrayList.add(workoutMap);

            } while(cursor.moveToNext());

        }

        return workoutArrayList;

    }

    public HashMap<String, String> getWorkoutInfo(String id){

        HashMap<String, String> workoutMap = new HashMap<String, String>();

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM workouts WHERE workoutId='" + id + "'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{

                workoutMap.put("workoutId", cursor.getString(0));
                workoutMap.put("name", cursor.getString(1));
                workoutMap.put("exercise1", cursor.getString(2));
                workoutMap.put("exercise2", cursor.getString(3));
                workoutMap.put("exercise3", cursor.getString(4));
                workoutMap.put("exercise4", cursor.getString(5));


            } while(cursor.moveToNext());

        }

        return workoutMap;

    }

}








