package cl.inacap.conciertosapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import cl.inacap.conciertosapp.dto.Evento;

public class EventosListAdapter extends ArrayAdapter<Evento> {

    Context mContext;
    private ArrayList<Evento> eventos;

    public EventosListAdapter(Context context, ArrayList<Evento> eventos) {
        super(context, R.layout.eventoslayout);
        this.eventos = eventos;
        this.mContext = context;

    }
    @Override
    public int getCount() {
        return eventos.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.eventoslayout, parent, false);
            ImageView eventoImage = convertView.findViewById(R.id.imageView);
            TextView tvNombre = convertView.findViewById(R.id.textView1);
            TextView tvFecha = convertView.findViewById(R.id.textView2);
            TextView tvValor = convertView.findViewById(R.id.textView3);

            int calif = eventos.get(position).getCalificacion();
            if (calif < 4) {
                eventoImage.setImageResource(R.drawable.homero_sad);

            } else if (calif < 6) {

                eventoImage.setImageResource(R.drawable.homero_normal);
            } else {

                eventoImage.setImageResource(R.drawable.homero_feliz);
            }

            tvNombre.setText(eventos.get(position).getNombre());
            tvFecha.setText(eventos.get(position).getFecha());
            tvValor.setText(eventos.get(position).getValor());
        }
        return convertView;
    }
}