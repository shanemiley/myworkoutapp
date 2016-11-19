package com.example.shane.myworkoutapp;



        import java.util.ArrayList;
        import java.util.HashMap;


        import android.os.Bundle;
        import android.app.ListActivity;
        import android.content.Intent;

        import android.util.Log;
        import android.view.View;

        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ListAdapter;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.ListView;

        import static android.content.ContentValues.TAG;


public class MainActivity extends ListActivity {

    TextView workoutId;


    DBTools dbTools = new DBTools(this);


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, String>> workoutList =  dbTools.getAllWorkouts();

        // Check to make sure there are contacts to display

        if(workoutList.size()!=0) {

            // Get the ListView and assign an event handler to it

            ListView listView = getListView();
            listView.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                    // When an item is clicked get the TextView
                    // with a matching checkId

                    workoutId = (TextView) view.findViewById(R.id.workoutId);


                    String workoutIdValue = workoutId.getText().toString();



                    Intent  theIntent = new Intent(getApplication(),EditWorkout.class);

                    // Put additional data in for EditContact to use

                    theIntent.putExtra("workoutId", workoutIdValue);

                    // Calls for EditContact

                    startActivity(theIntent);
                }
            });



            ListAdapter adapter = new SimpleAdapter( MainActivity.this,workoutList, R.layout.workout_entry, new String[] { "workoutId","name"}, new int[] {R.id.workoutId, R.id.name});
            setListAdapter(adapter);
        }
    }

    // When showAddContact is called with a click the Activity
    // NewContact is called

    public void showAddWorkout(View view) {
        Log.d(TAG, "in show add workout");

        Intent theIntent = new Intent(getApplicationContext(), NewWorkout.class);
        Log.d(TAG, "in show add workout after intent");
        startActivity(theIntent);
    }
}
