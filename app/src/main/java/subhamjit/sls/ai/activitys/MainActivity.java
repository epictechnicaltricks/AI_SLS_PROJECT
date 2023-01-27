package subhamjit.sls.ai.activitys;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.*;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.webkit.*;

import java.util.*;

import android.webkit.WebView;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import subhamjit.sls.ai.R;
import subhamjit.sls.ai.Util;


public class MainActivity extends AppCompatActivity {
	
	
	private WebView webview1;
	ImageView back;
	private Window wind;
	private TextToSpeech TexToSpeech;
	private SpeechRecognizer SpeechToTxt;
	private Timer _timer = new Timer();


	private TimerTask timer;

	private MediaPlayer mediaPlayer;


	private String fontName = "";
	private final String typeace = "";


	private double startTime = 0;
	private double finalTime = 0;
	private Handler myHandler = new Handler();;
	private int forwardTime = 5000;
	private int backwardTime = 5000;
	public static int oneTimeOnly = 0;

	ImageView drawer_open;
	private DrawerLayout _drawer;

	private ViewPager viewpager1;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private TimerTask scroll_time;
	int count=0;


	//  Google login
	GoogleSignInClient mGoogleSignInClient;
	int RC_SIGN_IN=123;

 // drawer layout id

	private ImageView _drawer_profile_img;

	private TextView _drawer_name, _drawer_email;
	LinearLayout  _drawer_logout;



	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);

		_changeActivityFont("en_med");

		//google login
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestEmail()
				.build();
		mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


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

		_slider();

		_hide();



		//google  login
		GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MainActivity.this);

		if (acct != null) {   //google login
			String personName = acct.getDisplayName();
			String personEmail = acct.getEmail();
			Uri personPhoto = acct.getPhotoUrl();
			String personGivenName = acct.getGivenName();
			String personId = acct.getId();

			if(personPhoto!=null) {
				Glide.with(getApplicationContext()).load(personPhoto).into(_drawer_profile_img);
			}
			//showMessage(personEmail);

			Log.d("Google login details",personPhoto+"\n"+personEmail+"\n"+personName);


			_drawer_name.setText(personName);
			_drawer_email.setText(personEmail);
			//_drawer_phone_no.setText(personId);  //this is person id if google login used

		}





		if(mediaPlayer.isPlaying())
		{
			SpeechToTxt.stopListening();
		}
		SpeechToTxt.stopListening();

		mediaPlayer.start();

		finalTime = mediaPlayer.getDuration();
		startTime = mediaPlayer.getCurrentPosition();

		String speak_text  = "Select a language ?";

		//	_speak(speak_text);
		//TexToSpeech.setSpeechRate((float)1);
		//TexToSpeech.setPitch((float)1);


		Snackbar.make(findViewById(android.R.id.content), speak_text, Snackbar.LENGTH_LONG)
				.setAction("", new OnClickListener() {
					@Override
					public void onClick(View v) {}
				})
				.setActionTextColor(Color.WHITE)
				.show();



		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						start_listen();

					}
				});
			}
		};
		_timer.schedule(timer, (int)(15000));

		//\\/\\//\////\\///\\/\/\//\\/\/\/\\\\//\/\//\\\//\\//\/\//\/\/\\//\//\//\
	/*	*//****** block is needed to raise the application if the lock is *********//*
		wind = this.getWindow();
		wind.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		wind.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		wind.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		*//* ^^^^^^^block is needed to raise the application if the lock is*/

	}




	private void initialize(Bundle _savedInstanceState) {

		mediaPlayer = MediaPlayer.create(this, R.raw.arjun);



		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		back = findViewById(R.id.back);
		_drawer = findViewById(R.id._drawer);
		drawer_open  = findViewById(R.id.drawer_open);
		viewpager1 = findViewById(R.id.viewpager1);

		drawer_open.setColorFilter(0xFFFFFFFF);
		LinearLayout _nav_view = findViewById(R.id._nav_view);

		_drawer_profile_img  = _nav_view.findViewById(R.id.profile_image_1);
		_drawer_name  = _nav_view.findViewById(R.id.name_1);
		_drawer_email = _nav_view.findViewById(R.id.id_1);

		_drawer_logout = _nav_view.findViewById(R.id.logout);

		_drawer_logout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

            Google_signOut();


			}
		});


		drawer_open.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {


					_drawer.openDrawer(GravityCompat.START);



			}
		});

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

				////Toast.makeText(getApplicationContext(), _errorMessage, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);

				Toast.makeText(MainActivity.this, _result, Toast.LENGTH_SHORT).show();

				if(_result.toLowerCase().contains("odia")) {
					startActivity(new Intent(getApplicationContext(), MainActivity2.class));
					//	load_url("https://preeminent-semolina-503ae4.netlify.app/odia.html");

				}else if(_result.toLowerCase().contains("english")) {
					startActivity(new Intent(getApplicationContext(), MainActivity2.class));
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


	public void activity_open(View view) {
		startActivity(new Intent(getApplicationContext(), MainActivity2.class));
	}

	public void activity_open2(View view) {
		startActivity(new Intent(getApplicationContext(), SubjectActivity.class));
	}
	private void start_listen() {

		if (TexToSpeech.isSpeaking()) {
			SpeechToTxt.stopListening();
			Toast.makeText(this, "Speaking", Toast.LENGTH_SHORT).show();
		}
		else {

			if(!mediaPlayer.isPlaying())
			{
				SpeechToTxt.stopListening();
				Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				_intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
				_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
				SpeechToTxt.startListening(_intent);
				Toast.makeText(this, "Listening..", Toast.LENGTH_SHORT).show();
			}


		}
	}



	private void load_url(final String _url) {

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

	private void Google_signOut() {
		mGoogleSignInClient.signOut()
				.addOnCompleteListener(this, new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						Intent i = new Intent();
						i.setClass(getApplicationContext(), SplashActivity.class);
						startActivity(i);
						finish();
						Toast.makeText(MainActivity.this, "Logout success.", Toast.LENGTH_SHORT).show();
					}
				});
	}

	public void _hide () {
		try{

			getSupportActionBar().hide();

		} catch (Exception e){}
		//mahdi_313
	}


	private void initializeLogic() {




		if (oneTimeOnly == 0) {

			oneTimeOnly = 1;
		}










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

	public class Viewpager1Adapter extends PagerAdapter {
		Context _context;
		ArrayList<HashMap<String, Object>> _data;
		public Viewpager1Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
			_context = _ctx;
			_data = _arr;
		}

		public Viewpager1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_context = getApplicationContext();
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public boolean isViewFromObject(View _view, Object _object) {
			return _view == _object;
		}

		@Override
		public void destroyItem(ViewGroup _container, int _position, Object _object) {
			_container.removeView((View) _object);
		}

		@Override
		public int getItemPosition(Object _object) {
			return super.getItemPosition(_object);
		}

		@Override
		public CharSequence getPageTitle(int pos) {
			// use the activitiy event (onTabLayoutNewTabAdded) in order to use this method
			return "page " + pos;
		}

		@Override
		public  Object instantiateItem(ViewGroup _container,  final int _position) {
			View _view = LayoutInflater.from(_context).inflate(R.layout.slider, _container, false);

			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);

			try {

				Glide.with(getApplicationContext())
						.load(Uri.parse(Objects.requireNonNull(listmap.get(_position).get("img_url")).toString()))
						.thumbnail(0.01f)
						.into(imageview1);

			}catch (Exception e) {
				showMessage("823 Line \n\n"+ e);
			}
			_container.addView(_view);
			return _view;
		}
	}


	public void _slider () {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("img_url", "https://media.istockphoto.com/id/1349104991/photo/e-learning-online-education-concept.jpg?b=1&s=170667a&w=0&k=20&c=OeYLvIbs1nXT4HC5lvYypLWRULv-CarzhMcpPbSIv3M=");
			listmap.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("img_url", "https://media.istockphoto.com/id/1227150854/vector/learn-from-distance-with-teacher-online-education-kids-boy-and-girl-is-sitting-on-laptop-and.jpg?s=612x612&w=0&k=20&c=wPYqFgjGpi9m6BWwKw-Bsz76oqfd0m-hFJcxNyccyMM=");
			listmap.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("img_url", "https://www.shutterstock.com/image-vector/mobile-education-concept-student-learning-260nw-2076982819.jpg");
			listmap.add(_item);
		}

		//Toast.makeText(this, listmap.size()+"", Toast.LENGTH_SHORT).show();
		//final float scaleFactor = 0.96f; viewpager1.setPageMargin(-15); viewpager1.setOffscreenPageLimit(2); viewpager1.setPageTransformer(false, new ViewPager.PageTransformer() { @Override public void transformPage(@NonNull View page1, float position) { page1.setScaleY((1 - Math.abs(position) * (1 - scaleFactor))); page1.setScaleX(scaleFactor + Math.abs(position) * (1 - scaleFactor)); } });
		viewpager1.setAdapter(new Viewpager1Adapter(listmap));

		//viewpager1.setAdapter(new Viewpager1Adapter(listmap));

		scroll_time = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						if (count > listmap.size()) {
							count = 0;
						}

						viewpager1.setCurrentItem(count);
						count++;


					}
				});
			}
		};
		_timer.scheduleAtFixedRate(scroll_time, 6000, 6000);
	}


	public void _changeActivityFont (final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView());
	}
	private void overrideFonts(final Context context, final View v) {

		try {
			Typeface
					typeace = Typeface.createFromAsset(getAssets(), fontName);
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
					 i < vg.getChildCount();
					 i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}
			else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}
				else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}
					else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)

		{
			Util.showMessage(getApplicationContext(), "Error Loading Font");
		}
	}



	public void _speak(String _speak1) {

		TexToSpeech.speak(_speak1, TextToSpeech.QUEUE_ADD, null);
		TexToSpeech.setSpeechRate((float)1);
		TexToSpeech.setPitch((float)1);

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
