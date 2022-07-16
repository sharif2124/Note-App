package com.example.noteappdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.noteappdemo.databinding.ActivityDataInsertBinding;
import com.example.noteappdemo.databinding.ActivityMainBinding;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NoteViewModel.class);
    binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,DataInsertActivity.class);
            startActivityForResult(intent,1);
        }
    });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==1){
           String title = data.getStringExtra("title");
           String Descriptions = data.getStringExtra("disp");
           Note note = new Note(title,Descriptions);
           noteViewModel.insert(note);
           Toast.makeText(this,"Note Added",Toast.LENGTH_LONG).show();
       }
    }
}