package edu.psu.sweng888.sweng888practice4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectedResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private SelectedProductAdapter selectedProductAdapter;
    private Button emailButton;
    private ArrayList<Products> productList;
    boolean successfulEmail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        recyclerView = findViewById(R.id.recyclerView);

        emailButton = findViewById(R.id.emailButton);


        productList = getIntent().getParcelableArrayListExtra("data");


        selectedProductAdapter = new SelectedProductAdapter(productList);

        recyclerView.setAdapter(selectedProductAdapter);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
                selectedProductAdapter.removeAll(productList);
                successfulEmail = true;

            }
        });

    }

        @Override
        protected void onResume() {
            super.onResume();

            if(successfulEmail){
                Toast.makeText(SelectedResultsActivity.this,"Email sent successfully",Toast.LENGTH_SHORT).show();
            }
        }

        public void sendEmail() {
            String[] TO = {"sweng888mobileapps@gmail.com"}; // Email address where you want to send the email
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Selected Products"); // Email subject
            emailIntent.putExtra(Intent.EXTRA_TEXT, parseProducts(productList)); // Email message body

            emailIntent.setType("message/rfc822");
            startActivity(Intent.createChooser(emailIntent,"Choose email client:"));
        }

        public String parseProducts(ArrayList<Products> products) {
            String productDetails = "";
            for (Products p : products) {
                productDetails += p.getName() + ": " + p.getDescription() + "\n";
            }

            return productDetails;
        }
}
