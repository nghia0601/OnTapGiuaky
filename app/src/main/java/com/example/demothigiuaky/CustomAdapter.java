package com.example.demothigiuaky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Player> {
    Context context;
    int resource;
    ArrayList<Player> players;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Player> players) {
        super(context, resource, players);
        this.players=players;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.custom_adapter,parent,false);
            viewHolder= new ViewHolder();
            viewHolder.imgAvt=convertView.findViewById(R.id.imgAVT);
            viewHolder.tvName=convertView.findViewById(R.id.tvNameplayer);
            viewHolder.tvDate=convertView.findViewById(R.id.tvDateplayer);
            viewHolder.imgFlag=convertView.findViewById(R.id.imgFlag);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Player player=players.get(position);
        viewHolder.imgFlag.setImageResource(player.flag);
        viewHolder.imgAvt.setImageResource(player.avt);
        viewHolder.tvName.setText(player.name);
        viewHolder.tvDate.setText(String.valueOf(""+player.year));
        return convertView;
    }
    public class ViewHolder {
        TextView tvName, tvDate;
        ImageView imgAvt, imgFlag;
    }
}
