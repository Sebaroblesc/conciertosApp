package cl.inacap.conciertosapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.conciertosapp.R;
import cl.inacap.conciertosapp.dto.Evento;

public class AdaptadorLista extends ArrayAdapter<Evento> {

    private Activity activity;
    private List<Evento> eventos;

    public AdaptadorLista(@NonNull Activity context, int resource, @NonNull List<Evento> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.eventos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();

        View xView = inflater.inflate(R.layout.eventoslayout, null, true);

        ImageView xImageView = xView.findViewById(R.id.imageView);
        TextView xTxtFecha = xView.findViewById(R.id.textView2);
        TextView xTxtNombre = xView.findViewById(R.id.textView1);
        TextView xTxtValor = xView.findViewById(R.id.textView3);

        int calif = eventos.get(position).getCalificacion();

        if (calif < 4) {
            xImageView.setImageResource(R.drawable.homero_sad);
        } else if (calif < 6) {
            xImageView.setImageResource(R.drawable.homero_normal);

        } else {
            xImageView.setImageResource(R.drawable.homero_feliz);
        }

        xTxtFecha.setText(eventos.get(position).getFecha());
        xTxtNombre.setText(eventos.get(position).getNombre());
        xTxtValor.setText(Integer.toString(eventos.get(position).getValor()));

        return xView;





    }
}

