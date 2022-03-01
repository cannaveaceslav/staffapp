package com.staffapp.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.LinkedTreeMap;
import com.staffapp.mobile.R;
import com.staffapp.mobile.activities.LinkingActivity;
import com.staffapp.mobile.fragment.LinkConfirmFragment;
import com.staffapp.mobile.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private static final String TAG = "ItemAdapter";

    private Context mCtx;
    private List<?> itemsList;


    public ItemAdapter(Context mCtx, List<Item> itemsList) {
        this.mCtx = mCtx;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        LinkedTreeMap itemMap = (LinkedTreeMap) itemsList.get(position);
        Item item = new Item(
                new Double((Double) itemMap.get("id")).longValue(),
                (String) itemMap.get("itemName"),
                (String) itemMap.get("barcode")
        );


        holder.textViewItemName.setText(item.getItemName());
        holder.textViewBarcode.setText(item.getBarcode());


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName;
        TextView textViewBarcode;
        View rootView;
        Item item;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            textViewItemName = itemView.findViewById(R.id.textViewItemName);
            textViewBarcode = itemView.findViewById(R.id.textViewBarcode);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    LinkedTreeMap itemsMap = (LinkedTreeMap) itemsList.get(position);

                    String itemName = (String) itemsMap.get("itemName");
                    String barcode = (String) itemsMap.get("barcode");
                    Long id = new Double((Double) itemsMap.get("id")).longValue();
                    /******************************************************************************************/



                    Intent i = ((Activity) mCtx).getIntent();
                    i.putExtra("itemId", id);


                    Toast.makeText(mCtx, "Was clicked " + itemName + "item barcode", Toast.LENGTH_SHORT).show();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new LinkConfirmFragment();


                    Log.i(TAG, "Bundle ITEM  :" + id);

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_check, myFragment).addToBackStack(null).commit();


//                    Intent intent = new Intent(mCtx, ItemsActivity.class);
//                    mCtx.startActivity(intent);
                }
            });
        }


    }


}
