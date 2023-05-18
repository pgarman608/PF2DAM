package com.example.proyectofinaldam1.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinaldam1.R;
import com.example.proyectofinaldam1.models.Usuario;

import java.util.List;

public class RaRanking extends RecyclerView.Adapter<RaRanking.RecyclerHolder> {

    private List<Usuario> users;

    private View.OnClickListener onClickListener;
    public RaRanking(List<Usuario> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ranking_list_row,parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);

        return recyclerHolder;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Usuario usuario = users.get(position);
        Log.d("Onview", "onBindViewHolder: " + usuario.getUid());
        holder.tvNick.setText(usuario.getNick());
        holder.tvPoints.setText("Puntos: " + usuario.getPoints());
        holder.btnSeeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView imgUser;
        TextView tvNick;
        TextView tvPoints;
        Button btnSeeUser;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = (ImageView) itemView.findViewById(R.id.ivSeeRnk);
            tvNick = (TextView) itemView.findViewById(R.id.txtSeeNameRnk);
            tvPoints = (TextView) itemView.findViewById(R.id.txtSeePointsRnk);
            btnSeeUser = (Button) itemView.findViewById(R.id.btnSeeUser);
        }
    }
}
