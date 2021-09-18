package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;
import java.lang.String;

public class Resources {
    public static TextureAtlas textures_textures;

    public static Skin skin_skin;

    public static Sound sfx_ahh;

    public static Sound sfx_click;

    public static Sound sfx_libgdx;

    public static Sound sfx_pleaseDontKillMe;

    public static Sound sfx_shot;

    public static Sound sfx_swoosh;

    public static Sound sfx_tv;

    public static Music bgm_audioSample;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        textures_textures = assetManager.get("textures/textures.atlas");
        SpineLibgdx.skeletonData = assetManager.get("spine/libgdx.json");
        SpineLibgdx.animationData = assetManager.get("spine/libgdx.json-animation");
        SpineLibgdx.animationAnimation = SpineLibgdx.skeletonData.findAnimation("animation");
        SpineLibgdx.animationStand = SpineLibgdx.skeletonData.findAnimation("stand");
        SpineLibgdx.skinDefault = SpineLibgdx.skeletonData.findSkin("default");
        SpineRay3k.skeletonData = assetManager.get("spine/ray3k.json");
        SpineRay3k.animationData = assetManager.get("spine/ray3k.json-animation");
        SpineRay3k.animationAnimation = SpineRay3k.skeletonData.findAnimation("animation");
        SpineRay3k.animationStand = SpineRay3k.skeletonData.findAnimation("stand");
        SpineRay3k.skinDefault = SpineRay3k.skeletonData.findSkin("default");
        skin_skin = assetManager.get("skin/skin.json");
        SkinSkinStyles.bClose = skin_skin.get("close", Button.ButtonStyle.class);
        SkinSkinStyles.bDefault = skin_skin.get("default", Button.ButtonStyle.class);
        SkinSkinStyles.bPause = skin_skin.get("pause", Button.ButtonStyle.class);
        SkinSkinStyles.bToggle = skin_skin.get("toggle", Button.ButtonStyle.class);
        SkinSkinStyles.bStop = skin_skin.get("stop", Button.ButtonStyle.class);
        SkinSkinStyles.bPlay = skin_skin.get("play", Button.ButtonStyle.class);
        SkinSkinStyles.cbDefault = skin_skin.get("default", CheckBox.CheckBoxStyle.class);
        SkinSkinStyles.ibDefault = skin_skin.get("default", ImageButton.ImageButtonStyle.class);
        SkinSkinStyles.itbDefault = skin_skin.get("default", ImageTextButton.ImageTextButtonStyle.class);
        SkinSkinStyles.itbRadio = skin_skin.get("radio", ImageTextButton.ImageTextButtonStyle.class);
        SkinSkinStyles.lSmall = skin_skin.get("small", Label.LabelStyle.class);
        SkinSkinStyles.lDefault = skin_skin.get("default", Label.LabelStyle.class);
        SkinSkinStyles.lstSelectBox = skin_skin.get("select-box", List.ListStyle.class);
        SkinSkinStyles.pDefaultVertical = skin_skin.get("default-vertical", ProgressBar.ProgressBarStyle.class);
        SkinSkinStyles.pDefaultHorizontal = skin_skin.get("default-horizontal", ProgressBar.ProgressBarStyle.class);
        SkinSkinStyles.spSelectBox = skin_skin.get("select-box", ScrollPane.ScrollPaneStyle.class);
        SkinSkinStyles.spDefault = skin_skin.get("default", ScrollPane.ScrollPaneStyle.class);
        SkinSkinStyles.sbDefault = skin_skin.get("default", SelectBox.SelectBoxStyle.class);
        SkinSkinStyles.sScrubber = skin_skin.get("scrubber", Slider.SliderStyle.class);
        SkinSkinStyles.sDefaultVertical = skin_skin.get("default-vertical", Slider.SliderStyle.class);
        SkinSkinStyles.sDefaultHorizontal = skin_skin.get("default-horizontal", Slider.SliderStyle.class);
        SkinSkinStyles.spltDefaultVertical = skin_skin.get("default-vertical", SplitPane.SplitPaneStyle.class);
        SkinSkinStyles.spltDefaultHorizontal = skin_skin.get("default-horizontal", SplitPane.SplitPaneStyle.class);
        SkinSkinStyles.tbToggle = skin_skin.get("toggle", TextButton.TextButtonStyle.class);
        SkinSkinStyles.tbDefault = skin_skin.get("default", TextButton.TextButtonStyle.class);
        SkinSkinStyles.tfDefault = skin_skin.get("default", TextField.TextFieldStyle.class);
        SkinSkinStyles.ttDefault = skin_skin.get("default", TextTooltip.TextTooltipStyle.class);
        SkinSkinStyles.tsDefault = skin_skin.get("default", Touchpad.TouchpadStyle.class);
        SkinSkinStyles.tDefault = skin_skin.get("default", Tree.TreeStyle.class);
        SkinSkinStyles.wDefault = skin_skin.get("default", Window.WindowStyle.class);
        sfx_ahh = assetManager.get("sfx/ahh.mp3");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_libgdx = assetManager.get("sfx/libgdx.mp3");
        sfx_pleaseDontKillMe = assetManager.get("sfx/please don't kill me.mp3");
        sfx_shot = assetManager.get("sfx/shot.mp3");
        sfx_swoosh = assetManager.get("sfx/swoosh.mp3");
        sfx_tv = assetManager.get("sfx/tv.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_menu = assetManager.get("bgm/menu.mp3");
    }

    public static class SpineLibgdx {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineRay3k {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SkinSkinStyles {
        public static Button.ButtonStyle bClose;

        public static Button.ButtonStyle bDefault;

        public static Button.ButtonStyle bPause;

        public static Button.ButtonStyle bToggle;

        public static Button.ButtonStyle bStop;

        public static Button.ButtonStyle bPlay;

        public static CheckBox.CheckBoxStyle cbDefault;

        public static ImageButton.ImageButtonStyle ibDefault;

        public static ImageTextButton.ImageTextButtonStyle itbDefault;

        public static ImageTextButton.ImageTextButtonStyle itbRadio;

        public static Label.LabelStyle lSmall;

        public static Label.LabelStyle lDefault;

        public static List.ListStyle lstSelectBox;

        public static ProgressBar.ProgressBarStyle pDefaultVertical;

        public static ProgressBar.ProgressBarStyle pDefaultHorizontal;

        public static ScrollPane.ScrollPaneStyle spSelectBox;

        public static ScrollPane.ScrollPaneStyle spDefault;

        public static SelectBox.SelectBoxStyle sbDefault;

        public static Slider.SliderStyle sScrubber;

        public static Slider.SliderStyle sDefaultVertical;

        public static Slider.SliderStyle sDefaultHorizontal;

        public static SplitPane.SplitPaneStyle spltDefaultVertical;

        public static SplitPane.SplitPaneStyle spltDefaultHorizontal;

        public static TextButton.TextButtonStyle tbToggle;

        public static TextButton.TextButtonStyle tbDefault;

        public static TextField.TextFieldStyle tfDefault;

        public static TextTooltip.TextTooltipStyle ttDefault;

        public static Touchpad.TouchpadStyle tsDefault;

        public static Tree.TreeStyle tDefault;

        public static Window.WindowStyle wDefault;
    }

    public static class Values {
        public static float jumpVelocity = 10.0f;

        public static String name = "Raeleus";

        public static boolean godMode = true;

        public static int id = 10;

        public static Range speedLimitRange = new Range(0.0f, 10.0f);

        public static float speedLimit = 5.0f;
    }

    public static class Range {
        public float min;

        public float max;

        Range(float min, float max) {
            this.min = min;
            this.max = max;
        }
    }
}
