package com.rentals.ma3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button continueBtn;
    private EditText addOrigin, addDest;
    static final String server_url = "http://192.168.56.1/user_info.php";
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Activity started");
        hideSoftKeyboard();

        continueBtn = findViewById(R.id.continue_btn);
        addOrigin = findViewById(R.id.add_origin);
        addDest = findViewById(R.id.add_dest);
        builder = new AlertDialog.Builder(this);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin = addOrigin.getText().toString();
                String dest = addDest.getText().toString();
                if (!TextUtils.isEmpty(origin) && !TextUtils.isEmpty(dest)) {
                    Intent intent = new Intent(MainActivity.this, TravelTimeActivity.class);
                    startActivity(intent);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }

                }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            return super.getParams();
                        }
                    };
                }
                else {
                    toastMessage("Please fill out all the fields");
                }
            }
        });


    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
