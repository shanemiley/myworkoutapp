package com.example.shane.myworkoutapp;



        import java.util.HashMap;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;

public class EditWorkout extends Activity{

    EditText name;
    EditText exercise1;
    EditText exercise2;
    EditText exercise3;
    EditText exercise4;

    DBTools dbTools = new DBTools(this);

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_workout);
        name = (EditText) findViewById(R.id.name);
        exercise1 = (EditText) findViewById(R.id.exercise1);
        exercise2 = (EditText) findViewById(R.id.exercise2);
        exercise3 = (EditText) findViewById(R.id.exercise3);
        exercise4 = (EditText) findViewById(R.id.exercise4);

        Intent theIntent = getIntent();

        String workoutId = theIntent.getStringExtra("workoutId");

        HashMap<String, String> contactList = dbTools.getWorkoutInfo(workoutId);

        if(contactList.size() != 0){

            name.setText(contactList.get("name"));
            exercise1.setText(contactList.get("exercise1"));
            exercise2.setText(contactList.get("exercise2"));
            exercise3.setText(contactList.get("exercise3"));
            exercise4.setText(contactList.get("exercise4"));

        }
    }

    public void editWorkout(View view){

        HashMap<String, String> queryValuesMap = new HashMap<String, String>();
        name = (EditText) findViewById(R.id.name);
        exercise1 = (EditText) findViewById(R.id.exercise1);
        exercise2 = (EditText) findViewById(R.id.exercise2);
        exercise3 = (EditText) findViewById(R.id.exercise3);
        exercise4 = (EditText) findViewById(R.id.exercise4);

        Intent theIntent = getIntent();

        String workoutId = theIntent.getStringExtra("workoutId");

        queryValuesMap.put("workoutId", workoutId);
        queryValuesMap.put("name", name.getText().toString());
        queryValuesMap.put("exercise1", exercise1.getText().toString());
        queryValuesMap.put("exercise2", exercise2.getText().toString());
        queryValuesMap.put("exercise3", exercise3.getText().toString());
        queryValuesMap.put("exercise4", exercise4.getText().toString());

        dbTools.updateWorkout(queryValuesMap);
        this.callMainActivity(view);

    }

    public void removeWorkout(View view){

        Intent theIntent = getIntent();

        String workoutId = theIntent.getStringExtra("workoutId");

        dbTools.deleteWorkout(workoutId);

        this.callMainActivity(view);

    }

    public void callMainActivity(View view){

        Intent objIntent = new Intent(getApplication(), MainActivity.class);

        startActivity(objIntent);

    }

}






