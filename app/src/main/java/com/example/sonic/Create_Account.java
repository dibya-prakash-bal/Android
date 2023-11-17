package com.example.sonic;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    private EditText password;
    private EditText confirm_password;
    private TextView back;

    private Button submit;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        phoneno = findViewById(R.id.phone);
        email = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        back = findViewById(R.id.createacc);
        submit = findViewById(R.id.loginbtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Create_Account.this, SignIN.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = username.getText().toString();
                String ph = phoneno.getText().toString();
                String e = email.getText().toString();
                String p = password.getText().toString();
                String cp = confirm_password.getText().toString();

                if (p.equals(cp)) {
                    // Save the user information in the SQLite database
                    saveUser(u, ph, e, p);

                    Toast.makeText(Create_Account.this, "Your account has been Created go to the sign in page", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Create_Account.this,SignIN.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(Create_Account.this, "Your password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to save user information in the SQLite database
    private void saveUser(String username, String phoneno, String email, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PHONENO, phoneno);
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        // Insert the values into the "users" table
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }
}
