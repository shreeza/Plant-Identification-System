package com.example.shailee.camo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by karsk on 01/04/2019.
 */

public class Adapter_SearchResult extends RecyclerView.Adapter<Adapter_SearchResult.MyViewHolder> {


    ArrayList<ModelItem> modelItemArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    private Context context;

    public Adapter_SearchResult(ArrayList<ModelItem> modelItemArrayList, Context context, RecyclerView recyclerView) {
        this.context = context;
        this.modelItemArrayList = modelItemArrayList;
        this.recyclerView = recyclerView;
    }

    public static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_searchresult, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ModelItem modelItem = modelItemArrayList.get(position);

        holder.tv_title.setText(modelItem.getCommonName());

        Bitmap image = toBitmap(modelItem.getImages());
        holder.imgView.setImageBitmap(image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Activity_Details.class);
                intent.putExtra("common_name", modelItem.getCommonName());
                intent.putExtra("benifits", modelItem.getBenifits());
                intent.putExtra("familyName", modelItem.getFamilyname());
                intent.putExtra("genus", modelItem.getGenus());
                intent.putExtra("image", modelItem.getImages());
                intent.putExtra("scientific_name", modelItem.getScientificName());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelItemArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView imgView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            imgView = itemView.findViewById(R.id.imgView);
        }
    }

}
