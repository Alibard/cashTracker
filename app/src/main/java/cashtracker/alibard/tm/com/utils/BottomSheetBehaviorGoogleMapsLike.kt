package cashtracker.alibard.tm.com.utils

import android.content.Context
import android.support.annotation.IntDef
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.NestedScrollingChild
import android.support.v4.view.ViewCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import cashtracker.alibard.tm.com.cashtracker.R
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.ref.WeakReference


class BottomSheetBehaviorGoogleMapsLike<V : View> : CoordinatorLayout.Behavior<V> {

    private val mMinimumVelocity: Float

    /**
     * Gets the height of the bottom sheet when it is collapsed.
     *
     * @return The height of the collapsed bottom sheet.
     * @attr ref android.support.design.R.styleable#BottomSheetBehavior_Params_behavior_peekHeight
     */
    /**
     * Sets the height of the bottom sheet when it is collapsed.
     *
     * @param peekHeight The height of the collapsed bottom sheet in pixels.
     * @attr ref android.support.design.R.styleable#BottomSheetBehavior_Params_behavior_peekHeight
     */
    var peekHeight: Int = 0
        set(peekHeight) {
            field = Math.max(0, peekHeight)
            mMaxOffset = mParentHeight - peekHeight
        }

    private var mMinOffset: Int = 0
    private var mMaxOffset: Int = 0
    var anchorPoint: Int = 0

    /**
     * Gets whether this bottom sheet can hide when it is swiped down.
     *
     * @return `true` if this bottom sheet can hide.
     * @attr ref android.support.design.R.styleable#BottomSheetBehavior_Params_behavior_hideable
     */
    /**
     * Sets whether this bottom sheet can hide when it is swiped down.
     *
     * @param hideable `true` to make this bottom sheet hideable.
     * @attr ref android.support.design.R.styleable#BottomSheetBehavior_Params_behavior_hideable
     */
    var isHideable: Boolean = false

    @State
    private var mState = STATE_ANCHOR_POINT
    @State
    private var mLastStableState = STATE_ANCHOR_POINT

    private var mViewDragHelper: ViewDragHelper? = null

    private var mNestedScrolled: Boolean = false

    private var mParentHeight: Int = 0

    private var mViewRef: WeakReference<V>? = null

    private var mNestedScrollingChildRef: WeakReference<View>? = null

    /**
     * Gets the current state of the bottom sheet.
     *
     * @return One of [.STATE_EXPANDED], [.STATE_ANCHOR_POINT], [.STATE_COLLAPSED],
     * [.STATE_DRAGGING], and [.STATE_SETTLING].
     */
    /**
     * Sets the state of the bottom sheet. The bottom sheet will transition to that state with
     * animation.
     *
     * @param state One of [.STATE_COLLAPSED], [.STATE_ANCHOR_POINT],
     * [.STATE_EXPANDED] or [.STATE_HIDDEN].
     */
    // The view is not laid out yet; modify mState and let onLayoutChild handle it later
    /**
     * New behavior (added: state == STATE_ANCHOR_POINT ||)
     */
    var state: Int
        @State
        get() = mState
        set(@State state) {
            if (state == mState) {
                return
            }
            if (mViewRef == null) {
                if (state == STATE_COLLAPSED || state == STATE_EXPANDED || state == STATE_ANCHOR_POINT ||
                        isHideable && state == STATE_HIDDEN) {
                    mState = state
                    mLastStableState = state
                }
                return
            }
            val child = mViewRef!!.get() ?: return
            val top: Int
            if (state == STATE_COLLAPSED) {
                top = mMaxOffset
            } else if (state == STATE_ANCHOR_POINT) {
                top = anchorPoint
            } else if (state == STATE_EXPANDED) {
                top = mMinOffset
            } else if (isHideable && state == STATE_HIDDEN) {
                top = mParentHeight
            } else {
                throw IllegalArgumentException("Illegal state argument: " + state)
            }
            if (mViewDragHelper!!.smoothSlideViewTo(child, child.left, top)) {
                ViewCompat.postOnAnimation(child, SettleRunnable(child, state))
            }
        }

    private val mDragCallback = object : ViewDragHelper.Callback() {

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return false
        }

    }


    /** @hide
     */
    @IntDef(STATE_EXPANDED.toLong(), STATE_COLLAPSED.toLong(), STATE_DRAGGING.toLong(), STATE_ANCHOR_POINT.toLong(), STATE_SETTLING.toLong(), STATE_HIDDEN.toLong())
    @Retention(RetentionPolicy.SOURCE)
    annotation class State

    /**
     * Default constructor for instantiating BottomSheetBehaviors.
     */
    constructor()

    /**
     * Default constructor for inflating BottomSheetBehaviors from layout.
     *
     * @param context The [Context].
     * @param attrs   The [AttributeSet].
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        var a = context.obtainStyledAttributes(attrs,
                android.support.design.R.styleable.BottomSheetBehavior_Layout)
        peekHeight = a.getDimensionPixelSize(
                android.support.design.R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, 0)
        isHideable = a.getBoolean(android.support.design.R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false)
        a.recycle()

        /**
         * Getting the anchorPoint...
         */
        anchorPoint = DEFAULT_ANCHOR_POINT
        a = context.obtainStyledAttributes(attrs, R.styleable.CustomBottomSheetBehavior)
        if (attrs != null)
            anchorPoint = a.getDimension(R.styleable.CustomBottomSheetBehavior_anchorPoint, 0f).toInt()
        a.recycle()

        val configuration = ViewConfiguration.get(context)
        mMinimumVelocity = configuration.scaledMinimumFlingVelocity.toFloat()
    }

    override fun onLayoutChild(parent: CoordinatorLayout?, child: V?, layoutDirection: Int): Boolean {
        // First let the parent lay it out
        if (mState != STATE_DRAGGING && mState != STATE_SETTLING) {
            if (ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
                ViewCompat.setFitsSystemWindows(child!!, true)
            }
            parent!!.onLayoutChild(child!!, layoutDirection)
        }
        // Offset the bottom sheet
        mParentHeight = parent!!.height
        mMinOffset = Math.max(0, mParentHeight - child!!.height)
        mMaxOffset = Math.max(mParentHeight - peekHeight, mMinOffset)

        /**
         * New behavior
         */
        if (mState == STATE_ANCHOR_POINT) {
            ViewCompat.offsetTopAndBottom(child, anchorPoint)
        } else if (mState == STATE_EXPANDED) {
            ViewCompat.offsetTopAndBottom(child, mMinOffset)
        } else if (isHideable && mState == STATE_HIDDEN) {
            ViewCompat.offsetTopAndBottom(child, mParentHeight)
        } else if (mState == STATE_COLLAPSED) {
            ViewCompat.offsetTopAndBottom(child, mMaxOffset)
        }
        if (mViewDragHelper == null) {
            mViewDragHelper = ViewDragHelper.create(parent, mDragCallback)
        }
        mViewRef = WeakReference(child)
        mNestedScrollingChildRef = WeakReference<View>(findScrollingChild(child))
        return true
    }
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        mNestedScrolled = false
        return nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, dx: Int, dy: Int, consumed: IntArray) {
        val scrollingChild = mNestedScrollingChildRef!!.get()
        if (target !== scrollingChild) {
            return
        }

        val currentTop = child.top
        val newTop = currentTop - dy
        // Force stop at the anchor - do not go from collapsed to expanded in one scroll
        if (mLastStableState == BottomSheetBehaviorGoogleMapsLike1.STATE_COLLAPSED && newTop < anchorPoint || mLastStableState == BottomSheetBehaviorGoogleMapsLike1.STATE_EXPANDED && newTop > anchorPoint) {
            consumed[1] = dy
            ViewCompat.offsetTopAndBottom(child, anchorPoint - currentTop)
            mNestedScrolled = true
            return
        }

        if (dy > 0) { // Upward
            if (newTop < mMinOffset) {
                consumed[1] = currentTop - mMinOffset
                ViewCompat.offsetTopAndBottom(child, -consumed[1])
            } else {
                consumed[1] = dy
                ViewCompat.offsetTopAndBottom(child, -dy)
            }
        }
        mNestedScrolled = true
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View) {
        if (child.top == mMinOffset) {
            mLastStableState = STATE_EXPANDED
            return
        }
        if (target !== mNestedScrollingChildRef!!.get() || !mNestedScrolled) {
            return
        }
        val top: Int
        val targetState: Int
        if (mLastStableState == STATE_COLLAPSED) {
            // Fling from collapsed to anchor
            top = anchorPoint
            targetState = STATE_ANCHOR_POINT
        }
        else if (mLastStableState == STATE_ANCHOR_POINT) {
            // Fling from anchor to expanded
            top = mMinOffset
            targetState = STATE_EXPANDED
            mState = STATE_EXPANDED
        } else {
            // We are already expanded
            top = mMinOffset
            targetState = STATE_EXPANDED
            mState = STATE_EXPANDED
        }

        mLastStableState = targetState
        if (mViewDragHelper!!.smoothSlideViewTo(child, child.left, top)) {

            ViewCompat.postOnAnimation(child, SettleRunnable(child, targetState))
        }
        mNestedScrolled = false
    }
//
//    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: V, target: View,
//                                  velocityX: Float, velocityY: Float): Boolean {
//        return target === mNestedScrollingChildRef!!.get() && mState != STATE_EXPANDED
//    }


    private fun findScrollingChild(view: View): View? {
        if (view is NestedScrollingChild) {
            return view
        }
        if (view is ViewGroup) {
            var i = 0
            val count = view.childCount
            while (i < count) {
                val scrollingChild = findScrollingChild(view.getChildAt(i))
                if (scrollingChild != null) {
                    return scrollingChild
                }
                i++
            }
        }
        return null
    }

    private inner class SettleRunnable internal constructor(private val mView: View, @param:State @field:State
    private val mTargetState: Int) : Runnable {

        override fun run() {
            if (mViewDragHelper != null && mViewDragHelper!!.continueSettling(true)) {
                ViewCompat.postOnAnimation(mView, this)
            }
        }
    }


    companion object {

        /**
         * The bottom sheet is dragging.
         */
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


        private val DEFAULT_ANCHOR_POINT = 700

        /**
         * A utility function to get the [BottomSheetBehaviorGoogleMapsLike] associated with the `view`.
         *
         * @param view The [View] with [BottomSheetBehaviorGoogleMapsLike].
         * @param <V> Instance of behavior
         * @return The [BottomSheetBehaviorGoogleMapsLike] associated with the `view`.
        </V> */
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

}
