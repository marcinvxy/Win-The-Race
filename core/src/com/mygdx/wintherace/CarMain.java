package com.mygdx.wintherace;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

class CarMain {
    private float w, s, r, p, width;

    CarMain(float width, float height) {
        this.width = width;
        w=height;
    }

    void setValues(float s2, float r2){
        p = (float) (w/8.533333333);
        s = s2;
        r = r2;
    }

    float getR(){
        return r;
    }

    float getP(){
        return p;
    }

    void car(ShapeRenderer sR){
        float widthP = (float) (width /14.4);
        float heightP = (float) (width /14.4);
        sR.setColor(Color.BLACK);
        sR.rect(s, p, widthP, heightP);
        sR.rect(s+widthP, p+heightP, widthP, heightP);
        sR.rect(s+widthP*2, p, widthP, heightP);
        sR.rect(s+widthP, p+heightP*2, widthP, heightP);
        sR.rect(s, p+widthP*2, widthP, heightP);
        sR.rect(s+widthP*2, p+heightP*2, widthP, heightP);
        sR.rect(s+widthP, p+heightP*3, widthP, heightP);
    }
}