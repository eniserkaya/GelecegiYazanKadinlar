package com.eniserkaya.customlistview;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private CustomWebViewClient webViewClient;
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mProgressDialog = new ProgressDialog(this);//ProgressDialog objesi oluşturuyoruz
        mProgressDialog.setMessage("Yükleniyor...");//ProgressDialog Yükleniyor yazısı



         webView = (WebView)findViewById(R.id.webviewid);

        webViewClient = new CustomWebViewClient();//CustomWebViewClient classdan webViewClient objesi oluşturuyoruz

        webView.getSettings().setBuiltInZoomControls(true); //zoom yapılmasına izin verir
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(webViewClient); //oluşturduğumuz webViewClient objesini webViewımıza set ediyoruz
        webView.loadUrl("https://www.eniserkaya.com");


    }



    private class CustomWebViewClient extends WebViewClient {
        //Alttaki methodların hepsini kullanmak zorunda deilsiniz
        //Hangisi işinize yarıyorsa onu kullanabilirsiniz.
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) { //Sayfa yüklenirken çalışır
            super.onPageStarted(view, url, favicon);

            if(!mProgressDialog.isShowing())//mProgressDialog açık mı kontrol ediliyor
            {
                mProgressDialog.show();//mProgressDialog açık değilse açılıyor yani gösteriliyor ve yükleniyor yazısı çıkıyor
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {//sayfamız yüklendiğinde çalışıyor.
            super.onPageFinished(view, url);

            if(mProgressDialog.isShowing()){//mProgressDialog açık mı kontrol
                mProgressDialog.dismiss();//mProgressDialog açıksa kapatılıyor
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Bu method açılan sayfa içinden başka linklere tıklandığında açılmasına yarıyor.
            //Bu methodu override etmez yada edip içini boş bırakırsanız ilk url den açılan sayfa dışında başka sayfaya geçiş yapamaz

            view.loadUrl(url);//yeni tıklanan url i açıyor
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,String description, String failingUrl) {
            //BU method webview yüklenirken herhangi bir hatayla karşilaşilırsa hata kodu dönüyor.
            //Dönen hata koduna göre kullanıcıyı bilgilendirebilir yada gerekli işlemleri yapabilirsiniz
            //errorCode ile hatayı alabilirsiniz
            //	if(errorCode==-8){
            //		Timeout
            //	} şeklinde kullanabilirsiniz

            //Hata Kodları aşağıdadır...

	    	/*
	    	 *  /** Generic error
		    public static final int ERROR_UNKNOWN = -1;

		    /** Server or proxy hostname lookup failed
		    public static final int ERROR_HOST_LOOKUP = -2;

		    /** Unsupported authentication scheme (not basic or digest)
		    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;

		    /** User authentication failed on server
		    public static final int ERROR_AUTHENTICATION = -4;

		    /** User authentication failed on proxy
		    public static final int ERROR_PROXY_AUTHENTICATION = -5;

		    /** Failed to connect to the server
		    public static final int ERROR_CONNECT = -6;

		    /** Failed to read or write to the server
		    public static final int ERROR_IO = -7;

		    /** Connection timed out
		    public static final int ERROR_TIMEOUT = -8;

		    /** Too many redirects
		    public static final int ERROR_REDIRECT_LOOP = -9;

		    /** Unsupported URI scheme
		    public static final int ERROR_UNSUPPORTED_SCHEME = -10;

		    /** Failed to perform SSL handshake
		    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;

		    /** Malformed URL
		    public static final int ERROR_BAD_URL = -12;

		    /** Generic file error
		    public static final int ERROR_FILE = -13;

		    /** File not found
		    public static final int ERROR_FILE_NOT_FOUND = -14;

		    /** Too many requests during this load
		    public static final int ERROR_TOO_MANY_REQUESTS = -15;
	    	*/

        }

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){//eğer varsa bir önceki sayfaya gidecek
            webView.goBack();
        }else{//Sayfa yoksa uygulamadan çıkacak
            super.onBackPressed();
        }
    }
}
