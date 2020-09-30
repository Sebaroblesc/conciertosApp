package cl.inacap.conciertosapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.conciertosapp.R;
import cl.inacap.conciertosapp.dao.EventosDAO;
import cl.inacap.conciertosapp.dto.Evento;

public class MainActivity extends AppCompatActivity {

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    TextView mTv;
    Button mBtn;


    private EditText nombreTxt;
    private EditText fechaTxt;
    private Spinner generoSpin;
    private EditText valorTxt;
    private Spinner notaSpin;

    private Button agregarBtn;

    private EventosDAO dao = new EventosDAO();
    String[] calif = {"1", "2", "3", "4", "5", "6", "7"};
    String[] generos = {"Rock", "Jazz", "Pop", "Reguetoon", "Salsa", "Metal"};
    private ListView conciertosList;

    private ArrayAdapter<Evento> adapter5;



    Calendar c;
    DatePickerDialog dpd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.conciertosList = findViewById(R.id.conciertosList);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nombreTxt = findViewById(R.id.nombreTxt);
        this.generoSpin = (Spinner) findViewById(R.id.generoSpin);

        this.valorTxt = findViewById(R.id.valorTxt);
        this.notaSpin = (Spinner) findViewById(R.id.notaSpin);

        this.agregarBtn = findViewById(R.id.agregarBtn);




        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        generoSpin.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, calif);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notaSpin.setAdapter(adapter2);



        mTv = (TextView) findViewById(R.id.txtView);
        mBtn = (Button) findViewById(R.id.fechaBtn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            c = Calendar.getInstance();
            int dia = c.get(Calendar.DAY_OF_MONTH);
            int mes = c.get(Calendar.MONTH);
            int anio = c.get(Calendar.YEAR);

            dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int mAnio, int mMes, int mDia) {
                    mTv.setText(mDia + "/" + (mMes+1)+ "/" + mAnio);
                }
            }, dia, mes, anio);
            dpd.show();
            }
        });



        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> errores = new ArrayList<>();
                String nombreString = nombreTxt.getText().toString().trim();
                String valorString = valorTxt.getText().toString().trim();
                String generoString = generoSpin.getSelectedItem().toString().trim();
                String notaString = notaSpin.getSelectedItem().toString().trim();
                String fechaString = mTv.getText().toString().trim();

                int valor = 0;
                int calif = 0;

                if (nombreString.isEmpty()) {
                    errores.add("Debe ingresar nombre artista.");
                }

                if (valorString.isEmpty()) {
                    errores.add("Debe ingresar valor entrada.");
                } else {
                    try {
                        valor = Integer.parseInt(valorString);
                        if (valor < 0) {
                            errores.add("El valor debe ser positivo.");
                        }
                    } catch (NumberFormatException ex) {
                        errores.add("El valor debe ser numérico.");
                    }
                }

                if (generoString.isEmpty()) {
                    errores.add("Debe seleccionar un género musical.");
                }

                if (notaString.isEmpty()) {
                    errores.add("Seleccione calificación.");
                } else {

                    try {
                        calif = Integer.parseInt(notaString);
                        if (calif < 1 || calif > 7) {
                            errores.add("Calificación debe ser entre 1 y 7");
                        }
                    } catch (NumberFormatException ex) {
                        errores.add("Calificación debe ser numérico");
                    }
                }

                if (fechaString.isEmpty()) {
                    errores.add("Seleccione fecha del Evento.");
                }


                if (errores.isEmpty()) {

                    Evento e = new Evento();
                    e.setNombre(nombreString);
                    e.setFecha(fechaString);
                    e.setGenero(generoString);
                    e.setValor(valor);
                    e.setCalificacion(calif);
                    dao.add(e);


                    Toast.makeText(MainActivity.this,
                            "Evento agregado!!", Toast.LENGTH_LONG).show();
                } else {
                    verErrores(errores);
                }


            }
        });
    }







    private void verErrores(List<String> errores) {
        String msjError = "";
        for (String e : errores) {
            msjError += "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error en la validación")
                .setMessage(msjError)
                .setPositiveButton("Aceptar", null)
                .create()
                .show();

    }




}