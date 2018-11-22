package anil.sardiwal.fbreboundrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import anil.sardiwal.reboundrecycler.ReboundRecycler;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);


        // init reboundrecycler
        ReboundRecycler.init(recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        // Fake data
        ArrayList<Integer> ints = new ArrayList<>();

        for(int j = 0; j < 1000; j++)
        {
            ints.add(j);
        }

        RecyclerView.Adapter adapter = new NewAdapter(this, ints);
        recyclerView.setAdapter(adapter);
    }

    class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder>
    {
        private Context context;
        private ArrayList<Integer> dataset;

        public NewAdapter(Context context, ArrayList<Integer> dataset) {
            this.context = context;
            this.dataset = dataset;
        }

        @NonNull
        @Override
        public NewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.single, viewGroup, false);
            ReboundRecycler.first(constraintLayout);

            return new ViewHolder(constraintLayout);
        }

        @Override
        public void onBindViewHolder(@NonNull NewAdapter.ViewHolder viewHolder, int i) {
            viewHolder.textView.setText(String.valueOf(dataset.get(i)));
            ReboundRecycler.bind(viewHolder.itemView, i);
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textview);
            }
        }
    }
}
