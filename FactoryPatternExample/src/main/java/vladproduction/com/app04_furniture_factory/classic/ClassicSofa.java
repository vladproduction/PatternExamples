package vladproduction.com.app04_furniture_factory.classic;

import vladproduction.com.app04_furniture_factory.Furniture;

public class ClassicSofa implements Furniture {

    @Override
    public void create() {
        System.out.println("Classic Sofa created");
    }
}
