package com.moringaschool.lend_a_hand.ui.activities;

import static com.moringaschool.lend_a_hand.Constants.BUSINESS_SHORT_CODE;
import static com.moringaschool.lend_a_hand.Constants.CALLBACKURL;
import static com.moringaschool.lend_a_hand.Constants.PARTYB;
import static com.moringaschool.lend_a_hand.Constants.PASSKEY;
import static com.moringaschool.lend_a_hand.Constants.TRANSACTION_TYPE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moringaschool.lend_a_hand.R;
import com.moringaschool.lend_a_hand.Utils;
import com.moringaschool.lend_a_hand.models.AccessToken;
import com.moringaschool.lend_a_hand.models.STKPush;
import com.moringaschool.lend_a_hand.services.DarajaApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class PaymentActivity extends AppCompatActivity {
    private DarajaApiClient mApiClient;
    private ProgressDialog mProgressDialog;

    @BindView(R.id.amount)
    EditText mAmount;
    @BindView(R.id.phone)EditText mPhone;
    @BindView(R.id.donate)
    Button mPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        mProgressDialog = new ProgressDialog(this);
        mApiClient = new DarajaApiClient();
        mApiClient.setIsDebug(false); //Set True to enable logging, false to disable.
        getAccessToken();
        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay();
            }
        });
    }
    public void getAccessToken() {
        mApiClient.setGetAccessToken(true);
        mApiClient.mpesaService().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    mApiClient.setAuthToken(response.body().accessToken);
                    Log.e("Tag", "onResponse: Responsive" );
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

            }
        });
    }
    public void pay() {
        String phone_number = mPhone.getText().toString();
        String amount = mAmount.getText().toString();
        performSTKPush(phone_number,amount);
    }
    public void performSTKPush(String phone_number,String amount) {
        mProgressDialog.setMessage("Processing your request");
        mProgressDialog.setTitle("Please Wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        String timestamp = Utils.getTimestamp();
        STKPush stkPush = new STKPush(
                BUSINESS_SHORT_CODE,
                Utils.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp),
                timestamp,
                TRANSACTION_TYPE,
                String.valueOf(amount),
                Utils.sanitizePhoneNumber(phone_number),
                PARTYB,
                Utils.sanitizePhoneNumber(phone_number),
                CALLBACKURL,
                "Lend A Hand", //Account reference
                "donation"  //Transaction description
        );

        mApiClient.setGetAccessToken(false);

        //Sending the data to the Mpesa API.
        mApiClient.mpesaService().sendPush(stkPush).enqueue(new Callback<STKPush>() {
            @Override
            public void onResponse(@NonNull Call<STKPush> call, @NonNull Response<STKPush> response) {
                mProgressDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        Timber.d("post submitted to API. %s", response.body());
                        Log.e("Tag", "onSuccessResponse: "+response.body());
                    } else {
//                        assert response.errorBody() != null;
                        Timber.e("Response %s", response.errorBody().string());
                        Log.e("Tag","Failed");
                        Log.e("Tag", "onResponse: "+ response.code());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<STKPush> call, @NonNull Throwable t) {
                mProgressDialog.dismiss();
                Timber.e(t);
                Log.e("Tag","Failed Response");
            }
        });
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}