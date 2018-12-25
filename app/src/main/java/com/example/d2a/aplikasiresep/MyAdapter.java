package com.example.d2a.aplikasiresep;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d2a.aplikasiresep.data.ResepModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{


    private LayoutInflater mInflater;

    List<ResepModel> resepModelList = new ArrayList<>();

    public MyAdapter(Context ct, List<ResepModel> resepModelList){
        mInflater = LayoutInflater.from(ct);
        this.resepModelList = resepModelList;
    }
    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View MyView= mInflater.inflate(R.layout.myrow,parent,false);
        return new  MyHolder(MyView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyHolder holder, int position) {
        String mdata1 = resepModelList.get(position).getTitle();
        holder.t1.setText(mdata1);
        String mdata2 = resepModelList.get(position).getDesc();
        holder.t2.setText(mdata2);

    }

    @Override
    public int getItemCount() {
        return resepModelList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView t1,t2;
        final MyAdapter mAdapter;
        Context context;


        public MyHolder(View itemView,MyAdapter adapter) {
            super(itemView);
            t1 = itemView.findViewById(R.id.text1);
            t2 = itemView.findViewById(R.id.text2);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view){
            int mPosition = getLayoutPosition();
            String element = resepModelList.get(mPosition).getTitle();//data1.get(mPosition);
            String element1 = resepModelList.get(mPosition).getBahan();//bahan.get(mPosition);
            String element2 = resepModelList.get(mPosition).getCara();//cara.get(mPosition);
            Intent intent = new Intent(view.getContext(), ResepTampil.class);
            intent.putExtra("title",element);
            intent.putExtra("bahan", element1);
            intent.putExtra("cara", element2);
            view.getContext().startActivity(intent);

            mAdapter.notifyDataSetChanged();
        }
    }


}
