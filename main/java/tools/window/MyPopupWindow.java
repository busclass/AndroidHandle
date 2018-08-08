package tools.window;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hstt.smart.R;

/**
 * Created by Administrator on 2018/08/06.
 */

public abstract class MyPopupWindow {

    private PopupWindow popupWindow;
    private Activity context;
    private String content;
    private String positiveWord = "确定";
    private String negativeWord = "取消";

    /**
     * 构造函数
     *
     * @param context
     */
    public MyPopupWindow(Activity context) {
        this.context = context;
    }



    /**
     * 显示警示框
     */
    public void show() {
        View popView = View.inflate(context, R.layout.popup, null);
        popupWindow = new PopupWindow(context);
        popupWindow.setHeight(400);
        popupWindow.setWidth(700);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setContentView(popView);
        popupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        TextView top_pop_text=     popView.findViewById(R.id.top_pop_text);
        top_pop_text.setText("警 告");

        TextView tv_pop_text = (TextView) popView.findViewById(R.id.tv_pop_text);
        tv_pop_text.setText(content);

        Button bt_pop_sure = (Button) popView.findViewById(R.id.bt_pop_sure);
        bt_pop_sure.setText(positiveWord);
        bt_pop_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureClick();
            }
        });

        Button bt_pop_cancel = (Button) popView.findViewById(R.id.bt_pop_cancel);
        bt_pop_cancel.setText(negativeWord);
        bt_pop_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelClick();
            }
        });
    }

    /**
     * 确定键按下后执行
     */
    public abstract void sureClick();

    /**
     * 取消键按下后执行
     */
    public abstract void cancelClick();

    /**
     * 为警示设置警示内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 设置确定键文字
     *
     * @param positiveWord
     */
    public void setPositiveWord(String positiveWord) {
        this.positiveWord = positiveWord;
    }

    /**
     * 设置取消键文字
     *
     * @param negativeWord
     */
    public void setNegativeWord(String negativeWord) {
        this.negativeWord = negativeWord;
    }

    /**
     * 手动取消警示框
     */
    public void dismiss() {
        popupWindow.dismiss();
    }
}
