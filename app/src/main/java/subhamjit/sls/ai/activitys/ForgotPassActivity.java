/*
package subhamjit.sls.ai.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

import subhamjit.sls.ai.R;
import subhamjit.sls.ai.RequestNetwork;


public class ForgotPassActivity extends  AppCompatActivity  {


	private String fontName = "";
	private String typeace = "";
	private String phone = "";
	private String code = "";
	private String codeSent = "";


	private HashMap<String, Object> api_map = new HashMap<>();


	private LinearLayout linear1;
	private LinearLayout get_otp_layout;
	private LinearLayout verify_otp_layout;
	private LinearLayout new_password_layout;
	private TextView textview2;
	private LinearLayout linear2;
	private EditText phone_no;
	private TextView send_otp;
	private ProgressBar progressbar1;
	private TextView textview4;
	private LinearLayout linear7;
	private TextView resend_otp;
	private EditText otp_value;
	private ProgressBar progressbar2;
	private TextView verify_otp;
	private TextView textview6;
	private TextView textview9;
	private EditText pass;
	private TextView textview10;
	private EditText cpass;
	private TextView textview7;

	private FirebaseAuth fauth;
	private OnCompleteListener<Void> fauth_updateEmailListener;
	private OnCompleteListener<Void> fauth_updatePasswordListener;
	private OnCompleteListener<Void> fauth_emailVerificationSentListener;
	private OnCompleteListener<Void> fauth_deleteUserListener;
	private OnCompleteListener<Void> fauth_updateProfileListener;
	private OnCompleteListener<AuthResult> fauth_phoneAuthListener;
	private OnCompleteListener<AuthResult> fauth_googleSignInListener;
	private OnCompleteListener<AuthResult> _fauth_create_user_listener;
	private OnCompleteListener<AuthResult> _fauth_sign_in_listener;
	private OnCompleteListener<Void> _fauth_reset_password_listener;


	private RequestNetwork register_api;
	private RequestNetwork.RequestListener _register_api_listener;
	String register_api_url =  "https://student.mlu.ac.in/api/register?";


	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.forgot_pass);
		initialize(_savedInstanceState);
//		FirebaseApp.initializeApp(this);
		initializeLogic();
	}

	private void initialize(Bundle _savedInstanceState) {

*/
/**//*

		register_api = new RequestNetwork(this);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		get_otp_layout = (LinearLayout) findViewById(R.id.get_otp_layout);
		verify_otp_layout = (LinearLayout) findViewById(R.id.verify_otp_layout);
		new_password_layout = (LinearLayout) findViewById(R.id.new_password_layout);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		phone_no = (EditText) findViewById(R.id.phone_no);
		send_otp = (TextView) findViewById(R.id.send_otp);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		textview4 = (TextView) findViewById(R.id.textview4);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		resend_otp = (TextView) findViewById(R.id.resend_otp);
		otp_value = (EditText) findViewById(R.id.otp_value);
		progressbar2 = (ProgressBar) findViewById(R.id.progressbar2);
		verify_otp = (TextView) findViewById(R.id.verify_otp);
		textview6 = (TextView) findViewById(R.id.textview6);
		textview9 = (TextView) findViewById(R.id.textview9);
		pass = (EditText) findViewById(R.id.pass);
		textview10 = (TextView) findViewById(R.id.textview10);
		cpass = (EditText) findViewById(R.id.cpass);
		textview7 = (TextView) findViewById(R.id.textview7);
		//fauth = FirebaseAuth.getInstance();


		_register_api_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

				showMessage(""+response);
				showMessage(getIntent().getStringExtra("email"));
				showMessage(getIntent().getStringExtra("pass"));
				showMessage(getIntent().getStringExtra("phone"));




				if(response.contains("success")) {



					showMessage("Account created, LOGIN NOW");


				} else {

					showMessage("Failed to create"+response);
				}
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
				finish();


			}

			@Override
			public void onErrorResponse(String tag, String message) {

				showMessage(message);


			}
		};






		send_otp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (phone_no.getText().toString().trim().length() == 10) {
					_sendotp();

				}
				else {
					Util.showMessage(getApplicationContext(), "Invalid number");
				}
			}
		});

		resend_otp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {

				_sendotp();
			}
		});

		verify_otp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {

				if(!otp_value.getText().toString().trim().equals("")){
				_verify();
				}
			}
		});

		fauth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

			}
		};

		fauth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

			}
		};

		fauth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

			}
		};

		fauth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

			}
		};

		fauth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";

			}
		};

		fauth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

			}
		};

		fauth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";

			}
		};

		_fauth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

			}
		};

		_fauth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				progressbar2.setVisibility(View.GONE);
				if (_success) {
					Util.showMessage(getApplicationContext(), "Verifications Success ");

					try{

						_register_api_request(
								"Student",
								getIntent().getStringExtra("email").substring(0,10),
								getIntent().getStringExtra("email"),
								getIntent().getStringExtra("pass"),
								getIntent().getStringExtra("phone"),
								"Class 5",
								"COMPUTER SCIENCE",
								"2011",
								"22");



						showMessage("Creating account..");

					}catch(Exception e){
						showMessage(e.toString());
					}

				}
				else {
					Util.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};

		_fauth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();

			}
		};
	}

	public void _register_api_request(String _role,String _name,
									  String _email, String _pass,
									  String _mob, String _class_name,
									  String _department, String _year,
									  String _roll)
	{
		progressbar2.setVisibility(View.VISIBLE);
		api_map.clear();
		api_map = new HashMap<>();

		api_map.put("role", _role.trim());
		api_map.put("name", _name.trim());
		api_map.put("email", _email.trim());
		api_map.put("password", _pass.trim());
		api_map.put("mobile", _mob.trim());
		api_map.put("classname", _class_name.trim());
		api_map.put("department", _department.trim());
		api_map.put("year", _year.trim());
		api_map.put("roll", _roll.trim());


		register_api.setParams(api_map, RequestNetworkController.REQUEST_PARAM);
		register_api.startRequestNetwork(RequestNetworkController.POST, register_api_url , "no tag", _register_api_listener);


	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this, MainActivity.class));
	}

	private void initializeLogic() {
		_changeActivityFont("en_light");


		if (getIntent().getStringExtra("action").equals("create")) {

			phone_no.setText(getIntent().getStringExtra("phone").trim());
			get_otp_layout.setVisibility(View.VISIBLE);
			verify_otp_layout.setVisibility(View.GONE);
			new_password_layout.setVisibility(View.GONE);
		}
		else {

			get_otp_layout.setVisibility(View.VISIBLE);

			verify_otp_layout.setVisibility(View.VISIBLE);
			new_password_layout.setVisibility(View.GONE);


		}
		progressbar1.setVisibility(View.GONE);
		progressbar2.setVisibility(View.GONE);
	}

	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {

		super.onActivityResult(_requestCode, _resultCode, _data);

		switch (_requestCode) {

			default:
			break;
		}
	}

	public void _changeActivityFont (final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView());
	}
	private void overrideFonts(final Context context, final View v) {

		try {
			Typeface
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
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
		};
	}


	public void _sendotp () {
		progressbar1.setVisibility(View.VISIBLE);
		phone = "+91".concat(phone_no.getText().toString().trim());
		verify_otp_layout.setVisibility(View.VISIBLE);
		//code for send otp on user mobile

		com.google.firebase.auth.PhoneAuthProvider.getInstance().verifyPhoneNumber(phone, 50, java.util.concurrent.TimeUnit.SECONDS, this, mCallbacks);
	}
	com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
		 @Override
		public void onVerificationCompleted(com.google.firebase.auth.PhoneAuthCredential phoneAuthCredential) {
				showMessage("OTP Sent.");

			 verify_otp_layout.setVisibility(View.VISIBLE);
				progressbar1.setVisibility(View.GONE);
		}
		 @Override
		public void onVerificationFailed(com.google.firebase.FirebaseException e) {
				showMessage(e.toString());
			progressbar1.setVisibility(View.GONE);
		}
		 @Override
		public void onCodeSent(String s, com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken forceResendingToken) {
				super.onCodeSent(s, forceResendingToken);
				codeSent = s;
		}
	};
	{
	}


	public void _verify () {
		progressbar2.setVisibility(View.VISIBLE);
		code = otp_value.getText().toString().trim();
		//code for verify otp

		com.google.firebase.auth.PhoneAuthCredential credential = com.google.firebase.auth.PhoneAuthProvider.getCredential(codeSent, code);
		signInWithPhoneAuthCredential(credential);
	}
	private void signInWithPhoneAuthCredential(com.google.firebase.auth.PhoneAuthCredential credential) {
		fauth.signInWithCredential(credential) .addOnCompleteListener(this, _fauth_sign_in_listener);
	}
	{


	}


	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}



}
*/
