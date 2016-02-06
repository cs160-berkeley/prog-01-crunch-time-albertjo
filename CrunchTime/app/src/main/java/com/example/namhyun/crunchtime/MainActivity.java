package com.example.namhyun.crunchtime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private CalorieCounter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getEquivalentWorkout(View v) {
        double calories = this.getTotalCaloriesBurned();
        if (calories > 0) {
            Boolean pushupsUsed, situpsUsed, jogged, jumpingJacksUsed;
            // check if push ups used
            CheckBox pushUpsCheckButton = (CheckBox) findViewById(R.id.pushupRadioButton);
            pushupsUsed = pushUpsCheckButton.isChecked() ;
            // check if sit ups used
            CheckBox situpsCheckButton = (CheckBox) findViewById(R.id.situpsRadioButton);
            situpsUsed = situpsCheckButton.isChecked();
            // check if jumping jacks used
            CheckBox jumpingJacksCheckButton = (CheckBox) findViewById(R.id.radioButton2);
            jumpingJacksUsed = jumpingJacksCheckButton.isChecked();
            // check if jogged
            CheckBox joggingCheckButton = (CheckBox) findViewById(R.id.radioButton3);
            jogged = joggingCheckButton.isChecked();

            if (!pushupsUsed) {
                int numPushUps = this.counter.getRepsOfPushUpsToReachCalories(calories);
                this.showAlert("To burn "+Math.round(calories)+ " calories", "Do "+ numPushUps+
                                    " push ups");
                return;
            }
            if (!situpsUsed) {
                int numSitUps = this.counter.getRepsOfSitUpsToReachCalories(calories);
                this.showAlert("To burn "+Math.round(calories)+ " calories", "Do "+ numSitUps+
                        " sit ups");
                return;
            }
            if (!jumpingJacksUsed) {
                double minsJumpingJacks = this.counter.getMinutesOfJumpingJacksToReachCalories(calories);
                minsJumpingJacks = this.round(minsJumpingJacks, 1);

                this.showAlert("To burn "+Math.round(calories)+ " calories", "Do "+ minsJumpingJacks+
                        " minutes of jumping jacks");
                return;
            }

            if (!jogged) {
                double minsJog = this.counter.getMinutesOfJoggingToReachCalories(calories);
                minsJog = this.round(minsJog, 1);

                this.showAlert("To burn "+Math.round(calories)+ " calories", "Do "+ minsJog+
                        " minutes of jogging");
                return;
            }
        }
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public double getTotalCaloriesBurned() {
        EditText weightEditText = (EditText) findViewById(R.id.weightText);
        String weightStr = weightEditText.getText().toString();
        double weight;
        double calories = 0;

        if (weightStr.trim().length() == 0) {
            //show alert
            this.showAlert("Error", "Please enter your weight");
            return -1.0;
        }
        weight = Double.parseDouble(weightStr);
        this.counter = new CalorieCounter(weight);

        CheckBox pushupsCheckButton = (CheckBox) findViewById(R.id.pushupRadioButton);
        if (pushupsCheckButton.isChecked()) {
            ToggleButton pushUpsRepsMinsToggleButton = (ToggleButton) findViewById(R.id.pushupsRepsMinsToggleButton);
            Boolean repsUsed = !pushUpsRepsMinsToggleButton.isChecked();
            EditText pushupsEditText = (EditText) findViewById(R.id.pushupAmt);
            double pushAmt = extractDoubleFromEditText(pushupsEditText);
            calories += counter.pushUpsCaloriesBurned(pushAmt, repsUsed);

        }

        CheckBox situpsCheckButton = (CheckBox) findViewById(R.id.situpsRadioButton);
        if (situpsCheckButton.isChecked()) {
            ToggleButton situpsToggleButton = (ToggleButton) findViewById(R.id.situpsToggleButton);
            Boolean repsUsed = !situpsToggleButton.isChecked();
            EditText situpsEditText = (EditText) findViewById(R.id.situpsEditText);
            double situpsAmt = extractDoubleFromEditText(situpsEditText);
            calories += counter.situpsCaloriesBurned(situpsAmt, repsUsed);
        }

        CheckBox jumpingJacksCheckButton = (CheckBox) findViewById(R.id.radioButton2);
        if (jumpingJacksCheckButton.isChecked()) {
            ToggleButton jumpingJacksToggleButton = (ToggleButton) findViewById(R.id.toggleButton);
            Boolean repsUsed = !jumpingJacksToggleButton.isChecked();
            EditText jumpingJacksEditText = (EditText) findViewById(R.id.editText2);
            double jumpingJacksAmt = extractDoubleFromEditText(jumpingJacksEditText);
            calories += counter.jumpingJacks(jumpingJacksAmt, repsUsed);
        }

        CheckBox joggingCheckButton = (CheckBox) findViewById(R.id.radioButton3);
        if (joggingCheckButton.isChecked()) {
            ToggleButton joggingToggleButton = (ToggleButton) findViewById(R.id.toggleButton2);
            Boolean minsUsed = joggingToggleButton.isChecked();
            EditText joggingEditText = (EditText) findViewById(R.id.editText3);
            double joggingAmt = extractDoubleFromEditText(joggingEditText);
            calories += counter.joggingCaloriesBurned(joggingAmt, minsUsed);
        }
        return calories;
    }

    public void calculateTotalCaloriesBurned(View v) {

        double calories = this.getTotalCaloriesBurned();
        if (calories < 0) {
            return;
        }
        // display calories burned as an alert
        showAlert("", "You burned " + Math.round(calories) + " calories");
    }

    private void showAlert(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /*
    This method checks whether an edit text is empty or not
     */
    private Boolean isEditTextEmpty(EditText editText) {
        String str = editText.getText().toString();
        return (str.trim().length() == 0);
    }

    private Double extractDoubleFromEditText(EditText editText) {
        String numStr = editText.getText().toString();
        return Double.parseDouble(numStr);
    }
}
