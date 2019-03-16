package inc.congressofozabnc2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import inc.congressofozabnc2019.Firebase.Pergunta;


public class Sala1 extends AppCompatActivity {


    private Button btnBuscar;
    private Button expertsNacionais,expertsInternacionais,btnVoltar;
    private LinearLayout linearBuscarPalestrante;
    private EditText buscarPalestrante;
    private Button buscar,btn_site_agencia;
    private RecyclerView mResultList;
    private DatabaseReference reference,reference2;
    RecyclerView recyclerView,recyclerView2;
    final int ITEM_LOAD_COUNT = 21;
    int total_item = 0, last_visible_item;

    Button btn_home,btn_voltar;
    ArrayList<Pergunta> list;
    ArrayList<Pergunta>list2;
    MyAdapterPergunta adapter;
    MyAdapterPergunta adapter2;
    boolean isLoading = false, isMaxData = false;

    String last_node="",last_key="";
    LinearLayoutManager layoutManager;
    String sala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala1);

        btn_home = findViewById(R.id.btn_home);
        btn_voltar = findViewById(R.id.btn_voltar);
        recyclerView2 = (RecyclerView) findViewById(R.id.id_recycler2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        //Fazendo cast dos botões Experts


        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sala1.this, Menu.class);
                startActivity(intent);
                finish();

            }
        });
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onBackPressed();

            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                Toast.makeText(this, "Houve um erro na transmissão dos dos dados", Toast.LENGTH_SHORT).show();
            } else {
                sala = extras.getString("sala");

            }
        } else {
            sala = (String) savedInstanceState.getSerializable("sala");

        }
        reference2 = FirebaseDatabase.getInstance().getReference().child("Perguntas/"+sala);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<Pergunta>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Pergunta p = dataSnapshot1.getValue(Pergunta.class);
                    list2.add(p);

                }

                adapter2 = new MyAdapterPergunta(Sala1.this, list2);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Sala1.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Sala1.this, PerguntePalestrante.class);
        startActivity(intent);
        finish();


    }
}
