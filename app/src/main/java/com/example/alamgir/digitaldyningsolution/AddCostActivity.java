package com.example.alamgir.digitaldyningsolution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alamgir.digitaldyningsolution.database.DBHelper;
import com.example.alamgir.digitaldyningsolution.model.Cost;

public class AddCostActivity extends AppCompatActivity {
    EditText etCost;
    Button save;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);
        etCost=findViewById(R.id.etcost);
        save=findViewById(R.id.save);
        dbHelper=new DBHelper(AddCostActivity.this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 saveCost();
            }
        });
    }

    private void saveCost() {
        String strcost=etCost.getText().toString();
        if(!strcost.isEmpty())
        {
            double totalcost=Double.parseDouble(strcost);
            Cost cost=new Cost(totalcost);
            long inserted=dbHelper.insertCost(cost);
            if (inserted>-1)
            {
                Toast.makeText(getApplicationContext(),"inserte successfull",Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddCostActivity.this,MainActivity.class));
            }
            else {
                Toast.makeText(getApplicationContext(),"inserte unsuccessfull",Toast.LENGTH_LONG).show();
            }

        }
        else {
            Toast.makeText(getApplicationContext(),"Data is empty",Toast.LENGTH_LONG).show();
        }


    }
}
