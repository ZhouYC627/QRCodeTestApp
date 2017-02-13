package nju.wujianchao.testapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    private Button start = null;
    private Button stop = null;
    private ImageView image = null;
    private AnimationDrawable animationDrawable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        System.out.println(getPackageName());

        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new StartListener());
        stop = (Button)findViewById(R.id.stop);
        stop.setOnClickListener(new StopListener());

        image = (ImageView)findViewById(R.id.imageview);

        animationDrawable = new AnimationDrawable();
        for(int i =0;i<50;i++){
            //第一个 就是我们的资源名称(图片名)
            //第二个 就是我们存放图片的文件夹drawable
            //第三个 包名也可以用Context的getPackageName返回应用程序的包名
            int id = getResources().getIdentifier( "p0000"+i, "drawable", "nju.wujianchao.testapp");
            System.out.println("ID:" + id);
            animationDrawable.addFrame(ContextCompat.getDrawable(this,id),0);
        }
        //设置手否重复播放，false为重复
        animationDrawable.setOneShot(true);
        image.setImageDrawable(animationDrawable);

    }

    private class StartListener implements View.OnClickListener {

        public void onClick(View v)
        {
            animationDrawable.start();
        }
    }

    private class StopListener implements View.OnClickListener {

        public void onClick(View v)
        {
            animationDrawable.stop();
        }
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button

//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);


    }

}
