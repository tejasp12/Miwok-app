package com.example.android.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceID=NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mAudioResourceID;
    public Word(String defaultTranslation,String miwokTranslation,int audioResourceID){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceID = audioResourceID;
    }
    public Word(String defaultTranslation,String miwokTranslation,int imageResourceID,
                int audioResourceID){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = imageResourceID;
        mAudioResourceID = audioResourceID;
    }
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }
    public int getImageResourceID(){
        return mImageResourceID;
    }
    public boolean hasImage(){
        if(mImageResourceID!=NO_IMAGE_PROVIDED){
            return true;
        }else {
            return false;
        }
    }
    public int getAudioResourceID(){
        return mAudioResourceID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation= "+mDefaultTranslation + '\n'+
                "mMiwokTranslation= "+mMiwokTranslation + '\n'+
                "mAudioResource ID= "+mAudioResourceID + '\n'+
                "mImageResourceID= "+mImageResourceID + '\n'+
        "}";
    }
}
