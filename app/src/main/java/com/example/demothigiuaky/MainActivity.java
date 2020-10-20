package com.example.demothigiuaky;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton btnAdd;
    ListView lviDetail;
    ArrayList<Player> players;
    CustomAdapter customAdapter;
    private static final int ADD=999;
    private static final int EDIT=888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd=(ImageButton) findViewById(R.id.imageButton);
        lviDetail=(ListView)findViewById(R.id.lvw1);
        players= new ArrayList<Player>();
        players.add(new Player("Beckham",1975,R.drawable.ava_beckham,R.drawable.flag_england));
        players.add(new Player("Ronaldo",1984,R.drawable.ava_ronaldo,R.drawable.flag_portugal));
        players.add(new Player("Messi",1987,R.drawable.ava_messi,R.drawable.flag_achentina));
        players.add(new Player("Ronaldo",1979,R.drawable.ava_ronaldo_beo,R.drawable.flag_brazil));
        players.add(new Player("Zidane",1975,R.drawable.ava_zidane,R.drawable.flag_france));
        players.add(new Player("Cruyff",1947,R.drawable.ava_cryff,R.drawable.flag_netheland));
        players.add(new Player("Pele",1940,R.drawable.ava_pele,R.drawable.flag_brazil));
        //players.add(new Player("Pele",1940,R.drawable.ava_pele,R.drawable.flag_brazil));
        customAdapter=new CustomAdapter(MainActivity.this,R.layout.custom_adapter,players);
        lviDetail.setAdapter(customAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,FormActivity.class);
                
                startActivityForResult(intent,ADD);
            }
        });
        lviDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Player playerlay= players.get(i);
                Intent intent= new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("PlayerDetail",playerlay);
                intent.putExtra("detail",bundle);
                startActivity(intent);
            }
        });
        registerForContextMenu(lviDetail);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.lvw1){
            MenuInflater menuInflater= getMenuInflater();
            menuInflater.inflate(R.menu.custom_menu,menu);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD){
            if(resultCode==RESULT_OK){
                Bundle bundle=data.getBundleExtra("add");
                Player player= (Player) bundle.getSerializable("player");
                players.add(player);
                customAdapter.notifyDataSetChanged();
            }
        }
        else if(requestCode==EDIT){
            if(resultCode==RESULT_OK){
                Bundle bundle=data.getBundleExtra("edit");
                int pos=bundle.getInt("pos");
                Player player= (Player) bundle.getSerializable("playeredit");
                Player needEdit=players.get(pos);
                needEdit.setName(player.name);
                needEdit.setYear(player.year);
                customAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final AdapterView.AdapterContextMenuInfo menuInfo= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.add:
                Intent intent=new Intent(MainActivity.this,FormActivity.class);
                startActivityForResult(intent,ADD);
            break;
            case R.id.edit:
                //ArrayList<Player> players1;
                //players1=new ArrayList<Player>();
                int pos=menuInfo.position;
                final Player player=players.get(pos);
                //players1.add(player);
                Intent intent1=new Intent(MainActivity.this, FormActivity.class);
                Bundle bundle= new Bundle();
                //bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) players1);
                bundle.putInt("pos",pos);
                bundle.putSerializable("playeredit",player);
                intent1.putExtra("edit",bundle);
                startActivityForResult(intent1,EDIT);
                break;
            case R.id.delete:
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete");
                builder.setNegativeButton("Cancel",null);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        players.remove(menuInfo.position);
                        customAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                break;
        }
        return super.onContextItemSelected(item);

    }
}