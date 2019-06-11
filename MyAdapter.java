package com.example.projet_seisme;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.zip.Inflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {
    ArrayList<HashMap<String, String>> listItem;
    Context context;

    public MyAdapter(ArrayList<HashMap<String, String>> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    //redéfinition des méthodes de la classe BaseAdapter
    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public HashMap<String, String> getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout articleMisEnPage;

        LayoutInflater mInflater = LayoutInflater.from(context);
        if (convertView == null) {

            articleMisEnPage = (LinearLayout) mInflater.inflate(R.layout.adapter_item, parent, false);
        } else {
            articleMisEnPage = (LinearLayout) convertView;
        }
        ViewHolder viewHolder = (ViewHolder) articleMisEnPage.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();

            //on va chercher les valeurs rentrées
            viewHolder.title = (TextView) articleMisEnPage.findViewById(R.id.title);
            viewHolder.place = (TextView) articleMisEnPage.findViewById(R.id.place);
           viewHolder.time = (TextView) articleMisEnPage.findViewById(R.id.time);
            viewHolder.magnitude = (TextView) articleMisEnPage.findViewById(R.id.magnitude);
            viewHolder.reaction = (ImageView) articleMisEnPage.findViewById(R.id.reaction);
            articleMisEnPage.setTag(viewHolder);
        }
        viewHolder.title.setText(listItem.get(position).get("titre"));
        viewHolder.place.setText(listItem.get(position).get("place"));
        viewHolder.time.setText(listItem.get(position).get("time"));
        viewHolder.magnitude.setText("magnitude : " + listItem.get(position).get("magnitude"));
        viewHolder.reaction.setImageResource(Integer.parseInt(listItem.get(position).get("reaction")));
        //on retourne l'article créé
        return articleMisEnPage;
    }

    private class ViewHolder {
        public TextView title;
        public TextView place;
        public TextView time;
        public TextView magnitude;
        public ImageView reaction;
    }
}
