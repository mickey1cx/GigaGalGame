package com.gigagal.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();

    public GigaGalAssets gigaGalAssets;
    public PlatformAssets platformAssets;

    private AssetManager assetManager;

    private Assets() {

    }

    public void init() {

        this.assetManager = new AssetManager();
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        gigaGalAssets = new GigaGalAssets(atlas);
        platformAssets = new PlatformAssets(atlas);
    }


    @Override
    public void dispose() {

        assetManager.dispose();

    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    public class GigaGalAssets {

        public final AtlasRegion standingRight;
        public final AtlasRegion standingLeft;
        public final AtlasRegion jumpingRight;
        public final AtlasRegion jumpingLeft;

        public final Animation<AtlasRegion> walkingRightAnimation;
        public final Animation<AtlasRegion> walkingLeftAnimation;

        public GigaGalAssets(TextureAtlas atlas) {

            standingRight = atlas.findRegion(Constants.STANDING_RIGHT);
            standingLeft = atlas.findRegion(Constants.STANDING_LEFT);
            jumpingRight = atlas.findRegion(Constants.JUMPING_RIGHT);
            jumpingLeft = atlas.findRegion(Constants.JUMPING_LEFT);

            Array<AtlasRegion> walkingRightFrames = new Array<AtlasRegion>();
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_2_RIGHT));
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_1_RIGHT));
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_2_RIGHT));
            walkingRightFrames.add(atlas.findRegion(Constants.WALK_3_RIGHT));
            walkingRightAnimation = new Animation(Constants.GIGAGAL_WALK_DURATION, walkingRightFrames, Animation.PlayMode.LOOP);

            Array<AtlasRegion> walkingLeftFrames = new Array<AtlasRegion>();
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_2_LEFT));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_1_LEFT));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_2_LEFT));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALK_3_LEFT));
            walkingLeftAnimation = new Animation(Constants.GIGAGAL_WALK_DURATION, walkingLeftFrames, Animation.PlayMode.LOOP);

        }

    }

    public class PlatformAssets {

        public final NinePatch platform;

        public PlatformAssets(TextureAtlas atlas) {

            AtlasRegion region = atlas.findRegion(Constants.PLATFORM);
            int edge = Constants.PLATFORM_EDGE;
            platform = new NinePatch(region, edge, edge, edge, edge);

        }
    }
}
