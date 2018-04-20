package com.upb.ti.easypaper;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button fireb;
    private DatabaseReference mDatabase;
    private EditText etName;
    private EditText etUbication;
    private EditText etDisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fireb = findViewById(R.id.btn_firebase);
        etName = findViewById(R.id.etName);
        etUbication = findViewById(R.id.etUbication);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        fireb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crearPapeleria();

            }
        });
    }

    //Metodo que recibe datos sobre una papeleria y los almacena en la base de datos. Finalmente hace un check para ver si la operacion se completo correctamente
    public void crearPapeleria(){
        HashMap<String,String> map = new HashMap();
        map.put("Nombre",etName.getText().toString().trim());
        map.put("Ubicacion",etUbication.getText().toString().trim());


        mDatabase.child("Papelerias").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Los datos se han almacenado",Toast.LENGTH_SHORT).show();
                    MainActivity.this.etName.setText("");
                    MainActivity.this.etUbication.setText("");

                }
                else{
                    Toast.makeText(MainActivity.this,"Ha ocurrido un error",Toast.LENGTH_SHORT).show();

                }
            }
        });



    }


}
