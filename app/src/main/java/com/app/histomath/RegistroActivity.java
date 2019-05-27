package com.app.histomath;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    private Button btn_registrar;
    private EditText editText_userName;
    private EditText editText_correo;
    private EditText editText_password;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btn_registrar=findViewById(R.id.btn_registrarse_registro);
        editText_userName=findViewById(R.id.editText_username_registro);
        editText_correo=findViewById(R.id.editText_correo_registro);
        editText_password=findViewById(R.id.editText_password_registro);

        progressDialog= new ProgressDialog(this);

        // get de instance of
        mAuth= FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("Usuario");


        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

    }



    public  void registrarUsuario(){

        final String  userName= editText_userName.getText().toString().trim();
        final String correo = editText_correo.getText().toString().trim();
        String password= editText_password.getText().toString().trim();


        if (TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Ingrese un correo",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Ingrese una contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registrando el usuario...");
        progressDialog.show();


        mAuth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Usuario usuario= new Usuario(userName,correo);

                            FirebaseDatabase.getInstance().getReference("Usuario")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(RegistroActivity.this,"Usuario Registrado",Toast.LENGTH_LONG).show();

                                    }else{
                                        Toast.makeText(RegistroActivity.this,"Error",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });


                            Toast.makeText(RegistroActivity.this,"Usuario Registrado",Toast.LENGTH_LONG).show();

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(RegistroActivity.this,"Ese usuario ya existe",Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(RegistroActivity.this,"No se pudo registrar el usuario",Toast.LENGTH_LONG).show();

                            }
                        }

                        // ...
                    }
                });
    }

}
