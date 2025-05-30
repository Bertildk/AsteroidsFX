package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

public class Entity extends World implements Serializable {

    private final UUID ID = UUID.randomUUID();
    private EntityType type;
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private float size;
    private int speed;
    private final HashMap<String, Object> properties = new HashMap<>();
    private String name ="Entity";

    public void setEntityType(EntityType type) {
        this.type = type;
    }
    public EntityType getEntityType() {
        return type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Object getSPI(String key) {
        return properties.get(key);
    }
    public void setSPI(String key, Object value) {
        properties.put(key, value);
    }

    public int getSpeed() {
        return (int) speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
        
    public float getRadius() {
        return this.radius;
    }
    public float getSize() {
        return this.size;
    }
    public void setSize(float size) {
        this.size = size;
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
