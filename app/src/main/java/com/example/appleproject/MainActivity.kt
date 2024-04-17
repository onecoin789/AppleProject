package com.example.appleproject

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appleproject.Adapter.MainAdapter
import com.example.appleproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivNoticication.setOnClickListener {
            notification()
        }

        //recyclerview에 표시될 itemList
        var itemList = arrayListOf<Item>()
        itemList.add(
            Item(
                R.drawable.sample1,
                getString(R.string.sample1),
                getString(R.string.sample1_explain),
                getString(R.string.sample1_address),
                getString(R.string.sample1_name),
                getString(R.string.sample1_price).toInt(),
                getString(R.string.sample1_like),
                getString(R.string.sample1_chat)
            )
        )
        itemList.add(
            Item(
                R.drawable.sample2,
                getString(R.string.sample2),
                getString(R.string.sample2_explain),
                getString(R.string.sample2_address),
                getString(R.string.sample2_name),
                getString(R.string.sample2_price).toInt(),
                getString(R.string.sample2_like),
                getString(R.string.sample2_chat)
            )
        )
        itemList.add(
            Item(
                R.drawable.sample3,
                getString(R.string.sample3),
                getString(R.string.sample3_explain),
                getString(R.string.sample3_address),
                getString(R.string.sample3_name),
                getString(R.string.sample3_price).toInt(),
                getString(R.string.sample3_like),
                getString(R.string.sample3_chat)
            )
        )
        itemList.add(
            Item(
                R.drawable.sample4,
                getString(R.string.sample4),
                getString(R.string.sample4_explain),
                getString(R.string.sample4_address),
                getString(R.string.sample4_name),
                getString(R.string.sample4_price).toInt(),
                getString(R.string.sample4_like),
                getString(R.string.sample4_chat)
            )
        )
        itemList.add(
            Item(
                R.drawable.sample5,
                getString(R.string.sample5),
                getString(R.string.sample5_explain),
                getString(R.string.sample5_address),
                getString(R.string.sample5_name),
                getString(R.string.sample5_price).toInt(),
                getString(R.string.sample5_like),
                getString(R.string.sample5_chat)
            )
        )
        itemList.add(
            Item(
                R.drawable.sample6,
                getString(R.string.sample6),
                getString(R.string.sample6_explain),
                getString(R.string.sample6_address),
                getString(R.string.sample6_name),
                getString(R.string.sample6_price).toInt(),
                getString(R.string.sample6_like),
                getString(R.string.sample6_chat)
            )
        )
        itemList.add(
            Item(
                R.drawable.sample7,
                getString(R.string.sample7),
                getString(R.string.sample7_explain),
                getString(R.string.sample7_address),
                getString(R.string.sample7_name),
                getString(R.string.sample7_price).toInt(),
                getString(R.string.sample7_like),
                getString(R.string.sample7_chat)
            )
        )
        itemList.add(
            Item(
                R.drawable.sample8,
                getString(R.string.sample8),
                getString(R.string.sample8_explain),
                getString(R.string.sample8_address),
                getString(R.string.sample8_name),
                getString(R.string.sample8_price).toInt(),
                getString(R.string.sample8_like),
                getString(R.string.sample8_chat)
            )
        )
        itemList.add(
            Item(
                R.drawable.sample9,
                getString(R.string.sample9),
                getString(R.string.sample9_explain),
                getString(R.string.sample9_address),
                getString(R.string.sample9_name),
                getString(R.string.sample9_price).toInt(),
                getString(R.string.sample9_like),
                getString(R.string.sample9_chat)
            )
        )
        itemList.add(
            Item(
                R.drawable.sample10,
                getString(R.string.sample10),
                getString(R.string.sample10_explain),
                getString(R.string.sample10_address),
                getString(R.string.sample10_name),
                getString(R.string.sample10_price).toInt(),
                getString(R.string.sample10_like),
                getString(R.string.sample10_chat)
            )
        )


        binding.recyclerview.adapter = MainAdapter(itemList)

        val adapter = MainAdapter(itemList)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        //Detail Acitivty로 data보내기(짧게 클릭시)
        adapter.itemClick = object : MainAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                var intent = Intent(this@MainActivity, DetailActivity::class.java)
                var data = Item(
                    itemList[position].image,
                    itemList[position].title,
                    itemList[position].explain,
                    itemList[position].address,
                    itemList[position].name,
                    itemList[position].price,
                    itemList[position].like,
                    itemList[position].chat
                )
                intent.putExtra("data", data)
                startActivity(intent)
            }
        }
        // 길게 클릭시
        adapter.itemLongClick = object : MainAdapter.ItemLongClick {
            override fun onLongClick(view: View, position: Int) {
                val itemRemove = itemList[position]
                var builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("삭제")
                builder.setMessage("삭제하시겠습니까?")
                builder.setCancelable(false)

                builder.setPositiveButton("삭제") { dialog, which ->
                    itemList.remove(itemRemove)
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
                builder.setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.show()
            }
        }

        // 플로팅 버튼 기능
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop = true
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerview.canScrollVertically(-1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    binding.btnFloat.startAnimation(fadeOut)
                    binding.btnFloat.visibility = View.GONE
                    isTop = true
                } else if (isTop) {
                    binding.btnFloat.visibility = View.VISIBLE
                    binding.btnFloat.startAnimation(fadeIn)
                    isTop = false
                }
            }
        })
        binding.btnFloat.setOnClickListener {
            binding.recyclerview.smoothScrollToPosition(0)
        }
    }

    //뒤로가기 버튼 동작
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        var builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("종료")
        builder.setMessage("정말 종료하시겠습니까?")
        builder.setIcon(R.drawable.comment)
        builder.setCancelable(false)

        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                when (p1) {
                    DialogInterface.BUTTON_POSITIVE ->
                        finish()
                }
            }
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)

        builder.show()
    }

    @SuppressLint("NotificationPermission")
    fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channelId)
        } else {
            builder = NotificationCompat.Builder(this)
        }
        builder.run {
            setSmallIcon(R.drawable.notification)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
        }
        manager.notify(11, builder.build())
    }
}


// fragment용
//                var fragment = DetailFragment()
//                val bundle = Bundle()
//                bundle.putInt("image", itemList[position].image)
//                bundle.putString("title", itemList[position].title)
//                bundle.putString("explain", itemList[position].explain)
//                bundle.putString("address", itemList[position].address)
//                bundle.putString("name", itemList[position].name)
//                bundle.putInt("price", itemList[position].price)
//                fragment.arguments = bundle
//                setFragment(fragment)
//            }
//        }
//    }
//
//    private fun setFragment(frag: Fragment) {
//        supportFragmentManager.commit {
//            replace(R.id.frame, frag)
//            setReorderingAllowed(true)
//            addToBackStack("")
//        }
//    }
//}


