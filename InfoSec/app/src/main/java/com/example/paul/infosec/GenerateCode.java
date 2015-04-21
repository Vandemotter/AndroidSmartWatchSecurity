package com.example.paul.infosec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class GenerateCode extends Activity {

    private String generatedAuthCode;

    private void checkCode(){
        EditText text = (EditText)findViewById(R.id.auth_code);
        String inputAuthCode = text.getText().toString();
        int messageResId =  0;

        if (inputAuthCode.equals(generatedAuthCode)){
            messageResId = R.string.correct_unlock;
            Intent i = new Intent(GenerateCode.this, BookListActivity.class);
            startActivity(i);
        }
        else {
            messageResId = R.string.incorrect_unlock;
        }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    private Button UnlockButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);

        UnlockButton = (Button) findViewById(R.id.unlock_button);
        UnlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                Date date = new Date();

                generatedAuthCode = hmacDigest(dateFormat.format(date), "abcabcabc", "HmacSHA1");

                checkCode();


            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.generate_code, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static String hmacDigest(String msg, String keyString, String algo) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("ASCII"), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException e) {
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        digest = digest.substring(0, digest.length() - 34);
        System.out.println("Your code is: " + digest);
        return digest;
    }
}
