Circle View

This is a custom circular view and can be used like the below :-
If your radius is more than the container bounds , it automaticaly adjusts itself
If you want to change this behavior please override the onMeasure method of the class

define it in your xml as:-
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res/com.itamecodes.customviews">

    <com.itamecodes.customviews.NewCircleView
        	android:layout_below="@id/thetext"
            android:layout_width="match_parent"
            android:layout_height="fill_parent" 
            app:radius="30"
            app:circletext="wowww"
            app:circlecolor="@android:color/black"
            app:circletextpadding="3"
          />

    </RelativeLayout>