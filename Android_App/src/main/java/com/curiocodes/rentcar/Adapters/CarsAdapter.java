package com.curiocodes.rentcar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.curiocodes.rentcar.Models.CarsModel;
import com.curiocodes.rentcar.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private List<CarsModel> list;
    private Context context;

    public CarsAdapter(List<CarsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_car,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.gear.setText(list.get(position).getGear());
        holder.seat.setText(list.get(position).getSeats());
        holder.price.setText(String.valueOf(list.get(position).getPrice()));
        Picasso.get().load(list.get(position).getImage()).placeholder(R.drawable.car_placeholder).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name,price,seat,gear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.carName);
            seat = itemView.findViewById(R.id.seat);
            gear = itemView.findViewById(R.id.gear);
            price = itemView.findViewById(R.id.price);
        }
    }
}
