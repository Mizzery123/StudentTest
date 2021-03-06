package sg.edu.rp.c346.studenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etID, etName, etGPA;
    Button btnInsert, btnShow;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnRetrieve);
        tvResult = findViewById(R.id.Result);
        etName = findViewById(R.id.etName);
        etID = findViewById(R.id.etID);
        etGPA = findViewById(R.id.etGPA);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etName.getText().toString(), Double.parseDouble(etGPA.getText().toString()));
                db.close();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<Student> data = db.getStudents();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += " Student ID: " + data.get(i).getId() + "\n Student Name: " + data.get(i).getName() + "\n Student GPA: " + data.get(i).getGpa() + "\n";


                }
                tvResult.setText(txt);
            }
        });

    }
}
