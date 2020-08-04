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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> implements OnContractItemClickListener {
    ArrayList<Contract> items = new ArrayList<Contract>();
    String userID;

    OnContractItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.contract_item, viewGroup, false);


        return new ViewHolder(itemView, this);
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

    public void setOnItemClickListener(OnContractItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        ImageView state;
        ImageView objects;
        TextView ddayView;
        TextView ddayView_alpha;
        ImageView waitImg;
        ImageView endImg;
        ImageView ddayImge;
        TextView expire;
        LinearLayout layout;

        public ViewHolder(View itemView, final OnContractItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            state = itemView.findViewById(R.id.state);
            objects= itemView.findViewById(R.id.objects);
            ddayView = itemView.findViewById(R.id.dday);
            ddayView_alpha = itemView.findViewById(R.id.dday_alpha);
            waitImg = itemView.findViewById(R.id.wait);
            endImg = itemView.findViewById(R.id.end);
            ddayImge = itemView.findViewById(R.id.dday_img);
            expire = itemView.findViewById(R.id.expire);


            layout = itemView.findViewById(R.id.layout);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
//                    Log.d("RecyclerView ■■■■■■", "리사이클러뷰 하나 클릭 : " + getAdapterPosition() );
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Contract item, String userID) {
            textView.setText(item.getGoods());
            textView2.setText(item.getDuedate());

            int got_state = item.getStatus();
            //1 대기
            //2 진행
            //3 완료

            // 상태 : 대기(1) , GREEN
            if(got_state==1){
                // 1# 배경화면 초록색
                layout.setBackgroundColor(Color.rgb(179,227,163));
                // 2# 상태 바 제거
                state.setVisibility(View.GONE);

                // 3# 이미지 처리
                //이미지 가시화 + 대기화면 이미지 출력
                waitImg.setVisibility(View.VISIBLE);

                // 종료 이미지 가리기
                endImg.setVisibility(View.GONE);
                ddayImge.setVisibility(View.GONE);
                ddayView.setVisibility(View.GONE);
                ddayView_alpha.setVisibility(View.GONE);
                expire.setVisibility(View.GONE);
            }

            // 상태 : 진행(2) , Blue-임대 or RED-임차
            else if(got_state ==2){
                layout.setBackgroundColor(Color.rgb(255,255,255));

                // 1. 조건을 걸어서 BLUE / RED 표시
                // 2. D-Day 날짜 표시

                // 1
                if(!userID.equals(item.getLender())) {
                    state.setVisibility(View.VISIBLE);
                    state.setImageResource(R.drawable.red);
                }
                else {
                    state.setVisibility(View.VISIBLE);
                    state.setImageResource(R.drawable.blue);
                }
                // 2
                // d-day counter
                String dueDate = item.getDuedate();

                long result = 0;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date now = sdf.parse(sdf.format(new Date()));
                    Date end = sdf.parse(dueDate);

                    long gap = now.getTime() - end.getTime();
                    result = (long) (Math.floor(gap/ (1000*60*60*24))*-1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // 남은 날짜가 1일 이상
                // 숫자만 그대로 표시
                if (result >= 1) {
                    layout.setBackgroundColor(Color.rgb(255, 255, 255));
                    ddayView_alpha.setVisibility(View.VISIBLE);
                    ddayView.setVisibility(View.VISIBLE);
                    ddayView.setText(Long.toString(result));

                    waitImg.setVisibility(View.GONE);
                    endImg.setVisibility(View.GONE);
                    ddayImge.setVisibility(View.GONE);
                    expire.setVisibility(View.GONE);
                }

                // 남은 기간이 0일. (D-Day)
                // D- View 제거.
                // D-Day 이미지 가시화
                else if (result == 0) {
                    layout.setBackgroundColor(Color.rgb(255, 255, 255));
                    ddayImge.setVisibility(View.VISIBLE);

                    waitImg.setVisibility(View.GONE);
                    endImg.setVisibility(View.GONE);
                    ddayView.setVisibility(View.GONE);
                    ddayView_alpha.setVisibility(View.GONE);
                    expire.setVisibility(View.GONE);
                }
                // 남은 기간이 음수.
                else {
                    layout.setBackgroundColor(Color.rgb(199, 199, 199));

                    waitImg.setVisibility(View.GONE);
                    endImg.setVisibility(View.GONE);
                    ddayImge.setVisibility(View.GONE);
                    ddayView_alpha.setVisibility(View.GONE);

                    ddayView.setVisibility(View.GONE);

                    expire.setVisibility(View.VISIBLE);
                }
            }
            // 상태 : 종료(3) , GRAY
            else if(got_state == 3){
                layout.setBackgroundColor(Color.rgb(199, 199, 199));
                endImg.setVisibility(View.VISIBLE);

                waitImg.setVisibility(View.GONE);
                state.setVisibility(View.GONE);
                waitImg.setVisibility(View.GONE);
                ddayImge.setVisibility(View.GONE);
                ddayView.setVisibility(View.GONE);
                ddayView_alpha.setVisibility(View.GONE);
                expire.setVisibility(View.GONE);
            }

            // 금전 또는 물건 image display area
            int got_type = item.getType();
            if(got_type==1){
                objects.setImageResource(R.drawable.objects);
            } else{
                objects.setImageResource(R.drawable.money);
            }

        }//end setItem method




    }

}
