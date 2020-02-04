package com.itrainasia.myform;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText nameEditText, emailEditText, phoneEditText, cellEditText, messageEditText;
Spinner spinner;
TextView dateTextView;
Button button;

AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        cellEditText = findViewById(R.id.cellEditText);
        messageEditText = findViewById(R.id.messageEditText);
        spinner = findViewById(R.id.spinner);
        dateTextView = findViewById(R.id.dateTextView);
        autoCompleteTextView = findViewById(R.id.autocompleteTextView);
        ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(
                MainActivity.this,
                R.array.department,
                android.R.layout.simple_spinner_item
                );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> acAdapter
                = ArrayAdapter.createFromResource(
                        MainActivity.this,
                R.array.states,
                android.R.layout.simple_list_item_1);
        acAdapter
                .setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(acAdapter);
button = findViewById(R.id.openList);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(
                MainActivity.this,
                ListActivity.class
        );
                startActivity(intent);
    }
});

    }

    public void sendPressed(View view) {

        String message = "User enter "+nameEditText.getText().toString()
                + " \n Email: "+emailEditText.getText().toString()
                + " \n Phone "+phoneEditText.getText().toString()
                + " \n Cell "+cellEditText.getText().toString()
                + " \n Message "+messageEditText.getText().toString();
        Toast.makeText(MainActivity.this,
                message,
                Toast.LENGTH_LONG).show();
    }

    public void dialogPressed(View view) {

        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder( MainActivity.this);
        alertDialogBuilder.setTitle("Hello World")
                .setMessage("Welcome to my app")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void openNewPage(View view) {
        Intent intent = new Intent(
                MainActivity.this,
                SecondActivity.class
        );
        intent.putExtra("name",
                nameEditText.getText().toString());
        intent.putExtra("email",
                emailEditText.getText().toString());
        intent.putExtra("phone",
                phoneEditText.getText().toString());
        intent.putExtra("cell",
                cellEditText.getText().toString());
        intent.putExtra("message",
                messageEditText.getText().toString());

        startActivity(intent);
    }

    public void openAnotherPage(View view) {
        Intent intent = new Intent(
                MainActivity.this,
                ThirdActivity.class
        );
        startActivityForResult(intent,
                1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK){
            String message =
                    data.getStringExtra("return");
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,
                menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about_us:
                Intent intent =
                        new Intent(MainActivity.this, WebActivity.class);
                startActivity(intent);
                break;
            case R.id.email_us:

                Uri email = Uri.parse(
                        "mailto:info@itrain.com.my");
                Intent emailIntent = new Intent(
                        Intent.ACTION_SENDTO,
                        email);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                        "Course feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT,
                        "Here is my feedback");
                startActivity(emailIntent);
                break;
            case R.id.contact_us:
                Uri number = Uri.parse("tel:0321661879");
                Intent callIntent = new
                        Intent(Intent.ACTION_DIAL,number);
                startActivity(callIntent);


                break;
            case R.id.exit:
                finish();
                break;
            case R.id.share:
                Intent intentShare = new Intent(
                        Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_SUBJECT,
                        "New app " +
                                "Check it out");

                intentShare.putExtra(Intent.EXTRA_TEXT,
                        "I found this cool app. " +
                                "Check it out");
               // intentShare.setPackage(
                 //       "com.facebook.katana");
                startActivity(intentShare);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void selectDatePressed(View view) {
        DialogFragment newFragment =
                new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                "datePicker");
    }

    public void processDatePickerResult(int year, int month, int dayOfMonth) {
    String monthString = Integer.toString(month+1);
    String dayString = Integer.toString(dayOfMonth);
    String yearString = Integer.toString(year);
    String dateToShow = dayString+"/"+monthString+"/"+yearString;
    dateTextView.setText(dateToShow);

    }
}
