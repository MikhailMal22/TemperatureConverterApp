package com.example.temperatureconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView temK, temF;
    private EditText temC;
    private Button button;
    private RadioGroup group;
    private RadioButton checked;
    private int from;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        temC = findViewById(R.id.editTextC);
        temK = findViewById(R.id.textK);
        temF = findViewById(R.id.textF);
        button = findViewById(R.id.button);
        group = findViewById(R.id.temGroup);
        


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                RadioButton checked = (RadioButton) group.findViewById(i);
                if(checked.getId() == R.id.toggleF)
                {
                    from = 1;
                }
                else if(checked.getId() == R.id.toggleK)
                {
                    from = 2;
                }
                else
                {
                    from = 3;
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                try{
                    double num1 = Double.parseDouble(temC.getText().toString());
                    double num2, num3;
                    if(from == 1)
                    {
                        num2 = (num1-32) * 5/9;
                        num3 = num2 + 273.15;
                        temF.setText("Celsius: "+String.format("%.2f",num2));
                        temK.setText("Kelvin: "+String.format("%.2f",num3));
                    }
                    else if (from == 2)
                    {
                        num2 = (num1-273.15)*1.8+32;
                        num3 = num1 - 273.15;
                        temF.setText("Fahrenheit: "+String.format("%.2f",num2));
                        temK.setText("Celsius: "+String.format("%.2f",num3));
                    }
                    else
                    {
                        num2 = num1*1.8 + 32;
                        num3 = num1+273.15;
                        temK.setText("Kelvin: "+String.format("%.2f",num3));
                        temF.setText("Fahrenheit: "+String.format("%.2f",num2));
                    }

                }
                catch (NumberFormatException e)
                {
                    temF.setText("N/A");
                    temK.setText("N/A");
                }
            }
        });
    }

}