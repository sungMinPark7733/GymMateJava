package com.example.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

public class Calories extends AppCompatActivity {

    private Button btn_addFood, btn_addWeight, btn_weight_update_cancel, btn_food_add_cancel, btn_food_add, btn_clear, btn_delete, btn_weight_update;
    private TextView tv_calDisplay, tv_protein, tv_carbs, tv_fat, tv_weight;
    private PieChart piechart;
    private CardView cv_weightpanel, cv_foodpanel;
    private NumberPicker numberPicker;
    private ListView lv_foodList, lv_latestList;
    private String gender, age, weight, selectedGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);


        tv_calDisplay = findViewById(R.id.tv_calDisplay);
        btn_addFood = findViewById(R.id.btn_addFood);
        btn_addWeight = findViewById(R.id.btn_addWeight);
        btn_weight_update_cancel = findViewById(R.id.btn_weight_update_cancel);
        btn_food_add_cancel = findViewById(R.id.btn_food_add_cancel);
        btn_food_add = findViewById(R.id.btn_food_add);
        btn_clear = findViewById(R.id.btn_clear);
        btn_delete = findViewById(R.id.btn_delete);
        btn_weight_update = findViewById(R.id.btn_weight_update);
        piechart = findViewById(R.id.piechart);
        tv_protein = findViewById(R.id.tv_protein);
        tv_carbs = findViewById(R.id.tv_carbs);
        tv_fat = findViewById(R.id.tv_fat);
        tv_weight = findViewById(R.id.tv_weight);
        cv_weightpanel = findViewById(R.id.cv_weightpanel);
        cv_foodpanel = findViewById(R.id.cv_foodpanel);
        numberPicker = findViewById(R.id.numberPicker);
        lv_foodList = findViewById(R.id.lv_foodList);
        lv_latestList = findViewById(R.id.lv_latestList);


        final ArrayList<FoodModel> selectedFoodList = new ArrayList<>();

        UserModel user = (UserModel) getIntent().getSerializableExtra("user");

        if (user != null) {
            // Data is coming from the UserModel object
            gender = user.getGender();
            age = String.valueOf(user.getAge());
            weight = String.valueOf(user.getWeight());
            selectedGoals = user.getGoal();
        } else {
            // Data is coming from getIntent() method
            gender = getIntent().getStringExtra("gender");
            age = getIntent().getStringExtra("age");
            weight = getIntent().getStringExtra("weight");
            selectedGoals = getIntent().getStringExtra("selectedGoals");
        }
        tv_weight.setText(weight + "kg");

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);

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
            tv_protein.setText("10~30% " + calculateValue(calorieValue, 10, 4) + " ~ " + calculateValue(calorieValue, 30, 4) + " grams Protein");
            tv_carbs.setText("45~65% " + calculateValue(calorieValue, 45, 4) + " ~ " + calculateValue(calorieValue, 65, 4) + " grams Carbs");
            if (intAge >= 51) {
                tv_fat.setText("10~30% " + calculateValue(calorieValue, 10, 9) * 2 + " ~ " + calculateValue(calorieValue, 30, 9) + " grams Fat");
            } else {
                tv_fat.setText("10~30% " + calculateValue(calorieValue, 10, 9) + " ~ " + calculateValue(calorieValue, 30, 9) + " grams Fat");
            }

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
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(Calories.this);
                    List<FoodModel> everyfood = dataBaseHelper.getEveryfood();

                    ArrayAdapter userArrayAdapter = new ArrayAdapter<FoodModel>(Calories.this, android.R.layout.simple_list_item_multiple_choice, everyfood);
                    lv_foodList.setAdapter(userArrayAdapter);
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

        btn_weight_update_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the weightpanel
                if (cv_weightpanel.getVisibility() == View.VISIBLE) {
                    cv_weightpanel.setVisibility(View.GONE);
                }
            }
        });

        btn_food_add_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the foodpanel
                if (cv_foodpanel.getVisibility() == View.VISIBLE) {
                    cv_foodpanel.setVisibility(View.GONE);
                }
            }
        });
        btn_food_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Loop through the items in lv_foodList
                int itemCount = lv_foodList.getCount();
                for (int i = 0; i < itemCount; i++) {
                    if (lv_foodList.isItemChecked(i)) {
                        // If the item is checked, add it to the selectedFoodList
                        selectedFoodList.add((FoodModel) lv_foodList.getItemAtPosition(i));
                    }
                }

                // Create an adapter for lv_latestList with the selectedFoodList
                ArrayAdapter<FoodModel> latestListAdapter = new ArrayAdapter<>(Calories.this,
                        android.R.layout.simple_list_item_multiple_choice, selectedFoodList);

                // Set the adapter for lv_latestList
                lv_latestList.setAdapter(latestListAdapter);

                // Notify the adapter that the data has changed
                latestListAdapter.notifyDataSetChanged();

                // Toggle the visibility of the foodpanel
                cv_foodpanel.setVisibility(View.GONE);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a list to store the items to be deleted
                List<FoodModel> itemsToDelete = new ArrayList<>();

                // Loop through the items in lv_latestList
                int itemCount = lv_latestList.getCount();
                for (int i = 0; i < itemCount; i++) {
                    if (lv_latestList.isItemChecked(i)) {
                        // If the item is checked, add it to the itemsToDelete
                        itemsToDelete.add((FoodModel) lv_latestList.getItemAtPosition(i));
                    }
                }

                // Remove the selected items from selectedFoodList
                selectedFoodList.removeAll(itemsToDelete);

                // Notify the adapter that the data has changed
                ((ArrayAdapter<FoodModel>) lv_latestList.getAdapter()).notifyDataSetChanged();

                // Clear the checked state of items in the ListView
                for (int i = 0; i < itemCount; i++) {
                    lv_latestList.setItemChecked(i, false);
                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all items from selectedFoodList
                selectedFoodList.clear();

                // Notify the adapter that the data has changed
                ((ArrayAdapter<FoodModel>) lv_latestList.getAdapter()).notifyDataSetChanged();
            }
        });
        btn_weight_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected value from the NumberPicker
                int selectedValue = numberPicker.getValue();

                // Update the tv_weight TextView with the selected value
                tv_weight.setText(selectedValue + " kg");

                // Use userEmail (previously extracted from UserModel) for updating the weight
                String userEmail = user.getEmail(); // Retrieve the user's email

                DataBaseHelper dbHelper = new DataBaseHelper(Calories.this);
                boolean updateSuccess = dbHelper.updateUserWeight(userEmail, selectedValue);

                if (updateSuccess) {
                    // Handle success, e.g., show a toast message
                    Toast.makeText(Calories.this, "Weight updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure, e.g., show an error message
                    Toast.makeText(Calories.this, "Failed to update weight", Toast.LENGTH_SHORT).show();
                }

                // Hide the weight panel or perform any other necessary actions
                cv_weightpanel.setVisibility(View.GONE);
            }
        });

    }

    public static int calculateValue(double doubleValue, int rate, int divideby) {
        // Calculate the result
        int result = (int) ((rate * doubleValue) / 100 / divideby);

        return result;
    }
}
