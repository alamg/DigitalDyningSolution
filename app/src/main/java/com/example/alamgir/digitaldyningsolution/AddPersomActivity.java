package com.example.alamgir.digitaldyningsolution;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alamgir.digitaldyningsolution.database.DBHelper;
import com.example.alamgir.digitaldyningsolution.model.Person;

/**
 * Created by Alamgir on 3/22/2018.
 */

public class AddPersomActivity extends AppCompatActivity {
private EditText etName, etmeal,etMoney, etTotalCost;
private Button btnSave,next;
private DBHelper dbHelper;
Person person;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_persion);
        etName=findViewById(R.id.etName);
        etmeal=findViewById(R.id.etMeal);
        etMoney=findViewById(R.id.etMoney);
        btnSave=findViewById(R.id.btnSave);
        next=findViewById(R.id.btnnext);
        dbHelper=new DBHelper(AddPersomActivity.this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddPersomActivity.this,AddCostActivity.class));
            }
        });

         person= (Person) getIntent().getSerializableExtra("person");
         btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                      addPerson();
             }
         });

    }

    private void addPerson() {
       String name=etName.getText().toString().trim();
       String strmeal=etmeal.getText().toString().trim();
       String strmoney=etMoney.getText().toString().trim();
       double meal=Double.parseDouble(strmeal);
       double money=Double.parseDouble(strmoney);


        Person person=new Person(name,meal,money);
         long inserted=dbHelper.insertPerson(person);
         if (inserted>-1)
         {
             Toast.makeText(getApplicationContext(), "Insert Success", Toast.LENGTH_SHORT).show();
             startActivity(new Intent(AddPersomActivity.this,MainActivity.class));
         }
         else {
             Toast.makeText(getApplicationContext(),"Insert not secces",Toast.LENGTH_LONG).show();
         }


    }
}
