package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DRINK = "drink"
        const val EXTRA_SUGAR = "sugar"
        const val EXTRA_ICE = "ice"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sec)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step6：定義元件變數，並通過 findViewById 取得元件
        val edDrink = findViewById<TextView>(R.id.edDrink)
        val rgSugar = findViewById<RadioGroup>(R.id.rgSugar)
        val rgIce = findViewById<RadioGroup>(R.id.rgIce)
        val btnSend = findViewById<Button>(R.id.btnSend)
        // Step7：設定 btnSend 的點擊事件
        btnSend.setOnClickListener {
            // Step8：如果 edDrink 為空，則顯示提示文字
            if (drinkName.isBlank()) {
                Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show()
            } else {
                val sugar = rgSugar.getSelectedText()
                val ice = rgIce.getSelectedText()

                // 使用 apply 作用域函式來設定 Intent
                val resultIntent = Intent().apply {
                    putExtra(EXTRA_DRINK, drinkName)
                    putExtra(EXTRA_SUGAR, sugar)
                    putExtra(EXTRA_ICE, ice)
                }
                
                // 設定結果並關閉此 Activity
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun RadioGroup.getSelectedText(): String {
        val selectedId = this.checkedRadioButtonId
        return if (selectedId != -1) {
            findViewById<RadioButton>(selectedId).text.toString()
        } else {
            "未選擇" // 提供一個預設值以避免崩潰
        }
    }
}