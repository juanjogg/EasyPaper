package com.upb.ti.easypaper;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    TextView tvUsuario;
    TextView tvPassword;
    Button btnIngresar;
    Button btnSalir;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvUsuario = findViewById(R.id.tv_usuario);
        tvPassword = findViewById(R.id.tv_Password);
        btnIngresar = findViewById(R.id.btn_Ingresar);
        btnSalir = findViewById(R.id.btn_Salir);

        mAuth=FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user == null){
                    btnIngresar.setVisibility(View.VISIBLE);
                    btnSalir.setVisibility(View.INVISIBLE);
                }
                else {
                    btnIngresar.setVisibility(View.INVISIBLE);
                    btnSalir.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(),"Ingresó bien cucho apá",Toast.LENGTH_SHORT).show();

                }
            }
        };
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });


    }
    public void ingresar(){
        String usuario = tvUsuario.getText().toString();
        String password = tvPassword.getText().toString();

        if(!usuario.isEmpty() && !password.isEmpty()){

            mAuth.signInWithEmailAndPassword(usuario,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"CORRECTO",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(),PapeleriaGralActivity.class);
                        finish();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"INCORRECTO",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(this,"Ningun campo debe estar vacio",Toast.LENGTH_SHORT);
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        btnIngresar.setVisibility(View.VISIBLE);
        btnSalir.setVisibility(View.INVISIBLE);
        mAuth.addAuthStateListener(listener);

    }
    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(listener);
    }
}
