package com.mantkowicz.light.board.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.mantkowicz.light.configuration.GamePrepareConfiguration;
import com.mantkowicz.light.service.resources.ResourcesService;

import java.util.ArrayList;
import java.util.List;

import static com.mantkowicz.light.board.tile.TilePoint.*;

public abstract class Tile extends Group {
    private static final int NOTIFICATION_OFFSET = 5;
    private static final float TAN_30_DIV_6 = 0.289f;

    private static int ID_SEQUENCE = 1;

    private final int id;
    private final List<Tile> neighbours;

    private MapProperties attributes;
    private Array<Vector2> polygon;
    private final Image background;
    private Image targetMarkerImage;

    private boolean marked = false;

    protected Tile(ResourcesService resourcesService, String backgroundTextureName) {
        this.id = ID_SEQUENCE++;
        this.neighbours = new ArrayList<>();

        Texture backgroundTexture = resourcesService.getAssetManager().get(backgroundTextureName, Texture.class);
        this.background = new Image(backgroundTexture);

        this.targetMarkerImage = resourcesService.getTargetMarkerImage();

        addActor(this.background);
        this.setSize(this.background.getWidth(), this.background.getHeight());
    }

    public abstract void prepare(GamePrepareConfiguration configuration);

    protected Vector2[] getPolygonVertices() {
        Vector2[] result = new Vector2[polygon.size];
        for (int i = 0; i < polygon.size; i++) {
            result[i] = polygon.get(i);
        }
        return result;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        initTilePolygon();
    }

    private void initTilePolygon() {
        polygon = new Array<>();
        polygon.add(getTilePointPosition(LEFT_BOTTOM_CORNER));
        polygon.add(getTilePointPosition(RIGHT_BOTTOM_CORNER));
        polygon.add(getTilePointPosition(RIGHT_CORNER));
        polygon.add(getTilePointPosition(RIGHT_TOP_CORNER));
        polygon.add(getTilePointPosition(LEFT_TOP_CORNER));
        polygon.add(getTilePointPosition(LEFT_CORNER));
    }

    private float getCornerOffset() {
        return TAN_30_DIV_6 * getHeight();
    }

    public boolean doesContainPoint(Vector2 point) {
        return Intersector.isPointInPolygon(this.polygon, point);
    }

    public void mark() {
        marked = true;
    }

    public void unmark() {
        marked = false;
    }

    public void showTargetMarker() {
        addActor(targetMarkerImage);
        targetMarkerImage.setPosition((getWidth() - targetMarkerImage.getWidth()) / 2f, (getHeight() - targetMarkerImage.getHeight()) / 2f);
    }

    public void hideTargetMarker() {
        targetMarkerImage.remove();
    }

    public boolean isMarked() {
        return marked;
    }

    public abstract boolean isAccessible();

    public List<Tile> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Tile neighbour) {
        this.neighbours.add(neighbour);
    }

    private Image getBackground() {
        return background;
    }

    private Integer getId() {
        return id;
    }

    public Vector2 getCenter() {
        return new Vector2(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }

    protected Vector2 getLeftBottom() {
        return new Vector2(getX(), getY());
    }

    protected MapProperties getAttributes() {
        return attributes;
    }

    public void setAttributes(MapProperties attributes) {
        this.attributes = attributes;
    }

    /**
     * Calculates position that provided actor must have to be located at the point of the tile
     *
     * @param actor the actor that must be located at the point of the tile
     * @return position of positioned actor
     */
    public Vector2 calculatePositionForActorAt(Actor actor, TilePoint tilePoint) {
        return getTilePointPosition(tilePoint).sub(actor.getWidth() / 2f, actor.getHeight() / 2f);
    }

    /**
     * @return center position for notification for this tile
     */
    public Vector2 getNotificationCenterPosition() {
        return getCenter().cpy().add(0, getHeight() / 2f + NOTIFICATION_OFFSET);
    }

    private Vector2 getTilePointPosition(TilePoint tilePoint) {
        Vector2 result = getCenter();
        switch (tilePoint) {
            case RIGHT_TOP_CORNER:
                result = new Vector2(getX() + getWidth() - getCornerOffset(), getY() + getHeight());
                break;
            case RIGHT_CORNER:
                result = new Vector2(getX() + getWidth(), getY() + getHeight() / 2f);
                break;
            case RIGHT_BOTTOM_CORNER:
                result = new Vector2(getX() + getWidth() - getCornerOffset(), getY());
                break;
            case LEFT_BOTTOM_CORNER:
                result = new Vector2(getX() + getCornerOffset(), getY());
                break;
            case LEFT_CORNER:
                result = new Vector2(getX(), getY() + getHeight() / 2f);
                break;
            case LEFT_TOP_CORNER:
                result = new Vector2(getX() + getCornerOffset(), getY() + getHeight());
                break;
            case CENTER:
                break;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (!getId().equals(tile.getId())) return false;
        if (isMarked() != tile.isMarked()) return false;
        if (getAttributes() != null ? !getAttributes().equals(tile.getAttributes()) : tile.getAttributes() != null)
            return false;
        if (polygon != null ? !polygon.equals(tile.polygon) : tile.polygon != null) return false;
        if (getBackground() != null ? !getBackground().equals(tile.getBackground()) : tile.getBackground() != null)
            return false;
        return targetMarkerImage != null ? targetMarkerImage.equals(tile.targetMarkerImage) : tile.targetMarkerImage == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getAttributes() != null ? getAttributes().hashCode() : 0);
        result = 31 * result + (polygon != null ? polygon.hashCode() : 0);
        result = 31 * result + (getBackground() != null ? getBackground().hashCode() : 0);
        result = 31 * result + (targetMarkerImage != null ? targetMarkerImage.hashCode() : 0);
        result = 31 * result + (isMarked() ? 1 : 0);
        return result;
    }
}
