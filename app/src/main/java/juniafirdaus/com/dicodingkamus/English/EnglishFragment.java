package juniafirdaus.com.dicodingkamus.English;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.Objects;

import juniafirdaus.com.dicodingkamus.ArtiActivity;
import juniafirdaus.com.dicodingkamus.Kamus;
import juniafirdaus.com.dicodingkamus.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFragment extends Fragment implements TextWatcher, AdapterView.OnItemClickListener  {

    private ListView lv;
    private DatabaseHelperEnglish dbHelper;
    private ArrayAdapter<Kamus> adapter;
    public static final String EXTRAS = "extras";
    public EnglishFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_english, container, false);
        lv = view.findViewById(R.id.lv_data);
        lv.setEmptyView(view.findViewById(R.id.empty));
        EditText search = view.findViewById(R.id.search);

        dbHelper = DatabaseHelperEnglish.getInstance(getContext());

        setData();

        search.addTextChangedListener(this);
        lv.setOnItemClickListener(this);

        return view;
    }
    private void setData() {
        List<Kamus> listKamus = dbHelper.getAllKamus();

        adapter = new ArrayAdapter<>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_expandable_list_item_1, listKamus);
        lv.setAdapter(adapter);

    }

    @Override
    public void afterTextChanged(Editable arg0) {
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
    }

    @Override
    public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
        adapter.getFilter().filter(s.toString());
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long id) {
        // TODO Auto-generated method stub
        Bundle b = new Bundle();
        b.putString("nama", Objects.requireNonNull(adapter.getItem(position)).getIstilah());
        b.putString("arti", Objects.requireNonNull(adapter.getItem(position)).getArti());

        Intent i = new Intent(this.getContext(), ArtiActivity.class);
        i.putExtras(b);
        startActivity(i);

    }

}
