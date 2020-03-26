package com.example.calculator_index;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    final int MENU_RESET_ID = 1;
    final int MENU_QUIT_ID = 2;

    double result = 0;
    float age_ext = 0;
    float weight_ext = 0;
    float height_ext = 0;
    double age = 0;
    double weight = 0;
    double height = 0;
    boolean ismale_ext = true;

    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (TextView) findViewById(R.id.text1);

        final Button buttonCalculateObj = findViewById(R.id.buttonCalculate);
        buttonCalculateObj.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                CalculateCalories();
            }
        });
        final Button buttonCalculate2Obj = findViewById(R.id.buttonCalculate2);
        buttonCalculate2Obj.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                CalculateCalories2();
            }
        });
    }

    public void CalculateCalories()
    {
        RadioButton rButtonMale = findViewById(R.id.isMale);
        RadioButton rButtonFemale = findViewById(R.id.isFemale);
        EditText eTextAge = findViewById(R.id.textAge);
        EditText eTextWeight = findViewById(R.id.textWeight);
        EditText eTextHeight = findViewById(R.id.textHeight);
        boolean ismale = false;
        boolean isfemale = false;
        double age = 0;
        double weight = 0;
        double height = 0;
        double intensity;
        try
        {
            ismale = rButtonMale.isChecked();
            isfemale = rButtonFemale.isChecked();
            if (preValidate())
            {
                age = Double.valueOf(eTextAge.getText().toString());
                weight = Double.valueOf(eTextWeight.getText().toString());
                height = Double.valueOf(eTextHeight.getText().toString());
                age_ext = (float) age;
                weight_ext = (float) weight;
                height_ext = (float) height;
            }
            else
            {
                return;
            }
            intensity = 1;
            if (ValidateInputFields(ismale, isfemale, age, weight, height))
            {
                ismale_ext = ismale;
                double result = getCalories(ismale, isfemale, age, weight, height);
                double BMI = getBMI(weight, height);
                String BMI_assertion_result = getBMItextAssertion(BMI);
                result *= intensity;
                Toast calories_toast = Toast.makeText(getApplicationContext(), "Количество калорий: " + result + "; Индекс массы тела: " + BMI + "кг/м^3 " + BMI_assertion_result, Toast.LENGTH_LONG);
                text1.setText("Количество калорий: " + result + "; Индекс массы тела: " + BMI + "кг/м^3 ;" + BMI_assertion_result);
                calories_toast.show();
            }
        }
        catch (Exception exc_1_e)
        {
            showErrorToast(exc_1_e.getMessage());
        }
    }

    public void CalculateCalories2()
    {
        RadioButton rButtonMale = findViewById(R.id.isMale);
        RadioButton rButtonFemale = findViewById(R.id.isFemale);
        EditText eTextAge = findViewById(R.id.textAge);
        EditText eTextWeight = findViewById(R.id.textWeight);
        EditText eTextHeight = findViewById(R.id.textHeight);
        boolean ismale = false;
        boolean isfemale = false;
        double age = 0;
        double weight = 0;
        double height = 0;
        double intensity;
        try
        {
            ismale = rButtonMale.isChecked();
            isfemale = rButtonFemale.isChecked();
            if (preValidate())
            {
                age = Double.valueOf(eTextAge.getText().toString());
                weight = Double.valueOf(eTextWeight.getText().toString());
                height = Double.valueOf(eTextHeight.getText().toString());
                age_ext = (float) age;
                weight_ext = (float) weight;
                height_ext = (float) height;
            }
            else
            {
                return;
            }
            intensity = 1;
            if (ValidateInputFields(ismale, isfemale, age, weight, height))
            {
                ismale_ext = ismale;
                double result = getCalories2(ismale, isfemale, age, weight, height);
                double BMI = getBMI(weight, height);
                String BMI_assertion_result = getBMItextAssertion(BMI);
                result *= intensity;
                Toast calories_toast = Toast.makeText(getApplicationContext(), "Количество калорий: " + result + "; Индекс массы тела: " + BMI + "кг/м^3 " + BMI_assertion_result, Toast.LENGTH_LONG);
                text1.setText("Количество калорий: " + result + "; Индекс массы тела: " + BMI + "кг/м^3 ;" + BMI_assertion_result);
                calories_toast.show();
            }
        }
        catch (Exception exc_1_e)
        {
            showErrorToast(exc_1_e.getMessage());
        }
    }

    public double getCalories(boolean ismale, boolean isfemale, double age, double weight, double height)
    {
        try
        {
            if (ismale == true && isfemale == false)
            {
                return (10 * weight + 6.25 * height - 5 * age + 5);
            }
            else if (ismale == false && isfemale == true)
            {
                return (10 * weight + 6.25 * height - 5 * age - 161);
            }
        }
        catch (Exception exc_1_e)
        {
            showErrorToast(exc_1_e.getMessage());
        }
        return 0;
    }

    public double getCalories2(boolean ismale, boolean isfemale, double age, double weight, double height)
    {
        try
        {
            if (ismale == true && isfemale == false)
            {
                return (66.5+(13.75*weight)+(5.003*height) - (6.775 * age));
            }
            else if (ismale == false && isfemale == true)
            {
                return (655.1 +(9.563*weight)+(1.85*height) - (4.676 * age));
            }
        }
        catch (Exception exc_1_e)
        {
            showErrorToast(exc_1_e.getMessage());
        }
        return 0;
    }

    public double getBMI(double weight, double height)
    {

        try
        {
            result = weight / ((height / 100) * (height / 100));
            String result_str = String.format("%.2f", result);
            result = Double.parseDouble(result_str);
        }
        catch (Exception exc_1_e)
        {
            showErrorToast(exc_1_e.getMessage());
        }
        return result;
    }

    public String getBMItextAssertion(double BMI)
    {
        try
        {
            if (BMI <= 16)
                return "Данное значение ИМТ соответствует: Выраженному дефициту массы тела";
            if (BMI > 16 && BMI < 18.5)
                return "Данное значение ИМТ соответствует: Недостаточной массе тела";
            if (BMI >= 18.5 && BMI < 25)
                return "Данное значение ИМТ соответствует: Нормальной массе тела";
            if (BMI >= 25 && BMI < 30)
                return "Данное значение ИМТ соответствует: Избыточной массе тела (предожирение)";
            if (BMI >= 30 && BMI < 35)
                return "Данное значение ИМТ соответствует: Ожирению 1-ой степени";
            if (BMI >= 35 && BMI < 40)
                return "Данное значение ИМТ соответствует: Ожирению 2-ой степени";
            if (BMI > 40)
                return "Данное значение ИМТ соответствует: Ожирению 3-ой степени";
        }
        catch (Exception exc_1_e)
        {
            showErrorToast(exc_1_e.getMessage());
        }
        return "НИКАКИХ ДАННЫХ";
    }

    public void showErrorToast(String input)
    {
        Toast error_toast = Toast.makeText(getApplicationContext(), input, Toast.LENGTH_LONG);
        error_toast.show();
    }

    public boolean ValidateInputFields(boolean ismale, boolean isfemale, double age, double weight, double height)
    {
        if (ismale == isfemale)
        {
            showErrorToast("Выберите пол.");
            return false;
        }
        if (age < 10 || age > 100)
        {
            showErrorToast("Возраст не может быть меньше 10 или боольше 100 для точного результата");
            return false;
        }
        if (weight < 20 || weight > 300)
        {
            showErrorToast("Вес не может быть меньше 20 или боольше 300 для точного результата");
            return false;
        }
        if (height < 50 || height > 250)
        {
            showErrorToast("Рост не может быть меньше 50 или боольше 250 для точного результата");
            return false;
        }
        return true;
    }

    public boolean preValidate()
    {
        EditText eTextAge = findViewById(R.id.textAge);
        EditText eTextWeight = findViewById(R.id.textWeight);
        EditText eTextHeight = findViewById(R.id.textHeight);
        if (eTextAge.getText().toString().isEmpty() || eTextWeight.getText().toString().isEmpty() || eTextHeight.getText().toString().isEmpty())
        {
            showErrorToast("Введите данные в поля.");
            return false;
        }
        return true;
    }

    public static final String PREFS_NAME = "SPO1PRefsFile";
    @Override
    protected void onPause()
    {
        super.onPause();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = settings.edit();

        editor.clear();
        try
        {
            RadioButton rButtonFemale = findViewById(R.id.isFemale);
            if (rButtonFemale.isChecked())
                ismale_ext = false;
            if (age_ext == 0)
            {
                EditText eTextAge = findViewById(R.id.textAge);
                age_ext = Float.valueOf(eTextAge.getText().toString());
            }
            if (weight_ext == 0)
            {
                EditText eTextWeight = findViewById(R.id.textWeight);
                weight_ext = Float.valueOf(eTextWeight.getText().toString());
            }
            if (height_ext == 0)
            {
                EditText eTextHeight = findViewById(R.id.textHeight);
                height_ext = Float.valueOf(eTextHeight.getText().toString());
            }
        }
        catch (Exception exc_1_e)
        {
        }
        editor.putFloat("ВОЗРАСТ", age_ext);
        editor.putFloat("ВЕС", weight_ext);
        editor.putFloat("РОСТ", height_ext);
        editor.putBoolean("МУЖЧИНА", ismale_ext);
        editor.commit();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,0);

        float get_age = prefs.getFloat("ВОЗРАСТ", 0);
        float get_weight = prefs.getFloat("ВЕС", 0);
        float get_height = prefs.getFloat("РОСТ", 0);
        boolean get_ismale = prefs.getBoolean("МУЖЧИНА", true);
        EditText eTextAge = findViewById(R.id.textAge);
        EditText eTextWeight = findViewById(R.id.textWeight);
        EditText eTextHeight = findViewById(R.id.textHeight);
        eTextAge.setText(String.valueOf(get_age), TextView.BufferType.EDITABLE);
        eTextWeight.setText(String.valueOf(get_weight), TextView.BufferType.EDITABLE);
        eTextHeight.setText(String.valueOf(get_height), TextView.BufferType.EDITABLE);
        RadioButton rButtonMale = findViewById(R.id.isMale);
        RadioButton rButtonFemale = findViewById(R.id.isFemale);
        if (get_ismale)
        {
            rButtonMale.setChecked(true);
            rButtonFemale.setChecked(false);
        }
        else
        {
            rButtonMale.setChecked(false);
            rButtonFemale.setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_RESET_ID, 0, "Reset");
        menu.add(0, MENU_QUIT_ID, 0, "Quit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case MENU_RESET_ID:
                EditText eTextAge = findViewById(R.id.textAge);
                EditText eTextWeight = findViewById(R.id.textWeight);
                EditText eTextHeight = findViewById(R.id.textHeight);
                eTextAge.setText("");
                eTextWeight.setText("");
                eTextHeight.setText("");
                text1.setText("");
                break;
            case MENU_QUIT_ID:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putDouble("count", result);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        result = savedInstanceState.getFloat("count");
        age_ext = savedInstanceState.getFloat("count1");
        weight_ext = savedInstanceState.getFloat("count2");
        height_ext = savedInstanceState.getFloat("count3");

        //Log.d(LOG_TAG, "onRestoreInstanceState");
        RadioButton rButtonMale = findViewById(R.id.isMale);
        RadioButton rButtonFemale = findViewById(R.id.isFemale);
        EditText eTextAge = findViewById(R.id.textAge);
        EditText eTextWeight = findViewById(R.id.textWeight);
        EditText eTextHeight = findViewById(R.id.textHeight);
        boolean ismale = false;
        boolean isfemale = false;
        double age = 0;
        double weight = 0;
        double height = 0;
        double intensity;
        try
        {
            ismale = rButtonMale.isChecked();
            isfemale = rButtonFemale.isChecked();
            if (preValidate())
            {
                age = Double.valueOf(eTextAge.getText().toString());
                weight = Double.valueOf(eTextWeight.getText().toString());
                height = Double.valueOf(eTextHeight.getText().toString());
                age_ext = (float) age;
                weight_ext = (float) weight;
                height_ext = (float) height;
            }
            else
            {
                return;
            }
            intensity = 1;
            if (ValidateInputFields(ismale, isfemale, age, weight, height))
            {
                ismale_ext = ismale;
                double result = getCalories(ismale, isfemale, age, weight, height);
                double BMI = getBMI(weight, height);
                String BMI_assertion_result = getBMItextAssertion(BMI);
                result *= intensity;
                Toast calories_toast = Toast.makeText(getApplicationContext(), "Количество калорий: " + result + "; Индекс массы тела: " + BMI + "кг/м^3 " + BMI_assertion_result, Toast.LENGTH_LONG);
                text1.setText("Количество калорий: " + result + "; Индекс массы тела: " + BMI + "кг/м^3 ;" + BMI_assertion_result);
                calories_toast.show();
            }
        }
        catch (Exception exc_1_e)
        {
            showErrorToast(exc_1_e.getMessage());
        }
    }
}
