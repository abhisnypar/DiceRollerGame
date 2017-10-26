package com.example.c15333.diceroller.Fragments;

import com.example.c15333.diceroller.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowWebView;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by c15333 on 2/15/17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class GameWikiPageFragmentTest {

    @Test
    public void testWebViewLinkOnStart() {
        GameWikiPageFragment gameWikiPageFragment = GameWikiPageFragment.newInstance(RuntimeEnvironment.application);
        SupportFragmentTestUtil.startFragment(gameWikiPageFragment);
        String webUrl = "https://en.wikipedia.org/wiki/List_of_dice_games";
        ShadowWebView shadowWebView = Shadows.shadowOf(gameWikiPageFragment.wikiLinkWebView);
        assertThat(shadowWebView.getLastLoadedUrl()).isEqualTo(webUrl);
    }

}