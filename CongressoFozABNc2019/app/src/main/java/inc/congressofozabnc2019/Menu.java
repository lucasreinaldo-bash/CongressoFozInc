package inc.congressofozabnc2019;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import inc.congressofozabnc2019.R;
public class Menu extends AppCompatActivity {

    private Button btn_cientifico,btn_voltar,btn_patrocinadores_expositores,btn_perguntePalestrante, btn_palestrante, btnVerQr, btnConcluirInscricao, btn_comochegar, btn_programa_social, btn_mapa_congresso, btn_enquete, btn_explorar_foz;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    String situacaoCadastroUsuario2, situacaoCadastroUsuario;
    private String situacaoCadastro, tipoDoUsuario, emailUsuario, nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);




        btn_cientifico = findViewById(R.id.btn_programa);
        btn_perguntePalestrante = findViewById(R.id.btn_perguntePalestrante);
        btn_palestrante = findViewById(R.id.btn_palestrantes);
        btn_comochegar = findViewById(R.id.btn_comochegar);
        btn_programa_social = findViewById(R.id.btn_programa_social);
        btn_mapa_congresso = findViewById(R.id.btn_mapadocongresso);
        btnVerQr = findViewById(R.id.btn_ver_qr);
        btnConcluirInscricao = findViewById(R.id.btn_concluir_inscricao);
        btn_enquete = findViewById(R.id.btn_enquete);
        btn_patrocinadores_expositores = findViewById(R.id.btn_patrocinadores_expositores);
        btn_explorar_foz = findViewById(R.id.btn_explorar_foz);
        btn_voltar = findViewById(R.id.btn_voltar);


        //Firebase
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        databaseReference = database.getReference("Usuario/" + currentUser.getUid());




        //Toast.makeText(this, situacaoCadastroUsuario, Toast.LENGTH_SHORT).show();






        btn_cientifico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ProgramaCientifico.class);
                startActivity(intent);
                finish();
            }
        });
        btn_perguntePalestrante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, PerguntePalestrante.class);
                startActivity(intent);
                finish();
            }
        });
        btn_explorar_foz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ExplorarFoz.class);
                startActivity(intent);
                finish();
            }
        });
        btn_patrocinadores_expositores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, PatrocinadoresExpositores.class);
                startActivity(intent);
                finish();
            }
        });

        btnConcluirInscricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, QRMenu.class);
                startActivity(intent);
                finish();
            }
        });
        btn_palestrante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Palestrante.class);
                startActivity(intent);
                finish();
            }
        });
        btn_comochegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Maps.class);
                startActivity(intent);
                finish();
            }
        });
        btn_programa_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ProgramaSocial.class);
                startActivity(intent);
                finish();
            }
        }); btn_mapa_congresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MapaCongresso.class);
                startActivity(intent);
                finish();
            }
        });btn_enquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MainEnquete.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
