package com.example.appleproject.presentation

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
import com.example.appleproject.R
import com.example.appleproject.data.DataSource
import com.example.appleproject.data.Item
import com.example.appleproject.data.itemList
import com.example.appleproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivNoticication.setOnClickListener {
            notification()
        }

        val itemList = DataSource.getDataSource().getItemList()
        val mainAdapter = MainAdapter(itemList)

        with(binding.recyclerview) {
            adapter = mainAdapter
            adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainAdapter.itemClick = object : MainAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(DetailActivity.EXTRA_ITEM, itemList[position])

                intent.putExtras(bundle)
                startActivity(intent)
            }
        }



    // 길게 클릭시
    mainAdapter.itemLongClick = object : MainAdapter.ItemLongClick {
        override fun onLongClick(view: View, position: Int) {
            val itemRemove = itemList[position]
            var builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("삭제")
            builder.setMessage("삭제하시겠습니까?")
            builder.setCancelable(false)

            builder.setPositiveButton("삭제") { dialog, which ->
                itemList.remove(itemRemove)
                mainAdapter.notifyDataSetChanged()
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
    binding.recyclerview.addOnScrollListener(
    object : RecyclerView.OnScrollListener() {
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


