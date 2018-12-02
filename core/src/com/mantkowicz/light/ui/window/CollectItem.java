package com.mantkowicz.light.ui.window;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mantkowicz.light.actor.Item;

import static com.mantkowicz.light.ui.window.CollectWindowItemState.UNCHOSEN;

public class CollectItem extends Button {
    private final Drawable unchosenDownDrawable;
    private final Drawable unchosenUpDrawable;
    private final Drawable chosenUpDrawable;
    private final Drawable ableToPutDrawable;

    private final CollectItemType collectItemType;
    private CollectWindowItemState state;

    private Item item;
    private Image itemImage;

    public CollectItem(AssetManager assetManager, CollectItemType collectItemType, Item item) {
        super(getButtonStyle(assetManager));
        this.item = item;
        this.collectItemType = collectItemType;
        this.state = UNCHOSEN;

        tryAddItemImage(item);

        unchosenUpDrawable = getDrawable(assetManager.get("item_background.png", Texture.class));
        unchosenDownDrawable = getDrawable(assetManager.get("item_background_pressed.png", Texture.class));
        chosenUpDrawable = getDrawable(assetManager.get("item_background_chosen.png", Texture.class));
        ableToPutDrawable = getDrawable(assetManager.get("item_background_able_to_put.png", Texture.class));
    }

    public CollectWindowItemState getState() {
        return state;
    }

    public CollectItem setState(CollectWindowItemState state) {
        this.state = state;
        switch (state) {
            case UNCHOSEN:
                getStyle().up = unchosenUpDrawable;
                break;
            case CHOSEN:
                getStyle().up = chosenUpDrawable;
                break;
            case ABLE_TO_PUT:
                getStyle().up = ableToPutDrawable;
                break;
        }
        return this;
    }

    private static ButtonStyle getButtonStyle(AssetManager assetManager) {
        ButtonStyle buttonStyle = new ButtonStyle();

        buttonStyle.up = getDrawable(assetManager.get("item_background.png", Texture.class));
        buttonStyle.down = getDrawable(assetManager.get("item_background_pressed.png", Texture.class));

        return buttonStyle;
    }

    private static Drawable getDrawable(Texture texture) {
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    public boolean isEmpty() {
        return item == null;
    }

    public Item getItem() {
        return item;
    }

    public Item removeItem() {
        removeActor(itemImage);
        return item;
    }

    public CollectItem setItem(Item item) {
        this.item = item;
        tryAddItemImage(item);
        return this;
    }

    private void tryAddItemImage(Item item) {
        if (item != null) {
            itemImage = new Image(item.getThumbnail());
            add(itemImage);
        } else {
            itemImage = null;
        }
    }

    public CollectItemType getCollectItemType() {
        return collectItemType;
    }
}
