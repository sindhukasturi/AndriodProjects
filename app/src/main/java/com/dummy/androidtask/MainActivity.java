package com.dummy.androidtask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private static final int GRID_SIZE = 100;
    Button btnOdd,btnEven,btnPrime,btnFibonacci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        initializeGrid();

         btnOdd = findViewById(R.id.btnOdd);
         btnEven = findViewById(R.id.btnEven);
         btnPrime = findViewById(R.id.btnPrime);
         btnFibonacci = findViewById(R.id.btnFibonacci);

        Toast.makeText(this, "Select any button for displaying odd, prime, even, and Fibonacci numbers", Toast.LENGTH_SHORT).show();

        btnOdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightNumbers("ODD");
            }
        });
        btnPrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightNumbers("PRIME");
            }
        });
        btnEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightNumbers("EVEN");
            }
        });
        btnFibonacci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highlightNumbers("FIBONACCI");
            }
        });

    }

    private void initializeGrid() {
        for (int i = 1; i <= GRID_SIZE; i++) {
            TextView numberView = new TextView(this);
            numberView.setText(String.valueOf(i));
            numberView.setTextSize(16);
            numberView.setPadding(16, 16, 16, 16);
            numberView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            gridLayout.addView(numberView);
        }
    }

    private void highlightNumbers(String rule) {
        for (int i = 0; i < GRID_SIZE; i++) {
            TextView numberView = (TextView) gridLayout.getChildAt(i);
            int number = i + 1;

            switch (rule) {
                case "ODD":
                    numberView.setBackgroundColor(isOdd(number) ? Color.YELLOW : Color.WHITE);
                    break;
                case "EVEN":
                    numberView.setBackgroundColor(isEven(number) ? Color.CYAN : Color.WHITE);
                    break;
                case "PRIME":
                    numberView.setBackgroundColor(isPrime(number) ? Color.MAGENTA : Color.WHITE);
                    break;
                case "FIBONACCI":
                    numberView.setBackgroundColor(isFibonacci(number) ? Color.GREEN : Color.WHITE);
                    break;
            }
        }
    }

    private boolean isOdd(int number) {
        return number % 2 != 0;
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }

    private boolean isFibonacci(int number) {
        HashSet<Integer> fibonacciSet = generateFibonacciUpTo(GRID_SIZE);
        return fibonacciSet.contains(number);
    }

    private HashSet<Integer> generateFibonacciUpTo(int limit) {
        HashSet<Integer> fibonacciSet = new HashSet<>();
        int a = 0, b = 1;
        while (a <= limit) {
            fibonacciSet.add(a);
            int temp = a;
            a = b;
            b = temp + b;
        }
        return fibonacciSet;
    }
}
