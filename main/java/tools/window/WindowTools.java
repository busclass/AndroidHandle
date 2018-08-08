package tools.window;

import android.app.Activity;

/**
 * Created by Administrator on 2018/08/08.
 */

public class WindowTools {

    public static void myAlertDialog_NoneData(Activity context, String tipContent){
        MyPopupWindow myPopupWindow = new MyPopupWindow(context)
        {
            @Override
            public void sureClick() {
                // Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                this.dismiss();
            }

            @Override
            public void cancelClick() {
                this.dismiss();
            }

        };
        myPopupWindow.setContent(tipContent);

        myPopupWindow.show();
    }


}
