package com.example.d2a.aplikasiresep;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    private final LinkedList<String> data1;
    private final LinkedList<String> data2;
    private final LinkedList<String> bahan;
    private final LinkedList<String> cara;
    private LayoutInflater mInflater;
    public MyAdapter(Context ct,LinkedList<String> s1,LinkedList<String> s2,LinkedList<String> s3,LinkedList<String> s4){
        mInflater = LayoutInflater.from(ct);
        this.data1 = s1;
        this.data2 = s2;
        this.bahan = s3;
        this.cara = s4;
    }
    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View MyView= mInflater.inflate(R.layout.myrow,parent,false);
        return new  MyHolder(MyView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyHolder holder, int position) {
        String mdata1 = data1.get(position);
        holder.t1.setText(mdata1);
        String mdata2 = data2.get(position);
        holder.t2.setText(mdata2);

    }

    @Override
    public int getItemCount() {
        return data1.size();
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
            String element = data1.get(mPosition);
            String element1 = bahan.get(mPosition);
            String element2 = cara.get(mPosition);
            Intent intent = new Intent(view.getContext(), ResepTampil.class);
            intent.putExtra("title",element);
            intent.putExtra("bahan", element1);
            intent.putExtra("cara", element2);
            view.getContext().startActivity(intent);

            mAdapter.notifyDataSetChanged();
        }
    }


}
