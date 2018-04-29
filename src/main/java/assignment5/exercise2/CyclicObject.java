package assignment5.exercise2;

import java.io.Serializable;

public class CyclicObject implements Serializable {

    String text;
    CyclicObject refb;

    public CyclicObject(String text){
        this.text = text;
        refb = new CyclicObject(this, "b");
    }

    public CyclicObject(CyclicObject refb, String text){
        this.text = text;
        this.refb = refb;
    }


}
