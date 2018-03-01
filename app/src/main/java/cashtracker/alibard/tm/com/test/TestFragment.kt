package cashtracker.alibard.tm.com.test

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cashtracker.alibard.tm.com.cashtracker.R
import cashtracker.alibard.tm.com.utils.BottomSheetBehaviorGoogleMapsLike
import cashtracker.alibard.tm.com.utils.CustomBehavior
import kotlinx.android.synthetic.main.test_fragment.*


class TestFragment : Fragment() {

    companion object {
        fun newInstance(): TestFragment = TestFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.test_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val behavior = CustomBehavior.from(bottom_sheet)
        behavior.state = CustomBehavior.COLLAPSED
//        val behavior = BottomSheetBehaviorGoogleMapsLike.from(bottom_sheet)
////        behavior.addBottomSheetCallback(object  : BottomSheetBehaviorGoogleMapsLike.BottomSheetCallback(){
////            override fun onStateChanged(bottomSheet: View, newState: Int) {
////                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////            }
////
////            override fun onSlide(bottomSheet: View, slideOffset: Float) {
////                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////            }
////
////        }){
////
////        }
//        behavior.state         =BottomSheetBehaviorGoogleMapsLike.STATE_COLLAPSED
    }
}