package com.example.paymentsummary_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // Access a Cloud Firestore instance from your Activity
    TextView  txt1, txt2,txt3,txt4;
    Button btn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get data
        txt1=findViewById(R.id.UName);
        txt2 = findViewById(R.id.RId);
        txt3 = findViewById(R.id.PLName);
        txt4 = findViewById(R.id.rTime);
        btn = findViewById(R.id.btnCPay);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference docRef = db.collection("Reservation").document( "DVS0Hiwvud4KYEO1ihOq");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Reservation res = document.toObject(Reservation.class);
                                txt2.setText(Long.toString(res.getReservationId()));
                                txt1.setText((res.getUserId()));
                                txt3.setText(res.getParkingSpotId());
                                txt4.setText(Long.toString(res.getCost()));

                                Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d("TAG", "No such document");
                            }
                        } else {
                            Log.d("TAG", "get failed with ", task.getException());
                        }
                    }
                });




            }



        });



    }
}