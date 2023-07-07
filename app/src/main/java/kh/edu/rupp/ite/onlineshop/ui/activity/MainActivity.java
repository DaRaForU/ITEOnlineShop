package kh.edu.rupp.ite.onlineshop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kh.edu.rupp.ite.onlineshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}