package edu.psu.sweng888.sweng888practice4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabaseHelper extends SQLiteOpenHelper {
    //initialize various database fields for future use
    private static final String DATABASE_NAME = "product_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PRODUCT = "products";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SELLER= "seller";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DESCRIPTION = "description";

    public ProductDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating the database table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCT);
    }

    //return the desired query to create the table
    private String createTable(){
        return "CREATE TABLE " + TABLE_PRODUCT + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, "+
                KEY_NAME + " TEXT," +
                KEY_SELLER + " TEXT," +
                KEY_DESCRIPTION + " TEXT," +
                KEY_PRICE + " TEXT" +
                ")";
    }

    //function to return a list of the products the user will select from
    public List<Products> getAllProducts() {
        List<Products> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " +TABLE_PRODUCT;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Products product = new Products(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return productList;
    }

    //check is the database is empty
    public boolean isEmpty() {
        boolean isEmpty = true;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + TABLE_PRODUCT, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            if (count > 0) {
                isEmpty = false;
            }
            cursor.close();
        }
        return isEmpty;
    }

    //populate the database with data the user is presented with
    public void populateProductDatabase(){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values = new ContentValues();
        values.put(KEY_NAME, "Wooden Raised Garden Bed");
        values.put(KEY_SELLER, "Home Depot");
        values.put(KEY_DESCRIPTION, "The raised garden bed with legs is made of cedar with non-paint, which features rot-resistant and high durability.");
        values.put(KEY_PRICE, "$86.80");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Hand Painted Outdoor Black Iron Plant Stand");
        values.put(KEY_SELLER, "Home Depot");
        values.put(KEY_DESCRIPTION, "2-layers of round pattern shelf adopts contemporary tray adorn design, supports 2 or 3 plants per tier.");
        values.put(KEY_PRICE, "$20.67");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Avocado Evergreen Tree");
        values.put(KEY_SELLER, "Home Depot");
        values.put(KEY_DESCRIPTION, "Wurtz \"Little Cado\" Avocado Tree, or Persea americana 'Wurtz Littlecado', is one of the smaller dwarf avocado trees, great for small spaces.");
        values.put(KEY_PRICE, "$89.99");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Full Spectrum LED Grow Light Kit");
        values.put(KEY_SELLER, "Lowe's");
        values.put(KEY_DESCRIPTION, "BoostGro 2FT LED full spectrum grow light is designed for anyone who wants to set up an indoor garden easily.");
        values.put(KEY_PRICE, "$19.98");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Stainless Steel Floorstanding Liquid Propane Patio Heater");
        values.put(KEY_SELLER, "Lowe's");
        values.put(KEY_DESCRIPTION, "Enjoy your outdoor patio space year-round with this propane patio heater.");
        values.put(KEY_PRICE, "$159.00");
        database.insert(TABLE_PRODUCT, null, values);


        values = new ContentValues();
        values.put(KEY_NAME, "Weave Self-Watering Round Planter, 14\"");
        values.put(KEY_SELLER, "Gardener's Supply Company");
        values.put(KEY_DESCRIPTION, "Textured to look like woven wicker, this indoor/outdoor container gives your plants a charming base.");
        values.put(KEY_PRICE, "$15.89");
        database.insert(TABLE_PRODUCT, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "BaltiSpore Lion's Mane Mushroom Grow Kit");
        values.put(KEY_SELLER, "Gardener's Supply Company");
        values.put(KEY_DESCRIPTION, "At Home mushroom grow kit is ready to fruit fresh Lions Mane in the comfort of your own home. Perfect for any experience level!");
        values.put(KEY_PRICE, "$29.99");
        database.insert(TABLE_PRODUCT, null, values);

        database.close();
    }

}
