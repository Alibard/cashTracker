package cashtracker.alibard.tm.com.utils.extention



import android.content.Context
import android.os.Parcelable
import android.support.annotation.IntDef
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewConfiguration
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.utils.BottomSheetBehaviorGoogleMapsLike
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.ref.WeakReference

/**
 * Behavior that imposes a maximum width on any ViewGroup.
 *
 * <p />Requires an attrs.xml of something like
 *
 * <pre>
 * &lt;declare-styleable name="MaxWidthBehavior_Params"&gt;
 *     &lt;attr name="behavior_maxWidth" format="dimension"/&gt;
 * &lt;/declare-styleable&gt;
 * </pre>
 */
class BottomSheetBehaviorGoogleMapsLike11<V : View> : CoordinatorLayout.Behavior<V> {


    @IntDef(STATE_EXPANDED.toLong(), STATE_COLLAPSED.toLong(), STATE_DRAGGING.toLong(), STATE_ANCHOR_POINT.toLong(), STATE_SETTLING.toLong(), STATE_HIDDEN.toLong())
    @Retention(RetentionPolicy.SOURCE)
    annotation class State

    /**
     * Callback for monitoring events about bottom sheets.
     */
    abstract class BottomSheetCallback {

        /**
         * Called when the bottom sheet changes its state.
         *
         * @param bottomSheet The bottom sheet view.
         * @param newState    The new state. This will be one of [.STATE_DRAGGING],
         * [.STATE_SETTLING], [.STATE_ANCHOR_POINT],
         * [.STATE_EXPANDED],
         * [.STATE_COLLAPSED], or [.STATE_HIDDEN].
         */
        abstract fun onStateChanged(bottomSheet: View, @State newState: Int)

        /**
         * Called when the bottom sheet is being dragged.
         *
         * @param bottomSheet The bottom sheet view.
         * @param slideOffset The new offset of this bottom sheet within its range, from 0 to 1
         * when it is moving upward, and from 0 to -1 when it moving downward.
         */
        abstract fun onSlide(bottomSheet: View, slideOffset: Float)
    }

    private var mTargetId: Int = 0
    private var DEFAULT_ANCHOR_POINT = 700

    private var mAnchorPoint: Int = 0
    private val mMinimumVelocity: Float

    /**
     * Конструктор для создания экземпляра FancyBehavior через разметку.
     *
     * @param context The {@link Context}.
     * @param attrs The {@link AttributeSet}.
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        var a = context.obtainStyledAttributes(attrs,
                android.support.design.R.styleable.BottomSheetBehavior_Layout)
        setPeekHeight(a.getDimensionPixelSize(
                android.support.design.R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, 0))
        setHideable(a.getBoolean(android.support.design.R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false))
        a.recycle()

        /**
         * Getting the anchorPoint...
         */
        mAnchorPoint = DEFAULT_ANCHOR_POINT
        a = context.obtainStyledAttributes(attrs, R.styleable.CustomBottomSheetBehavior)
        if (attrs != null)
            mAnchorPoint = a.getDimension(R.styleable.CustomBottomSheetBehavior_anchorPoint, 0f).toInt()
        a.recycle()

        val configuration = ViewConfiguration.get(context)
        mMinimumVelocity = configuration.scaledMinimumFlingVelocity.toFloat()
    }

    private fun setHideable(hideable: Boolean) {
        mHideable = hideable
    }

    private var mPeekHeight: Int = 0
    private var mParentHeight: Int = 0
    private fun setPeekHeight(peekHeight: Int) {
        mMaxWidth = Math.max(0, peekHeight)
        mMaxOffset = mParentHeight - peekHeight
    }


    private var mMaxWidth: Int = 0

    override fun onSaveInstanceState(parent: CoordinatorLayout?, child: V): Parcelable {
        return super.onSaveInstanceState(parent, child)
    }


    override fun onRestoreInstanceState(parent: CoordinatorLayout?, child: V, state: Parcelable?) {
        super.onRestoreInstanceState(parent, child, state)
    }


    /**
     * A utility function to get the [BottomSheetBehaviorGoogleMapsLike] associated with the `view`.
     *
     * @param view The [View] with [BottomSheetBehaviorGoogleMapsLike].
     * @param <V> Instance of behavior
     * @return The [BottomSheetBehaviorGoogleMapsLike] associated with the `view`.
    </V> */
    companion object {
        const val STATE_DRAGGING = 1

        /**
         * The bottom sheet is settling.
         */
        const val STATE_SETTLING = 2

        /**
         * The bottom sheet is expanded_half_way.
         */
        const val STATE_ANCHOR_POINT = 3

        /**
         * The bottom sheet is expanded.
         */
        const val STATE_EXPANDED = 4

        /**
         * The bottom sheet is collapsed.
         */
        const val STATE_COLLAPSED = 5

        /**
         * The bottom sheet is hidden.
         */
        const val STATE_HIDDEN = 6

        /** @hide
         */
        fun <V : View> from(view: V): BottomSheetBehaviorGoogleMapsLike<V> {
            val params = view.layoutParams as? CoordinatorLayout.LayoutParams
                    ?: throw IllegalArgumentException("The view is not a child of CoordinatorLayout")
            val behavior = params
                    .behavior as? BottomSheetBehaviorGoogleMapsLike<*>
                    ?: throw IllegalArgumentException(
                            "The view is not associated with BottomSheetBehaviorGoogleMapsLike")
            return behavior as BottomSheetBehaviorGoogleMapsLike<V>
        }
    }

    fun addBottomSheetCallback(bottomSheetCallback: BottomSheetCallback, function: () -> Unit) {

    }


    private var mMaxOffset: Int = 0
    private var mMinOffset: Int = 0
    private var mViewDragHelper: ViewDragHelper? = null
    override fun onLayoutChild(parent: CoordinatorLayout?, child: V, layoutDirection: Int): Boolean {
        if (mState != STATE_DRAGGING && mState != STATE_SETTLING) {
            if (ViewCompat.getFitsSystemWindows(parent) &&
                    !ViewCompat.getFitsSystemWindows(child)) {
                ViewCompat.setFitsSystemWindows(child, true);
            }
            parent?.onLayoutChild(child, layoutDirection);
        }
        mParentHeight = parent?.height ?: 0
        mMinOffset = Math.max(0, mParentHeight - child.getHeight())
        mMaxOffset = Math.max(mParentHeight - mPeekHeight, mMinOffset)

        if (mState == STATE_COLLAPSED) {
            ViewCompat.offsetTopAndBottom(child, mMaxOffset - mMaxWidth)
        }
        if (mViewDragHelper == null) {
            mViewDragHelper = ViewDragHelper.create(parent, object : ViewDragHelper.Callback() {
                override fun tryCaptureView(child: View?, pointerId: Int): Boolean {
                    return false
                }
            })
        }
        return true
    }

    @State
    private var mState = STATE_ANCHOR_POINT
    @State
    private var mLastStableState = STATE_ANCHOR_POINT

    private var mViewRef: WeakReference<V>? = null
    private var mHideable: Boolean = false
    fun setState(state: Int) {
        if (state == mState) {
            return
        }
        if (mViewRef == null) {
            // The view is not laid out yet; modify mState and let onLayoutChild handle it later
            /**
             * New behavior (added: state == STATE_ANCHOR_POINT ||)
             */
            if (state == STATE_COLLAPSED || state == STATE_EXPANDED || state == STATE_ANCHOR_POINT ||
                    mHideable && state == STATE_HIDDEN) {
                mState = state
                mLastStableState = state
            }
            return
        }
    }

    private var mNestedScrolled: Boolean = false
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        mNestedScrolled = false
        return axes != 0 && ViewCompat.SCROLL_AXIS_VERTICAL != 0

    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val currentTop = child.top
        val newTop = currentTop - dy
        if (dy > 0) {
            if (newTop < mMinOffset) {
                consumed[1] = currentTop - mMinOffset
                ViewCompat.offsetTopAndBottom(child, -consumed[1])
            } else {
                consumed[1] = dy
                ViewCompat.offsetTopAndBottom(child, -dy)
            }

        } else {
            if (!ViewCompat.canScrollVertically(target, -1)) {
                if (newTop <= mMaxOffset || mHideable) {
                    Log.d("scroll", "$newTop, $mMaxOffset, $mHideable")
                    if (newTop <= mMaxOffset - mMaxWidth) {
                        consumed[1] = dy
                        ViewCompat.offsetTopAndBottom(child, -dy)
                    }
                } else {
                    Log.d("scroll1", "$currentTop, $mMaxOffset")
                    consumed[1] = currentTop - mMaxOffset
                    ViewCompat.offsetTopAndBottom(child, -consumed[1])
                }

            }
            mNestedScrolled = true
        }
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, type: Int) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type)
        if (child.top == mMinOffset) {
            mLastStableState = STATE_EXPANDED
            return
        }
        var top: Int=0
        var targetState : Int = -1
        if (mLastStableState == STATE_COLLAPSED) {
            top = mAnchorPoint
            targetState = STATE_ANCHOR_POINT
        }
        mLastStableState = targetState
//        if(child.top>mAnchorPoint){
//            mViewDragHelper!!.smoothSlideViewTo(child, child.left, top)
//            ViewCompat.postOnAnimation(child, SettleRunnable(child, 1))
//        }
//        if (mViewDragHelper!!.smoothSlideViewTo(child, child.left, top)) {
////            setStateInternal(targetState)
//            ViewCompat.postOnAnimation(child, SettleRunnable(child, 1))
////            ViewCompat.postOnAnimation(child, SettleRunnable(child, targetState))
//        }else{
//
//        }

    }

    private fun setStateInternal(targetState: Int) {
        mLastStableState = targetState
    }

    private inner class SettleRunnable internal constructor(private val mView: View, @State private val mTargetState: Int) : Runnable {

        override fun run() {
            if (mViewDragHelper != null && mViewDragHelper!!.continueSettling(true)) {
                ViewCompat.postOnAnimation(mView, this)
            } else {

            }
        }
    }
}


