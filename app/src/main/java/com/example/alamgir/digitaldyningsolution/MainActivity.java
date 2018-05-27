package com.example.alamgir.digitaldyningsolution;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alamgir.digitaldyningsolution.database.DBHelper;
import com.example.alamgir.digitaldyningsolution.model.Cost;
import com.example.alamgir.digitaldyningsolution.model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private Toolbar toolbar;
    private FloatingActionButton adPerson, btnshow;
    private ListView lv;
    private DBHelper dbHelper;
    private Person person;
   private ArrayList<Person> personlist;
   private double countmeal=0,countmoney=0,mealrate=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        adPerson=findViewById(R.id.addp);
        lv=findViewById(R.id.personlist);
        dbHelper=new DBHelper(MainActivity.this);
        adPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddPersomActivity.class));
            }
        });

        showPersonDetails();

    }

    private void showPersonDetails() {

        personlist=new ArrayList<>();
        personlist=dbHelper.personInfo();

        for (Person person:personlist)
        {
             countmeal=countmeal+person.getPersionMeal();
            countmoney=countmoney+person.getPersonMoney();
        }
        final Cost cost=dbHelper.getTotalcost();
        mealrate=cost.getTotalCost()/countmeal;


        ArrayAdapter<Person> adapter=new ArrayAdapter<Person>(MainActivity.this,R.layout.shoedetails,personlist){
            @NonNull
            @Override
            public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
                if (v==null)
                {
                    v=getLayoutInflater().inflate(R.layout.shoedetails,parent,false);
                    TextView tvname=v.findViewById(R.id.tvname);
                    TextView tvmeal=v.findViewById(R.id.tvmeal);
                    TextView tvmoney=v.findViewById(R.id.tvmoney);
                    TextView tvidvCost=v.findViewById(R.id.idvCost);
                    Button btnedit=v.findViewById(R.id.edit);
                    final Person person=personlist.get(position);
                    person.setTotalMoney(countmoney);
                    person.setMealRate(mealrate);
                    person.setTotalMeal(countmeal);
                    tvname.setText(person.getPersonName());
                    tvmeal.setText("Meal = "+person.getPersionMeal());
                    tvmoney.setText("Money = "+person.getPersonMoney());
                    tvidvCost.setText("total cost="+person.getIndivisualCost());
                    btnedit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            edit(person);
                        }


                    });


                }
                return v;
            }
        };
        lv.setAdapter(adapter);

    }

    private void edit(final Person person) {

        AlertDialog.Builder  mydialog= new AlertDialog.Builder(MainActivity.this);
        View v=getLayoutInflater().inflate(R.layout.add_persion,null);
        final EditText etname=v.findViewById(R.id.etName);
        final EditText etmeal=v.findViewById(R.id.etMeal);
        final EditText etmoney=v.findViewById(R.id.etMoney);
        Button btnsave=v.findViewById(R.id.btnSave);
        mydialog.setView(v);
        AlertDialog dialog=mydialog.create();
        dialog.show();
        etname.setText(person.getPersonName());
        etmeal.setText(""+person.getPersionMeal());
        etmoney.setText(""+person.getPersonMoney());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etname.getText().toString().trim();
                String strmeal=etmeal.getText().toString().trim();
                String strmoney=etmoney.getText().toString().trim();
                double meal=Double.parseDouble(strmeal);
                double money=Double.parseDouble(strmoney);

                Person person1=new Person(person.getId(),name,meal,money);
                dbHelper.updatePersonData(person1);
                Toast.makeText(getApplicationContext(),"Update success",Toast.LENGTH_LONG).show();

            }
        });
    }





}

