package com.example.lab6

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 使用 lazy 初始化，只有在第一次使用時才會執行 findViewById
        val btnToast by lazy { findViewById<Button>(R.id.btnToast) }
        val btnSnackBar by lazy { findViewById<Button>(R.id.btnSnackBar) }
        val btnDialog1 by lazy { findViewById<Button>(R.id.btnDialog1) }
        val btnDialog2 by lazy { findViewById<Button>(R.id.btnDialog2) }
        val btnDialog3 by lazy { findViewById<Button>(R.id.btnDialog3) }

        // 建立要顯示在的列表上的字串陣列
        val item = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

        // 設定按鈕的點擊事件
        btnToast.setOnClickListener {
            // 呼叫 showToast 方法，顯示 Toast 訊息
            showToast("預設 Toast")
        }

        btnSnackBar.setOnClickListener { view ->
            showUndoSnackbar(view)
        }

        btnDialog1.setOnClickListener {
            showConfirmationDialog()
        }

        btnDialog2.setOnClickListener {
            showListDialog(items)
        }

        btnDialog3.setOnClickListener {
            showSingleChoiceDialog(items)
        }
    }

    // 建立 showToast 方法，顯示 Toast 訊息
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    // 顯示 Snackbar 的輔助函式
    private fun showUndoSnackbar(view: View) {
        Snackbar.make(view, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
            .setAction("回應") {
                showToast("已回應")
            }
            .show()
    }

    // 顯示標準按鈕對話框
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("按鈕式 AlertDialog")
            .setMessage("這是 AlertDialog 的內容文字。")
            .setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
            .setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
            .setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
            .show()
    }

    // 顯示列表對話框
    private fun showListDialog(items: Array<String>) {
        MaterialAlertDialogBuilder(this)
            .setTitle("列表式 AlertDialog")
            .setItems(items) { _, which ->
                showToast("你選的是${items[which]}")
            }
            .show()
    }

    // 顯示單選對話框
    private fun showSingleChoiceDialog(items: Array<String>) {
        var selectedPosition = 0
        MaterialAlertDialogBuilder(this)
            .setTitle("單選式 AlertDialog")
            .setSingleChoiceItems(items, selectedPosition) { _, which ->
                selectedPosition = which
            }
            .setPositiveButton("確定") { _, _ ->
                showToast("你選的是${items[selectedPosition]}")
            }
            .setNegativeButton("取消", null) // 提供一個取消按鈕是好的使用者體驗
            .show()
    }
}