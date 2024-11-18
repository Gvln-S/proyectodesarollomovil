package com.example.prolinkfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {
    private List<Service> services;
    private OnServiceClickListener listener;

    public interface OnServiceClickListener {
        void onServiceClick(Service service);
    }

    public ServiceAdapter(List<Service> services, OnServiceClickListener listener) {
        this.services = services;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = services.get(position);
        holder.bind(service);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView providersTextView;
        private ImageView serviceImageView;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.serviceTitleTextView);
            providersTextView = itemView.findViewById(R.id.availableProvidersTextView);
            serviceImageView = itemView.findViewById(R.id.serviceImageView); // Inicializar el ImageView

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onServiceClick(services.get(position));
                }
            });
        }

        public void bind(Service service) {
            // Asignar los datos al TextView
            titleTextView.setText(service.getTitle());
            providersTextView.setText(service.getAvailableProviders() + " proveedores disponibles");
            // Asignar la imagen al ImageView
            serviceImageView.setImageResource(service.getImageResourceId());
        }
    }
}
