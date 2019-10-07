package com.app.histomath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import it.sephiroth.android.library.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {
    private TextView textView_muerte,textView_nacimiento,textView_nombre,textView_descripcion;
    private ImageView imageView_personaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Intent intent = getIntent();
        Matematicos matematicoItem = intent.getParcelableExtra("Matematico");

        textView_nombre=findViewById(R.id.textView_nombre_detalle);
        textView_nombre.setText(matematicoItem.getNombre());


        imageView_personaje=findViewById(R.id.image_item_detalle);
        Picasso.with(getApplicationContext())
                .load(matematicoItem.getImagen())
                .fit()
                .centerCrop()
                .into(imageView_personaje);

        textView_nacimiento=findViewById(R.id.textView_fNacimiento_detalle);
        textView_nacimiento.setText(matematicoItem.getfNacimiento());

        textView_muerte=findViewById(R.id.textView_fMuerte_detalle);
        textView_muerte.setText(matematicoItem.getfMuerte());


        textView_descripcion=findViewById(R.id.textView_descripcion_detalle);
        textView_descripcion.setText(matematicoItem.getDescripcion());




    }
}
