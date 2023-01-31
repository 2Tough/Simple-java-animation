package objectanimation.animate.simplejavaanimation;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageH;
    private TextView scoreText;
    ConstraintLayout mBackgroundView;
    private int score;
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageH = findViewById(R.id.image_heart);
        scoreText = (TextView) findViewById(R.id.textScore);

        //
        mBackgroundView = findViewById(R.id.backgroundImage);

        findViewById(R.id.backgroundImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusCounter();
            }
            private void minusCounter() {
                score--;
                scoreText.setText("Score: " + score);

                //to display game over
                scoreChecker();
                //
            }

            // function to display game over screen
            private void scoreChecker() {
                if (score == -4) {

                    Intent gameOverIntent = new Intent(MainActivity.this, GameOverActivity.class);
                    startActivity(gameOverIntent);

                }
            }
            //

        });

        //


        findViewById(R.id.image_heart).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plusCounter();

                imageH.startAnimation(AnimationUtils.loadAnimation(
                        getApplicationContext(),
                        R.anim.grow


                ));

                positionImage();

            }

            private void plusCounter() {
                score++;
                scoreText.setText("Score: " + score);
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