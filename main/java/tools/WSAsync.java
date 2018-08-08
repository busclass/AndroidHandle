package tools;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import CallBackInterface.IWSCallback;

/**
 * Created by Administrator on 2018/08/06.
 */

public class WSAsync {

    public  static String WebService( String jksqm,String jkid,
                                      String jsonStr,String ws_url,
                                      IWSCallback wsCallback    ){

// //支线程不能调用延时操作，会阻碍主线程，AlertDialog就是延时操作


        return WebServiceTask(jksqm, jkid, jsonStr, ws_url,  wsCallback);
    }

    private static String WebServiceTask(String jksqm, String jkid,
                                         String jsonStr, String ws_url,
                                        IWSCallback wsCallback ){

        //判断值是否有效
        //无效处理

        String result=null;
//        if (jksqm==null){
//            wsCallback.WSRequestCallBack(result);
//            return    null;
//        }
//        if (jkid==null)
//        {
//            wsCallback.WSRequestCallBack(result);
//            return  null;
//        }
//        if(jsonStr==null)
//        {
//            wsCallback.WSRequestCallBack(result);
//            return  null;
//        }
        String namespace = "http://impl.cxf.hstt.com/";
        String methodName = "action";
        SoapObject request = new SoapObject(namespace, methodName);

        request.addProperty("arg0", jksqm);
        request.addProperty("arg1", jkid);
        request.addProperty("arg2", jsonStr);
        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.bodyOut = request;
        //如果是.net开发则需要打开
//        envelope.dotNet = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(ws_url);
        try{
            httpTransportSE.call(null, envelope);//请求
        }catch (Exception e){

            Log.e("...",e.getMessage());
        }
        //获取返回数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        result = object.getProperty(0).toString();
        wsCallback.WSRequestCallBack(result);
//        Log.d("debug",result);
//        MainActivity.resultData="WSAsync:"+result;
        return object.getProperty(0).toString();// object;
    }

    public static Boolean canRequestWS (String jksqm, String jkid,
                                       String jsonStr, String ws_url){
        if (jksqm==null)return false;

        if (jkid==null) return false;

        if(jsonStr==null) return false;

        if (ws_url==null) return false;
        return true;
    }

}
