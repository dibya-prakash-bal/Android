package com.example.sonic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Create_Account extends AppCompatActivity {

    private EditText username;
    private EditText phoneno;
    private EditText email;
    private  EditText password;
    private EditText confoirm_password;
    private TextView back;


    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username=findViewById(R.id.username);
        phoneno=findViewById(R.id.phone);
        email=findViewById(R.id.mail);
        password=findViewById(R.id.password);
        confoirm_password=findViewById(R.id.confirm_password);
        back=findViewById(R.id.createacc);
        submit=findViewById(R.id.loginbtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Create_Account.this,SignIN.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = confoirm_password.getText().toString();
                String p = password.getText().toString();
                if (p.equals(s)) { // Compare the text inside EditText fields
                    Toast.makeText(Create_Account.this, "Your account has been Created", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Create_Account.this,Home_page.class);
                    startActivity(intent);
                } else {
                 Toast.makeText(Create_Account.this, "your password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}