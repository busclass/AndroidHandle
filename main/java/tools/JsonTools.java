package tools;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2018/08/07.
 */

public class JsonTools {

//    传参数
    public static String  ObjectToJsonString_JSONObject(String key, Object value) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(key,value);
        return jsonObject.toString();
    }
    public static String ObjectToJsonString_GSON(Object object){
//        Gson gson=new Gson();
//        gson.toJson(object);
        return  new Gson().toJson(object);
    }
    public static <T> T JsonStringToObject(String jsonString,Class<T> classOfT ){
            T obj=new Gson().fromJson(jsonString, (Type) classOfT  );
        return   obj;
    }

//    public static String  getUserLoginBack(String key, String  jsonString) throws JSONException {
//        UserLoginBack userLoginBack=new UserLoginBack();
//        JSONObject jsonObject=new JSONObject();
//        JSONObject userLoginBackjsonObject  = jsonObject.getJSONObject("UserLoginBack");
//       // UserLoginBack.Root rootObj=new UserLoginBack.Root();
//        return jsonObject.toString();
//    }
//    public static UserLoginBack.Root getObj(String jsonString){
//        //接收{}对象，此处接收数组对象会有异常
//        if(jsonString.indexOf("[") != -1){
//            jsonString = jsonString.replace("[", "");
//        }
//        if(jsonString.indexOf("]") != -1){
//            jsonString = jsonString.replace("]", "");
//        }
//        JSONObject obj = new JSONObject().fromObject(jsonString);//将json字符串转换为json对象
//        UserLoginBack.Root rootObj = (UserLoginBack.Root)JSONObject.toBean(obj,UserLoginBack.Root.class);//将建json对象转换为Person对象
//        return rootObj;//返回一个Person对象
//    }
//    public static String createSimpleJsonString(Object obj){
//        JSONObject jsonObject=JSONObject.fromObject(obj);
////没有用，无效api
//    }
}
