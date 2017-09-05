package harsh.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import harsh.recyclerviewdemo.Adapter.ChatAdapter;
import harsh.recyclerviewdemo.Model.ChatModel;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class Users extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ChatModel> chatlist;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ChatAdapter adapter;
    private ProgressBar googleprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
        //Creating a Blank list in Memory
        chatlist = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new ChatAdapter(chatlist);
        recyclerView.setAdapter(adapter);
        googleprogress = (ProgressBar) findViewById(R.id.google_progress);
        SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        recyclerView.setItemAnimator(animator);
        recyclerView.getItemAnimator().setAddDuration(1000);
        //Setup listener
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatlist.clear();
                int position = 0;
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        chatlist.add(new ChatModel(snapshot));
                        adapter.notifyDataSetChanged();
                        adapter.notifyItemInserted(position);
                        position++;

                    }
                    Toast.makeText(Users.this, "Data Loaded Succesfully", Toast.LENGTH_SHORT).show();
                    ChatAdapter adapter = new ChatAdapter(chatlist);
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(Users.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
                googleprogress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
