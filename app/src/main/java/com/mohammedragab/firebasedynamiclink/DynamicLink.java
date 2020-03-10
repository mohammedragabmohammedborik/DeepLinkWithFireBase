package com.mohammedragab.firebasedynamiclink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.mohammedragab.firebasedynamiclink.databinding.ActivityDynamicLinkBinding;

public class DynamicLink extends AppCompatActivity {
    private ActivityDynamicLinkBinding activityDynamicLinkBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDynamicLinkBinding= DataBindingUtil.setContentView(this,R.layout.activity_dynamic_link);

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            Log.w("TAG", "onSuccess: "+deepLink );
                            activityDynamicLinkBinding.textviewId.setText(deepLink.toString());

                          //  activityMainBinding.textTitle.setText(deepLink.toString());
                        }
                       // activityMainBinding.textTitle.setText("deepLink.toString()");

                        Log.w("TAG", "onSuccess: "+"null" );


                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "getDynamicLink:onFailure", e);
                        activityDynamicLinkBinding.textviewId.setText(e.getMessage());
                    }
                });

    }
}
