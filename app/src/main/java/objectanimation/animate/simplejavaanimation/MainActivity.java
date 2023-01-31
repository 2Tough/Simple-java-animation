package objectanimation.animate.simplejavaanimation;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageH;
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageH = findViewById(R.id.image_heart);





        findViewById(R.id.image_heart).setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View v) {

                clicked = true;
                if(clicked) {
                    count++;
                } else {
                    count--;
                }

                TextView text = (TextView) findViewById(R.id.textScore);
                text.setText(count);

                imageH.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.grow


                ));

                positionImage();

            }

            public void positionImage() {

                DisplayMetrics metrics = getResources().getDisplayMetrics();

                int DeviceTotalWidth = metrics.widthPixels;
                int DeviceTotalHeight = metrics.heightPixels;

                float randX = getRandomPositionX(DeviceTotalWidth);
                float randY = getRandomPositionY(DeviceTotalHeight);

                Log.d(TAG, "positionImage: totalX: " + DeviceTotalWidth + " totalY: " + DeviceTotalHeight);
                Log.d(TAG, "positionImage: ranX: " + randX + " ranY: " + randY);

                imageH.setX(randX);
                imageH.setY(randY);

            }

            public float getRandomPositionX(float deviceTotalWidth) {
                Random random = new Random();
                float randX = random.nextInt((int) deviceTotalWidth - (int) getImageSizeinPixels());
                return randX;
            }

            public float getRandomPositionY(float DeviceTotalHeight) {
                Random random = new Random();
                float randY = random.nextInt((int) DeviceTotalHeight - (int) getImageSizeinPixels());
                return randY;
            }

            public float getImageSizeinPixels() {
                // Converts 65 dip into its equivalent px
                float imageSize = 65f;
                float extraPadding = 40f;
                float dip = imageSize + extraPadding;
                Resources r = getResources();
                float px = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        dip,
                        r.getDisplayMetrics()
                );

                return px;
            }



        });





    }


}