package com.app.histomath;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCorreo;
    private EditText editTextPasword;

    private FirebaseAuth mAuth;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Button btn_registrar = findViewById(R.id.btn_registrarse);
        Button btn_login = findViewById(R.id.btn_login);
        editTextCorreo= findViewById(R.id.editText_Correo);
        editTextPasword= findViewById(R.id.editText_password);


        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

    }


    public void registrarUsuario(){
        Intent intent = new Intent(getApplication(),RegistroActivity.class);
        startActivity(intent);


    }


    public void iniciarSesion(){
        String correo = editTextCorreo.getText().toString().trim();
        String password= editTextPasword.getText().toString().trim();
        if (TextUtils.isEmpty(correo)){
            Toast.makeText(MainActivity.this,"Ingrese un correo",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this,"Ingrese una contraseña",Toast.LENGTH_LONG).show();
            return;
        }



        mAuth.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"Bienvenido",Toast.LENGTH_LONG).show();
                            nombre=task.getResult().getAdditionalUserInfo().getUsername();
                            gotoInicio();
                        } else {
                            if (task.getException() instanceof FirebaseNoSignedInUserException){
                                Toast.makeText(MainActivity.this,"No se pudo iniciar sesión",Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(MainActivity.this,"Usuario no existe",Toast.LENGTH_LONG).show();
                            }
                        }
                        // ...
                    }
                });
    }

    public void gotoInicio(){
        Intent intent = new Intent(getApplication(),PrincipalActivity.class);
        startActivity(intent);
    }
}
