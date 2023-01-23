package subhamjit.sls.ai.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import subhamjit.sls.ai.R;


public class MainActivity2 extends AppCompatActivity {
	
	
	private WebView webview1;
	ImageView back;
	private Window wind;
	private TextToSpeech TexToSpeech;
	private SpeechRecognizer SpeechToTxt;
	private Timer _timer = new Timer();
	private TimerTask timer;

	private MediaPlayer mediaPlayer;

	private double startTime = 0;
	private double finalTime = 0;
	private Handler myHandler = new Handler();;
	private int forwardTime = 5000;
	private int backwardTime = 5000;
	public static int oneTimeOnly = 0;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main2);
		initialize(_savedInstanceState);





		/*IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		MyReceiver mReceiver = new MyReceiver (this);
		registerReceiver(mReceiver, filter);*/

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
				|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
				|| ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
		} else {
			initializeLogic();
		}



	}

	@Override
	protected void onResume() {
		super.onResume();

	}




	private void initialize(Bundle _savedInstanceState) {


	}
	@Override
	protected void onPause() {
		super.onPause();
	}



	private void initializeLogic() {





	}



	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	@Override
	public void onBackPressed() {

	finish();
	}
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

	
}
