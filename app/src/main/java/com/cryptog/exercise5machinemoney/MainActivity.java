package com.cryptog.exercise5machinemoney;

import android.graphics.drawable.Drawable;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int[][] slotValues = new int[3][3];
    private ImageView[][] tableImages = new ImageView[3][3];
    private Button btnGo;
    private int score = 0;
    private TextView textViewResult;
    private Drawable[] imagesResult;
    private int [] points = new int[]{100,20,5,1};
    private boolean [] winsVertical = new boolean[]{false,false,false};
    private boolean [] winsHorizontal = new boolean[]{false,false,false};
    private boolean winsDiagonalEsquerda = false;
    private boolean winsDiagonalDireita = false;
    private SpringAnimation[] animationsWins;
    private boolean stopAnimationWins = false;
    private TextView textViewValueAcquired;
    private int valueAcquired = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagesResult = new Drawable[]{ContextCompat.getDrawable(this,R.drawable.rabbitfacingright),ContextCompat.getDrawable(this,R.drawable.elephantfacingright),
                ContextCompat.getDrawable(this,R.drawable.dromedaryfacingright),ContextCompat.getDrawable(this,R.drawable.deerfacingright)};



        int[] imagesViewIds = new int[]{
                R.id.imageView0,
                R.id.imageView1,
                R.id.imageView2,
                R.id.imageView3,
                R.id.imageView4,
                R.id.imageView5,
                R.id.imageView6,
                R.id.imageView7,
                R.id.imageView8
        };

        textViewResult = findViewById(R.id.textViewResult);
        textViewValueAcquired = findViewById(R.id.textViewValueAcquiredResult);

        // Linkando as referencias das ImagesView
        for(int i = 0;i < 3;i++){
            for(int j = 0;j < 3;j++){
                tableImages[i][j] = findViewById(imagesViewIds[3*i + j]);
            }
        }

        btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        btnGo.setEnabled(false);
                // parando as animaçoes
                valueAcquired = 0;
                stopAnimationWins = true;
                winsDiagonalDireita = false;
                winsDiagonalEsquerda = false;

                for (int i = 0;i < 3;i++){
                  winsHorizontal[i] = false;
                  winsVertical[i] = false;
                }

                //

                Random random = new Random();
                for (int i = 0;i < 3;i++) {
                    for(int k = 0;k < 3;k++){
                        slotValues[i][k] = random.nextInt(4);
                    }
                }

                // Set Values random in ImageView
                for(int i = 0;i < 3;i++) {
                    for (int j = 0; j < 3; j++) {
                        tableImages[i][j].setImageDrawable(formatedImage(slotValues[i][j]));
                    }
                }

                // values  horizontal e vertical
                for (int i = 0; i < 3; i++) {
                    for(int j = 0;j < 4;j++){
                        if (j == slotValues[0][i] && j == slotValues[1][i] && j == slotValues[2][i]) {

                            score = formatedResult(j);
                            valueAcquired = formatedValueAcquired(j);
                            winsVertical[i] = true;
                        }

                        if (j == slotValues[i][0] && j == slotValues[i][1] && j == slotValues[i][2]) {
                            score = formatedResult(j);
                            valueAcquired = formatedValueAcquired(j);
                            winsHorizontal[i] = true;
                        }
                    }

                }

                // valores na diagonal
                for(int i = 0;i < 4;i++){
                    if( i == slotValues[0][0] && i == slotValues[1][1] && i == slotValues[2][2]){
                        score = formatedResult(i);
                        valueAcquired = formatedValueAcquired(i);
                        winsDiagonalEsquerda = true;
                    }

                    if (i == slotValues[0][2] && i == slotValues[1][1] && i == slotValues[2][0]){
                        score = formatedResult(i);
                        valueAcquired = formatedValueAcquired(i);
                        winsDiagonalDireita = true;
                    }
                }
                rollImage();

            }
        });

    }

    public int formatedResult(int i){
        Log.d("value",String.valueOf(score + points[i]));
    return score + points[i] ;
    }

    public int formatedValueAcquired(int i){
        return valueAcquired + points[i];
    }

    public Drawable formatedImage(int numeber){
        return imagesResult[numeber];
    }

    public void rollImage(){

        final int[] rollImageNumber = {0};
        // 1 table row

        //image0
        final SpringAnimation anim1Image0 = new SpringAnimation(tableImages[0][0], DynamicAnimation.TRANSLATION_X,  600);
        final SpringAnimation anim2Image0 = new SpringAnimation(tableImages[0][0], DynamicAnimation.TRANSLATION_X,  0);


        //image1
        final SpringAnimation anim1Image1 = new SpringAnimation(tableImages[0][1], DynamicAnimation.TRANSLATION_X,  -300);
        final SpringAnimation anim2Image1 = new SpringAnimation(tableImages[0][1], DynamicAnimation.TRANSLATION_X,  300);
        final SpringAnimation anim3Image1 = new SpringAnimation(tableImages[0][1], DynamicAnimation.TRANSLATION_X,  0);

        //image2
        final SpringAnimation anim1Image2 = new SpringAnimation(tableImages[0][2], DynamicAnimation.TRANSLATION_X,  -600);
        final SpringAnimation anim2Image2 = new SpringAnimation(tableImages[0][2], DynamicAnimation.TRANSLATION_X,  0);

        // 2 table Row

        //image3
        final SpringAnimation anim1Image3 = new SpringAnimation(tableImages[1][0], DynamicAnimation.TRANSLATION_X,  600);
        final SpringAnimation anim2Image3 = new SpringAnimation(tableImages[1][0], DynamicAnimation.TRANSLATION_X,  0);

        //image4
        final SpringAnimation anim1Image4 = new SpringAnimation(tableImages[1][1], DynamicAnimation.TRANSLATION_X,  -300);
        final SpringAnimation anim2Image4 = new SpringAnimation(tableImages[1][1], DynamicAnimation.TRANSLATION_X,  300);
        final SpringAnimation anim3Image4 = new SpringAnimation(tableImages[1][1], DynamicAnimation.TRANSLATION_X,  0);

        // image5
        final SpringAnimation anim1Image5 = new SpringAnimation(tableImages[1][2], DynamicAnimation.TRANSLATION_X,  -600);
        final SpringAnimation anim2Image5 = new SpringAnimation(tableImages[1][2], DynamicAnimation.TRANSLATION_X,  0);


        // table row 3
        //image6
        final SpringAnimation anim1Image6 = new SpringAnimation(tableImages[2][0], DynamicAnimation.TRANSLATION_X,  600);
        final SpringAnimation anim2Image6 = new SpringAnimation(tableImages[2][0], DynamicAnimation.TRANSLATION_X,  0);

        //image7
        final SpringAnimation anim1Image7 = new SpringAnimation(tableImages[2][1], DynamicAnimation.TRANSLATION_X,  -300);
        final SpringAnimation anim2Image7 = new SpringAnimation(tableImages[2][1], DynamicAnimation.TRANSLATION_X,  300);
        final SpringAnimation anim3Image7 = new SpringAnimation(tableImages[2][1], DynamicAnimation.TRANSLATION_X,  0);

        // image8
        final SpringAnimation anim1Image8 = new SpringAnimation(tableImages[2][2], DynamicAnimation.TRANSLATION_X,  -600);
        final SpringAnimation anim2Image8 = new SpringAnimation(tableImages[2][2], DynamicAnimation.TRANSLATION_X,  0);


        // animacoes 1 start
        anim1Image0.start();
        anim1Image1.start();
        anim1Image2.start();
        anim1Image3.start();
        anim1Image4.start();
        anim1Image5.start();
        anim1Image6.start();
        anim1Image7.start();
        anim1Image8.start();

        // table row 1
        //image0
        anim1Image0.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim2Image0.start();

            }
        });

        anim2Image0.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
               if(rollImageNumber[0] < 3) {
                   anim1Image0.start();
               }
            }
        });

        // image1

        anim1Image1.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim2Image1.start();
            }
        });

        anim2Image1.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim3Image1.start();
            }
        });
        anim3Image1.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
         if(rollImageNumber[0] < 3){
             anim1Image1.start();
         }else{
             textViewResult.setText(Integer.toString(score));
             btnGo.setEnabled(true);
             stopAnimationWins = false;
             animationWinsRunning();
             textViewValueAcquired.setText(Integer.toString(valueAcquired));
         }
         rollImageNumber[0]++;

            }
        });

        // image2

        anim1Image2.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim2Image2.start();

            }
        });

        anim2Image2.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
            if(rollImageNumber[0] < 3) {
                anim1Image2.start();
            }
            }
        });


        // table row 2
        // image3

        anim1Image3.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim2Image3.start();

            }
        });

        anim2Image3.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                if(rollImageNumber[0] < 3) {
                    anim1Image3.start();
                }
            }
        });

        // image4

        anim1Image4.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim2Image4.start();
            }
        });

        anim2Image4.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim3Image4.start();
            }
        });
        anim3Image4.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                if(rollImageNumber[0] < 3){
                    anim1Image4.start();
                }

            }
        });

        // image5

        anim1Image5.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim2Image5.start();

            }
        });

        anim2Image5.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                if(rollImageNumber[0] < 3) {
                    anim1Image5.start();
                }
            }
        });


        // table row 3
        // image6

        anim1Image6.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim2Image6.start();

            }
        });

        anim2Image6.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                if(rollImageNumber[0] < 3) {
                    anim1Image6.start();
                }
            }
        });

        // image7

        anim1Image7.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim2Image7.start();
            }
        });

        anim2Image7.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim3Image7.start();
            }
        });
        anim3Image7.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                if(rollImageNumber[0] < 3){
                    anim1Image7.start();
                }

            }
        });

        // image8

        anim1Image8.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                anim2Image8.start();

            }
        });

        anim2Image8.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                if(rollImageNumber[0] < 3) {
                    anim1Image8.start();
                }
            }
        });
    }

    public void animationWinsRunning(){
        animationsWins = createAnimation();

        if(winsHorizontal[0]){
            if(!animationsWins[0].isRunning()){
                animationsWins[0].start();
            }
            if(!animationsWins[2].isRunning()){
                animationsWins[2].start();
            }
            if(!animationsWins[4].isRunning()){
                animationsWins[4].start();
            }

            animationsWins[0].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                animationsWins[1].start();
                }
            });

            animationsWins[1].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                 if(stopAnimationWins == false){
                     animationsWins[0].start();
                 }

                }
            });

            animationsWins[2].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[3].start();
                }
            });

            animationsWins[3].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                     animationsWins[2].start();
                    }

                }
            });

            animationsWins[4].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[5].start();
                }
            });

            animationsWins[5].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[4].start();
                    }
                }
            });
        }

        if(winsHorizontal[1]){
            if(!animationsWins[6].isRunning()){
                animationsWins[6].start();
            }
            if(!animationsWins[8].isRunning()){
                animationsWins[8].start();
            }
            if(!animationsWins[10].isRunning()){
                animationsWins[10].start();
            }

            animationsWins[6].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[7].start();
                }
            });

            animationsWins[7].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[6].start();
                    }
                }
            });

            animationsWins[8].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[9].start();
                }
            });

            animationsWins[9].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[8].start();
                    }
                }
            });

            animationsWins[10].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[11].start();
                }
            });

            animationsWins[11].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[10].start();
                    }
                }
            });
        }

        if(winsHorizontal[2]){
            if(!animationsWins[12].isRunning()){
                animationsWins[12].start();
            }
            if(!animationsWins[14].isRunning()){
                animationsWins[14].start();
            }
            if(!animationsWins[16].isRunning()){
                animationsWins[16].start();
            }

            animationsWins[12].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[13].start();
                }
            });

            animationsWins[13].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[12].start();
                    }
                }
            });

            animationsWins[14].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[15].start();
                }
            });

            animationsWins[15].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[14].start();
                    }
                }
            });

            animationsWins[16].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[17].start();
                }
            });

            animationsWins[17].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[16].start();
                    }
                }
            });
        }



        // vertical

        if(winsVertical[0]){
           if(!animationsWins[0].isRunning()){
               animationsWins[0].start();
           }
           if(!animationsWins[6].isRunning()){
               animationsWins[6].start();
           }
           if(!animationsWins[12].isRunning()){
               animationsWins[12].start();
           }

           animationsWins[0].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[1].start();
                }
            });

            animationsWins[1].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[0].start();
                    }
                }
            });

            animationsWins[6].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[7].start();
                }
            });

            animationsWins[7].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[6].start();
                    }
                }
            });

            animationsWins[12].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[13].start();
                }
            });

            animationsWins[13].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[12].start();
                    }
                }
            });

        }

        if(winsVertical[1]){
            if(!animationsWins[2].isRunning()){
                animationsWins[2].start();
            }
            if(!animationsWins[8].isRunning()){
                animationsWins[8].start();
            }
            if(!animationsWins[14].isRunning()){
                animationsWins[14].start();
            }

            animationsWins[2].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[3].start();
                }
            });

            animationsWins[3].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[2].start();
                    }
                }
            });

            animationsWins[8].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[9].start();
                }
            });

            animationsWins[9].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[8].start();
                    }
                }
            });

            animationsWins[14].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[15].start();
                }
            });

            animationsWins[15].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[14].start();
                    }
                }
            });

        }

        if(winsVertical[2]){
            if(!animationsWins[4].isRunning()){
                animationsWins[4].start();
            }
            if(!animationsWins[10].isRunning()){
                animationsWins[10].start();
            }
            if(!animationsWins[16].isRunning()){
                animationsWins[16].start();
            }

            animationsWins[4].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[5].start();
                }
            });

            animationsWins[5].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[4].start();
                    }
                }
            });

            animationsWins[10].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[11].start();
                }
            });

            animationsWins[11].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[10].start();
                    }
                }
            });

            animationsWins[16].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[17].start();
                }
            });

            animationsWins[17].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[16].start();
                    }
                }
            });

        }

        // diagonal right

        if(winsDiagonalDireita){
            if(!animationsWins[4].isRunning()){
                animationsWins[4].start();
            }
            if(!animationsWins[8].isRunning()){
                animationsWins[8].start();
            }
            if(!animationsWins[12].isRunning()){
                animationsWins[12].start();
            }

            animationsWins[4].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[5].start();
                }
            });

            animationsWins[5].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[4].start();
                    }
                }
            });

            animationsWins[8].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[9].start();
                }
            });

            animationsWins[9].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[8].start();
                    }
                }
            });

            animationsWins[12].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[13].start();
                }
            });

            animationsWins[13].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[12].start();
                    }
                }
            });
        }

        // diagonal left

        if(winsDiagonalEsquerda) {
            if (!animationsWins[0].isRunning()) {
                animationsWins[0].start();
            }
            if (!animationsWins[8].isRunning()) {
                animationsWins[8].start();
            }
            if (!animationsWins[16].isRunning()) {
                animationsWins[16].start();
            }


            animationsWins[0].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[1].start();
                }
            });

            animationsWins[1].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[0].start();
                    }
                }
            });

            animationsWins[8].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[9].start();
                }
            });

            animationsWins[9].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[8].start();
                    }
                }
            });

            animationsWins[16].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    animationsWins[17].start();
                }
            });

            animationsWins[17].addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                    if(stopAnimationWins == false){
                        animationsWins[16].start();
                    }
                }
            });
        }


    }

    public SpringAnimation[] createAnimation(){
        int rotation = -15;
        int finalPoint = 0;

        //image0
        SpringAnimation anim1Image0 = new SpringAnimation(tableImages[0][0], DynamicAnimation.ROTATION,  rotation);
        SpringAnimation anim2Image0 = new SpringAnimation(tableImages[0][0], DynamicAnimation.ROTATION,  finalPoint);


        //image1
        SpringAnimation anim1Image1 = new SpringAnimation(tableImages[0][1], DynamicAnimation.ROTATION,  rotation);
        SpringAnimation anim2Image1 = new SpringAnimation(tableImages[0][1], DynamicAnimation.ROTATION,  finalPoint);

        //image2
        SpringAnimation anim1Image2 = new SpringAnimation(tableImages[0][2], DynamicAnimation.ROTATION,  rotation);
        SpringAnimation anim2Image2 = new SpringAnimation(tableImages[0][2], DynamicAnimation.ROTATION,  finalPoint);

        // 2 table Row

        //image3
        SpringAnimation anim1Image3 = new SpringAnimation(tableImages[1][0], DynamicAnimation.ROTATION,  rotation);
        SpringAnimation anim2Image3 = new SpringAnimation(tableImages[1][0], DynamicAnimation.ROTATION,  finalPoint);


        //image4
        SpringAnimation anim1Image4 = new SpringAnimation(tableImages[1][1], DynamicAnimation.ROTATION,  rotation);
        SpringAnimation anim2Image4 = new SpringAnimation(tableImages[1][1], DynamicAnimation.ROTATION,  finalPoint);

        // image5
        SpringAnimation anim1Image5 = new SpringAnimation(tableImages[1][2], DynamicAnimation.ROTATION,  rotation);
        SpringAnimation anim2Image5 = new SpringAnimation(tableImages[1][2], DynamicAnimation.ROTATION,  finalPoint);


        // table row 3
        //image6
        SpringAnimation anim1Image6 = new SpringAnimation(tableImages[2][0], DynamicAnimation.ROTATION,  rotation);
        SpringAnimation anim2Image6 = new SpringAnimation(tableImages[2][0], DynamicAnimation.ROTATION,  finalPoint);

        //image7
        SpringAnimation anim1Image7 = new SpringAnimation(tableImages[2][1], DynamicAnimation.ROTATION,  rotation);
        SpringAnimation anim2Image7 = new SpringAnimation(tableImages[2][1], DynamicAnimation.ROTATION,  finalPoint);

        // image8
        SpringAnimation anim1Image8 = new SpringAnimation(tableImages[2][2], DynamicAnimation.ROTATION,  rotation);
        SpringAnimation anim2Image8 = new SpringAnimation(tableImages[2][2], DynamicAnimation.ROTATION,  finalPoint);


       SpringAnimation[] animations = new SpringAnimation[]{anim1Image0,anim2Image0,anim1Image1,anim2Image1,anim1Image2,anim2Image2,
                anim1Image3,anim2Image3,anim1Image4,anim2Image4,anim1Image5,anim2Image5,anim1Image6,anim2Image6,
                anim1Image7,anim2Image7,anim1Image8,anim2Image8};

       return animations;
    }
}
