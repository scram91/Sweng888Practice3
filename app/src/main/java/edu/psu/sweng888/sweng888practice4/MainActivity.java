package edu.psu.sweng888.sweng888practice4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //create various view items
    private RecyclerView mRecyclerView;
    private ProductDatabaseHelper mProductDatabaseHelper;
    private ProductAdapter mProductAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instantiate and assign values to view items
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mProductDatabaseHelper = new ProductDatabaseHelper(this);
        mButton = findViewById(R.id.button);

        List<Products> products = mProductDatabaseHelper.getAllProducts();
        //Populate the products list from database
        if(mProductDatabaseHelper.isEmpty()) {
            mProductDatabaseHelper.populateProductDatabase();
        }
        //create adapter
        mProductAdapter = new ProductAdapter(products);
        mRecyclerView.setAdapter(mProductAdapter);
        mRecyclerView.addItemDecoration(new SpaceItemDecorator(4));
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //Select Button click listener
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectedResultsActivity.class);
                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) mProductAdapter.selectedItems);
                startActivity(intent);
            }
        });
    }
    //Simple spacing decorator
    public class SpaceItemDecorator extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;
        public SpaceItemDecorator(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }
}