package com.example.projetosalvecunha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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


public class TelaCadastro extends AppCompatActivity {

    private EditText edtemail;
    private EditText edtsenha;
    private EditText edtcsenha;
    private EditText edtnpm;
    private EditText edtnome;
    private EditText edtnomem;
    private EditText edtdatan;
    private EditText edtcpf;
    private EditText edtrg;
    private EditText edttelefone;
    private EditText edtendereco;
    private EditText edtnumero;
    private EditText edtbairro;
    private EditText edtcep;
    private EditText edtcomplemento;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);
        edtemail = findViewById(R.id.edtEmail);
        edtsenha = findViewById(R.id.edtSenha);
        edtcsenha = findViewById(R.id.edtCSenha);
        edtnpm = findViewById(R.id.edtNPM);
        edtnome = findViewById(R.id.edtNome);
        edtnomem = findViewById(R.id.edtNomeM);
        edtdatan = findViewById(R.id.edtDataN);
        edtcpf = findViewById(R.id.edtCPF);
        edtrg = findViewById(R.id.edtRG);
        edttelefone = findViewById(R.id.edtTelefone);
        edtendereco = findViewById(R.id.edtEndereco);
        edtnumero = findViewById(R.id.edtNumero);
        edtbairro = findViewById(R.id.edtBairro);
        edtcep = findViewById(R.id.edtCEP);
        edtcomplemento = findViewById(R.id.edtComplemento);


    }

    public int validar(String email, String senha, String csenha, String npm, String nome){
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(csenha)
                || TextUtils.isEmpty(npm) || TextUtils.isEmpty(nome)){
            Toast.makeText(TelaCadastro.this, "Há campos necessarios em branco", Toast.LENGTH_LONG).show();
            return 1;
        }else{
            if(senha.equals(csenha)){
                Toast.makeText(TelaCadastro.this, "valido", Toast.LENGTH_LONG).show();
                return 0;
            }else{
                Toast.makeText(TelaCadastro.this, "As senhas não coincidem", Toast.LENGTH_LONG).show();
                return 0;
            }
        }
    }

    public void cadastrar(View view){
        final String email = edtemail.getText().toString().trim();
        String senha = edtsenha.getText().toString().trim();
        String csenha = edtcsenha.getText().toString();
        final String npm = edtnpm.getText().toString();
        final String nome = edtnome.getText().toString();
        final String nomem = edtnomem.getText().toString();
        final String datan = edtdatan.getText().toString();
        final String cpf = edtcpf.getText().toString();
        final String rg = edtrg.getText().toString();
        final String telefone = edttelefone.getText().toString();
        final String endereco = edtendereco.getText().toString();
        final String numero = edtnumero.getText().toString();
        final String bairro = edtbairro.getText().toString();
        final String cep = edtcep.getText().toString();
        final String complemento = edtcomplemento.getText().toString();

        if(validar(email, senha, csenha, npm, nome)==1){

        }else{
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser rUser = firebaseAuth.getCurrentUser();
                                String UserId = rUser.getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(UserId);
                                databaseReference.child("npm").setValue(npm);
                                databaseReference.child("nome").setValue(nome);
                                databaseReference.child("nomedamae").setValue(nomem);
                                databaseReference.child("datanasc").setValue(datan);
                                databaseReference.child("cpf").setValue(cpf);
                                databaseReference.child("rg").setValue(rg);
                                databaseReference.child("telefone").setValue(telefone);
                                databaseReference.child("endereco").setValue(endereco);
                                databaseReference.child("numero").setValue(numero);
                                databaseReference.child("bairro").setValue(bairro);
                                databaseReference.child("cep").setValue(cep);
                                databaseReference.child("complemento").setValue(complemento);

                                Toast.makeText(TelaCadastro.this, "Cadastrado com Sucesso", Toast.LENGTH_LONG).show();


                            }else{
                                Toast.makeText(TelaCadastro.this, "Erro na Comunicação com o Servidor", Toast.LENGTH_LONG).show();

                            }
                            Intent it = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(it);
                        }
                    });
        }

    }


}
