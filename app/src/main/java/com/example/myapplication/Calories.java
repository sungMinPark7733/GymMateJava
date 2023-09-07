package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;

public class Calories extends AppCompatActivity {

    private Button btn_addFood, btn_addWeight, btn_cancel1, btn_cancel2;
    private TextView tv_calDisplay, selectedValueTextView;
    private PieChart piechart;
    private TextView tv_protein, tv_carbs, tv_fat;
    private CardView cv_weightpanel, cv_foodpanel;
    private NumberPicker numberPicker;
    private Spinner spinnerAdd, spinnerSize;
    private ListView lv_foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        tv_calDisplay = findViewById(R.id.tv_calDisplay);
        btn_addFood = findViewById(R.id.btn_addFood);
        btn_addWeight = findViewById(R.id.btn_addWeight);
        btn_cancel1 = findViewById(R.id.btn_cancel1);
        btn_cancel2 = findViewById(R.id.btn_cancel2);
        piechart = findViewById(R.id.piechart);
        tv_protein = findViewById(R.id.tv_protein);
        tv_carbs = findViewById(R.id.tv_carbs);
        tv_fat = findViewById(R.id.tv_fat);
        cv_weightpanel = findViewById(R.id.cv_weightpanel);
        cv_foodpanel = findViewById(R.id.cv_foodpanel);
        numberPicker = findViewById(R.id.numberPicker);
        selectedValueTextView = findViewById(R.id.selectedValueTextView);
//        spinnerAdd = findViewById(R.id.spinnerAdd);
//        spinnerSize = findViewById(R.id.spinnerSize);
        lv_foodList = findViewById(R.id.lv_foodList);


        String gender = getIntent().getStringExtra("gender");
        String age = getIntent().getStringExtra("age");
        String selectedGoals = getIntent().getStringExtra("selectedGoals");

//        // DB Helper
//        FoodDatabaseHelper dbHelper = new FoodDatabaseHelper(Calories.this);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        String query = "SELECT * FROM foods;";
//        Cursor cursor = db.rawQuery(query, null);
//
//
//        int idIndex = cursor.getColumnIndex(FoodDatabaseHelper.COLUMN_ID);
//        int foodNameIndex = cursor.getColumnIndex(FoodDatabaseHelper.COLUMN_FOOD_NAME);
//        int caloriesIndex = cursor.getColumnIndex(FoodDatabaseHelper.COLUMN_FOOD_CALORIES);
//        int portionIndex = cursor.getColumnIndex(FoodDatabaseHelper.COLUMN_FOOD_PORTION);
//        int proteinIndex = cursor.getColumnIndex(FoodDatabaseHelper.COLUMN_FOOD_PROTEIN);
//        int carbsIndex = cursor.getColumnIndex(FoodDatabaseHelper.COLUMN_FOOD_CARBS);
//        int fatIndex = cursor.getColumnIndex(FoodDatabaseHelper.COLUMN_FOOD_FAT);
//
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(idIndex);
//            String foodName = cursor.getString(foodNameIndex);
//            int calories = cursor.getInt(caloriesIndex);
//            String portion = cursor.getString(portionIndex);
//            float protein = cursor.getFloat(proteinIndex);
//            float carbs = cursor.getFloat(carbsIndex);
//            float fat = cursor.getFloat(fatIndex);
//        }
//        // Close the cursor and database when done
//        cursor.close();
//        db.close();


        if (gender != null && age != null && selectedGoals != null) {
            int intAge = Integer.parseInt(age);
            double calorieValue = 0.0;

            if (gender.equals("Male")) {
                if (intAge <= 14) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 2500 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 2500 - 500;
                    }
                } else if (intAge <= 18) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 3000 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 3000 - 500;
                    }
                } else if (intAge <= 24) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 2900 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 2900 - 500;
                    }
                } else if (intAge <= 50) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 2900 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 2900 - 500;
                    }
                } else if (intAge >= 51) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 3000 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 3000 - 500;
                    }
                }
            } else {
                if (intAge <= 14) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 2200 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 2200 - 500;
                    }
                } else if (intAge <= 18) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 2200 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 2200 - 500;
                    }
                } else if (intAge <= 24) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 2200 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 2200 - 500;
                    }
                } else if (intAge <= 50) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 2200 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 2200 - 500;
                    }
                } else if (intAge >= 51) {
                    if (selectedGoals.equals("Building muscle")) {
                        calorieValue = 1900 * 1.1;
                    } else if (selectedGoals.equals("Losing weight")) {
                        calorieValue = 1900 - 500;
                    }
                }
            }

            // Format the calorieValue for display
            String calorieDisplay = String.format("%.2f cal", calorieValue);
            tv_calDisplay.setText(calorieDisplay);

            // Calculating nutrition intake
            tv_protein.setText("30% " + 0.3 * calorieValue + " cal Protein");
            tv_carbs.setText("40% " + 0.4 * calorieValue + " cal Carbs");
            tv_fat.setText("30% " + 0.3 * calorieValue + " cal Fat");

            // Set the data and color to the pie chart
            piechart.addPieSlice(
                    new PieModel(
                            "Protein",
                            30,
                            Color.parseColor("#FFA726")));
            piechart.addPieSlice(
                    new PieModel(
                            "Carbs",
                            40,
                            Color.parseColor("#66BB6A")));
            piechart.addPieSlice(
                    new PieModel(
                            "Fat",
                            30,
                            Color.parseColor("#EF5350")));

            // To animate the pie chart
            piechart.startAnimation();
        }


        // Add click listeners to your buttons
        btn_addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the foodpanel
                if (cv_foodpanel.getVisibility() == View.VISIBLE) {
                    cv_foodpanel.setVisibility(View.GONE);
                } else {
                    cv_foodpanel.setVisibility(View.VISIBLE);
//                    FoodDatabaseHelper foodDatabaseHelper = new FoodDatabaseHelper(Calories.this);
//                    List<FoodModel> everyfood = foodDatabaseHelper.getEveryfood();
//
//                    ArrayAdapter foodArrayAdapter = new ArrayAdapter<FoodModel>(Calories.this, android.R.layout.simple_list_item_1, everyfood);
//                    lv_foodList.setAdapter(foodArrayAdapter);
                }
            }
        });

        btn_addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the weightpanel
                if (cv_weightpanel.getVisibility() == View.VISIBLE) {
                    cv_weightpanel.setVisibility(View.GONE);
                } else {
                    cv_weightpanel.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the weightpanel
                if (cv_weightpanel.getVisibility() == View.VISIBLE) {
                    cv_weightpanel.setVisibility(View.GONE);
                }
            }
        });

        btn_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the foodpanel
                if (cv_foodpanel.getVisibility() == View.VISIBLE) {
                    cv_foodpanel.setVisibility(View.GONE);
                }
            }
        });
    }
}
