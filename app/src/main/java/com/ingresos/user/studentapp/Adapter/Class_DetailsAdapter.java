package com.ingresos.user.studentapp.Adapter;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ingresos.user.studentapp.Model.Class_Model;
import com.ingresos.user.studentapp.R;
import java.util.List;

/**
 * Created by user on 20-Apr-17.
 */

public class Class_DetailsAdapter extends RecyclerView.Adapter<Class_DetailsAdapter.ViewHolder> {

    private List<Class_Model> class_list;

    public Class_DetailsAdapter(List<Class_Model> class_list) {
        this.class_list = class_list;


    }

    @Override
    public Class_DetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newcard_list, null);
        //create ViewHolder
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(Class_DetailsAdapter.ViewHolder holder, int position) {
        holder.class_no.setText(class_list.get(position).getClass_no());
        holder.class_location.setText(class_list.get(position).getClass_location());
        holder.class_payment.setText(class_list.get(position).getClass_payment());
        holder.class_start_time.setText(class_list.get(position).getClass_start_time());
        holder.class_end_time.setText(class_list.get(position).getClass_end_time());
        holder.class_start_date.setText(class_list.get(position).getClass_start_date());
        holder.class_end_date.setText(class_list.get(position).getClass_end_date());

        if (position % 2 == 0)
        {
           // holder.setBackgroundColor(Color.parseColor("#EA5B61"));
            holder.itemView.setBackgroundColor(Color.parseColor("#F44336"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FF9800"));
        }



    }

    @Override
    public int getItemCount() {
        Log.v(Class_DetailsAdapter.class.getSimpleName(), "" + class_list.size());
        return class_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView class_no, class_location, class_payment, class_start_time, class_end_time, class_start_date, class_end_date;

        public ViewHolder(View itemView) {
            super(itemView);
            class_no = (TextView) itemView.findViewById(R.id.class_name);
            class_location = (TextView) itemView.findViewById(R.id.class_location);
            class_payment = (TextView) itemView.findViewById(R.id.class_payment);
            class_start_time = (TextView) itemView.findViewById(R.id.class_start_time);
            class_end_time = (TextView) itemView.findViewById(R.id.class_end_time);
            class_start_date = (TextView) itemView.findViewById(R.id.class_start_date);
            class_end_date = (TextView) itemView.findViewById(R.id.class_end_date);


        }


    }
}
