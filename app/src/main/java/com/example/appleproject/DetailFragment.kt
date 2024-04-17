//package com.example.appleproject
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import com.example.appleproject.databinding.FragmentDetailBinding
//import java.text.DecimalFormat
//
//private const val ARG_PARAM1 = "param1"
//
//
//class DetailFragment : Fragment() {
//    lateinit var binding: FragmentDetailBinding
//
//    private var image: Int? = null
//    private var title: String? = null
//    private var explain: String? = null
//    private var address: String? = null
//    private var name: String? = null
//    private var price: Int? = null
//
//
//    private var param1: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            image = it.getInt("image")
//            title = it.getString("title")
//            explain = it.getString("explain")
//            address = it.getString("address")
//            name = it.getString("name")
//            price = it.getInt("price")
//
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentDetailBinding.inflate(inflater)
//
//        //back 버튼 종료
//        binding.imgDetailClose.setOnClickListener {
//            activity?.supportFragmentManager
//                ?.beginTransaction()
//                ?.remove(this)
//                ?.commit()
//        }
//        arguments?.let { binding.imgDetailMain.setImageResource(it.getInt("image")) }
//        binding.textDetailTitle.text = arguments?.getString("title").toString()
//        binding.textDetailExplain.text = arguments?.getString("explain").toString()
//        binding.textDetailAddress.text = arguments?.getString("address").toString()
//        binding.textDetailName.text = arguments?.getString("name").toString()
//        binding.textDetailPrice.text = DecimalFormat("###,###").format(arguments?.getInt("price")).toString() + "원"
//
//        return binding.root
//    }
//
//    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String) =
//            DetailFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                }
//            }
//    }
//}