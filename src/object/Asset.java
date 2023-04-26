package object;
import java.awt.*;
import java.awt.image.BufferedImage;

public interface Asset {
    void update();

    void draw(Graphics2D graphics2D);

    String getName();

    void speak();

    void damageReaction();

    void setIndex(int i);

    int getIndex();

    Rectangle getCollisionArea();

    int getCollisionDefaultX();

    int getCollisionDefaultY();

    boolean isCollision();

    int getWorldX();

    int getWorldY();

    void setWorldX(int i);

    void setWorldY(int i);

    Object getCurrentBahanMakanan();

    BufferedImage getImage1();

    String getDescription();

    void use();

    void dropObject(Asset droppedObject);

    int getPrice();
}
