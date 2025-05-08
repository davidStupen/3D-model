package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Spatial;
import com.jme3.math.Vector3f;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.anim.AnimComposer;
import com.jme3.scene.Node;
import com.jme3.light.DirectionalLight;
import com.jme3.light.SpotLight;
import com.jme3.math.FastMath;
//import com.jme3.input.KeyInput;
//import com.jme3.input.controls.KeyTrigger;
//import com.jme3.input.controls.ActionListener;

public class Main extends SimpleApplication {
    private AnimComposer animComposer;
    public static void main(String[] args) {
        
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        
        SpotLight spot = new SpotLight();
// Pozice světla
spot.setPosition(new Vector3f(2.0162942f, 0.520663084f, -1.0343894f));
// Směr, kterým světlo svítí
spot.setDirection(new Vector3f(0, 0, 1)); // dolů
// Barva světla
spot.setColor(ColorRGBA.White);
// Vzdálenost, kam dosáhne
spot.setSpotRange(1000f); // dosvit ve světelném kuželu
// Úhel rozptylu (v radiánech, max π/2)
spot.setSpotInnerAngle(25f * FastMath.DEG_TO_RAD);  // ostré jádro kužele
spot.setSpotOuterAngle(50f * FastMath.DEG_TO_RAD); // celkový rozptyl
rootNode.addLight(spot);

//DirectionalLight sun = new DirectionalLight();
//sun.setColor(ColorRGBA.White);
//sun.setDirection(new Vector3f(-1f, -1f, -1f).normalizeLocal()); // Směr světla
//rootNode.addLight(sun);

        // === Kamera ===
        cam.setLocation(new Vector3f(2.0162942f, 2.120663084f, 1.9656105f));
        cam.lookAt(new Vector3f(5,0,5), Vector3f.UNIT_Y);

        // === Model ===
        Spatial myGame = assetManager.loadModel("Models/importGame.j3o");
        myGame.scale(0.1f);
        rootNode.attachChild(myGame);
        
        Spatial car = assetManager.loadModel("Models/car.j3o");
        car.scale(0.1f);
        car.setLocalTranslation(2f, 0.07f, 1f);
        car.rotate(0, 35f, 0);
        rootNode.attachChild(car);
        
        Spatial anim = assetManager.loadModel("Models/animation.j3o");
        anim.setLocalTranslation(8f, 0.5f, 0);
        anim.scale(0.1f);
        rootNode.attachChild(anim);
        animComposer = findAnimComposer(anim);
        animComposer.setCurrentAction("move");
        //animComposer.setCurrentAction("rotation");
        
        //inputManager.addListener("gg", "start");

        // === Debug: výpis pozice modelu ===
        System.out.println("Model bounds: " + car.getWorldBound());
    }
    private AnimComposer findAnimComposer(Spatial spatial){
        AnimComposer composer = spatial.getControl(AnimComposer.class);
        if (composer != null) return composer;
        if (spatial instanceof Node) {
            for(Spatial child : ((Node) spatial).getChildren()){
                composer = findAnimComposer(child);
                if (composer != null) return composer;
            }
        }
        return null;
    }
    
}

//private AnimComposer findAnimComposer(Spatial spatial) {
  //      AnimComposer composer = spatial.getControl(AnimComposer.class);
    //    if (composer != null) {
      //      return composer;
       // }
  //      if (spatial instanceof Node) {
    //        for (Spatial child : ((Node) spatial).getChildren()) {
      //          composer = findAnimComposer(child);
        //        if (composer != null) {
          //          return composer;
            //    }
    //        }
      //  }
        //return null;
  //  }
