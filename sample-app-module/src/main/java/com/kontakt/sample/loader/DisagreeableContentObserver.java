package com.kontakt.sample.loader;

import android.database.ContentObserver;
import android.os.Handler;

final class DisagreeableContentObserver extends ContentObserver {

    private final ContentObserver contentObserver;

    private boolean isEnabled = true;

    DisagreeableContentObserver(final ContentObserver contentObserver) {
        super(new Handler());
        this.contentObserver = contentObserver;
    }

    @Override
    public void onChange(boolean selfChange) {
        if(isEnabled) {
            contentObserver.onChange(selfChange);
        }
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}