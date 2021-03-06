package com.example.madcamp_2nd.fb_app


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.madcamp_2nd.fb_app.tab1_fb.ContactFragment
import com.example.madcamp_2nd.fb_app.tab2_fb.GalleryFragment
import com.example.madcamp_2nd.fb_app.tab3_fb.CustomFragment

class MainAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> ContactFragment()
            1 -> GalleryFragment()
            2 -> CustomFragment()
            else -> ContactFragment()
        }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Contacts"
            1 -> "Gallery"
            2 -> "Custom"
            else -> "Contacts"
        }
    }
}

//
//class MainAdapter(manager: FragmentManager): PagerAdapter() {
//
//    private var list: ArrayList<BaseFragment> = ArrayList<BaseFragment>()
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val inflater = LayoutInflater.from(container.context)
//        val view = inflater.inflate(R.layout.fragment_main, container, false)
//        container.addView(view)
//        return view
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return list[position].title()
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
//        container.removeView(obj as View?)
//    }
//
//    override fun isViewFromObject(view: View, obj: Any): Boolean {
//        return view == obj
//    }
//
//    override fun getCount(): Int {
//        return list.size
//    }
//}