package com.example.shailee.camo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class AboutPage extends Fragment {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private  View mView;
    private CharSequence mDescription;
    private int mImage = 0;
    private boolean mIsRTL = false;
    private Typeface mCustomFont;

    public AboutPage(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mView =  mInflater.inflate(R.layout.fragment_about, null);
        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");
        isRTL(false)
                .setImage(R.drawable.dummy_image)
                .addItem(new Element().setTitle("Version 3.0"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("shreejashrestha15@gmail.com")
                .addWebsite("http://medyo.github.io/")
                .addFacebook("shreezastha")
                .addTwitter(" ")
                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addInstagram("shreeja.stha")
                .addGitHub("shreeza")
                .addItem(getCopyRightsElement());
        return create();
    }

    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
       // copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
        copyRightsElement.setIconTint(R.color.about_item_text_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }

    public AboutPage setCustomFont(String path) {
        mCustomFont = Typeface.createFromAsset(mContext.getAssets(), path);
        return this;
    }


    public AboutPage addEmail(String email) {
        return addEmail(email, mContext.getString(R.string.about_contact_us));
    }


    public AboutPage addEmail(String email, String title) {
        Element emailElement = new Element();
        emailElement.setTitle(title);
        //emailElement.setIconDrawable(R.drawable.about_icon_email);
        emailElement.setIconTint(R.color.about_item_text_color);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailElement.setIntent(intent);


        addItem(emailElement);
        return this;
    }


    public AboutPage addFacebook(String id) {
        return addFacebook(id, mContext.getString(R.string.about_facebook));
    }


    public AboutPage addFacebook(String id, String title) {
        Element facebookElement = new Element();
        facebookElement.setTitle(title);
       // facebookElement.setIconDrawable(R.drawable.about_icon_facebook);
        facebookElement.setIconTint(R.color.about_facebook_color);
        facebookElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (AboutPageUtils.isAppInstalled(mContext, "com.facebook.katana")) {
            intent.setPackage("com.facebook.katana");
            int versionCode = 0;
            try {
                versionCode = mContext.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            if (versionCode >= 3002850) {
                Uri uri = Uri.parse("fb://facewebmodal/f?href=" + "http://m.facebook.com/" + id);
                intent.setData(uri);
            } else {
                Uri uri = Uri.parse("fb://page/" + id);
                intent.setData(uri);
            }
        } else {
            intent.setData(Uri.parse("http://m.facebook.com/" + id));
        }

        facebookElement.setIntent(intent);

        addItem(facebookElement);
        return this;
    }


    public AboutPage addTwitter(String id) {
        return addTwitter(id, mContext.getString(R.string.about_twitter));
    }


    public AboutPage addTwitter(String id, String title) {
        Element twitterElement = new Element();
        twitterElement.setTitle(title);
      //  twitterElement.setIconDrawable(R.drawable.about_icon_twitter);
        twitterElement.setIconTint(R.color.about_twitter_color);
        twitterElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (AboutPageUtils.isAppInstalled(mContext, "com.twitter.android")) {
            intent.setPackage("com.twitter.android");
            intent.setData(Uri.parse(String.format("twitter://user?screen_name=%s", id)));
        } else {
            intent.setData(Uri.parse(String.format("http://twitter.com/intent/user?screen_name=%s", id)));
        }

        twitterElement.setIntent(intent);
        addItem(twitterElement);
        return this;
    }


    public AboutPage addPlayStore(String id) {
        return addPlayStore(id, mContext.getString(R.string.about_play_store));
    }


    public AboutPage addPlayStore(String id, String title) {
        Element playStoreElement = new Element();
        playStoreElement.setTitle(title);
       // playStoreElement.setIconDrawable(R.drawable.about_icon_google_play);
        playStoreElement.setIconTint(R.color.about_play_store_color);
        playStoreElement.setValue(id);

        Uri uri = Uri.parse("market://details?id=" + id);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        playStoreElement.setIntent(goToMarket);

        addItem(playStoreElement);
        return this;
    }

    /**
     * Convenience method for {@link AboutPage#addYoutube(String, String)} but with
     * a predefined title string
     *
     * @param id the id of the channel to deep link to
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage addYoutube(String id) {
        return addYoutube(id, mContext.getString(R.string.about_youtube));
    }


    public AboutPage addYoutube(String id, String title) {
        Element youtubeElement = new Element();
        youtubeElement.setTitle(title);
       // youtubeElement.setIconDrawable(R.drawable.about_icon_youtube);
        youtubeElement.setIconTint(R.color.about_youtube_color);
        youtubeElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(String.format("http://youtube.com/channel/%s", id)));

        if (AboutPageUtils.isAppInstalled(mContext, "com.google.android.youtube")) {
            intent.setPackage("com.google.android.youtube");
        }

        youtubeElement.setIntent(intent);
        addItem(youtubeElement);

        return this;
    }


    public AboutPage addInstagram(String id) {
        return addInstagram(id, mContext.getString(R.string.about_instagram));
    }


    public AboutPage addInstagram(String id, String title) {
        Element instagramElement = new Element();
        instagramElement.setTitle(title);
       // instagramElement.setIconDrawable(R.drawable.about_icon_instagram);
        instagramElement.setIconTint(R.color.about_instagram_color);
        instagramElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://instagram.com/_u/" + id));

        if (AboutPageUtils.isAppInstalled(mContext, "com.instagram.android")) {
            intent.setPackage("com.instagram.android");
        }

        instagramElement.setIntent(intent);
        addItem(instagramElement);

        return this;
    }


    public AboutPage addGitHub(String id) {
        return addGitHub(id, mContext.getString(R.string.about_github));
    }


    public AboutPage addGitHub(String id, String title) {
        Element gitHubElement = new Element();
        gitHubElement.setTitle(title);
       // gitHubElement.setIconDrawable(R.drawable.about_icon_github);
        gitHubElement.setIconTint(R.color.about_github_color);
        gitHubElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(String.format("https://github.com/%s", id)));

        gitHubElement.setIntent(intent);
        addItem(gitHubElement);

        return this;
    }


    public AboutPage addWebsite(String url) {
        return addWebsite(url, mContext.getString(R.string.about_website));
    }


    public AboutPage addWebsite(String url, String title) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Element websiteElement = new Element();
        websiteElement.setTitle(title);
      //  websiteElement.setIconDrawable(R.drawable.about_icon_link);
        websiteElement.setIconTint(R.color.about_item_text_color);
        websiteElement.setValue(url);

        Uri uri = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);

        websiteElement.setIntent(browserIntent);
        addItem(websiteElement);

        return this;
    }


    public AboutPage addItem(Element element) {
        LinearLayout wrapper = (LinearLayout) mView.findViewById(R.id.about_providers);
        wrapper.addView(createItem(element));
        wrapper.addView(getSeparator(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelSize(R.dimen.about_separator_height)));
        return this;
    }


    public AboutPage setImage(@DrawableRes int resource) {
        this.mImage = resource;
        return this;
    }


    public AboutPage addGroup(String name) {

        TextView textView = new TextView(mContext);
        textView.setText(name);
        TextViewCompat.setTextAppearance(textView, R.style.about_groupTextAppearance);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (mCustomFont != null) {
            textView.setTypeface(mCustomFont);
        }

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_group_text_padding);
        textView.setPadding(padding, padding, padding, padding);


        if (mIsRTL) {
            textView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        } else {
            textView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        }
        textView.setLayoutParams(textParams);

        ((LinearLayout) mView.findViewById(R.id.about_providers)).addView(textView);
        return this;
    }

    /**
     * Turn on the RTL mode.
     *
     * @param value
     * @return this AboutPage instance for builder pattern support
     */
    public AboutPage isRTL(boolean value) {
        this.mIsRTL = value;
        return this;
    }

    public AboutPage setDescription(CharSequence description) {
        this.mDescription = description;
        return this;
    }

    /**
     * Create and inflate this AboutPage. After this method is called the AboutPage
     * cannot be customized any more.
     *
     * @return the inflated {@link View} of this AboutPage
     */
    public View create() {
        TextView description = (TextView) mView.findViewById(R.id.description);
        ImageView image = (ImageView) mView.findViewById(R.id.image);
        if (mImage > 0) {
            image.setImageResource(mImage);
        }

        if (!TextUtils.isEmpty(mDescription)) {
            description.setText(mDescription);
        }

        description.setGravity(Gravity.CENTER);

        if (mCustomFont != null) {
            description.setTypeface(mCustomFont);
        }

        return mView;
    }

    private View createItem(final Element element) {
        LinearLayout wrapper = new LinearLayout(mContext);
        wrapper.setOrientation(LinearLayout.HORIZONTAL);
        wrapper.setClickable(true);

        if (element.getOnClickListener() != null) {
            wrapper.setOnClickListener(element.getOnClickListener());
        } else if (element.getIntent() != null) {
            wrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        mContext.startActivity(element.getIntent());
                    } catch (Exception e) {
                    }
                }
            });

        }

        TypedValue outValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
        wrapper.setBackgroundResource(outValue.resourceId);

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_text_padding);
        wrapper.setPadding(padding, padding, padding, padding);
        LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wrapper.setLayoutParams(wrapperParams);


        TextView textView = new TextView(mContext);
        TextViewCompat.setTextAppearance(textView, R.style.about_elementTextAppearance);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textParams);
        if (mCustomFont != null) {
            textView.setTypeface(mCustomFont);
        }

        ImageView iconView = null;

        if (element.getIconDrawable() != null) {
            iconView = new ImageView(mContext);
            int size = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_size);
            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(size, size);
            iconView.setLayoutParams(iconParams);
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            iconView.setPadding(iconPadding, 0, iconPadding, 0);

            if (Build.VERSION.SDK_INT < 21) {
                Drawable drawable = VectorDrawableCompat.create(iconView.getResources(), element.getIconDrawable(), iconView.getContext().getTheme());
                iconView.setImageDrawable(drawable);
            } else {
                iconView.setImageResource(element.getIconDrawable());
            }

            Drawable wrappedDrawable = DrawableCompat.wrap(iconView.getDrawable());
            wrappedDrawable = wrappedDrawable.mutate();
            if (element.getAutoApplyIconTint()) {
                int currentNightMode = mContext.getResources().getConfiguration().uiMode
                        & Configuration.UI_MODE_NIGHT_MASK;
                if (currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
                    if (element.getIconTint() != null) {
                        DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconTint()));
                    } else {
                        DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, R.color.about_item_text_color));
                    }
                } else if (element.getIconNightTint() != null) {
                    DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, element.getIconNightTint()));
                } else {
                    DrawableCompat.setTint(wrappedDrawable, AboutPageUtils.getThemeAccentColor(mContext));
                }
            }

        } else {
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            textView.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);
        }


        textView.setText(element.getTitle());


        if (mIsRTL) {

            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.END;

            wrapper.setGravity(gravity | Gravity.CENTER_VERTICAL);
            //noinspection ResourceType
            textParams.gravity = gravity | Gravity.CENTER_VERTICAL;
            wrapper.addView(textView);
            if (element.getIconDrawable() != null) {
                wrapper.addView(iconView);
            }

        } else {
            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.START;
            wrapper.setGravity(gravity | Gravity.CENTER_VERTICAL);
            //noinspection ResourceType
            textParams.gravity = gravity | Gravity.CENTER_VERTICAL;
            if (element.getIconDrawable() != null) {
                wrapper.addView(iconView);
            }
            wrapper.addView(textView);
        }

        return wrapper;
    }

    private View getSeparator() {
        return mInflater.inflate(R.layout.seperator, null);
    }
}
