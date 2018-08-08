package com.hstt.smart;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import CallBackInterface.IWSCallback;
import info.UserLoginInfo;
import tools.JsonTools;
import tools.WSAsync;
import tools.window.WindowTools;


public class MainActivity extends AppCompatActivity {

    private EditText editTextLoginName;
    private EditText editTextPassWord;
    private Button loginButton;
    private TextView showMessage;
    private String jksqm=null;
    private String jkid="10015";
    private String ws_url= "http://192.168.3.177:8080/smart/ws/hsttService?wsdl";
    private String jsonStr=null;
    public static String resultData=null;

    private Handler mHandler;
    private boolean mRunning = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        showMessage=(TextView)findViewById(R.id.showMessage);
        mHandler =new Handler();

//        editTextLoginName = (EditText) findViewById(R.id.loginName);
//        editTextPassWord = (EditText) findViewById(R.id.passWord);
//        editTextLoginName.addTextChangedListener(new MaxLengthWatcher(20, editTextLoginName));
//        editTextPassWord.addTextChangedListener(new MaxLengthWatcher(20, editTextPassWord));
        loginButton =   findViewById(R.id.Login);

        loginButton.setOnClickListener(new ButtonListener());

        /*-------------------------------------------------------*/


        /*--------------------------------------------------------*/


    }

    String getJson() {
        String jsonStr = "{"
                + "\"loginName\":" + "\"" + "admin" + "\"" + "," +
                "\"password\":" + "\"" + "21232f297a57a5a743894a0e4a801fc3" + "\"" +
                "}";
        return jsonStr;
    }

    void webServiceAction(String arg0, String arg1, String arg2) throws IOException, XmlPullParserException {
        String WSDL_URI = "http://192.168.3.177:8080/smart/ws/hsttService?wsdl";
        String namespace = "http://impl.cxf.hstt.com/";
        String methodName = "action";
        SoapObject request = new SoapObject(namespace, methodName);

        request.addProperty("arg0", arg0);
        request.addProperty("arg1", arg1);
        request.addProperty("arg2", arg2);
        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.bodyOut = request;
        //如果是.net开发则需要打开
//        envelope.dotNet = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        try{
            httpTransportSE.call(null, envelope);//请求
        }catch (Exception e){
            Log.e("...",e.getMessage());
        }


        //获取返回数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        String result = object.getProperty(0).toString();

        resultData ="webServiceAction:"+ result;
    }



    public class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            String factorOneStr = editTextLoginName.getText().toString();
//            String factorTwoStr = editTextPassWord.getText().toString();
//            Intent intent = new Intent();
            // intent.putExtra(String.valueOf(R.string.login_name),factorOneStr);

          //  WindowTools.myAlertDialog_NoneData(MainActivity.this,"输入数据异常");

            if (WSAsync.canRequestWS(jksqm,jkid,jsonStr,ws_url)){
                new Thread(){
                    @Override
                    public void run() {
                        //  String str = getJson();
                        UserLoginInfo uer=new UserLoginInfo("admin",
                                "21232f297a57a5a743894a0e4a801fc3");
                        // WSAsyncTask.WebService("","10015",str,"http://192.168.3.177:8080/smart/ws/hsttService?wsdl");
                        jsonStr= JsonTools.ObjectToJsonString_GSON(uer);
                        //要写回调函数
                        resultData=  WSAsync.WebService(jksqm,jkid,jsonStr,ws_url,new MainActivityWSCallBack());

                    }
                }.start();
            }else
            {
                WindowTools.myAlertDialog_NoneData(MainActivity.this,"参 数 有 误");
            }

            //  Log.d("...",resultData);
           // showMessage.setText(resultData);
            //myPopupWindow
//还要保存用户信息
//            intent.putExtra("LOGIN_NAME", factorOneStr);
//            intent.putExtra("PASSWORD", factorTwoStr);
            //发送到服务器
//跳转
            // intent.setClass(MainActivity.this,ResultActivity.class);
//            MainActivity.this.startActivity(intent);
        }

    }

    public  class MainActivityWSCallBack implements IWSCallback{

        @Override
        public String WSRequestCallBack(final String resultData) {
//            WindowTools.myAlertDialog_NoneData(MainActivity.this
//            ,"-----------回调函数------------");
         //   showMessage.setText(resultData);
            final Runnable   runnableUi = new Runnable(){

                @Override
                public void run() {
                    showMessage.setText(resultData);
                }
            };
            mHandler.post(runnableUi);
            return resultData;
        }
    }
    //输入字符最大值
    public class MaxLengthWatcher implements TextWatcher {

        private int maxLen = 0;
        private EditText editText = null;


        public MaxLengthWatcher(int maxLen, EditText editText) {
            this.maxLen = maxLen;
            this.editText = editText;
        }

        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub

        }

        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            // TODO Auto-generated method stub

        }

        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            Editable editable = editText.getText();
            int len = editable.length();

            if (len > maxLen) {
                int selEndIndex = Selection.getSelectionEnd(editable);
                String str = editable.toString();
                //截取新字符串
                String newStr = str.substring(0, maxLen);
                editText.setText(newStr);
                editable = editText.getText();

                //新字符串的长度
                int newLen = editable.length();
                //旧光标位置超过字符串长度
                if (selEndIndex > newLen) {
                    selEndIndex = editable.length();
                }
                //设置新光标所在的位置
                Selection.setSelection(editable, selEndIndex);

            }
        }


    }
}
