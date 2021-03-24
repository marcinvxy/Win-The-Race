package com.mygdx.wintherace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

class Game extends ApplicationAdapter {
    private Car car1;
    private Car car2;
    private Car car3;
    private Car car4;
    private Car car5;
    private CarMain carMain;
    private BitmapFont font;
    private Music soundButton;
    private SpriteBatch batch;
    private Random r = new Random();
    private ShapeRenderer shapeRenderer;
    private float a1, a2, a3, a4, a5, p1, p2, p3, p4, p5, margin1, margin2, move, height, width, textScale;
    private int r1 = r.nextInt(3), r2 = r.nextInt(3), r3 = r.nextInt(3), r4 = r.nextInt(3), r5 = r.nextInt(3), rG=1, live, points, level, step, play = 0;
    private boolean pause = false, crash = false, addPoint1 = true, addPoint2 = true, addPoint3 = true, addPoint4 = true, addPoint5 = true, levelBool = false, varBool = true;

    @Override
    public void create () {
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        carMain = new CarMain(width, height);
        car1 = new Car(width);
        car2 = new Car(width);
        car3 = new Car(width);
        car4 = new Car(width);
        car5 = new Car(width);
        font = new BitmapFont();
        font.setColor(Color.YELLOW);
        textScale = (float) (width /378.947368421);
        font.getData().setScale(textScale);
        margin1 = (float) (width /5.538461538);
        margin2 = (float) (width /1.358490566);
        move = (float) (width /3.512195122);
        Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.play();
        music.setLooping(true);
        soundButton = Gdx.audio.newMusic(Gdx.files.internal("clickSound.mp3"));
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        if(play==0)
            gameMenu("EXIT", "PLAY GAME", x, y);
        else if(play==1){
            if(varBool){
                varBool = false;
                variables();
            }
            start(x, y);
        }
        else if(play==2)
            gameMenu("EXIT", "PLAY AGAIN", x, y);
    }

    private void variables(){
        levelBool = false;
        step = 6;
        points = 0;
        level = 1;
        live = 3;
        p1 = height;
        p2 = (float) (height /0.761904762);
        p3 = (float) (height /0.615384615);
        p4 = (float) (height /0.516129032);
        p5 = (float) (height /0.444444444);
        a1 = 0;
        a2 = (float) (height /3.657142857);
        a3 = (float) (height /1.828571429);
        a4 = (float) (height /1.219047619);
        a5 = (float) (height /0.914285714);
    }

    private void clickSound(){
        soundButton.play();
    }

    private void gameMenu(String a, String b ,float x, float y){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        button((float) (width/36), (float) (height/2.265486726), (float) (width/2.181818182), (float) (height/8.533333333),Color.RED);
        button((float) (width/1.945945946), (float) (height/2.265486726), (float) (width/2.181818182), (float) (height/8.533333333),Color.RED);
        shapeRenderer.end();
        font.getData().setScale((float) 2.5);
        text(a, (float) (width/4.64516129), (float) (height/1.969230769), Color.YELLOW);
        text(b, (float) (width/1.655172414), (float) (height/1.969230769), Color.YELLOW);
        if(Gdx.input.justTouched()){
            System.out.println(x);
            System.out.println(y);
            if (x >= width/1.945945946 && x <= width/1.028571429 && y >= height/2.265486726 && y <= height/1.79020979){
                clickSound();
                varBool = true;
                sleep(400);
                play=1;
                font.getData().setScale(textScale);
            }
            else if(x >= width/36 && x <= width/2.057142857 && y >= height/2.265486726 && y <= height/1.79020979){
                clickSound();
                Gdx.app.exit();
            }
        }
    }

    private void start(float x, float y){
        if(Gdx.input.justTouched()){
            if(x>=0 && x< width /2.117647059 && y<= height && y> height /1.103448276 && !pause){
                clickSound();
                move = (float) (width /3.512195122);
                rG=1;
            }
            else if(x> width /1.894736842 && x<= width && y<= height && y> height /1.103448276 && !pause){
                clickSound();
                move = (float) (width /1.97260274);
                rG=2;
            }
            else if(x> width /2.117647059 && x< width /1.894736842 && y< height && y> height /1.103448276){
                clickSound();
                if(!pause)
                    pause = true;
                else
                    pause = false;
            }
        }
        if(crash){
            crash=false;
            sleep(1000);
            if(live==0)
                play=2;
            if(rG==1){
                move = (float) (width /1.97260274);
                rG=2;
            }
            else if(rG==2){
                move = (float) (width /3.512195122);
                rG=1;
            }
        }
        carMain.setValues(move, rG);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        rectangle(margin1,a5); rectangle(margin2,a5);
        rectangle(margin1,a4); rectangle(margin2,a4);
        rectangle(margin1,a3); rectangle(margin2,a3);
        rectangle(margin1,a2); rectangle(margin2,a2);
        rectangle(margin1,a1); rectangle(margin2,a1);
        if(r1!=0){
            car1.setValues(p1,r1);
            car1.carDraw(shapeRenderer);
            if(!crash(p1, r1))
                addPoint1 =false;
            if(addPoint1){
                if(!points(p1, r1, addPoint1))
                    addPoint1 = false;
            }
        }
        if(r2!=0){
            car2.setValues(p2,r2);
            car2.carDraw(shapeRenderer);
            if(!crash(p2, r2)){
                addPoint2 =false;
            }
            if(addPoint2){
                if(!points(p2, r2, addPoint2))
                    addPoint2 = false;
            }
        }
        if(r3!=0){
            car3.setValues(p3,r3);
            car3.carDraw(shapeRenderer);
            if(!crash(p3, r3))
                addPoint3 =false;
            if(addPoint3){
                if(!points(p3, r3, addPoint3))
                    addPoint3 = false;
            }
        }
        if(r4!=0){
            car4.setValues(p4,r4);
            car4.carDraw(shapeRenderer);
            if(!crash(p4, r4))
                addPoint4 =false;
            if(addPoint4){
                if(!points(p4, r4, addPoint4)){
                    addPoint4 = false;
                }
            }
        }
        if(r5!=0){
            car5.setValues(p5,r5);
            car5.carDraw(shapeRenderer);
            if(!crash(p5, r5))
                addPoint5 =false;
            if(addPoint5){
                if(!points(p5, r5, addPoint5))
                    addPoint5 = false;
            }
        }
        carMain.car(shapeRenderer);
        button((float) (0), (float) (0), (float) (width /2), (float) (height /10.666666667), Color.DARK_GRAY);
        button((float) (width /2), (float) 0, (float) (width /2), (float) (height /10.666666667), Color.DARK_GRAY);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.circle((float) (width /2), (float) (height /21.333333333), (float) (width /18));
        shapeRenderer.triangle((float) (width /24), (float) (height /21.333333333), (float) (width /9), (float) (height /42.666666667), (float) (width /9), (float) (height /14.222222222));
        shapeRenderer.triangle((float) (width /1.043478261), (float) (height /21.333333333), (float) (width /1.125), (float) (height /42.666666667), (float) (width /1.125), (float) (height /14.222222222));

        if(crash)
            button(0, (float) (height /2.461538462), width, (float) (height /4.571428571), Color.YELLOW);

        shapeRenderer.end();
        text("Points: "+points, (float) (width /144), (float) (height /1.024), Color.YELLOW);
        text("Level: "+level, (float) (width /144), (float) (height /1.066666667), Color.YELLOW);
        text("Live: "+live, (float) (width /144), (float) (height /1.113043478), Color.YELLOW);

        if(crash){
            font.getData().setScale(4);
            text("CRASH", (float) (width /2.93877551), (float) (height /1.855072464), Color.RED);
            font.getData().setScale(textScale);
        }
        if(a1<=-height /5.12)
            a1 = (float) (height *1.171875);
        else if(a2<=-height /5.12)
            a2 = (float) (height *1.171875);
        else if(a3<=-height /5.12)
            a3 = (float) (height *1.171875);
        else if(a4<=-height /5.12)
            a4 = (float) (height *1.171875);
        else if(a5<=-height /5.12)
            a5 = (float) (height *1.171875);
        if(p1<= height /-6.4){
            addPoint1 = true;
            r1 = r.nextInt(3);
            p1 = (float) (height /0.719101124);
        }
        else if(p2<= height /-6.4){
            addPoint2 = true;
            r2 = r.nextInt(3);
            p2 = (float) (height /0.719101124);
        }
        else if(p3<= height /-6.4){
            addPoint3 = true;
            r3 = r.nextInt(3);
            p3 = (float) (height /0.719101124);
        }
        else if(p4<= height /-6.4){
            addPoint4 = true;
            r4 = r.nextInt(3);
            p4 = (float) (height /0.719101124);
        }
        else if(p5<= height /-6.4){
            addPoint5 = true;
            r5 = r.nextInt(3);
            p5 = (float) (height /0.719101124);
        }
        if(points%15==0 && levelBool){
            sleep(1000);
            step = step +3;
            level = level+1;
            levelBool = false;
        }
        else if (points%15 !=0)
            levelBool = true;
        if(!pause){
            a1 -= step; a2 -= step; a3 -= step; a4 -= step; a5 -= step;
            p1 -= step; p2 -= step; p3 -= step; p4 -= step; p5 -= step;
        }
        else if(pause){
            font.getData().setScale(3);
            text("PAUSE", (float) (width /2.571428571), (float) (height /1.828571429),Color.RED);
            font.getData().setScale(textScale);
        }
    }

    private void sleep(int s){
        try {
            Thread.sleep(s);}
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void rectangle(float a, float b){
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(a, b, (float) (width /14.4), (float) (height /5.12));
    }

    private void button(float x, float y, float width1, float height1, Color color){
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, width1, height1);
    }

    private void text(String str, float x, float y, Color color){
        batch.begin();
        font.setColor(color);
        font.draw(batch, str, x, y);
        batch.end();
    }

    private boolean crash(float p11, float r11) {
        float l1 = (float) (p11+ height /25.6);
        float l2 = (float) (p11+ height /6.4);
        float pG1 = (float) (carMain.getP()+ height /25.6);
        float pG2 = (float) (carMain.getP()+ height /6.4);
        if(carMain.getR()==r11){
            if(l1 > pG1 && l1 < pG2 || l2 > pG1 && l2 < pG2){
                live = live-1;
                crash=true;
                return false;
            }
        }
        return true;
    }

    private boolean points(float p11, float r11, boolean add){
        float l2 = (float) (p11+ height /6.4);
        float pG1 = (float) (carMain.getP()+ height /25.6);
        if(l2<pG1 && r11!=rG && add){
            points = points+1;
            return false;
        }
        else
            return true;
    }

    @Override
    public void dispose () {
        font.dispose();
        soundButton.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}