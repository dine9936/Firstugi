package com.example.firstugi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Phoneauth extends AppCompatActivity {
    private Button submitbtn,backbtn;
    private DatabaseReference reference;
    private String phone;
    private EditText editTextScid;
    private String StudentId;
    private LinearLayout layout,buttonSorT,buttonInfo;
    private TextView textView;
    private CircleImageView techer,student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneauth);

        reference = FirebaseDatabase.getInstance().getReference().child("Stdntcllgid");

        textView = (TextView)findViewById(R.id.text_notify);
        layout = (LinearLayout)findViewById(R.id.progrees);
        buttonInfo = (LinearLayout)findViewById(R.id.layout_info);
        buttonSorT = (LinearLayout)findViewById(R.id.button_layout_s_or_t);
        submitbtn = (Button)findViewById(R.id.submit_button);
        backbtn = (Button)findViewById(R.id.back_button);
        techer = (CircleImageView)findViewById(R.id.teacher_circle);

        techer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextScid.setHint("Enter Teacher ID");
                buttonSorT.setVisibility(View.GONE);
                buttonInfo.setVisibility(View.VISIBLE);

            }
        });
        student = (CircleImageView)findViewById(R.id.student_circle);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextScid.setHint("Enter Student ID");
                buttonSorT.setVisibility(View.GONE);
                buttonInfo.setVisibility(View.VISIBLE);
            }
        });

          backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (backbtn.getText().equals("BACK")){
                   buttonSorT.setVisibility(View.VISIBLE);
                   buttonInfo.setVisibility(View.GONE);
               }
            }
        });
        editTextScid = (EditText)findViewById(R.id.edit_stdnt_id);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudentId = editTextScid.getText().toString().trim();


                if (TextUtils.isEmpty(StudentId)){
                    Toast.makeText(Phoneauth.this, "Enter Your College Id", Toast.LENGTH_SHORT).show();
                }else {

                    layout.setVisibility(View.VISIBLE);
                    editTextScid.setVisibility(View.GONE);
                    submitbtn.setEnabled(false);
                    reference.child(StudentId).orderByChild("phone").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                                if (dataSnapshot1.exists()){
                                    textView.setVisibility(View.VISIBLE);
                                    textView.setText("OTP sent on "+"+91 "+dataSnapshot1.getValue().toString());
                                    layout.setVisibility(View.GONE);
                                    editTextScid.setText(null);
                                    editTextScid.setVisibility(View.VISIBLE);
                                    editTextScid.setHint("Enter OTP");
                                    submitbtn.setText("Submit");
                                    submitbtn.setEnabled(true);
                                    Toast.makeText(Phoneauth.this, dataSnapshot1.getValue().toString(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });
    }


}
