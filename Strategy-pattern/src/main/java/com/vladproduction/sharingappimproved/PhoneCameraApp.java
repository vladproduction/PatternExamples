package com.vladproduction.sharingappimproved;

import com.vladproduction.sharingappimproved.exceptions.CameraException;
import com.vladproduction.sharingappimproved.exceptions.ShareException;

public abstract class PhoneCameraApp {

    private ShareStrategy shareStrategy;
    private EditStrategy editStrategy;
    protected Photo currentPhoto;

    //template method
    public final void processPhoto() throws CameraException {

        try{
            takePhoto();
            if(editStrategy != null){
                currentPhoto = editStrategy.edit(currentPhoto);
            }
            savePhoto();
            if(shareStrategy != null){
                sharePhoto();
            }
        }catch (Exception e){
            throw new CameraException("Failed to process photo: " + e.getMessage());
        }
    }

    // Strategy setters
    public void setShareStrategy(ShareStrategy shareStrategy) {
        this.shareStrategy = shareStrategy;
    }

    public void setEditStrategy(EditStrategy editStrategy) {
        this.editStrategy = editStrategy;
    }

    // Protected methods for subclasses
    protected abstract void takePhoto() throws CameraException;
    protected abstract void savePhoto() throws CameraException;

    protected void sharePhoto() throws ShareException {
        if(shareStrategy != null && currentPhoto != null){
            shareStrategy.share(String.format("Photo%s", currentPhoto.getFormat()), currentPhoto.getMetadata());
        }
    }

}