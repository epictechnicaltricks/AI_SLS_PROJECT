package subhamjit.sls.ai.activitys;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import subhamjit.sls.ai.R;
import subhamjit.sls.ai.RequestNetwork;
import subhamjit.sls.ai.RequestNetworkController;
import subhamjit.sls.ai.Util;


public class AuthActivity extends  AppCompatActivity  {

	private final Timer _timer = new Timer();

	private String fontName = "";
	private final String typeace = "";
	private boolean Current = false;
	private boolean Hiden = false;
	private boolean onStart = false;
	private double count = 0;

	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();

	private LinearLayout linear2;
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private ViewPager viewpager1;
	private LinearLayout linear10,google_layout, facebook_layout;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear7;
	private CheckBox checkbox1;
	private TextView textview1;
	private TextView textview2;
	private TextView textview3;
	private EditText email;
	private TextView textview4;
	private LinearLayout linear9;
	private TextView textview9;
	private LinearLayout linear8;
	private EditText pass;
	private TextView textview5;
	private TextView action_btn;
	private TextView textview7,textview10;
	private ImageView imageview2;
	private EditText edittext3;


	private TimerTask timer;
	private final Intent in = new Intent();
	private TimerTask scroll_time;

	private RequestNetwork login_api;
	private RequestNetwork.RequestListener _login_api_listener;

	private RequestNetwork register_api;
	private RequestNetwork.RequestListener _register_api_listener;


	private RequestNetwork class_api;
	private RequestNetwork.RequestListener _class_api_listener;


	private RequestNetwork department_api;
	private RequestNetwork.RequestListener _department_api_listener;


	private RequestNetwork year_api;
	private RequestNetwork.RequestListener _year_api_listener;


	private RequestNetwork slider_api;
	private RequestNetwork.RequestListener _slider_api_listener;


	private  RequestNetwork college_api;
	private RequestNetwork.RequestListener _college_api_listener;


	private HashMap<String, Object> api_map = new HashMap<>();

	private HashMap<String, Object> api_map2 = new HashMap<>();

	private TextView edittext4_textview_91;


	private HashMap<String, Object> map = new HashMap<>();

	String list="";
	private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
	private long mBackPressed;

	private Spinner spinner_class_name,spinner_department,spinner_year,spinner_blg, spinner_college;

	LinearLayout linear_mname, linear_name, linear_fname, linear_addr, linear_phone, linear_roll,spn_ln0,spn_ln1,spn_ln2,spn_ln3,spn_ln4;

	EditText fname, mname, name, addr, roll,phone_edit;

	TextView
			text_class_name_,
			text_department_,
			text_year_,
			text_blg_,
			dob_text,
	        dob_value,
			fname_text,
			mname_text,
			name_text,
			addr_text,
			roll_text,
	        college_text;


	ArrayList<HashMap<String, Object>> class_list = new ArrayList<>();

	ArrayList<HashMap<String, Object>> year_list = new ArrayList<>();

	ArrayList<HashMap<String, Object>> department_list = new ArrayList<>();

	ArrayList<HashMap<String, Object>> blg_list = new ArrayList<>();

	ArrayList<HashMap<String, Object>> college_list = new ArrayList<>();


	private ListView listview1;
	private ListView listview2;
	private ListView listview3;
	private ListView listview4;
	private ListView listView5;


	String class_name,class_id="";
	String year_name,year_id="";
	String dept_name,dept_id="";
	String blg_name="";
	String college_id;


	///////////////////////////////////////////////////////////





  /**   ░█████╗░██████╗░██╗      ██╗░░░░░██╗███╗░░██╗██╗░░██╗
		██╔══██╗██╔══██╗██║      ██║░░░░░██║████╗░██║██║░██╔╝
		███████║██████╔╝██║      ██║░░░░░██║██╔██╗██║█████═╝░
		██╔══██║██╔═══╝░██║      ██║░░░░░██║██║╚████║██╔═██╗░
		██║░░██║██║░░░░░██║      ███████╗██║██║░╚███║██║░╚██╗
		╚═╝░░╚═╝╚═╝░░░░░╚═╝      ╚══════╝╚═╝╚═╝░░╚══╝╚═╝░░╚═╝


   **/

	   String api = "https://student.mlu.ac.in/api/";
	   String api_mlu = "https://mlu.ac.in/api/v1/";

	   //https://nft.digitallinkcard.xyz/api/
	   //https://student.mlu.ac.in/api/


	//  Google login
	GoogleSignInClient mGoogleSignInClient;
	int RC_SIGN_IN=123;

	TextView google_btn;


	// facebook login
	private CallbackManager callbackManager;
	private LoginManager loginManager;

	////////////////////////////////////////////////

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.auth);
		initialize(_savedInstanceState);



		// google login
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestEmail()
				.build();
		mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


		// facebook login
		FacebookSdk.sdkInitialize(this);
		callbackManager = CallbackManager.Factory.create();
		facebookLogin();

		initializeLogic();
	}

	private void initialize(Bundle _savedInstanceState) {


///////////////////////////////////////////////////////////////////////////////////

		/** ////       API SECTION    /////////////  **/

         class_api = new RequestNetwork(this);
         department_api = new RequestNetwork( this);
         year_api = new RequestNetwork(this);
         slider_api = new RequestNetwork(this);
         college_api = new RequestNetwork(this);

		/** ////       API SECTION    /////////////  **/



		spinner_class_name = findViewById(R.id.spinner_class_name);
		spinner_department = findViewById(R.id.spinner_department);
		spinner_year = findViewById(R.id.spinner_year);
		spinner_blg = findViewById(R.id.spinner_blg);
		spinner_college = findViewById(R.id.spinner_college_name);

		listview1 =  findViewById(R.id.listview_class);
		listview2 =  findViewById(R.id.listview_dept);
		listview3 =  findViewById(R.id.listview_year);
		listview4 =  findViewById(R.id.listview_blg);
		listView5 = findViewById(R.id.listview_college);

		phone_edit = findViewById(R.id.phone_edit);


		google_layout = findViewById(R.id.google_layout);

		facebook_layout = findViewById(R.id.facebook_layout);
		facebook_layout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				loginManager.logInWithReadPermissions(AuthActivity.this, Arrays.asList(
						"email",
						"public_profile",
						"user_birthday"));


				}
		});

		google_btn = findViewById(R.id.google_btn);
		google_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Google_signIn();
			}
		});




		spinner_class_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;

				if(!Objects.requireNonNull(class_list.get(_position).get("id")).toString().equals("")){

					class_id = Objects.requireNonNull(class_list.get(_position).get("id")).toString();
					//showMessage(class_id);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> _param1) {

			}
		});


		spinner_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;

				if(!Objects.requireNonNull(department_list.get(_position).get("id")).toString().equals("")){

					dept_id = Objects.requireNonNull(department_list.get(_position).get("id")).toString();
					//showMessage(dept_id);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> _param1) {

			}
		});


		spinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;


				if(!Objects.requireNonNull(year_list.get(_position).get("id")).toString().equals("")){

					year_id = Objects.requireNonNull(year_list.get(_position).get("id")).toString();
					//showMessage(year_id);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> _param1) {

			}
		});


		spinner_blg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;


				if(!Objects.requireNonNull(blg_list.get(_position).get("name")).toString().equals("Select blood group..")){

					blg_name = Objects.requireNonNull(blg_list.get(_position).get("name")).toString();
					//showMessage(blg_name);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> _param1) {

			}
		});

		spinner_college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int _position, long id) {

				try {
					if(!Objects.requireNonNull(college_list.get(_position).get("college_name")).toString().equals("")){

						college_id = Objects.requireNonNull(college_list.get(_position).get("id")).toString();
						showMessage(college_list.get(_position).get("id").toString());
					}
				}catch (Exception e)
				{

				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});




		_class_api_listener = new RequestNetwork.RequestListener() {
	  @Override
	  public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

		/*  HashMap<String, Object> _item = new HashMap<>();
		  _item.put("name", "Select your class..");
		  _item.put("id", 0);
		  class_list.add(_item);*/



		  try {

			  class_list.clear();

			  class_list = new Gson().fromJson(response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			  // refresh the list or recycle or grid	  Log.d("class_Api",list);



			  listview1.setAdapter(new Listview1Adapter(class_list));
			  ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			  spinner_class_name.setAdapter(new
					  Listview1Adapter(class_list));

		 /* String name = Objects.requireNonNull(class_list.get(0).get("name")).toString();
		  Toast.makeText(AuthActivity.this, name, Toast.LENGTH_SHORT).show();*/





		  }catch (Exception e){

			  showMessage(e.toString());
		  }




	  }

	  @Override
	  public void onErrorResponse(String tag, String message) {

		  showMessage("No internet.");

	  }
  };

  _department_api_listener = new RequestNetwork.RequestListener() {
	  @Override
	  public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
		  Log.d("api depart",response);

		  try {
			  department_list.clear();

			  department_list = new Gson().fromJson(response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			  // refresh the list or recycle or grid


			  listview2.setAdapter(new Listview1Adapter(department_list));
			  ((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
			  spinner_department.setAdapter(new
					  Listview2Adapter(department_list));

		  }catch (Exception e){

			  showMessage(e.toString());
		  }



	  }

	  @Override
	  public void onErrorResponse(String tag, String message) {
		  showMessage("No internet.");
	  }
  };


  _year_api_listener = new RequestNetwork.RequestListener() {
	  @Override
	  public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
		  Log.d("api year",response);

		  try {

			  year_list.clear();

			  year_list = new Gson().fromJson(response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			  // refresh the list or recycle or grid


			  listview2.setAdapter(new Listview1Adapter(year_list));
			  ((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
			  spinner_year.setAdapter(new
					  Listview3Adapter(year_list));


		  }catch (Exception e){

			  showMessage(e.toString());
		  }

	  }

	  @Override
	  public void onErrorResponse(String tag, String message) {



	  }
  };


  _slider_api_listener = new RequestNetwork.RequestListener() {
	  @Override
	  public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
		  Log.d("api slider",response);

         listmap.clear();
		  listmap = new Gson().fromJson(response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

		  _slider();
	  }

	  @Override
	  public void onErrorResponse(String tag, String message) {

	  }
  };


		_register_api_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
				Log.d("login_register", response);


				//Log.d("signup",response);
				if(response.contains("success")) {

					showMessage("Account created");
					showMessage("Login Now");
					success();


				}else {
					failure();
					showMessage("Unable to create account");
				}

			}

			@Override
			public void onErrorResponse(String tag, String message) {

				failure();
				Toast.makeText(AuthActivity.this, "No internet !", Toast.LENGTH_SHORT).show();

			}
		};

		_login_api_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

				///showMessage(""+response);

				Log.d("login",response);

				if(response.contains("avatar")) {

					map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());




					SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
					SharedPreferences.Editor myEdit = sharedPreferences.edit();


					myEdit.putString("name", Objects.requireNonNull(map.get("name")).toString());
					myEdit.putString("userId", Objects.requireNonNull(map.get("userId")).toString());
					myEdit.putString("roleId",  Objects.requireNonNull(map.get("roleId")).toString());
					myEdit.putString("token", Objects.requireNonNull(map.get("token")).toString());
					myEdit.putString("role", Objects.requireNonNull(map.get("role")).toString());
					myEdit.putString("avatar", Objects.requireNonNull(map.get("avatar")).toString());
					myEdit.putString("API", Objects.requireNonNull(api));




					myEdit.apply();


					success();

				} else {

					failure();
					showMessage("Email or password not valid");
				}



			}

			@Override
			public void onErrorResponse(String tag, String message) {

				failure();
				showMessage(message);


			}
		};



		_college_api_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

				Log.d("college_api",response);
				try {
					college_list.clear();
					college_list = new Gson().fromJson(response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					// refresh the list or recycle or grid

					//Toast.makeText(AuthActivity.this, response, Toast.LENGTH_SHORT).show();

					listView5.setAdapter(new Listview5Adapter(college_list));
					((BaseAdapter)listView5.getAdapter()).notifyDataSetChanged();
					spinner_college.setAdapter(new
							Listview5Adapter(college_list));

				}catch (Exception e){

					showMessage("College API error\n"+e.toString());
				}


			}

			@Override
			public void onErrorResponse(String tag, String message) {

			}
		};




		////////////////////////////////////////////////////////////////////////




		linear_mname = findViewById(R.id.linear_mname_);
		linear_name  = findViewById(R.id.linear_name_);
		linear_fname = findViewById(R.id.linear_fname_);

		linear_addr  = findViewById(R.id.linear_addr_);
		linear_phone = findViewById(R.id.linear_phone_);
		linear_roll  = findViewById(R.id.linear_roll_);





		spn_ln0 = findViewById(R.id.spn_ln0);
		spn_ln1 = findViewById(R.id.spn_ln1);
		spn_ln2 = findViewById(R.id.spn_ln2);
		spn_ln3 = findViewById(R.id.spn_ln3);
		spn_ln4 = findViewById(R.id.spn_ln4);




		fname      = findViewById(R.id.fname_);
		mname      = findViewById(R.id.mname_);
		name       = findViewById(R.id.name_);

		addr       = findViewById(R.id.addr_);
		roll       = findViewById(R.id.roll_);



		        dob_text   = findViewById(R.id.text_dob); // textview
				fname_text = findViewById(R.id.text_father_); // textview
				mname_text = findViewById(R.id.text_mother); // textview
				name_text = findViewById(R.id.stu_name); // textview
				addr_text = findViewById(R.id.text_addr); // textview
				roll_text = findViewById(R.id.text_roll_); // textview
		  college_text =  findViewById(R.id.text_college_name_);

		        text_class_name_ = findViewById(R.id.text_class_name_);
				text_department_ = findViewById(R.id.text_department_);
				text_year_ = findViewById(R.id.text_year_);
				text_blg_ = findViewById(R.id.text_blg_);
				dob_value = findViewById(R.id.dob);

		linear2 = findViewById(R.id.linear2);
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		viewpager1 = findViewById(R.id.viewpager1);
		linear10 = findViewById(R.id.linear10);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear7 = findViewById(R.id.linear7);
		checkbox1 = findViewById(R.id.checkbox1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);

		textview10 = findViewById(R.id.textview10);

		email = findViewById(R.id.email);
		textview4 = findViewById(R.id.textview4);
		linear9 = findViewById(R.id.linear9);
		textview9 = findViewById(R.id.textview9);
		linear8 = findViewById(R.id.linear8);
		pass = findViewById(R.id.pass);
		textview5 = findViewById(R.id.textview5);


		action_btn = findViewById(R.id.action_btn);

		edittext4_textview_91 = findViewById(R.id.edittext4_textview_91);

		textview7 = findViewById(R.id.textview7);
		imageview2 = findViewById(R.id.imageview2);
		edittext3 = findViewById(R.id.edittext3);


		login_api = new RequestNetwork(this);

		register_api = new RequestNetwork(this);


		dob_value.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				_DateDialog(dob_value);

			}
		});


		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				//in.setClass(getApplicationContext(), ForgotPassActivity.class);
				//in.putExtra("action", "");
				//in.putExtra("email", "");
				//in.putExtra("pass", "");
				//in.putExtra("phone", "");
				//startActivity(in);


			}
		});

		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if(Hiden){
					Hiden = false;
					pass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());

					edittext3.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());

					imageview2.setImageResource(R.drawable.pido_1);
					imageview2.setColorFilter(0xFFBDBDBD);

				}else {

					imageview2.setImageResource(R.drawable.pido_2);
					imageview2.setColorFilter(0xFF2196F3);



					pass.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());

					edittext3.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());

					Hiden = true;

				}
			}
		});

		action_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {

			/*	/////////////////////////////////////
				in.setClass(getApplicationContext(), ProfileActivity.class);
				in.putExtra("action", "create");
				startActivity(in);
				////////////////////*/


				if (email.getText().toString().trim().equals("")) {

					textview3.setTextColor(0xFFFF1744);
					final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(linear3, "backgroundColor", new ArgbEvaluator(), 0xffEEEEEE, 0xffFF1744);
					backgroundColorAnimator.setDuration(500);
					backgroundColorAnimator.start();
				}
				else {
					if (pass.getText().toString().equals("")) {
						textview4.setTextColor(0xFFFF1744);
						final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(linear4,
								"backgroundColor",
								new ArgbEvaluator(),
								0xffEEEEEE,
								0xffFF1744);
						backgroundColorAnimator.setDuration(500);
						backgroundColorAnimator.start();
					} else {
						if (Current) {
							if (edittext3.getText().toString().equals("") ) {
								textview9.setTextColor(0xFFFF1744);
								final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(linear7,
										"backgroundColor",
										new ArgbEvaluator(),
										0xffEEEEEE,
										0xffFF1744);
								backgroundColorAnimator.setDuration(500);
								backgroundColorAnimator.start();
							}
							else {

								if(!edittext3.getText().toString().trim().equals(pass.getText().toString().trim())){

									showMessage("confirm password not match!");

							}

								if(phone_edit.getText().toString().length()!=10){

									showMessage("Not a valid phone no !");


								} else {

								_autoTransitionScroll(vscroll1);
								linear8.setVisibility(View.VISIBLE);
								action_btn.setVisibility(View.GONE);
								textview7.setEnabled(false);
								checkbox1.setEnabled(false);

								pass.setEnabled(false);
								edittext3.setEnabled(false);


								timer = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {

												if (Util.isConnected(getApplicationContext())) {

													///////////////////// register  ///////////////
													/*in.setClass(getApplicationContext(), ForgotPassActivity.class);
													in.putExtra("action", "create");
													in.putExtra("email", email.getText().toString().trim());
													in.putExtra("pass", pass.getText().toString().trim());
													in.putExtra("phone", edittext4.toString().trim());
													startActivity(in);*/

                              if(name.getText().toString().trim().equals("")
							   || email.getText().toString().trim().equals("")
			                   || pass.getText().toString().trim().equals("")
			                   || phone_edit.getText().toString().trim().equals("")
					           || class_id.equals("")
					           || dept_id.equals("")
							   || year_id.equals("")
							   || roll.toString().trim().equals("")
							   || fname.toString().trim().equals("")
							   || mname.toString().trim().equals("")
							   || blg_name.equals("")
							   || dob_value.getText().toString().trim().equals("")
							   || addr.getText().toString().trim().equals("")) {


								  failure();
								  showMessage("Some fields are missing.. check it again ! ");



					   } else {
								  _register_api_request(
										  "4"
										  ,name.getText().toString()
										  ,email.getText().toString()
										  ,pass.getText().toString()
										  ,phone_edit.getText().toString()
										  ,class_id.trim()
										  ,dept_id.trim()
										  ,roll.getText().toString()
										  ,year_id.trim()
										  ,fname.getText().toString()
										  ,mname.getText().toString()
										  ,blg_name.trim()
										  ,dob_value.getText().toString()
										  ,college_id.trim()
										  ,addr.getText().toString());


					   }


													//fauth.signInWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim()).addOnCompleteListener(LoginActivity.this, _fauth_sign_in_listener);
												}
												else {
													showMessage( "No internet !");

												}


												//retweet.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "", _retweet_request_listener);
												   timer.cancel();


											}
										});
									}
								};
								_timer.schedule(timer, 1000);
								//Util.showMessage(getApplicationContext(), "created account ");

							}

							}
						}
						else {
							//Util.showMessage(getApplicationContext(), "Login success");
							_autoTransitionScroll(vscroll1);
							linear8.setVisibility(View.VISIBLE);
							action_btn.setVisibility(View.GONE);
							textview7.setEnabled(false);
							checkbox1.setEnabled(false);

							pass.setEnabled(false);
							edittext3.setEnabled(false);
							timer = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {




											if (Util.isConnected(getApplicationContext())) {

												//_login_api_request("student@example.com","abcdabcd");
													_login_api_request(email.getText().toString().trim()
															,pass.getText().toString().trim());



													//fauth.signInWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim()).addOnCompleteListener(LoginActivity.this, _fauth_sign_in_listener);
											}
											else {
												showMessage( "No internet !");

											}



											timer.cancel();

										///	retweet.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "", _retweet_request_listener);
										}
									});
								}
							};
							_timer.schedule(timer, 1000);
						}
					}
				}
			}
		});

		textview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (Current) {
					Current = false;
					_autoTransitionScroll(vscroll1);
					textview1.setText("Welcome back !");
					textview2.setText("Connect to your account");
					action_btn.setText("Login now");
					textview7.setText("Create account");
					textview9.setVisibility(View.GONE);
					textview10.setVisibility(View.GONE);

					phone_edit.setVisibility(View.GONE);  //phone no
					edittext4_textview_91.setVisibility(View.GONE); // phone +91 text

					gone();


					edittext3.setVisibility(View.GONE);
					linear7.setVisibility(View.GONE);
					checkbox1.setVisibility(View.GONE);
					textview5.setVisibility(View.VISIBLE);
				}
				else {
					Current = true;
					_autoTransitionScroll(vscroll1);
					textview1.setText("Hello new user !");
					textview2.setText("Sign in to continue ");
					action_btn.setText("Create account");
					textview7.setText("Login now");


					textview9.setVisibility(View.VISIBLE);
					textview10.setVisibility(View.VISIBLE);
					edittext3.setVisibility(View.VISIBLE);
					phone_edit.setVisibility(View.VISIBLE);  //phone no
					edittext4_textview_91.setVisibility(View.VISIBLE); // phone +91 text

					visible();

					linear7.setVisibility(View.VISIBLE);
					//checkbox1.setVisibility(View.VISIBLE);
					textview5.setVisibility(View.GONE);
				}
			}
		});






	}



	private void blood_gp()
	{
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "Select blood group..");
			blg_list.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "A+");
			blg_list.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "A-");
			blg_list.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "B+");
			blg_list.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "B-");
			blg_list.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "O+");
			blg_list.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "O-");
			blg_list.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "AB+");
			blg_list.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "AB-");
			blg_list.add(_item);
		}

		listview4.setAdapter(new Listview4Adapter(blg_list));
		((BaseAdapter)listview4.getAdapter()).notifyDataSetChanged();
		spinner_blg.setAdapter(new Listview4Adapter(blg_list));

	}

	private void initializeLogic() {


		//check google login
		GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);



		//check facebook login
		AccessToken accessToken = AccessToken.getCurrentAccessToken();
		boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


	/*	if ((FirebaseAuth.getInstance().getCurrentUser() != null || account != null ||isLoggedIn)) {
			i.setClass(getApplicationContext(), .class);
			startActivity(i);
			finish();
		}*/

		if(account!=null || isLoggedIn)
		{
			Intent i = new Intent();
			i.setClass(getApplicationContext(), MainActivity.class);
			startActivity(i);
			finish();
		}



		request_class_API();
		request_department_API();
		request_year_API();
		request_slider_API();
		college_api_request();
		blood_gp();




		linear10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
	}

	///////////////////////////////////////////////////////////////////////

    /*	ALL REQUEST API */

	private void request_class_API(){
		class_api.startRequestNetwork(RequestNetworkController.GET,
				api+"get/class",
				"no tag",
				_class_api_listener);

	}

	private void college_api_request(){

		/*college_api.startRequestNetwork(RequestNetworkController.GET,
				api+"get/class",
				"no tag",
				_college_api_listener);*/

		college_api.startRequestNetwork(RequestNetworkController.GET,
				"https://nft.digitallinkcard.xyz/api/get/college",
				"no tag",
				_college_api_listener);
	}


	private void request_department_API(){

		department_api.startRequestNetwork(RequestNetworkController.GET,
				api+"get/department",
				"no tag",
				_department_api_listener);
	}



	private void request_year_API(){


		year_api.startRequestNetwork(RequestNetworkController.GET,
				api+"get/year",
				"no tag",
				_year_api_listener);

	}


	private void request_slider_API(){


		slider_api.startRequestNetwork(RequestNetworkController.GET,
				api_mlu+"slider",
				"no tag",
				_slider_api_listener);

	}



	private void success(){



		_autoTransitionScroll(vscroll1);
		linear8.setVisibility(View.GONE);
		action_btn.setVisibility(View.VISIBLE);
		textview7.setEnabled(true);
		checkbox1.setEnabled(true);

		pass.setEnabled(true);
		edittext3.setEnabled(true);
		in.setClass(getApplicationContext(), MainActivity.class);
		startActivity(in);
		finish();

	}


	private void failure(){

		_autoTransitionScroll(vscroll1);
		linear8.setVisibility(View.GONE);
		action_btn.setVisibility(View.VISIBLE);
		textview7.setEnabled(true);
		checkbox1.setEnabled(true);

		pass.setEnabled(true);
		edittext3.setEnabled(true);


	}

	public void _login_api_request(String email,String password)
	{
		linear7.setVisibility(View.VISIBLE);

		api_map = new HashMap<>();

		api_map.put("email", email);
		api_map.put("password", password);

		login_api.setParams(api_map, RequestNetworkController.REQUEST_PARAM);
		login_api.startRequestNetwork(RequestNetworkController.POST, api+"login?", "no tag", _login_api_listener);

		api_map.clear();

		//Toast.makeText(this, "Login complete", Toast.LENGTH_SHORT).show();
	}



	public void _register_api_request(String _role,String _name,
									  String _email, String _pass,
									  String _mob, String _class_name,
									  String _department, String _year,
									  String _roll,
									  String _fname, String _mname,
									  String _blg, String _dob,
									  String _addr, String _collage_id)

	{
		linear7.setVisibility(View.VISIBLE);
		api_map2.clear();
		api_map2 = new HashMap<>();


		api_map2.put("role", _role.trim());
		api_map2.put("name", _name.trim());
		api_map2.put("email", _email.trim());
		api_map2.put("password", _pass.trim());
		api_map2.put("mobile", _mob.trim());
		api_map2.put("class_name", _class_name.trim());
		api_map2.put("department", _department.trim());
		api_map2.put("roll", _roll.trim());
		api_map2.put("year", _year.trim());
		api_map2.put("field_1", _fname.trim());
		api_map2.put("field_2", _mname.trim());
		api_map2.put("field_3", _blg.trim());
		api_map2.put("field_4", _dob.trim());
		api_map2.put("field_5", _addr.trim());
		api_map2.put("field_6", _collage_id.trim());

		register_api.setParams(api_map2, RequestNetworkController.REQUEST_PARAM);
		register_api.startRequestNetwork(RequestNetworkController.POST, api+"register?", "no tag", _register_api_listener);

		showMessage(_dob);

		//textview1.setText(_role +"\n"+_class_name+"\n"+_department+"\n"+_year+"\n"+_blg+"\n");

		//Toast.makeText(this, "Login complete", Toast.LENGTH_SHORT).show();
	}


	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {

		super.onActivityResult(_requestCode, _resultCode, _data);


		//GOOGLE LOGIN
		// Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
		if (_requestCode == RC_SIGN_IN) {

			// The Task returned from this call is always completed, no need to attach
			// a listener.
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(_data);
			handleSignInResult(task);

		}


		//FACE BOOK LOGIN

		callbackManager.onActivityResult(
				_requestCode,
				_resultCode,
				_data);


	}



	private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
		try {
			GoogleSignInAccount account = completedTask.getResult(ApiException.class);


			//if (account.getAccount() != null) {

			showMessage( "Login Success");

            Intent i = new Intent();
			i.setClass(getApplicationContext(), MainActivity.class);
			startActivity(i);
			finish();

			// this is not woeking on released apk      */

			// Signed in successfully, show authenticated UI.

		} catch (ApiException e) {
			// The ApiException status code indicates the detailed failure reason.
			// Please refer to the GoogleSignInStatusCodes class reference for more information.
			Log.w("ERROR", "signInResult:failed code=" + e.getStatusCode());
			//showMessage( "Log in ERROR");

			showMessage("Login Failed");

		}
	}


	public void facebookLogin()
	{

		loginManager
				= LoginManager.getInstance();
		callbackManager
				= CallbackManager.Factory.create();

		loginManager
				.registerCallback(
						callbackManager,
						new FacebookCallback<LoginResult>() {

							@Override
							public void onSuccess(LoginResult loginResult)
							{
								GraphRequest request = GraphRequest.newMeRequest(

										loginResult.getAccessToken(),

										new GraphRequest.GraphJSONObjectCallback() {



											@Override
											public void onCompleted(JSONObject object,
																	GraphResponse response)
											{

												if (object != null) {
													try {
														String name = object.getString("name");
														String email = object.getString("email");
														String id = object.getString("id");
														String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";






													//sh.edit().putString("fb_email", email).apply();
													//sh.edit().putString("fb_id", id).apply();
													//sh.edit().putString("fb_name", name).apply();
													//sh.edit().putString("fb_image", image_url).apply();



														showMessage(name +"\n"+email+"\n"+id+"\n\n"+image_url);

														Intent i = new Intent();
														i.setClass(getApplicationContext(),MainActivity.class);
														startActivity(i);
														finish();


														//disconnectFromFacebook();
														// do action after Facebook login success
														// or call your API
													}
													catch (NullPointerException |
														   JSONException e) {
														e.printStackTrace();
													}
												}
											}
										});

								Bundle parameters = new Bundle();
								parameters.putString(
										"fields",
										"id, name, email, gender, birthday");
								request.setParameters(parameters);
								request.executeAsync();
							}

							@Override
							public void onCancel()
							{
								Log.v("LoginScreen", "---onCancel");
							}

							@Override
							public void onError(FacebookException error)
							{
								// here write code when get error
								Log.v("LoginScreen", "----onError: "
										+ error.getMessage());
							}
						});
	}





	@Override
	public void onBackPressed() {

		if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
		{
			super.onBackPressed();
			return;
		}
		else {

			Toast.makeText(getBaseContext(), "Tap again to exit", Toast.LENGTH_SHORT).show();
		}

		mBackPressed = System.currentTimeMillis();

	}

	@Override
	public void onStart() {
		super.onStart();
		if (onStart) {

		}
		else {
			onStart = true;
			_Design_UI_Logic();
			_Typeface_Logic();
			_Edittexts_Logic();
		}
	}
	public void _NavStatusBarColor (final String _color1, final String _color2) {
		Window w = this.getWindow();
		w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));
		w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
	}


	public void _DARK_ICONS () {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
	}


	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		GradientDrawable GG = new GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
				Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
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


	public void _autoTransitionScroll (final View _scroll) {
		android.transition.TransitionManager.beginDelayedTransition((ScrollView)_scroll, new android.transition.AutoTransition());
	}


	public void _transitionComplete (final View _view, final String _transitionName) {
		_view.setTransitionName(_transitionName);
	}


	public void _TextFieldStatus (final boolean _status, final TextView _edittext) {
		_edittext.setOnFocusChangeListener(new OnFocusChangeListener() { @Override public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus) {
			}
			else {
			} } });
	}


	public void _EditTexts (final TextView _et, final TextView _title, final View _line) {
		_et.setOnFocusChangeListener(new OnFocusChangeListener() { @Override public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus) {
				_title.setTextColor(0xFF651fff);
				_et.setTextColor(0xFF212121);
				final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(_line,
						"backgroundColor",
						new ArgbEvaluator(),
						0xffEEEEEE,
						0xff651fff);
				backgroundColorAnimator.setDuration(500);
				backgroundColorAnimator.start();
			}
			else {
				_title.setTextColor(0xFF9E9E9E);
				_et.setTextColor(0xFF9E9E9E);
				final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(_line,
						"backgroundColor",
						new ArgbEvaluator(),
						0xff651fff,
						0xffEEEEEE);
				backgroundColorAnimator.setDuration(500);
				backgroundColorAnimator.start();
			} } });
	}



	/////////////////////////////////////////////


	private void Google_signIn() {
		Intent signInIntent = mGoogleSignInClient.getSignInIntent();
		startActivityForResult(signInIntent, RC_SIGN_IN);

	}


	///Spinner adpters

	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}

		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.clg_list_cus, null);
			}

			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);

			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), Typeface.NORMAL);

			textview1.setText(Objects.requireNonNull(class_list.get((int) _position).get("name")).toString());





			return _view;
		}
	}

	public class Listview2Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}

		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.clg_list_cus, null);
			}

			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);

			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), Typeface.NORMAL);

			textview1.setText(Objects.requireNonNull(department_list.get((int) _position).get("name")).toString());






			return _view;
		}
	}


	public class Listview3Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview3Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}

		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.clg_list_cus, null);
			}

			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);

			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), Typeface.NORMAL);

			textview1.setText(Objects.requireNonNull(year_list.get((int) _position).get("year")).toString());





			return _view;
		}
	}


	public class Listview4Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview4Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}

		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.clg_list_cus, null);
			}

			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);

			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), Typeface.NORMAL);

			textview1.setText(Objects.requireNonNull(blg_list.get((int) _position).get("name")).toString());



			return _view;
		}
	}


	public class Listview5Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview5Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}

		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.clg_list_cus, null);
			}

			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);

			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), Typeface.NORMAL);


			try {
				//textview1.setText(Objects.requireNonNull(college_list.get(_position).get("id")).toString());

try{
	textview1.setText(Objects.requireNonNull(college_list.get(_position).get("college_name")).toString());

}catch (Exception e)
{

}

			}catch (Exception e){


				showMessage("college api error\n"+e);
			}




			return _view;
		}
	}


	////////////////////////










	public void _dotsProgress (final View _view, final String _color1, final String _color2, final double _count, final double _size, final double _scale, final double _growth, final double _spacing) {
		final DilatingDotsProgressBar bar = new DilatingDotsProgressBar(this);
		bar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

		/*dots size here*/

		bar.setDotRadius((float)_size);
		/* dots color here*/

		bar.setDotColors(Color.parseColor("#" + _color1.replace("#", "")),Color.parseColor("#" + _color2.replace("#", "")));
		/*total dots here*/

		bar.setNumberOfDots((int)_count);
		bar.setDotScaleMultiplier((float)_scale);
		/*Set animation speed here*/

		bar.setGrowthSpeed((int)_growth);
		/* dot spacing between dots here*/

		bar.setDotSpacing((float)_spacing);
		((LinearLayout) _view).addView(bar);

		bar.showNow();
		/*bar.hideNow();*/

	}
	public static class DilatingDotDrawable extends android.graphics.drawable.Drawable {
		private static final String TAG = DilatingDotDrawable.class.getSimpleName();
		private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		private float radius;
		private float maxRadius;
		final Rect mDirtyBounds = new Rect(0, 0, 0, 0);

		public DilatingDotDrawable(final int color, final float radius, final float maxRadius) {
			mPaint.setColor(color);
			mPaint.setStyle(Paint.Style.FILL);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setStrokeJoin(Paint.Join.ROUND);

			this.radius = radius;
			setMaxRadius(maxRadius);
		}

		@Override
		public void draw(Canvas canvas) {
			final Rect bounds = getBounds();
			canvas.drawCircle(bounds.centerX(), bounds.centerY(), radius - 1, mPaint);
		}

		@Override
		public void setAlpha(int alpha) {
			if (alpha != mPaint.getAlpha()) {
				mPaint.setAlpha(alpha);
				invalidateSelf();
			}
		}

		@Override
		public void setColorFilter(ColorFilter colorFilter) {
			mPaint.setColorFilter(colorFilter);
			invalidateSelf();
		}

		@Override
		public int getOpacity() {
			return PixelFormat.TRANSLUCENT;
		}

		public void setColor(int color) {
			mPaint.setColor(color);
			invalidateSelf();
		}

		public void setRadius(float radius) {
			this.radius = radius;
			invalidateSelf();
		}

		public float getRadius() {
			return radius;
		}

		@Override
		public int getIntrinsicWidth() {
			return (int) (maxRadius * 2) + 2;
		}

		@Override
		public int getIntrinsicHeight() {
			return (int) (maxRadius * 2) + 2;
		}

		public void setMaxRadius(final float maxRadius) {
			this.maxRadius = maxRadius;
			mDirtyBounds.bottom = (int) (maxRadius * 2) + 2;
			mDirtyBounds.right = (int) (maxRadius * 2) + 2;
		}

		@Override
		public Rect getDirtyBounds() {
			return mDirtyBounds;
		}

		@Override
		protected void onBoundsChange(final Rect bounds) {
			super.onBoundsChange(bounds);
			mDirtyBounds.offsetTo(bounds.left, bounds.top);
		}
	}


	public static class DilatingDotsProgressBar extends View {
		public static final String TAG = DilatingDotsProgressBar.class.getSimpleName();
		public static final double START_DELAY_FACTOR = 0.35;
		private static final float DEFAULT_GROWTH_MULTIPLIER = 1.75f;
		private static final int MIN_SHOW_TIME = 500; // ms
		private static final int MIN_DELAY = 500; // ms
		private int mDotColor;
		private int mDotEndColor;
		private int mAnimationDuration;
		private int mWidthBetweenDotCenters;
		private int mNumberDots;
		private float mDotRadius;
		private float mDotScaleMultiplier;
		private float mDotMaxRadius;
		private float mHorizontalSpacing;
		private long mStartTime = -1;
		private boolean mShouldAnimate;
		private boolean mDismissed = false;
		private boolean mIsRunning = false;
		private boolean mAnimateColors = false;
		private final ArrayList<DilatingDotDrawable> mDrawables = new ArrayList<>();
		private final List<android.animation.Animator> mAnimations = new ArrayList<>();
		/** delayed runnable to stop the progress */
		private final Runnable mDelayedHide = new Runnable() {
			@Override
			public void run() {
				mStartTime = -1;
				mIsRunning = false;
				setVisibility(View.GONE);
				stopAnimations();
			}
		};

		/** delayed runnable to start the progress */
		private final Runnable mDelayedShow = new Runnable() {
			@Override
			public void run() {
				if (!mDismissed) {
					mStartTime = System.currentTimeMillis();
					setVisibility(View.VISIBLE);
					startAnimations();
				}
			}
		};

		public DilatingDotsProgressBar(Context context) {
			this(context, null);
		}

		public DilatingDotsProgressBar(Context context, AttributeSet attrs) {
			this(context, attrs, 0);
		}

		public DilatingDotsProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			init(attrs);
		}

		private void init(AttributeSet attrs) {
			//TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DilatingDotsProgressBar);
			//mNumberDots = a.getInt(R.styleable.DilatingDotsProgressBar_dd_numDots, 3);
			//mDotRadius = a.getDimension(R.styleable.DilatingDotsProgressBar_android_radius, 8);
			//mDotColor = a.getColor(R.styleable.DilatingDotsProgressBar_android_color, 0xff9c27b0);
			//mDotEndColor = a.getColor(R.styleable.DilatingDotsProgressBar_dd_endColor, mDotColor);
			//mDotScaleMultiplier = a.getFloat(R.styleable.DilatingDotsProgressBar_dd_scaleMultiplier, DEFAULT_GROWTH_MULTIPLIER);
			//mAnimationDuration = a.getInt(R.styleable.DilatingDotsProgressBar_dd_animationDuration, 300);
			//mHorizontalSpacing = a.getDimension(R.styleable.DilatingDotsProgressBar_dd_horizontalSpacing, 12);
			//a.recycle();


			mNumberDots = 3;
			mDotRadius = 8;
			mDotColor = Color.RED;
			mDotEndColor = mDotColor;
			mDotScaleMultiplier = DEFAULT_GROWTH_MULTIPLIER;
			mAnimationDuration = 300;
			mHorizontalSpacing = 12;



			mShouldAnimate = false;
			mAnimateColors = mDotColor != mDotEndColor;
			calculateMaxRadius();
			calculateWidthBetweenDotCenters();

			initDots();
			updateDots();
		}

		@Override
		protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
			super.onSizeChanged(w, h, oldw, oldh);
			if (computeMaxHeight() != h || w != computeMaxWidth()) {
				updateDots();
			}
		}

		@Override
		public void onDetachedFromWindow() {
			super.onDetachedFromWindow();
			removeCallbacks();
		}

		private void removeCallbacks() {
			removeCallbacks(mDelayedHide);
			removeCallbacks(mDelayedShow);
		}

		public void reset() {
			hideNow();
		}

		/**
		 * Hide the progress view if it is visible. The progress view will not be
		 * hidden until it has been shown for at least a minimum show time. If the
		 * progress view was not yet visible, cancels showing the progress view.
		 */
		@SuppressWarnings ("unused")
		public void hide() {
			hide(MIN_SHOW_TIME);
		}

		public void hide(int delay) {
			mDismissed = true;
			removeCallbacks(mDelayedShow);
			long diff = System.currentTimeMillis() - mStartTime;
			if ((diff >= delay) || (mStartTime == -1)) {
				mDelayedHide.run();
			} else {
				if ((delay - diff) <= 0) {
					mDelayedHide.run();
				} else {
					postDelayed(mDelayedHide, delay - diff);
				}
			}
		}

		/**
		 * Show the progress view after waiting for a minimum delay. If
		 * during that time, hide() is called, the view is never made visible.
		 */
		@SuppressWarnings ("unused")
		public void show() {
			show(MIN_DELAY);
		}

		@SuppressWarnings ("unused")
		public void showNow() {
			show(0);
		}

		@SuppressWarnings ("unused")
		public void hideNow() {
			hide(0);
		}

		public void show(int delay) {
			if (mIsRunning) {
				return;
			}

			mIsRunning = true;

			mStartTime = -1;
			mDismissed = false;
			removeCallbacks(mDelayedHide);

			if (delay == 0) {
				mDelayedShow.run();
			} else {
				postDelayed(mDelayedShow, delay);
			}
		}

		@Override
		protected void onDraw(Canvas canvas) {
			if (shouldAnimate()) {
				for (DilatingDotDrawable dot : mDrawables) {
					dot.draw(canvas);
				}
			}
		}

		@Override
		protected boolean verifyDrawable(final android.graphics.drawable.Drawable who) {
			if (shouldAnimate()) {
				return mDrawables.contains(who);
			}
			return super.verifyDrawable(who);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension((int) computeMaxWidth(), (int) computeMaxHeight());
		}

		private float computeMaxHeight() {
			return mDotMaxRadius * 2;
		}

		private float computeMaxWidth() {
			return computeWidth() + ((mDotMaxRadius - mDotRadius) * 2);
		}

		private float computeWidth() {
			return (((mDotRadius * 2) + mHorizontalSpacing) * mDrawables.size()) - mHorizontalSpacing;
		}

		private void calculateMaxRadius() {
			mDotMaxRadius = mDotRadius * mDotScaleMultiplier;
		}

		private void calculateWidthBetweenDotCenters() {
			mWidthBetweenDotCenters = (int) (mDotRadius * 2) + (int) mHorizontalSpacing;
		}

		private void initDots() {
			mDrawables.clear();
			mAnimations.clear();
			for (int i = 1; i <= mNumberDots; i++) {
				final DilatingDotDrawable dot = new DilatingDotDrawable(mDotColor, mDotRadius, mDotMaxRadius);
				dot.setCallback(this);
				mDrawables.add(dot);

				final long startDelay = (i - 1) * (int) (START_DELAY_FACTOR * mAnimationDuration);

				// Sizing
				android.animation.ValueAnimator growAnimator = ObjectAnimator.ofFloat(dot, "radius", mDotRadius, mDotMaxRadius, mDotRadius);
				growAnimator.setDuration(mAnimationDuration);
				growAnimator.setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator());
				if (i == mNumberDots) {
					growAnimator.addListener(new android.animation.AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(android.animation.Animator animation) {
							if (shouldAnimate()) {
								startAnimations();
							}
						}
					});
				}
				growAnimator.setStartDelay(startDelay);

				mAnimations.add(growAnimator);

				if (mAnimateColors) {
					// Gradient
					android.animation.ValueAnimator colorAnimator = android.animation.ValueAnimator.ofInt(mDotEndColor, mDotColor);
					colorAnimator.setDuration(mAnimationDuration);
					colorAnimator.setEvaluator(new ArgbEvaluator());
					colorAnimator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

						@Override
						public void onAnimationUpdate(android.animation.ValueAnimator animator) {
							dot.setColor((int) animator.getAnimatedValue());
						}

					});
					if (i == mNumberDots) {
						colorAnimator.addListener(new android.animation.AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(android.animation.Animator animation) {
								if (shouldAnimate()) {
									startAnimations();
								}
							}
						});
					}
					colorAnimator.setStartDelay(startDelay);

					mAnimations.add(colorAnimator);
				}
			}
		}

		private void updateDots() {
			if (mDotRadius <= 0) {
				mDotRadius = getHeight() / 2 / mDotScaleMultiplier;
			}

			int left = (int) (mDotMaxRadius - mDotRadius);
			int right = (int) (left + mDotRadius * 2) + 2;
			int top = 0;
			int bottom = (int) (mDotMaxRadius * 2) + 2;

			for (int i = 0; i < mDrawables.size(); i++) {
				final DilatingDotDrawable dot = mDrawables.get(i);
				dot.setRadius(mDotRadius);
				dot.setBounds(left, top, right, bottom);
				android.animation.ValueAnimator growAnimator = (android.animation.ValueAnimator) mAnimations.get(i);
				growAnimator.setFloatValues(mDotRadius, mDotRadius * mDotScaleMultiplier, mDotRadius);

				left += mWidthBetweenDotCenters;
				right += mWidthBetweenDotCenters;
			}
		}

		protected void startAnimations() {
			mShouldAnimate = true;
			for (android.animation.Animator anim : mAnimations) {
				anim.start();
			}
		}

		protected void stopAnimations() {
			mShouldAnimate = false;
			removeCallbacks();
			for (android.animation.Animator anim : mAnimations) {
				anim.cancel();
			}
		}

		protected boolean shouldAnimate() {
			return mShouldAnimate;
		}

		// -------------------------------
		// ------ Getters & Setters ------
		// -------------------------------

		public void setDotRadius(float radius) {
			reset();
			mDotRadius = radius;
			calculateMaxRadius();
			calculateWidthBetweenDotCenters();
			setupDots();
		}

		public void setDotSpacing(float horizontalSpacing) {
			reset();
			mHorizontalSpacing = horizontalSpacing;
			calculateWidthBetweenDotCenters();
			setupDots();
		}

		public void setGrowthSpeed(int growthSpeed) {
			reset();
			mAnimationDuration = growthSpeed;
			setupDots();
		}

		public void setNumberOfDots(int numDots) {
			reset();
			mNumberDots = numDots;
			setupDots();
		}

		public void setDotScaleMultiplier(float multiplier) {
			reset();
			mDotScaleMultiplier = multiplier;
			calculateMaxRadius();
			setupDots();
		}

		public void setDotColor(int color) {
			if (color != mDotColor) {
				if (mAnimateColors) {
					// Cancel previous animations
					reset();
					mDotColor = color;
					mDotEndColor = color;
					mAnimateColors = false;

					setupDots();

				} else {
					mDotColor = color;
					for (DilatingDotDrawable dot : mDrawables) {
						dot.setColor(mDotColor);
					}
				}
			}
		}

		/**
		 * Set different start and end colors for dots. This will result in gradient behaviour. In case you want set 1 solid
		 * color - use {@link #setDotColor(int)} instead
		 *
		 * @param startColor starting color of the dot
		 * @param endColor   ending color of the dot
		 */
		public void setDotColors(int startColor, int endColor) {
			if (mDotColor != startColor || mDotEndColor != endColor) {
				if (mAnimateColors) {
					reset();
				}
				mDotColor = startColor;
				mDotEndColor = endColor;

				mAnimateColors = mDotColor != mDotEndColor;

				setupDots();
			}
		}

		private void setupDots() {
			initDots();
			updateDots();
			showNow();
		}

		public int getDotGrowthSpeed() {
			return mAnimationDuration;
		}

		public float getDotRadius() {
			return mDotRadius;
		}

		public float getHorizontalSpacing() {
			return mHorizontalSpacing;
		}

		public int getNumberOfDots() {
			return mNumberDots;
		}

		public float getDotScaleMultiplier() {
			return mDotScaleMultiplier;
		}
	}
	{
	}


	public void _ICC (final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}


	public void _RippleEffects (final String _color, final View _view) {
		android.content.res.ColorStateList clr = new android.content.res.ColorStateList(new int[][]{new int[]{}},new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdr = new android.graphics.drawable.RippleDrawable(clr, null, null);
		_view.setBackground(ripdr);
	}


	public void _removeScollBar (final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}


	public void _Design_UI_Logic () {

		_dotsProgress(linear8, "#661fff", "#FFFFFF", 3, 8, 1, 600, 11);

		_rippleRoundStroke(action_btn, "#661fff", "#33FFFFFF", 50, 2, "#661fff");

		_rippleRoundStroke(google_layout, "#eeeeee", "#ffffff", 50, 2, "#E0E0E0");

		_rippleRoundStroke(facebook_layout, "#1778F2", "#16000000", 50, 2, "#E0E0E0");

		_rippleRoundStroke(linear8, "#661fff", "#33FFFFFF", 50, 2, "#661fff");
		_rippleRoundStroke(textview7, "#0000B0FF", "#16000000", 50, 2, "#E0E0E0");
		_ICC(imageview2, "#9E9E9E", "#661fff");
		_NavStatusBarColor("#FFFFFF", "#FFFFFF");
		_RippleEffects("#F5F5F5", imageview2);
		textview9.setVisibility(View.GONE);
		edittext3.setVisibility(View.GONE);
		checkbox1.setVisibility(View.GONE);
		linear7.setVisibility(View.GONE);
		linear8.setVisibility(View.GONE);

		phone_edit.setVisibility(View.GONE);   //phone no
		textview10.setVisibility(View.GONE); // PHONE NO TEXT

		edittext4_textview_91.setVisibility(View.GONE); // phone +91 text


		gone();



		_removeScollBar(vscroll1);
		_DARK_ICONS();
	}



	private void gone(){

		          dob_text.setVisibility(View.GONE);
				fname_text.setVisibility(View.GONE);
				mname_text.setVisibility(View.GONE);
				 name_text.setVisibility(View.GONE);
				 addr_text.setVisibility(View.GONE);
				 roll_text.setVisibility(View.GONE);
				 college_text.setVisibility(View.GONE);

		        fname.setVisibility(View.GONE);
				mname.setVisibility(View.GONE);
				name.setVisibility(View.GONE);
				addr.setVisibility(View.GONE);
				roll.setVisibility(View.GONE);


				spinner_class_name.setVisibility(View.GONE);
				spinner_department.setVisibility(View.GONE);
	         	spinner_year.setVisibility(View.GONE);
				spinner_blg.setVisibility(View.GONE);
				spinner_college.setVisibility(View.GONE);


		        linear_mname.setVisibility(View.GONE);
				linear_name.setVisibility(View.GONE);
				linear_fname.setVisibility(View.GONE);
				linear_addr.setVisibility(View.GONE);
				linear_phone.setVisibility(View.GONE);
				linear_roll.setVisibility(View.GONE);


		text_class_name_.setVisibility(View.GONE);
		text_department_.setVisibility(View.GONE);
		text_year_ .setVisibility(View.GONE);
		text_blg_ .setVisibility(View.GONE);
		dob_value .setVisibility(View.GONE);



		spn_ln0.setVisibility(View.GONE);
		spn_ln1.setVisibility(View.GONE);
		spn_ln2.setVisibility(View.GONE);
		spn_ln3.setVisibility(View.GONE);
		spn_ln4.setVisibility(View.GONE);

	}

	private void visible(){

		  dob_text.setVisibility(View.VISIBLE);
		fname_text.setVisibility(View.VISIBLE);
		mname_text.setVisibility(View.VISIBLE);
		 name_text.setVisibility(View.VISIBLE);
		 addr_text.setVisibility(View.VISIBLE);
		 roll_text.setVisibility(View.VISIBLE);
		 college_text.setVisibility(View.VISIBLE);


		fname.setVisibility(View.VISIBLE);
		mname.setVisibility(View.VISIBLE);
		 name.setVisibility(View.VISIBLE);
		 addr.setVisibility(View.VISIBLE);
		 roll.setVisibility(View.VISIBLE);


		spinner_class_name.setVisibility(View.VISIBLE);
		spinner_department.setVisibility(View.VISIBLE);
		      spinner_year.setVisibility(View.VISIBLE);
		       spinner_blg.setVisibility(View.VISIBLE);
		   spinner_college.setVisibility(View.VISIBLE);


		 linear_mname.setVisibility(View.VISIBLE);
		  linear_name.setVisibility(View.VISIBLE);
		 linear_fname.setVisibility(View.VISIBLE);
		  linear_addr.setVisibility(View.VISIBLE);
		 linear_phone.setVisibility(View.VISIBLE);
		  linear_roll.setVisibility(View.VISIBLE);

		text_class_name_.setVisibility(View.VISIBLE);
		text_department_.setVisibility(View.VISIBLE);
		     text_year_ .setVisibility(View.VISIBLE);
		      text_blg_ .setVisibility(View.VISIBLE);
		      dob_value .setVisibility(View.VISIBLE);


		spn_ln0.setVisibility(View.VISIBLE);
		spn_ln1.setVisibility(View.VISIBLE);
		spn_ln2.setVisibility(View.VISIBLE);
		spn_ln3.setVisibility(View.VISIBLE);
		spn_ln4.setVisibility(View.VISIBLE);

	}


	public void _Typeface_Logic () {
		_changeActivityFont("en_light");
		action_btn.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_med.ttf"), Typeface.NORMAL);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_med.ttf"), Typeface.NORMAL);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_med.ttf"), Typeface.NORMAL);
	}


	public void _Edittexts_Logic () {
		_EditTexts(email, textview3, linear3);
		_EditTexts(pass, textview4, linear4);
		_EditTexts(edittext3, textview9, linear7);
		_EditTexts(phone_edit, textview10, linear_phone);

	_EditTexts(fname, fname_text, linear_fname);
	_EditTexts(mname, mname_text, linear_mname);
	_EditTexts(name,  name_text,  linear_name);
	_EditTexts(addr,  addr_text,  linear_addr);
	_EditTexts(roll,  roll_text,  linear_roll);


		pass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
		edittext3.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());

	}



	public void _DateDialog (final TextView _textview) {
		DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

			@SuppressLint("SimpleDateFormat")
			final Calendar c = Calendar.getInstance();
			final String now_year = new SimpleDateFormat("yyyy").format(c.getTime());
			final int nowyear = Integer.parseInt(now_year);
			//c.set(nowyear, 0, 1);



			@Override
			public void onDateSet(DatePicker view, int year, int month, int day) {
				month++;

				// before =                output   1/9/2022
				// after the below logic = output 01/09/2022

				String tempMonth=""+month,tempDay=""+day;
				if(month<10) {
					tempMonth = "/0" + month;

					if(day<10) { tempDay = "0" + day; }

					_textview.setText(tempDay + "" + tempMonth + "/" + year);


				}else {

					if(day<10) { tempDay = "0" + day; }

					_textview.setText(tempDay + "/" + tempMonth + "/" + year);

				}



				// this bool variable for check user is selected data or not

				//edited by shubhamjeet 27th oct 2022, 9:20pm


			}
		};
		showDatePicker(datePickerListener);
	}
	public void showDatePicker(DatePickerDialog.OnDateSetListener listener) {
		DatePickerDialog datePicker = null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
			datePicker = new DatePickerDialog(this);
		}


		// datePicker.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(0));

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			datePicker.setOnDateSetListener(listener);
		}
		datePicker.show();
	}



	public void _slider () {







		//final float scaleFactor = 0.96f; viewpager1.setPageMargin(-15); viewpager1.setOffscreenPageLimit(2); viewpager1.setPageTransformer(false, new ViewPager.PageTransformer() { @Override public void transformPage(@NonNull View page1, float position) { page1.setScaleY((1 - Math.abs(position) * (1 - scaleFactor))); page1.setScaleX(scaleFactor + Math.abs(position) * (1 - scaleFactor)); } });
		viewpager1.setAdapter(new Viewpager1Adapter(listmap));
		scroll_time = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						viewpager1.setCurrentItem((int)count);
						count++;
						if (count == listmap.size()) {
							count = 0;
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(scroll_time, 2500, 2000);
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
			View _view = LayoutInflater.from(_context).inflate(R.layout.slider_big, _container, false);

			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);


			Glide.with(getApplicationContext())
					.load(Uri.parse(Objects.requireNonNull(listmap.get(_position).get("img_url")).toString()))
					.error(R.drawable.pyramids)
					.placeholder(R.drawable.pyramids)
					.thumbnail(0.01f)
					.into(imageview1);


			_container.addView(_view);
			return _view;
		}
	}

	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_LONG).show();
	}



}
