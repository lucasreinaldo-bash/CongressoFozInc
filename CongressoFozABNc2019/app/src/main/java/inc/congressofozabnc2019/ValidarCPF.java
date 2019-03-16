package inc.congressofozabnc2019;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.MultiFormatWriter;

import inc.congressofozabnc2019.Firebase.ConfiguracaoFirebase;
import inc.congressofozabnc2019.Firebase.Usuario;


public class ValidarCPF extends AppCompatActivity implements  Runnable {

    private ImageView qrCode;
    private String tipoDoUsuario;
    private String nomeUsuario;
    private String cpfUsuario;
    private String emailUsuario;
    private String informacoesParaQr;
    private Button gerar,btn_home,btn_voltar;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText cpf;

    MultiFormatWriter multi = new MultiFormatWriter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarFirebase();

        qrCode = findViewById(R.id.id_qr);
        cpf = findViewById(R.id.cpf_id);
        gerar = findViewById(R.id.btn_gerar);
        btn_home = findViewById(R.id.btn_home);
        btn_voltar = findViewById(R.id.btn_voltar);


        // Instaciando o servidor
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();





        Toast.makeText(this, informacoesParaQr, Toast.LENGTH_SHORT).show();


        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed ();
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(ValidarCPF.this, Menu.class);
               startActivity(intent);
               finish();
            }
        });
        gerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseUser currentUser = mAuth.getCurrentUser();
                DatabaseReference reference = database.getReference("Usuario/" + currentUser.getUid());
                String cpfDigitado = cpf.getText().toString();
                cpfValidar();
               isAccountValid();




            }
        });


        Handler handler = new Handler();
        handler.postDelayed(this, 100000);
    }

    public void run() {
        startActivity(new Intent(this, Menu.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void isAccountValid() {
        String uid = mAuth.getCurrentUser().getUid();
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebase().child("Usuario").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("situacaoCadastro").getValue() == null)
                    Toast.makeText(ValidarCPF.this, "Null", Toast.LENGTH_SHORT).show();
                else {
                    Boolean accountChecked = Boolean.parseBoolean(dataSnapshot.child("situacaoCadastro").getValue().toString());
                    if (!accountChecked) {
                        Toast.makeText(ValidarCPF.this, "O CPF informado não se encontra na lista de inscrições confirmadas.", Toast.LENGTH_SHORT).show();
                    }
                    else{



                        Intent intent = new Intent(ValidarCPF.this, QRConfirmado.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    } private void cpfValidar() {
        final String cpfDigitado = cpf.getText().toString();
        final String uid = mAuth.getCurrentUser().getUid();
        final Usuario user2 = new Usuario();
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebase().child("CPF");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(cpfDigitado).getValue() == null) {

                    //Toast.makeText(ValidarCPF.this, "CPF não cadastrado", Toast.LENGTH_SHORT).show();
                } else {
                    user2.setSituacaoCadastro("true");
                    DatabaseReference reference = database.getReference("Usuario/" + uid);
                    reference.child("situacaoCadastro").setValue("true");
                    reference.child("CPF").setValue(cpfDigitado);


                    Toast.makeText(ValidarCPF.this, "QR-CODE gerado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onBackPressed () {

        Intent intent = new Intent(ValidarCPF.this, Menu.class);
        startActivity(intent);
        finish();

    }
}
