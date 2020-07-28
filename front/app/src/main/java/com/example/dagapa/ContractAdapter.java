package com.example.dagapa;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> {
    ArrayList<Contract> items = new ArrayList<Contract>();
    String userID;

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
        viewHolder.setItem(item, userID);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Contract item, String userID) {
        items.add(item);
        this.userID = userID;
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
        TextView ddayView;
        TextView ddayView_alpha;
        ImageView dday_img;
        LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            state = itemView.findViewById(R.id.state);
            objects= itemView.findViewById(R.id.objects);
            ddayView = itemView.findViewById(R.id.dday);
            ddayView_alpha = itemView.findViewById(R.id.dday_alpha);
            dday_img = itemView.findViewById(R.id.dday_img);

            layout = itemView.findViewById(R.id.layout);
        }

        public void setItem(Contract item, String userID) {
            textView.setText(item.getGoods());
            textView2.setText(item.getDuedate());


            Log.d("로그인된 아이디 + Lender",  userID + " , " + item.getLender());
            int got_state = item.getStatus();
            //1 대기
            //2 진행
            //3 완료

            // 상태 : 대기(1) , GREEN
            if(got_state==1){
//                state.setImageResource(R.drawable.green);
                layout.setBackgroundColor(Color.rgb(179,227,163));
                state.setVisibility(View.INVISIBLE);

                dday_img.setVisibility(View.VISIBLE);
                dday_img.setImageResource(R.drawable.waiting);
                ddayView.setVisibility(View.GONE);
                ddayView_alpha.setVisibility(View.GONE);
                //대기중으로 바꿔서 표현하기
            }
            // 상태 : 진행(2) , GREEN or RED
            else if(got_state ==2){
                // 여기서 2가지 조건을 걸기.d
                if(!userID.equals(item.getLender()))
                    state.setImageResource(R.drawable.red);
                else
                    state.setImageResource(R.drawable.blue);
            }
            // 상태 : 종료(3) , GRAY
            else{
                state.setImageResource(R.drawable.gray);
            }

            // 금전 또는 물건 image display area
            int got_type = item.getType();
            if(got_type==1){
                objects.setImageResource(R.drawable.objects);
            } else{
                objects.setImageResource(R.drawable.money);
            }


            // d-day counter
            String dueDate = item.getDuedate();
            Log.d("마감일 : " , dueDate);

            long result = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date now = sdf.parse(sdf.format(new Date()));
                Log.d("오늘 : " , String.valueOf(now));
                Date end = sdf.parse(dueDate);

                long gap = now.getTime() - end.getTime();
                result = (long) (Math.floor(gap/ (1000*60*60*24))*-1);
                Log.d("남은 날짜 : ", String.valueOf(result));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(got_state != 1) {
                if (result >= 1) {
                    ddayView.setText(Long.toString(result));
                } else if (result == 0) {
                    dday_img.setVisibility(View.VISIBLE);
                    dday_img.setImageResource(R.drawable.dday);
                    ddayView.setVisibility(View.GONE);
                    ddayView_alpha.setVisibility(View.GONE);
                } else {
                    layout.setBackgroundColor(Color.rgb(199, 199, 199));
                    state.setVisibility(View.INVISIBLE);

                    dday_img.setVisibility(View.VISIBLE);
                    dday_img.setImageResource(R.drawable.end);
                    ddayView.setVisibility(View.GONE);
                    ddayView_alpha.setVisibility(View.GONE);
                }
            }


        }

    }

}
