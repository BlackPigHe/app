package com.example.baiduyuyin;

import java.util.ArrayList;  

import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;  
import android.os.Bundle;  
import android.app.Activity;  
import android.view.Menu;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button;  
import android.widget.EditText;  
  
public class MainActivity extends Activity {  
      
    //��ʼ��ť   
    private Button BtnStart;  
    //�ı���  
    private EditText InputBox;  
    //�ٶ�����ʶ��Ի���  
    private BaiduASRDigitalDialog mDialog=null;  
    
    private DialogRecognitionListener mDialogListener=null;  
    //Ӧ����Ȩ��Ϣ ������ʹ���˹ٷ�SDK�еĲ����������Ҫ�����������룬���޸�Ϊ�Լ�����Ȩ��Ϣ  
    private String API_KEY="8MAxI5o7VjKSZOKeBzS4XtxO";  
    private String SECRET_KEY="Ge5GXVdGQpaxOmLzc8fOM8309ATCz9Ha";  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
            if (mDialog == null) {  
                if (mDialog != null) {  
                mDialog.dismiss();  
            }  
            Bundle params = new Bundle();  
            //����API_KEY, SECRET_KEY  
            params.putString(BaiduASRDigitalDialog.PARAM_API_KEY, API_KEY);  
            params.putString(BaiduASRDigitalDialog.PARAM_SECRET_KEY, SECRET_KEY);  
            //��������ʶ��Ի���Ϊ��ɫ��������  
            params.putInt(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_BLUE_LIGHTBG);  
            //ʵ�����ٶ�����ʶ��Ի���  
            mDialog = new BaiduASRDigitalDialog(this, params);  
            //���ðٶ�����ʶ��ص��ӿ�  
            mDialogListener=new DialogRecognitionListener()  
            {  
  
                @Override  
                public void onResults(Bundle mResults)   
                {  
                    ArrayList<String> rs = mResults != null ? mResults.getStringArrayList(RESULTS_RECOGNITION) : null;      
                    if (rs != null && rs.size() > 0) {  
                        InputBox.setText(rs.get(0));  
                    }  
                      
                }  
                  
            };  
            mDialog.setDialogRecognitionListener(mDialogListener);  
        }  
        //��������ʶ��ģʽΪ����ģʽ  
        mDialog.setSpeechMode(BaiduASRDigitalDialog.SPEECH_MODE_INPUT);  
        //��������ʶ��  
        mDialog.getParams().putBoolean(BaiduASRDigitalDialog.PARAM_NLU_ENABLE, false);  
          
        //����Ԫ��  
        BtnStart=(Button)findViewById(R.id.BtnStart);  
        InputBox=(EditText)findViewById(R.id.InputBox);  
        BtnStart.setOnClickListener(new OnClickListener()  
        {  
            @Override  
            public void onClick(View v)   
            {  
                mDialog.show();  
            }  
        });  
    }
}
