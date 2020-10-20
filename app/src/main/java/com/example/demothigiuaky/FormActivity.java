package com.example.demothigiuaky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    EditText edtName,edtDate;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        edtName=(EditText)findViewById(R.id.editTextTextPersonName);
        edtDate=(EditText)findViewById(R.id.editTextTextPersonName2);
        btnSave=(Button)findViewById(R.id.btnSave);
        Intent intent=getIntent();
       if(!intent.hasExtra("edit")){
           btnSave.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Player player=new Player(edtName.getText().toString(),Integer.parseInt(edtDate.getText().toString()),R.drawable.footballplayer,R.drawable.flag);
                   Intent intent1=new Intent();
                   Bundle bundle=new Bundle();
                   bundle.putSerializable("player",player);
                   intent1.putExtra("add",bundle);
                   setResult(RESULT_OK,intent1);
                   finish();
               }
           });
       }
       else{
                final Bundle bundle=intent.getBundleExtra("edit");
                final Player player=(Player) bundle.getSerializable("playeredit");
                int pos=bundle.getInt("pos");
                edtName.setText(player.name.toString());
                edtDate.setText(String.valueOf(""+player.year));
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.setName(edtName.getText().toString());
                        player.setYear(Integer.parseInt(edtDate.getText().toString()));
                        Intent intent1=new Intent();
                        intent1.putExtra("edit",bundle);
                        setResult(RESULT_OK,intent1);
                        finish();
                    }
                });
       }
    }
}