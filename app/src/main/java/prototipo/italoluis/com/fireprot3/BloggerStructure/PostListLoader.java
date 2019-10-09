package prototipo.italoluis.com.fireprot3.BloggerStructure;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import prototipo.italoluis.com.fireprot3.APIs.APIBlogger;
import prototipo.italoluis.com.fireprot3.AutorizacaoActivity;
import prototipo.italoluis.com.fireprot3.BlogModel.Item;
import prototipo.italoluis.com.fireprot3.BlogModel.PostList;
import prototipo.italoluis.com.fireprot3.ListaAutoresActivity;
import prototipo.italoluis.com.fireprot3.R;
import prototipo.italoluis.com.fireprot3.SendInviteActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListLoader extends AppCompatActivity {
    private FloatingActionButton fab_main, fab1_quest, fab2_invite, fab3_author, fab4_autorizacao;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    private TextView txt_quest, txt_invite, txt_author, txt_autoziracao;
    private Boolean isOpen = false;
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private int idCVEscolhido;
    private List<Item> items = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.listadepost);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostAdapter(this, items);
        recyclerView.setAdapter(adapter);
        idCVEscolhido = getIntent().getIntExtra("cardview escolhido", 0);
        txt_quest =  findViewById(R.id.txt_questionario);
        txt_invite =  findViewById(R.id.txt_indicacao);
        txt_author =  findViewById(R.id.txt_autores);
        txt_autoziracao = findViewById(R.id.txt_autorizacao);

        fabConfig();
        getData();
        fabMainAction();
        fabOnClick();
    }

    private void fabOnClick(){
        fab1_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostListLoader.this, Questionarios.class);
                startActivity(intent);
                closeFloatActionButton();
            }
        });

        fab2_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostListLoader.this, SendInviteActivity.class);
                startActivity(intent);
                closeFloatActionButton();
            }
        });

        fab3_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostListLoader.this, ListaAutoresActivity.class);
                startActivity(intent);
                closeFloatActionButton();
            }
        });

        fab4_autorizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostListLoader.this, AutorizacaoActivity.class);
                startActivity(intent);
                closeFloatActionButton();
            }
        });
    }

    private void fabConfig(){
        fab_main = findViewById(R.id.fabmain);
        fab1_quest = findViewById(R.id.fab1);
        fab2_invite = findViewById(R.id.fab2);
        fab3_author = findViewById(R.id.fab3);
        fab4_autorizacao = findViewById(R.id.fab4);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotacao2);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotacao1);
    }

    private void getData(){
        Call<PostList> postList = APIBlogger.getService().getPostList();

        postList.enqueue(new Callback<PostList>() {

            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                items.addAll(list.getItems());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(new PostAdapter(PostListLoader.this, list.getItems()));
                Toast.makeText(PostListLoader.this, "Efetuado com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(PostListLoader.this, "Não foi possível carregar a página. " +
                        "Verifique sua internet e tente novamente.", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void fabMainAction(){

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {
                    closeFloatActionButton();
                } else {
                    openFloatActionButton();
                }


            }
        });
    }

    private void openFloatActionButton(){
        fab1_quest.startAnimation(fab_open);
        fab2_invite.startAnimation(fab_open);
        fab3_author.startAnimation(fab_open);
        fab4_autorizacao.startAnimation(fab_open);
        fab_main.startAnimation(fab_clock);
        fab1_quest.setClickable(true);
        fab2_invite.setClickable(true);
        fab3_author.setClickable(true);
        fab4_autorizacao.setClickable(true);
        txt_author.setVisibility(View.VISIBLE);
        txt_author.startAnimation(fab_open);
        txt_invite.setVisibility(View.VISIBLE);
        txt_invite.startAnimation(fab_open);
        txt_quest.setVisibility(View.VISIBLE);
        txt_quest.startAnimation(fab_open);
        txt_autoziracao.setVisibility(View.VISIBLE);
        txt_autoziracao.startAnimation(fab_open);
        isOpen = true;
    }

    private void closeFloatActionButton(){
        fab1_quest.startAnimation(fab_close);
        fab2_invite.startAnimation(fab_close);
        fab3_author.startAnimation(fab_close);
        fab4_autorizacao.startAnimation(fab_close);
        fab_main.startAnimation(fab_anticlock);
        fab1_quest.setClickable(false);
        fab2_invite.setClickable(false);
        fab3_author.setClickable(false);
        fab4_autorizacao.setClickable(false);
        txt_author.setVisibility(View.INVISIBLE);
        txt_author.startAnimation(fab_close);
        txt_invite.setVisibility(View.INVISIBLE);
        txt_invite.startAnimation(fab_close);
        txt_quest.setVisibility(View.INVISIBLE);
        txt_quest.startAnimation(fab_close);
        txt_autoziracao.setVisibility(View.INVISIBLE);
        txt_autoziracao.startAnimation(fab_close);
        isOpen = false;
    }
}