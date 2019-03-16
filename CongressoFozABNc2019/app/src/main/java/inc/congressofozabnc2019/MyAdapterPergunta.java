package inc.congressofozabnc2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import inc.congressofozabnc2019.Firebase.Pergunta;


public class MyAdapterPergunta extends RecyclerView.Adapter<MyAdapterPergunta.MyViewHolder> {

    Context context;
    ArrayList<Pergunta> Experts;

    public MyAdapterPergunta(Context c , ArrayList<Pergunta> p)
    {
        context = c;
        Experts = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.lista_pergunta,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.palestrante.setText(Experts.get(position).getNome());
        holder.numeroSala.setText(Experts.get(position).getNumeroSala());
        holder.autor.setText(Experts.get(position).getAutor());
        holder.email.setText(Experts.get(position).getEmail());
        holder.mensagem.setText(Experts.get(position).getMensagem());



    }

    @Override
    public int getItemCount() {
        return Experts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView numeroSala,palestrante,autor,mensagem,email;
        ImageView profilePic;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            palestrante = (TextView) itemView.findViewById(R.id.profile_nome);
            numeroSala = (TextView) itemView.findViewById(R.id.profile_numero);
            autor = (TextView) itemView.findViewById(R.id.profile_autor);
            email = (TextView) itemView.findViewById(R.id.profile_email);
            mensagem = (TextView) itemView.findViewById(R.id.profile_mensagem);

           // btn = (Button) itemView.findViewById(R.id.checkDetails);
        }

        }
    }

