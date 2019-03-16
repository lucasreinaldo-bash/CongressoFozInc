package inc.congressofozabnc2019;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;


import inc.congressofozabnc2019.Firebase.ConfiguracaoFirebase;
import inc.congressofozabnc2019.Firebase.Pergunta;
import inc.congressofozabnc2019.Firebase.Usuario;
import inc.congressofozabnc2019.R;
public class PerguntePalestrante extends AppCompatActivity {

    private Button btn_enviar, btn_entrar,btn_home,btn_voltar,btnConcluirInscricao;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button btnRegistrar, btnPalestrante,btnParticipante;
    private EditText txtNomePalestrante,txtMensagem,txtCpf,txtSenha;
    private LinearLayout linear_curriculo;
    private String tipoDoUsuario,cpfValido;
    private DatabaseReference reference, referenciaCPF;
    private DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child("CPF");
    private RadioButton sala1,sala2,sala3,sala4,sala5,sala6,radioButao;
    private RadioGroup radioGroup;
    private int numSala = 0;

    ArrayList<Pergunta> list2;
    private String nomePalestrante,pergunta,autor,email,senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunte_palestrante);

        txtNomePalestrante = findViewById(R.id.pergunta_nome);
        txtMensagem = findViewById(R.id.id_mensagem);
        txtSenha = findViewById(R.id.txtSenha);
        btn_enviar = findViewById(R.id.btn_enviar_msg);
        btn_entrar = findViewById(R.id.btn_entrar);
        btn_home = findViewById(R.id.btn_home);
        btn_voltar = findViewById(R.id.btn_voltar);
        radioGroup = findViewById(R.id.radiogroup_id);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        //Cast dos Radios Buttons
        sala1 = findViewById(R.id.radioButton);
        sala2 = findViewById(R.id.radioButton2);
        sala3 = findViewById(R.id.radioButton3);
        sala4 = findViewById(R.id.radioButton4);
        sala5 = findViewById(R.id.radioButton5);
        sala6 = findViewById(R.id.radioButton6);




        //Firebase
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int codigoSala1 = 58957;
                int senha  = Integer.parseInt(txtSenha.getText().toString());
                if (senha == codigoSala1) {
                    Intent intent = new Intent(PerguntePalestrante.this, Sala1.class);
                    intent.putExtra("sala","Sala 1");
                    startActivity(intent);
                    finish();
                }
                else if (senha == 67892){
                    Intent intent = new Intent(PerguntePalestrante.this, Sala1.class);
                    intent.putExtra("sala","Sala 2");
                    startActivity(intent);
                    finish();
                }
                else if (senha == 59348){
                    Intent intent = new Intent(PerguntePalestrante.this, Sala1.class);
                    intent.putExtra("sala","Sala 3");
                    startActivity(intent);
                    finish();
                }
                else if (senha == 83246){
                    Intent intent = new Intent(PerguntePalestrante.this, Sala1.class);
                    intent.putExtra("sala","Sala 4");
                    startActivity(intent);
                    finish();
                }
                else if (senha == 73851){
                    Intent intent = new Intent(PerguntePalestrante.this, Sala1.class);
                    intent.putExtra("sala","Sala 5");
                    startActivity(intent);
                    finish();
                }
                else if (senha == 91837){
                    Intent intent = new Intent(PerguntePalestrante.this, Sala1.class);
                    intent.putExtra("sala","Sala 6");
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(PerguntePalestrante.this, "Senha Errada!", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //Firebase
                FirebaseUser currentUser = mAuth.getCurrentUser();
                DatabaseReference reference2 = database.getReference("Usuario/"+currentUser.getUid());
                recuperandoUsuario();


//


            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    private void recuperandoUsuario() {
        String uid = mAuth.getCurrentUser().getUid();
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebase().child("Usuario").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null)
                    Toast.makeText(PerguntePalestrante.this, "Null", Toast.LENGTH_SHORT).show();
                else {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    nomePalestrante = txtNomePalestrante.getText().toString();
                    pergunta = txtMensagem.getText().toString();
                    senha = txtSenha.getText().toString();
                    // Check which radio button was clicked


                    radioButao = (RadioButton) findViewById(selectedId);
                    numSala = Integer.parseInt(radioButao.getText().toString());

                    autor = dataSnapshot.child("nome").getValue().toString();
                    email = dataSnapshot.child("email").getValue().toString();
                    Boolean accountChecked = Boolean.parseBoolean(dataSnapshot.child("situacaoCadastro").getValue().toString());
                    if (accountChecked == true) {

                        String sala = "Sala "+ numSala;
                        //Invocando as classes
                        Usuario usuario = new Usuario();
                        Pergunta perguntar = new Pergunta();
                        perguntar.setAutor(autor);
                        perguntar.setEmail(email);
                        perguntar.setNumeroSala(sala);
                        perguntar.setNome(nomePalestrante);
                        perguntar.setMensagem(pergunta);


                        DatabaseReference reference = database.getReference("Perguntas/"+sala).push();
                        reference.setValue(perguntar);

                        txtNomePalestrante.setText("");
                        txtMensagem.setText("");
                        Toast.makeText(PerguntePalestrante.this, "Mensagem enviada com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(PerguntePalestrante.this, "Você precisa validar sua conta para usar essa funcionalidade!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(PerguntePalestrante.this, Menu.class);
        startActivity(intent);
        finish();


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    numSala = 1;
                    break;
            case R.id.radioButton2:
                if (checked)
                    numSala = 2;
                    break;
                case R.id.radioButton3:
                if (checked)
                    numSala = 3;
                    break;
                case R.id.radioButton4:
                if (checked)
                    numSala = 4;
                    break;
                case R.id.radioButton5:
                if (checked)
                    numSala = 5;
                    break;
                case R.id.radioButton6:
                if (checked)
                    numSala = 6;
                    break;
        }
    }



}
