package com.android.noisefield.test;

import android.content.ComponentName;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

public class NoiseFieldFpsTest extends ActivityInstrumentationTestCase2<PreviewStubActivity> {

    private static final String TARGET_PACKAGE = "com.android.noisefield";
    private static final String TARGET_PREVIEW_CLASS = ".PreviewStubActivity";
    private static final String TARGET_WALLPAPER_CLASS = "com.android.noisefield.NoiseFieldWallpaper";
    private static final int FPS_TEST_PERIOD = 20000;

    private Intent mWallpaperIntent;
    private PreviewStubActivity mStubActivity;

    public NoiseFieldFpsTest() {
        super(PreviewStubActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        mWallpaperIntent = new Intent();
        mWallpaperIntent.setAction(Intent.ACTION_MAIN);
        mWallpaperIntent.setComponent(new ComponentName(TARGET_PACKAGE, TARGET_PREVIEW_CLASS));
        mWallpaperIntent.putExtra(PreviewStubActivity.PACKAGE_NAME, TARGET_PACKAGE);
        mWallpaperIntent.putExtra(PreviewStubActivity.CLASS_NAME, TARGET_WALLPAPER_CLASS);
        setActivityIntent(mWallpaperIntent);

        mStubActivity = getActivity();
    }

    protected void tearDown() throws Exception {
        if (mStubActivity != null) {
            mStubActivity.finish();
            mStubActivity = null;
        }
        super.tearDown();
    }

    @MediumTest
    public void testNoiseFieldWallpaperFps() {
        assertNotNull(mStubActivity);
        assertTrue(mStubActivity.isResumed());
        try {
            Thread.sleep(FPS_TEST_PERIOD);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotNull(mStubActivity);
        assertTrue(mStubActivity.isResumed());
    }

}
