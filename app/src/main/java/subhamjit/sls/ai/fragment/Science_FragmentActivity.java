package subhamjit.sls.ai.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import subhamjit.sls.ai.R;

public class Science_FragmentActivity extends Fragment {
	
	private LinearLayout linear1;
	private TextView textview1;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout._science, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
//		linear1 = _view.findViewById(R.id.linear1);
//		textview1 = _view.findViewById(R.id.textview1);
	}
	
	private void initializeLogic() {
	}
	
}
