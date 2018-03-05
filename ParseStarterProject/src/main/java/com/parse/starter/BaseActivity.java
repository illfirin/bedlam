package pakuteam.bedlam_experiment_0_1;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;

import android.view.LayoutInflater;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import studios.codelight.smartloginlibrary.LoginType;
import studios.codelight.smartloginlibrary.SmartLogin;
import studios.codelight.smartloginlibrary.SmartLoginCallbacks;
import studios.codelight.smartloginlibrary.SmartLoginConfig;
import studios.codelight.smartloginlibrary.SmartLoginFactory;
import studios.codelight.smartloginlibrary.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartFacebookUser;
import studios.codelight.smartloginlibrary.users.SmartGoogleUser;
import studios.codelight.smartloginlibrary.users.SmartUser;
import studios.codelight.smartloginlibrary.util.SmartLoginException;

import com.parse.Parse;

import butterKnife.ButterKnife;
import butterKnife.BindView;

import android.support.annotation.NonNull;
import com.parse.starter.MainController;
import com.parse.starter.R;
import com.parse.starter.UserPreferenceManager;
import com.anupcowkur.reservoir.Reservoir;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.android.AndroidSentryClientFactory;

public class BaseActivity extends AppCompatActivity implements StartLoginCallbacks
{
    private View view_simpleToolbar;
    public FrameLayout container;
    //View bindings
    @BindView(R.id.facebook_button) private Button facebookLoginButton;
    @BindView(R.id.google_button)   private Button googleLoginButton;
    @BindView(R.id.custom_signIn)   private Button customSigninButton;
    @BindView(R.id.custom_signUp)   private Button customSignupButton;
    @BindView(R.id.logout_button)   private Button logoutButton;
    @BindView(R.id.facebook_button) private EditText emailEditText;
    @BindView(R.id.facebook_button) private EditText passwordEditText;
  /*
      we will add theme changing in the next update
      private static String Theme_Current = "AppliedTheme";

  */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //TODO: add app id later
        //Parse server initialization
        Parse.initialize(new Parse.Configuration.Builder(this)
                          .applicationId("enter_app_id_here")
                          .server("https://myBedlamServer:8096/parse")
                          .build());
        try
        {
            //TODO: add app id later
            //Parse server initialization
            Parse.initialize(new Parse.Configuration.Builder(this)
                            .applicationId("enter_app_id_here")
                            .server("https://myBedlamServer:8096/parse")
                            .build());

            //Sentry initialization
            Context ctx = this.getApplicationContext();
            //use client key from project settings page
            String sentryDsn = "https://publicKey:secretKey@host:port/1?options";
            Sentry.init(sentryDsn, new AndroidSentryClientFactory(ctx));
            Sentry.init(new AndroidSentryClientFactory(ctx));


            setAppTheme();
            super.onCreate();
            setContentView(R.layout.activity_base);

            base_toolbarContainer = (AppBarLayout) findViewById(R.id.base_appbar);
            container = (FrameLayout)findViewById(R.id.container);
            mainLayout = (CoordinatorLayout) findViewById(R.id.full_layout);
            //Ads initialization
            mAdView = (AdView) findViewById(R.id.View);
            AdRequest adReq = new AdRequest.Builder().build();
            mAdView.loadAd(adReq);
            //TODO: enter admob app id
            MobileAds.initialize(this, "here");

            Reservoir.init(this, 8192);
        }
        catch(Exception e)
        {
            Sentry.capture(e);
            e.printStackTrace();
        }
    }
/*
    public void setSimpleToolbar(boolean isSimpleToolbarRequire)
    {
        if(isSimpleToolbarRequire)
        {
            view_simpleToolbar = LayoutInflater.from(this).inflate(R.layout.simple_toolbar, base_toolbarContainer);
            toolbar = (android.support.v7.widget.Toolbar) view_simpleToolbar.findViewById(R.id.toolbar);
            if(toolbar)
            {
                setSupportActionBar(toolbar);
                toolbar.setTitle(R.string.application_name);
                toolbar.setTitleTextColor(Color.WHITE);
            }
        }
    }

    public void setToolbarSubTitle(String header)
    {
        if(toolbar != null)
        {
            toolbar.setSubtitle(header);
        }
    }

    public void setToolbarElevation(float value)
    {
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if(toolbar != null)
            {
                toolbar.setElevation(value);
            }
        }
    }
    //set XML objext reference

    public abstract void setReference();

    private void setAppTheme()
    {
        //Will be added later
        if(!UserPreferenceManager.preferenceGetString(Theme_Current, "").equals(""))
        {
            if(UserPreferenceManager.preferenceGetString(Theme_Current, "").equals("Green"))
            {
                setTheme(R.style.ThemeApp_Green);
            }
            else if(UserPreferenceManager.preferenceGetString(Theme_Current, "").equals("Purple"))
            {

            }
            else
            {
                    setTheme(R.style.ThemeApp_Green);
            }
            //etc for all of themes
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        try
        {
            Reservoir.clear();
        }
        catch(Exception e)
        {
            Sentry.capture(e);
        }
    }*/
}
