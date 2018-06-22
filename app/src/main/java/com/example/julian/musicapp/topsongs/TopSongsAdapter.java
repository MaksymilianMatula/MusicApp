package com.example.julian.musicapp.topsongs;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.julian.musicapp.api.TrendingSingle;
import com.example.user.myapplication.R;

import java.util.List;

public class TopSongsAdapter extends RecyclerView.Adapter<TopSongsAdapter.TopSongsViewHolder> {

    List<TrendingSingle> singles;

    public TopSongsAdapter(List<TrendingSingle> singles) {
        this.singles = singles;
    }

    @Override
    public TopSongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_top_songs, parent, false);

        return new TopSongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopSongsViewHolder holder, int position) {

        final TrendingSingle single = singles.get(position);

        holder.tvPlace.setText(String.valueOf(single.intChartPlace));
        holder.tvTrack.setText(single.strTrack);
        holder.tvArtist.setText(single.strArtist);
        holder.tvAlbum.setText(single.strAlbum);

        holder.llContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SongDetailsActivity.class);
                intent.putExtra(SongDetailsActivity.TRACK, single.strTrack);
                intent.putExtra(SongDetailsActivity.ARTIST, single.strArtist);
                intent.putExtra(SongDetailsActivity.TRACK_ID, single.idTrack);

                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return singles == null ? 0 : singles.size();
    }

    public class TopSongsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llContainer;
        TextView tvPlace;
        TextView tvTrack;
        TextView tvArtist;
        TextView tvAlbum;

        public TopSongsViewHolder(View itemView) {
            super(itemView);

            llContainer = itemView.findViewById(R.id.llContainer);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvTrack = itemView.findViewById(R.id.tvTrack);
            tvArtist = itemView.findViewById(R.id.tvArtist);
            tvAlbum = itemView.findViewById(R.id.tvAlbum);
        }
    }

}