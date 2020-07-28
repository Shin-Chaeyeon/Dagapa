package com.example.dagapa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> {
    ArrayList<Contract> items = new ArrayList<Contract>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.contract_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Contract item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Contract item) {
        items.add(item);
    }

    public void setItems(ArrayList<Contract> items) {
        this.items = items;
    }

    public Contract getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Contract item) {
        items.set(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        ImageView state;
        ImageView objects;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            state = itemView.findViewById(R.id.state);
            objects= itemView.findViewById(R.id.objects);

        }

        public void setItem(Contract item) {
            textView.setText(item.getGoods());
            textView2.setText(item.getDuedate());

            int got_state = item.getStatus();
            //1 대기
            //2 진행
            //3 완료
            if(got_state==1){
                state.setImageResource(R.drawable.green);
            } else if(got_state ==2){
                state.setImageResource(R.drawable.red);
            } else{
                state.setImageResource(R.drawable.gray);
            }

            int got_type = item.getType();
            if(got_type==1){
                objects.setImageResource(R.drawable.objects);
            } else{
                objects.setImageResource(R.drawable.money);
            }




        }

    }

}
