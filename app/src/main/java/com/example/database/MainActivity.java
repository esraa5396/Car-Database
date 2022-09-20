package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et_carModel,et_carColor,et_carDistancePerLitter;
    Button btn_save,btn_restore;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_carModel=findViewById(R.id.et_carModel);
        et_carColor=findViewById(R.id.et_carColor);
        et_carDistancePerLitter=findViewById(R.id.et_carDistancePerLitter);
        btn_save=findViewById(R.id.btn_save);
        btn_restore=findViewById(R.id.btn_res);

        db=new MyDatabase(this);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String model=et_carModel.getText().toString();
                String color=et_carColor.getText().toString();
                double dpl=Double.parseDouble(et_carDistancePerLitter.getText().toString());

                Car c = new Car(model,color,dpl);
                boolean res = db.insertCar(c);
                if(res)
                    Toast.makeText(MainActivity.this,"Success Insertion",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Error occurred",Toast.LENGTH_LONG).show();

            }
        });

        btn_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Car> cars = db.getAllCars();
                for (Car c:cars){
                    Log.d("car # "+c.getId(),c.getModel());
                }
                // Toast.makeText(MainActivity.this,"#"+cars.size(),Toast.LENGTH_LONG).show();
//                long carsCount=db.getCarsCount();
//                Toast.makeText(MainActivity.this,"Count is: "+carsCount,Toast.LENGTH_LONG).show();

            }
        });

    }
}