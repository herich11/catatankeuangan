package com.catatankeuangan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDescription, editTextAmount;
    private RadioGroup radioGroupType;
    private ArrayList<String> transactionsList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDescription = findViewById(R.id.editTextDescription);
        editTextAmount = findViewById(R.id.editTextAmount);
        radioGroupType = findViewById(R.id.radioGroupType);
        ListView listViewTransactions = findViewById(R.id.listViewTransactions);

        transactionsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, transactionsList);
        listViewTransactions.setAdapter(adapter);
    }

    public void addTransaction(View view) {
        String description = editTextDescription.getText().toString().trim();
        String amountStr = editTextAmount.getText().toString().trim();
        if (description.isEmpty() || amountStr.isEmpty()) {
            return;
        }

        double amount = Double.parseDouble(amountStr);
        int selectedRadioButtonId = radioGroupType.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedRadioButtonId);
        String type = radioButton.getText().toString();

        String transaction = type + ": " + description + " - Rp " + amount;
        transactionsList.add(transaction);
        adapter.notifyDataSetChanged();

        editTextDescription.setText("");
        editTextAmount.setText("");
    }
}
