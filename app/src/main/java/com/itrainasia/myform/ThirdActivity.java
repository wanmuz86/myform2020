package com.itrainasia.myform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    EditText returnEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        returnEditText = findViewById(R.id.returnEditText);
    }

    public void returnMessage(View view) {
        Intent intent = new Intent();
        intent.putExtra("return",
                returnEditText.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}
