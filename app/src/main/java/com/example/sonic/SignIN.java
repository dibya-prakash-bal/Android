package com.example.sonic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignIN extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;

    private TextView next;
    String username1 ="dibya";
    String password1="1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username=findViewById(R.id.username);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login = findViewById(R.id.loginbtn);

        next=findViewById(R.id.createacc);





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=username.getText().toString();
                String p=password.getText().toString();
                if (username1.equals(s) && password1.equals(p)) {

                    Toast.makeText(SignIN.this, "Wait a second your Home page is loading", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignIN.this,Home_page.class);
                    startActivity(intent);

                }
                else {

                    Toast.makeText(SignIN.this, "User Id and password does not match", Toast.LENGTH_LONG).show();


                }


            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignIN.this,Create_Account.class);
                startActivity(intent);
            }
        });


    }
}