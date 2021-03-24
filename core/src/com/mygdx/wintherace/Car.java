package com.mygdx.wintherace;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

class Car {
    private float p, s, r, width;

    Car(float width1) {
        width = width1;
    }

    void setValues(float p2, float r2){
        p = p2;
        r = r2;
    }

    void carDraw(ShapeRenderer sR){

        if(r==1)
            s = (float) (width /3.512195122);
        else if (r==2)
            s = (float) (width /1.97260274);

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