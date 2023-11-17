package com.example.sonic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginbtn);
        next = findViewById(R.id.createacc);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = username.getText().toString();
                String p = password.getText().toString();

                // Check credentials in the SQLite database
                if (checkCredentials(s, p)) {
                    Toast.makeText(SignIN.this, "Wait a second your Home page is loading", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignIN.this, Home_page.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignIN.this, "User Id and password do not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIN.this, Create_Account.class);
                startActivity(intent);
            }
        });
    }

    // Method to check credentials in the SQLite database
    private boolean checkCredentials(String username, String password) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {DatabaseHelper.COLUMN_ID};
        String selection = DatabaseHelper.COLUMN_USERNAME + "=? AND " + DatabaseHelper.COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }
}
