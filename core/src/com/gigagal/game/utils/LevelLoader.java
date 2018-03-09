package com.gigagal.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gigagal.game.Level;
import com.gigagal.game.entities.Enemy;
import com.gigagal.game.entities.ExitPortal;
import com.gigagal.game.entities.GigaGal;
import com.gigagal.game.entities.Platform;
import com.gigagal.game.entities.PowerUp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.Comparator;

/**
 * Created by mickey.1cx on 09.03.2018.
 */

public class LevelLoader {

    public static final String TAG = LevelLoader.class.toString();

    public static Level load(String levelName, Viewport viewport){

        Level level = new Level(viewport);
        String levelPath = Constants.LEVEL_DIR + File.separator + levelName + "." + Constants.LEVEL_FILE_EXTENSION;

        try {

            FileHandle levelSource = Gdx.files.internal(levelPath);

            JSONParser parser = new JSONParser();

            JSONObject rootJson = (JSONObject) parser.parse(levelSource.reader());
            JSONObject composite = (JSONObject) rootJson.get(Constants.LEVEL_COMPOSITE);

            JSONArray platforms = (JSONArray) composite.get(Constants.LEVEL_9PATCHES);
            loadPlatforms(platforms, level);

            JSONArray images = (JSONArray) composite.get(Constants.LEVEL_IMAGES);
            loadGameObjects(images, level);

        } catch (Exception ex) {

            Gdx.app.error("LEVEL_ERROR_MESSAGE", ex.getMessage());

        }

        return level;

    }

    private static void loadGameObjects(JSONArray array, Level level) {

        for (Object object: array) {

            final JSONObject gameObject = (JSONObject) object;

            final String identifier = (String) gameObject.get(Constants.LEVEL_IMAGENAME_KEY);

            final float x = safeGetFloat(gameObject, Constants.LEVEL_X_KEY);
            final float y = safeGetFloat(gameObject, Constants.LEVEL_Y_KEY);

            if (identifier != null && identifier.equals(Constants.LEVEL_GIGAGAL_TAG)) {
                level.setGigagal(new GigaGal(level, new Vector2(x, y + Constants.GIGAGAL_HEIGHT * 2)));
            } else if (identifier != null && identifier.equals(Constants.EXIT_PORTAL_TAG)) {
                level.setExitPortal(new ExitPortal(new Vector2(x + Constants.EXIT_PORTAL_CENTER.x,
                        y + Constants.EXIT_PORTAL_CENTER.y)));
            }  else if (identifier != null && identifier.equals(Constants.POWERUP_SPRITE)) {
                level.getPowerUps().add(new PowerUp(new Vector2(x + Constants.POWERUP_CENTER.x,
                        y + Constants.POWERUP_CENTER.y)));
            }

        }

    }

    private static float safeGetFloat(JSONObject object, String key){
        Number number = (Number) object.get(key);
        return (number == null) ? 0 : number.floatValue();
    }

    private static void loadPlatforms(JSONArray array, Level level) {

        Array<Platform> platformArray = new Array<Platform>();

        for (Object object : array) {
            final JSONObject platformObject = (JSONObject) object;

            final float x = safeGetFloat(platformObject, Constants.LEVEL_X_KEY);
            final float y = safeGetFloat(platformObject, Constants.LEVEL_Y_KEY);

            final float width = ((Number) platformObject.get(Constants.LEVEL_WIDTH_KEY)).floatValue();
            final float height = ((Number) platformObject.get(Constants.LEVEL_HEIGHT_KEY)).floatValue();

            Platform platform = new Platform(x, y + height, width, height);
            platformArray.add(platform);

            final String identifier = (String) platformObject.get(Constants.LEVEL_IDENTIFIER_KEY);

            if (identifier != null && identifier.equals(Constants.LEVEL_ENEMY_TAG)) {
                level.addEnemy(new Enemy(platform));
            }

        }

        platformArray.sort(new Comparator<Platform>() {
            @Override
            public int compare(Platform p1, Platform p2) {

                float t1 = p1.getTop();
                float t2 = p2.getTop();

                if (t1 > t2) {
                    return -1;
                } else if (t1 < t2) {
                    return 1;
                }

                return 0;
            }
        });

        level.setPlatforms(platformArray);

    }


}
