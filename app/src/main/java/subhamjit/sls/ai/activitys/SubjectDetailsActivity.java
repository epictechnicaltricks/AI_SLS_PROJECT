package subhamjit.sls.ai.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
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


public class SubjectDetailsActivity extends AppCompatActivity {
	
	
	private WebView webview1;
	ImageView back;
	private Window wind;
	private TextToSpeech TexToSpeech;
	private SpeechRecognizer SpeechToTxt;
	private Timer _timer = new Timer();
	private TimerTask timer;


	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.subject_details);
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
	/*	*//****** block is needed to raise the application if the lock is *********//*
		wind = this.getWindow();
		wind.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		wind.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		wind.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		*//* ^^^^^^^block is needed to raise the application if the lock is*/

	}




	private void initialize(Bundle _savedInstanceState) {
		
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		back = findViewById(R.id.back);

		TexToSpeech = new TextToSpeech(getApplicationContext(), null);
		SpeechToTxt = SpeechRecognizer.createSpeechRecognizer(this);


		SpeechToTxt.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle params) {

			}

			@Override
			public void onBeginningOfSpeech() {

			}

			@Override
			public void onRmsChanged(float rmsdB) {

			}

			@Override
			public void onBufferReceived(byte[] buffer) {

			}

			@Override
			public void onEndOfSpeech() {

			}

			@Override
			public void onError(int error) {

				final String _errorMessage;
				switch (error) {
					case SpeechRecognizer.ERROR_AUDIO:
						_errorMessage = "audio error";
						break;

					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
						_errorMessage = "speech timeout";
						start_listen();
						break;

					case SpeechRecognizer.ERROR_NO_MATCH:
						_errorMessage = "speech no match";
						start_listen();
						break;

					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
						_errorMessage = "recognizer busy";
						break;

					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
						_errorMessage = "recognizer insufficient permissions";
						break;

					default:
						_errorMessage = "recognizer other error";
						break;
				}
				Toast.makeText(SubjectDetailsActivity.this, _errorMessage, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);

				//Toast.makeText(MainActivity.this, _result, Toast.LENGTH_SHORT).show();



				if(_result.toLowerCase().contains("odia")) {
					startActivity(new Intent(getApplicationContext(), OdiaActivity.class));
					
					//	load_url("https://preeminent-semolina-503ae4.netlify.app/odia.html");

				}else if(_result.toLowerCase().contains("english")) {
					startActivity(new Intent(getApplicationContext(), EnglishActivity.class));
				
					//	load_url("https://preeminent-semolina-503ae4.netlify.app/english.html");

				} else if(_result.toLowerCase().contains("back")) {
					finish();

				} else if(_result.toLowerCase().contains("home")) {
					startActivity(new Intent(getApplicationContext(), MainActivity.class));
				} else {
					startActivity(new Intent(getApplicationContext(), SubjectActivity.class));

				}

				start_listen();






			}

			@Override
			public void onPartialResults(Bundle partialResults) {

			}

			@Override
			public void onEvent(int eventType, Bundle params) {

			}
		});




		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;


				super.onPageFinished(_param1, _param2);
			}
		});
	}

	private void start_listen()
	{
		if (TexToSpeech.isSpeaking()) {
			SpeechToTxt.stopListening();
			Toast.makeText(this, "Speaking", Toast.LENGTH_SHORT).show();
		}
		else {

			SpeechToTxt.stopListening();

			Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			_intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
			_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
			SpeechToTxt.startListening(_intent);
			Toast.makeText(this, "Listening..", Toast.LENGTH_SHORT).show();

		}
	}

	private void load_url(final String _url)
	{

		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						//Toast.makeText(MainActivity.this, _url, Toast.LENGTH_SHORT).show();
						webview1.loadUrl(_url.trim());

					}
				});
			}
		};
		_timer.schedule(timer, (int)(1000));
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		SpeechToTxt.stopListening();
	}

	private void initializeLogic() {





		String speak_text  = "This is chapter details  ?";
		_speak(speak_text);



		Snackbar.make(findViewById(android.R.id.content), speak_text, Snackbar.LENGTH_LONG)
				.setAction("", new OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				})
				.setActionTextColor(Color.WHITE)
				.show();

          start_listen();




		// this is for desktop view
		// code by Shubhamjeet
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if(webview1.canGoBack())
				{
					webview1.goBack();
				}

			}
		});


		webview1.getSettings().setLoadWithOverviewMode(true);
		webview1.getSettings().setUseWideViewPort(true);
		final WebSettings webSettings = webview1.getSettings();
		final String newUserAgent;
		newUserAgent = "Mozilla/5.0 (Android) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
		webSettings.setUserAgentString(newUserAgent);

		// offline
		//webview1.loadUrl("file:///android_asset/rto/subhamjit.sls.ai/index.html");


		webview1.loadUrl("https://preeminent-semolina-503ae4.netlify.app/");


	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	public void _speak(String _speak1) {

		TexToSpeech.speak(_speak1, TextToSpeech.QUEUE_ADD, null);
		TexToSpeech.setSpeechRate((float)1);
		TexToSpeech.setPitch((float)1);

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
