package com.po.wadim.projectonerem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityOfListStation extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] namesOfStation, imagesOfStation;
    static String TAG_FOR_SEND_STATION = "tag_for_send_station";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_of_list_station);

        namesOfStation = getResources().getStringArray(R.array.listOfNames);
        imagesOfStation = getResources().getStringArray(R.array.listOfImages);

        recyclerView = (RecyclerView) findViewById(R.id.RVListOfStation);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StationAdapter(namesOfStation));


    }

    private class StationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView IVImageOfStation;
        TextView TVTitleOfStation;

        public StationViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_of_list_station, viewGroup, false));

            TVTitleOfStation = itemView.findViewById(R.id.TVTitleOfStation);
            IVImageOfStation = itemView.findViewById(R.id.IVImageOfStation);

            itemView.setOnClickListener(this);
        }

        public void bind(String title, String nameOfImage) {
            TVTitleOfStation.setText(title);
            IVImageOfStation.setImageResource(getResources().getIdentifier(nameOfImage, "drawable", getPackageName()));

        }

        @Override
        public void onClick(View view) {
            int itemId = getAdapterPosition();
            Intent intent = new Intent(ActivityOfListStation.this, ActivityPlayer.class);
            intent.putExtra(TAG_FOR_SEND_STATION, itemId);
            startActivity(intent);
        }
    }

    public class StationAdapter extends RecyclerView.Adapter<StationViewHolder> {
        String[] namesOfStation;

        public StationAdapter(String[] namesOfStation) {
            this.namesOfStation = namesOfStation;
        }

        @Override
        public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActivityOfListStation.this);
            return new StationViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(StationViewHolder holder, int position) {
            holder.bind(namesOfStation[position], imagesOfStation[position]);
        }

        @Override
        public int getItemCount() {
            return namesOfStation.length;
        }
    }
}
