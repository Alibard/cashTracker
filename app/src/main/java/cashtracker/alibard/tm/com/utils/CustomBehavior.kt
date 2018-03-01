package cashtracker.alibard.tm.com.utils

import android.content.Context
import android.support.annotation.IntDef
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewConfiguration
import cashtracker.alibard.tm.com.cashtracker.R
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.ref.WeakReference


class CustomBehavior<V : View> : CoordinatorLayout.Behavior<V> {

    constructor()

    private var peekHeight: Int = 0
    private var anchorPoint: Int = 700
    private var lastState = -1
    @StateBehavior
    private var mState = COLLAPSED
    private var mViewDragHelper: ViewDragHelper? = null
    @StateBehavior
    private var mLastStableState = ANCHOR

    private var mViewRef: WeakReference<V>? = null

    private var mMinimumVelocity: Float = 0f

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        var typeArray = context.obtainStyledAttributes(attrs, android.support.design.R.styleable.BottomSheetBehavior_Layout)
        peekHeight = typeArray.getDimensionPixelOffset(android.support.design.R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, 0)
        typeArray.recycle()
        typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomBottomSheetBehavior)
        if (attrs != null) {
            anchorPoint = typeArray.getDimension(R.styleable.CustomBottomSheetBehavior_anchorPoint, 0f).toInt()
        }
        typeArray.recycle()
        val configuration = ViewConfiguration.get(context)
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity().toFloat()
        Log.d(TAG,"mMinimumVelocity - $mMinimumVelocity")
    }


    private var mMinOffset: Int = 0
    private var mMaxOffset: Int = 0
    private var mParentHeight: Int = 0

    override fun onLayoutChild(parent: CoordinatorLayout?, child: V, layoutDirection: Int): Boolean {
        if (mState != DRAGGING && mState != SETTLING) {
            if (ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
                ViewCompat.setFitsSystemWindows(child!!, true)
            }
            parent!!.onLayoutChild(child!!, layoutDirection)
        }
        mParentHeight = parent!!.height
        mMinOffset = Math.max(0, mParentHeight - child!!.height)
        mMaxOffset = Math.max(mParentHeight - peekHeight, mMinOffset)
        if (mState == COLLAPSED) {
            ViewCompat.offsetTopAndBottom(child, mMaxOffset)
        }
        if (mViewDragHelper == null) {
            mViewDragHelper = ViewDragHelper.create(parent, mDragCallback)
        }
        mViewRef = WeakReference(child)
        return true

    }

    var nestedScroll = false


    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        Log.d(TAG, "onStartNestedScroll")
        nestedScroll = false
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, type: Int) {
        if (child.top == mMinOffset) {
            mLastStableState = EXPANDED
            return
        }
        if (!nestedScroll) {
            return
        }
        val top: Int
        val targetState: Int
        if(myVelocityTracker.mScrollVelocity){
            if (mState == COLLAPSED) {
                top = anchorPoint
                targetState = ANCHOR
            } else {
                top = mMinOffset
                targetState = EXPANDED
            }
        }else{
            if(child.top>anchorPoint*1.25){
                top = mMaxOffset
                targetState = COLLAPSED
            }else if(child.top< anchorPoint*0.5){
                top = mMinOffset
                targetState = EXPANDED
            } else{
                top = anchorPoint
                targetState = ANCHOR
            }
//            if(mState== EXPANDED){
//                top=anchorPoint
//                targetState = ANCHOR
//            }else{
//                top = mMaxOffset
//                targetState = COLLAPSED
//            }
        }

        mState = targetState
        Log.d(TAG, "onStopNestedScroll : $mState, $top $nestedScroll, ${myVelocityTracker.mScrollVelocity1}")
        if (mViewDragHelper!!.smoothSlideViewTo(child, child.left, top)) {
            Log.d(TAG, "onStopNestedScroll : smoothSlideViewTo")
            ViewCompat.postOnAnimation(child, SettleRunnable(child))
        }
        nestedScroll = false

    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val currentTop = child.top
        val newTop = currentTop - dy
        myVelocityTracker.recordScroll(dy)
        Log.d(TAG, "onNestedPreScroll : $mState, $nestedScroll")
        if (dy > 0) { // Upward
            if (newTop < mMinOffset) {
                consumed[1] = currentTop - mMinOffset
                ViewCompat.offsetTopAndBottom(child, -consumed[1])
            } else {
                consumed[1] = dy
                ViewCompat.offsetTopAndBottom(child, -dy)
            }
        }else{
            if(!ViewCompat.canScrollVertically(target, -1)){
                if(newTop<=mMaxOffset){
                    consumed[1] = dy
                    ViewCompat.offsetTopAndBottom(child, -dy)
//                    setStateInternal(STATE_DRAGGING)
                }
            }else{
                consumed[1] = currentTop - mMaxOffset
                ViewCompat.offsetTopAndBottom(child, -consumed[1])
            }
        }
        nestedScroll = true
    }


    private inner class SettleRunnable internal constructor(private val mView: View) : Runnable {

        override fun run() {
            if (mViewDragHelper != null && mViewDragHelper!!.continueSettling(true)) {
                ViewCompat.postOnAnimation(mView, this)
            }
        }
    }
    private val myVelocityTracker by  lazy {
        ScrollVelocityTracker()
    }
    private inner class ScrollVelocityTracker {
        private var mPreviousScrollTime: Long = 0
        var mScrollVelocity: Boolean = false
        var mScrollVelocity1: Float = 0f
        fun recordScroll(dy: Int) {
            val now = System.currentTimeMillis()

            if (mPreviousScrollTime != 0L) {
                val elapsed = now - mPreviousScrollTime
                mScrollVelocity1= dy.toFloat() / elapsed * 1000 // pixels per sec
            }

            mPreviousScrollTime = now
            mScrollVelocity = dy > 0
        }


    }

    private fun setCurrentState(@StateBehavior state: Int) {
        mState = state
    }

    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: V, target: View, velocityX: Float, velocityY: Float): Boolean {
        return mState != EXPANDED || super.onNestedPreFling(coordinatorLayout, child, target,
                velocityX, velocityY)
    }

    companion object {
        val TAG = "CustomBehavior"
        const val COLLAPSED = 1
        const val DRAGGING = 2
        const val SETTLING = 3
        const val EXPANDED = 4
        const val ANCHOR = 5

        fun <V : View> from(view: V): CustomBehavior<V> {
            val params = view.layoutParams as? CoordinatorLayout.LayoutParams
                    ?: throw ClassCastException("Ololo?")
            val behavior = params
                    .behavior as? CustomBehavior<*>
                    ?: throw IllegalArgumentException(
                            "The view is not associated with BottomSheetBehaviorGoogleMapsLike")
            return behavior as CustomBehavior<V>
        }
    }

    var state: Int
        @BottomSheetBehaviorGoogleMapsLike.State
        get() = mState
        set(@BottomSheetBehaviorGoogleMapsLike.State state) {
            if (state == mState) {
                return
            }
            if (mViewRef == null) {
                if (state == COLLAPSED || state == EXPANDED || state == ANCHOR) {
                    mState = state
                    mLastStableState = state
                }
                return
            }
            val child = mViewRef!!.get() ?: return
            val top: Int
            if (state == BottomSheetBehaviorGoogleMapsLike.STATE_COLLAPSED) {
                top = mMaxOffset
            } else if (state == BottomSheetBehaviorGoogleMapsLike.STATE_ANCHOR_POINT) {
                top = anchorPoint
            } else if (state == BottomSheetBehaviorGoogleMapsLike.STATE_EXPANDED) {
                top = mMinOffset
            } else {
                throw IllegalArgumentException("Illegal state argument: " + state)
            }
            if (mViewDragHelper!!.smoothSlideViewTo(child, child.left, top)) {
                ViewCompat.postOnAnimation(child, SettleRunnable(child))
            }
        }

    /** @hide
     */

    @IntDef(COLLAPSED.toLong(), DRAGGING.toLong(), SETTLING.toLong(), EXPANDED.toLong(), ANCHOR.toLong())
    @Retention(RetentionPolicy.SOURCE)
    annotation class StateBehavior

    private val mDragCallback = object : ViewDragHelper.Callback() {

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return false
        }

    }
}