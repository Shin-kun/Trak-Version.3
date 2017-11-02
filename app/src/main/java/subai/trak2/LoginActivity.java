package subai.trak2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private static EditText busNumber;
    private static EditText route;
    private Button login;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private boolean checked = false;
    private boolean busChecked = false;
    private boolean routeChecked = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        busNumber = (EditText) findViewById(R.id.busNumText);
        route = (EditText) findViewById(R.id.routeText);
        login = (Button) findViewById(R.id.btnLogin);


        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (busNumber.getText().toString().isEmpty() || route.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "INCOMPLETE DATA", Toast.LENGTH_LONG).show();
                } else {
                    checkBusNumber();
                }
            }

        });
    }

    public void sendMessage() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void checkBusNumber(){
        DatabaseReference busRef = ref.child("Bus_Accounts");
        busRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String busNum = busNumber.getText().toString();
                if(!busNum.isEmpty()){
                    if (!dataSnapshot.hasChild(busNum)) {
                        busNumber.setText("");
                        checked = true;
                        Toast.makeText(getApplicationContext(), "BUS DOES NOT EXIST", Toast.LENGTH_LONG).show();
                    }
                    else {
                        busChecked = true;
                        checkRoute();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void checkRoute(){
        DatabaseReference routeRef = ref.child("Route");
        routeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String r = route.getText().toString();
                if(!r.isEmpty()){
                    if (!dataSnapshot.hasChild(r)) {
                        route.setText("");
                        checked = true;
                        Toast.makeText(getApplicationContext(), "ROUTE DOES NOT EXIST", Toast.LENGTH_LONG).show();
                    } else {
                        routeChecked = true;
                        sendMessage();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static String getBusNumber(){
        return busNumber.getText().toString();
    }

    public static String getRoute(){
        return route.getText().toString();
    }

}

