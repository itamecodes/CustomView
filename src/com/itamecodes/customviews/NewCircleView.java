package com.itamecodes.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 
 * This is the custom view which is a circle. Basically it can be defined as a custom circle text view
 * We could have extended this directly from the TextView rather than View class.
 * The customization is limited  now
 * 
 * @author vivek
 *
 */
public class NewCircleView extends View {
	//the text to be displayed in the circle
	private String circleText;
	//the radius of the circle and the padding for the text 
	private int circleradius,circletextpadding;
	//the height and width of our custom view
	private int viewHeightWidth;
	//the color of the circle
	private int circlecolor;
	//thepaint object used to draw the view
	private Paint myPaint;
	
	public NewCircleView(Context context) {
		super(context);
	}

	public NewCircleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	/**
	 * This is the constructor where we will do our initialization. Some ppl tend to do the initialization in the 
	 * onDraw/onMeasure but that would be a little heavy on the memory
	 * 
	 * @param context
	 * @param attrs
	 * 
	 */
	public NewCircleView(Context context, AttributeSet attrs) {
		//call the superclass
		super(context, attrs);
		//call the init method which will do the initialization of the paint obj
		init();
		//get the attributes declared in values/attrs.xml and put them in a typed array
		TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.NewCircleView) ;
		//obtain each of the value of the attributes from our xml which will be using the custom view
		circleText=a.getString(R.styleable.NewCircleView_circletext);
		circleradius=a.getInt(R.styleable.NewCircleView_radius, 0);
		circletextpadding=a.getInt(R.styleable.NewCircleView_circletextpadding, 0);
		circlecolor=a.getColor(R.styleable.NewCircleView_circlecolor, 0);
		//do the invalidate and requestlayout .although it will work without it
		invalidate();
		requestLayout();
		//recycle the typed array
		a.recycle();
	}
	
	
	/**
	 * Sets the paint object and set the style of the paint obj
	 */
	public void init(){
		myPaint=new Paint();
		myPaint.setAntiAlias(true);
		myPaint.setColor(Color.BLUE);
		myPaint.setStyle(Paint.Style.STROKE);
	}

	/**
	 * the onDraw method which draws the view on the canvas
	 * 
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		//call the super
		super.onDraw(canvas);
		//if the view's height and width is less than the circle's diamter defined in the xml then
		//use the view's height and width as the circle's radius
		if(viewHeightWidth<circleradius*2){
			circleradius=viewHeightWidth/2;
		}
		//draw the circle
		canvas.drawCircle(circleradius,circleradius,circleradius,myPaint);
		//draw the text on the canvas
		canvas.drawText(circleText,0,circleradius,myPaint);
	}
	
	/**
	 * Calculate the bounds of the view .As we are drawing a circle we will keep the height and width of the view equal
	 * basically our views bounds will be a square
	 * 
	 * @param widthmeasurespec:-the restriction on width defined by the container of the view
	 * @param heightmeasurespec:-the restriction on height defined by the container of the view
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//measure the width of the view
		int widthView=measureWidthHeightOfView(widthMeasureSpec);
		//measure the height of the view
		int heightView=measureWidthHeightOfView(heightMeasureSpec);
		//keep the minimum of width and height as the height,width of the view
		viewHeightWidth=Math.min(widthView, heightView);
		//set the above for the view
		setMeasuredDimension(viewHeightWidth, viewHeightWidth);
	}
	
	/**
	 * measures the dimensions for the view.
	 * @param widthHeightMeasureSpec
	 * @return
	 */
	private int measureWidthHeightOfView(int widthHeightMeasureSpec){
		int result=0;
		//find the mode, which can be EXACTLY,UNSPECIFIED or AT_MOST
		int specMode=MeasureSpec.getMode(widthHeightMeasureSpec);
		//find the size restriction given by the container
		int specSize=MeasureSpec.getSize(widthHeightMeasureSpec);
		
		if(specMode==MeasureSpec.EXACTLY){
			//if exactly we should be using the exact value , but here i have used a tolerance of 3
			result=specSize-3;
		}else {
			//else give a tolerance of 4. This can be changed as required
			result=specSize-4;
			
		}
		return result;
	}
	

	

}
