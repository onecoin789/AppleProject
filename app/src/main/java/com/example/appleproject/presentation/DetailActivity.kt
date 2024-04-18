package com.example.appleproject.presentation


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appleproject.data.Item
import com.example.appleproject.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    companion object {
        const val EXTRA_ITEM: String = "extra_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val getItem = intent.getParcelableExtra<Item>(EXTRA_ITEM)

        binding.imgDetailMain.setImageResource(getItem!!.image)
        binding.textDetailName.text = getItem?.name
        binding.textDetailAddress.text = getItem?.address
        binding.textDetailTitle.text = getItem?.title
        binding.textDetailExplain.text = getItem?.explain
        binding.textDetailPrice.text =
            DecimalFormat("###,###원").format(getItem?.price).toString()


        binding.imgDetailClose.setOnClickListener {
            finish()
        }

        binding.imgDetail2.setOnCheckedChangeListener { ToggleButton, isChecked ->
            if (ToggleButton?.isChecked == true) {

                Snackbar.make(binding.layoutBar, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
