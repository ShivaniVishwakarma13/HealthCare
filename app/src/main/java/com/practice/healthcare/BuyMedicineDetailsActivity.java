package com.practice.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {
    TextView tvPackageName,tvTotalCost;
    EditText edtxtDetails;
    Button btnBack,btnAddCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);
        tvPackageName=findViewById(R.id.BDPackageName);
        tvTotalCost=findViewById(R.id.BDTotalCost);
        btnAddCart=findViewById(R.id.BDaddToCart);
        btnBack=findViewById(R.id.BDbtnback);
        edtxtDetails=findViewById(R.id.editBDMultiline);

        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edtxtDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost :"+intent.getStringExtra("text3")+"/-");


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                String product=tvPackageName.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());


                if(db.checkCart(username,product)==1) {
                    Toast.makeText(BuyMedicineDetailsActivity.this, "product Already Added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(BuyMedicineDetailsActivity.this,"Record inserted to Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }

//                Toast.makeText(BuyMedicineDetailsActivity.this,"Record inserted to Cart",Toast.LENGTH_SHORT).show();
            }
        });





    }
}