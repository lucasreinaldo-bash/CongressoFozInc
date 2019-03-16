package inc.congressofozabnc2019;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import inc.congressofozabnc2019.R;
/**
 * Essa classe é utilizada como tela inicial. Possui uma animação e faz transição após 3 segundos para a ValidarCPF
 */
public class Maps extends AppCompatActivity {


    private Button btn_home,btn_voltar;
    private LocationManager lm;
    private Location location;
    private double longitude = -25.5598719;
    private double latitude = -54.5616859;

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_mapss);

        // android.support.v7.app.ActionBar bar = getSupportActionBar();
        // bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8F00")));
        Bundle extras = getIntent().getExtras();
        mWebView = (WebView) findViewById(R.id.site);
        btn_home =  findViewById(R.id.btn_home);
        btn_voltar =  findViewById(R.id.btn_voltar);


        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //Recebendo informação de outra Activity
        if (null != getIntent()) {
            /** Pegamos o VALOR_1**/
            final String site = getIntent().getStringExtra("site");


            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setSupportZoom(false);
            mWebView.loadUrl("https://www.google.com/maps?ll=-25.559872,-54.559497&z=12&t=m&hl=pt-BR&gl=BR&mapclient=embed&cid=13044238909293844399");
            mWebView.setWebViewClient(new HelloWebViewClient());


        }
    }
    @Override
    public void onBackPressed () {

        Intent intent = new Intent(Maps.this, Menu.class);
        startActivity(intent);
        finish();

    }

    private class HelloWebViewClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            //progressBar.setVisibility(view.GONE);
        }

    }


    @Override
    public boolean onKeyDown ( int keyCode, KeyEvent event)
    { //if back key is pressed
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }
}
