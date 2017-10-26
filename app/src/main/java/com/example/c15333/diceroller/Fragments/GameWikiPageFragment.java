package com.example.c15333.diceroller.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.c15333.diceroller.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Use the {@link GameWikiPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameWikiPageFragment extends Fragment {
    private static final String web_link_url = "https://en.wikipedia.org/wiki/List_of_dice_games";

    @Bind(R.id.web_view)
    WebView wikiLinkWebView;


    public GameWikiPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GameWikiPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameWikiPageFragment newInstance(Context context) {
        GameWikiPageFragment fragment = new GameWikiPageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_wiki_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        setUpWebView();
    }

    private void setUpWebView() {
        WebSettings webSettings = wikiLinkWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wikiLinkWebView.setWebViewClient(new GameWikiLinkClient());
        wikiLinkWebView.loadUrl(web_link_url);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public class GameWikiLinkClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
        }
    }
}
