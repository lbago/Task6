package androidlab.epam.com.task6;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private List<Character> characters;

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView word;
        TextView translation;
        ImageView characterImage;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            word = (TextView) itemView.findViewById(R.id.word);
            translation = (TextView) itemView.findViewById(R.id.translation);
            characterImage = (ImageView) itemView.findViewById(R.id.character);
        }
    }

    public RVAdapter(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.word.setText(characters.get(position).word);
        holder.translation.setText(characters.get(position).translation);
        holder.characterImage.setImageResource(characters.get(position).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
