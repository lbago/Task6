package androidlab.epam.com.task6;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends Fragment {
    private List<Character> characters;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, null);
        initializeData();
        initializeAdapter();

        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        rv = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && fab.isShown()) {
                    fab.hide();
                }
                if (dy < 0 && !fab.isShown()) {
                    fab.show();
                }
            }
        });
        return view;
    }

    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(characters);
        rv.setAdapter(adapter);
    }

    private void initializeData() {
        characters = new ArrayList<>();
        characters.add(new Character("Gatsu", "Месяц", R.drawable.gatsu));
        characters.add(new Character("Hi", "День, солнце", R.drawable.hi));
        characters.add(new Character("Hito", "Человек", R.drawable.hito));
        characters.add(new Character("Kawa", "Река", R.drawable.kawa));
        characters.add(new Character("Ki", "Дерево", R.drawable.ki));
        characters.add(new Character("Me", "Глаз", R.drawable.me));
        characters.add(new Character("Miru", "Смотреть", R.drawable.miru));
        characters.add(new Character("Misu", "Вода", R.drawable.misu));
        characters.add(new Character("Naka", "Внтури", R.drawable.naka));
        characters.add(new Character("Yama", "Гора", R.drawable.yama));
    }
}
