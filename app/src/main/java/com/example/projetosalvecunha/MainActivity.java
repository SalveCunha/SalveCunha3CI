package com.example.projetosalvecunha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText edtemail;
    EditText edtsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.tela_login );

        edtemail = findViewById(R.id.edtemaill);
        edtsenha = findViewById(R.id.edtsenhal);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }
    public void login(View view){
        String email = edtemail.getText().toString();
        String senha = edtsenha.getText().toString();
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent it = new Intent(getApplicationContext(), TelaInicial.class);
                            startActivity(it);
                        } else {
                            // If sign in fails, display a message to the user.


                        }

                        // ...
                    }
                });
    }

    public void irtelacadastro(View view){
        Intent it = new Intent(getApplicationContext(), TelaCadastro.class);
        startActivity(it);
    }

    public void irtelarecu(View view){
        Intent it = new Intent(getApplicationContext(), RecuperacaoSenha.class);
        startActivity(it);
    }

}