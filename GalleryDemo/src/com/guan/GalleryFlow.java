package com.guan;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryFlow extends Gallery {

	private Camera mCamera = new Camera();
	private int mMaxRotationAngle = 60;
	private int mMaxZoom = -280;
	private int mCoveflowCenter;
	
	public GalleryFlow(Context context) {
		super(context);
		setStaticTransformationsEnabled(true);
	}

	public GalleryFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        setStaticTransformationsEnabled(true);
    }

    public GalleryFlow(Context context, AttributeSet attrs, int defStyle) {
    	super(context, attrs, defStyle);
    	setStaticTransformationsEnabled(true);
    }

	public int getmMaxRotationAngle() {
		return mMaxRotationAngle;
	}

	public void setmMaxRotationAngle(int mMaxRotationAngle) {
		this.mMaxRotationAngle = mMaxRotationAngle;
	}

	public int getmMaxZoom() {
		return mMaxZoom;
	}

	public void setmMaxZoom(int mMaxZoom) {
		this.mMaxZoom = mMaxZoom;
	}

	private int getCenterOfCoverflow() {
		return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
	}
	
	private static int getCoveflowOfChild(View v){
		return (v.getLeft() + v.getWidth()) / 2;
	}
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCoveflowCenter = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }
	
	//控制gallery中每个图片的旋转(重写的gallery中方法)
    protected boolean getChildStaticTransformation(View child, Transformation t) {
    	final int childCenter = getCoveflowOfChild(child);
    	final int childWidth = child.getWidth();
    	int rotationAngle = 0;
    	t.clear();
    	t.setTransformationType(Transformation.TYPE_MATRIX);
    	if(childCenter == mCoveflowCenter){
    		transformImageBitmap((ImageView) child, t, 0);
    	} else {
    		rotationAngle = (int)((float)(mCoveflowCenter - childCenter) / childWidth * mMaxRotationAngle);
    		if(Math.abs(rotationAngle) > mMaxRotationAngle){
    			rotationAngle = rotationAngle < 0 ? -mMaxRotationAngle : mMaxRotationAngle;
    		}
    		transformImageBitmap((ImageView) child, t, rotationAngle);
    	}
    	return true;
    }
    
    private void transformImageBitmap(ImageView child, Transformation t, int rotationAngle) {
    	mCamera.save();
    	final Matrix imageMatrix = t.getMatrix();
    	final int imgWidth = child.getLayoutParams().width;
    	final int imgHeight = child.getLayoutParams().height;
    	final int rotation = Math.abs(rotationAngle);

    	mCamera.translate(0.0f, 0.0f, 100.0f);
    	if(rotation < mMaxRotationAngle){
    		float zoomFloat = (float)(mMaxZoom + (rotation * 1.5));
    		mCamera.translate(0.0f, 0.0f, zoomFloat);
    	}
    	mCamera.rotateY(rotationAngle);
    	mCamera.getMatrix(imageMatrix);
    	imageMatrix.preTranslate(-(imgWidth / 2), -(imgHeight / 2));
    	imageMatrix.postTranslate(imgWidth / 2, imgHeight / 2);
    	mCamera.restore();
    }
    
}
