package tools;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Administrator on 2018/1/21.
 */

public class WSAsyncTask {
//  setContentView(R.layout.activity_main);
    // 由服务提供方提供参数，注意最后一个“/”不能少，否则服务端无法接收参数
//    private static final String NAMESPACE ="http://tempuri.org/";

    // 服务所在的URL
//    private static final String METHOD_NAME="LedGuardService";

    // 要调用的方法名
//    private static final String SOAP_ACTION ="http://tempuri.org/LedGuardService";
    // SOAP_ACTION == NAMESPACE + METHOD_NAME;


    public static SoapObject WebService(String jksqm, String jkid,String json, String serviceUrl) throws SoapFault {
        SoapObject soapObject=null;
        // WSDL文档的URL，192.168.17.156为PC的ID地址
        //String serviceUrl = "http://192.168.2.11:8080/s/ws/hsttService?wsdl";
        // 定义调用的WebService方法名
        String methodName = "action";
        SoapObject request = new SoapObject("http://impl.cxf.hstt.com/",methodName);
        // 第2步：设置WebService方法的参数
        request.addProperty("arg0",jksqm);
        request.addProperty("arg1",jkid);
        request.addProperty("arg2",json);
        // 第3步：创建SoapSerializationEnvelope对象，并指定WebService的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
//        SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
        // 设置bodyOut属性
        envelope.bodyOut = request;

        // 设置是否调用的是dotNet开发的WebService
//        envelope.implicitTypes=true;
//        envelope.dotNet = true;
        // 第4步：创建HttpTransportSE对象，并指定WSDL文档的URL
        HttpTransportSE ht = new HttpTransportSE(serviceUrl);
        try {
            // 第5步：调用WebService
            ht.call(null, envelope);
            if (envelope.getResponse() != null){
                  soapObject = (SoapObject) envelope.bodyIn;
            //   result= JSONObject.parseObject(soapObject.getProperty(0).toString());
                JSONObject root= new JSONObject(soapObject.getProperty(0).toString());
//                root.get("result");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return soapObject;
    }

}
