package nju.wujianchao.testapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    private Button start = null;
    private Button stop = null;
    private ImageView image = null;
    private AnimationDrawable animationDrawable = null;

    //播放图片数目
    int resCounts=50;

    //图片资源编号数组
    int []res=new int[resCounts];

    //图片位图对象数组
    Bitmap []bitmaps=new Bitmap[resCounts];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new StartListener());
        stop = (Button)findViewById(R.id.stop);
        stop.setOnClickListener(new StopListener());

        image = (ImageView)findViewById(R.id.imageview);

        animationDrawable = new AnimationDrawable();

        for(int i=0;i<resCounts;i++){
            int id = getResources().getIdentifier("b"+i, "drawable", "nju.wujianchao.testapp");
            res[i]=id;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable=true;
        options.inInputShareable=true;
        //设置该属性可获得图片的长宽等信息，但是避免了不必要的提前加载动画
        options.inJustDecodeBounds=false;
        InputStream inputStream=null;

        for(int i=0;i<resCounts;i++){
            inputStream=getResources().openRawResource(res[i]);
            Bitmap bitmap =BitmapFactory.decodeStream(inputStream, null, options);
            bitmaps[i]=bitmap;
        }


        for(int i=0;i<resCounts;i++){
            //添加每一帧图像，第二个参数为帧间时间间隔(毫秒)
            animationDrawable.addFrame(new BitmapDrawable(bitmaps[i]),0);
        }



//        for(int i =0;i<30;i++){
//            //第一个 就是我们的资源名称(图片名)
//            //第二个 就是我们存放图片的文件夹drawable
//            //第三个 包名也可以用Context的getPackageName返回应用程序的包名
//            int id = getResources().getIdentifier( "a"+i, "drawable", "nju.wujianchao.testapp");
//            System.out.println("ID:" + i);
//            animationDrawable.addFrame(ContextCompat.getDrawable(this,id),0);
//        }

        //设置是否循环播放，false为循环
        animationDrawable.setOneShot(true);
        image.setImageDrawable(animationDrawable);


    }


    //start stop 按钮监听函数
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



}
