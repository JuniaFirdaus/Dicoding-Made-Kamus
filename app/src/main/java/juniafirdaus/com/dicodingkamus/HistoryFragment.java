package juniafirdaus.com.dicodingkamus;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    public static final String EXTRAS = "extras";
    public static final String KEYPREFF="key";
    public static final String KEYNAMA ="nama";
    public static final String KEYARTI ="arti";
    TextView ShA, ShB;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ShA = view.findViewById(R.id.SA);
        ShB = view.findViewById(R.id.SB);


        SharedPreferences sharedPreferences =Objects.requireNonNull(this.getActivity()).getSharedPreferences(KEYPREFF, Context.MODE_PRIVATE);
        String edit = sharedPreferences.getString(KEYNAMA, null);
        String edit2 = sharedPreferences.getString(KEYARTI, null);

        ShA.setText(edit);
        ShB.setText(edit2);
        return view;
    }

}
