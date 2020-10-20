package com.example.demothigiuaky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvName, tvYear;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvName=(TextView)findViewById(R.id.tvNameDetail);
        tvYear=(TextView)findViewById(R.id.tvYearDetail);
        btnBack=(Button)findViewById(R.id.btnBack);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("detail");
        Player playerlay= (Player) bundle.getSerializable("PlayerDetail");
        tvName.setText(playerlay.name);
        tvYear.setText(String.valueOf(""+playerlay.year));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}