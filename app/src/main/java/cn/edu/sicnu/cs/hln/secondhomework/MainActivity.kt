package cn.edu.sicnu.cs.hln.secondhomework

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.edu.sicnu.cs.hln.secondhomework.databinding.ActivityMainBinding // 自动生成，如果报红请先 Sync

class MainActivity : AppCompatActivity() {

    // 1. 声明 ViewBinding
    private lateinit var binding: ActivityMainBinding

    // 2. 实例化 Model 层（区2的核心，对应老师视频里的 ProgramExpert）
    private val adviser = ProgramAdviser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 3. 初始化 ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 4. 设置系统安全区域（替代你原来 findViewById 的写法）
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ================= 区1：动态添加 TextView（代码生成UI） =================
        binding.btnAdd.setOnClickListener {
            // 动态创建 TextView
            val textView = TextView(this).apply {
                // 从 strings.xml 获取字符串，严禁硬编码
                text = getString(R.string.default_added_text) + " " + (binding.llContainer.childCount + 1)
                textSize = 16f
                gravity = Gravity.CENTER_VERTICAL
                setPadding(30, 30, 30, 30)
                setBackgroundColor(Color.WHITE) // 增加白色背景，方便看清

                // 设置布局参数，让它填满宽度，并带有上下间距
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 10, 0, 10)
                }
            }

            // 动态添加到 ScrollView 内部的 LinearLayout 中
            binding.llContainer.addView(textView)

            // 自动滚动到最底部，显示刚添加的 TextView
            binding.svContainer.post {
                binding.svContainer.fullScroll(View.FOCUS_DOWN)
            }
        }

        // ================= 区2：MVC 查询模式（参考老师教学视频） =================
        binding.button.setOnClickListener {
            // 1. 从 View 层获取用户选择的内容
            val selectedFeature = binding.spinner.selectedItem.toString()

            // 2. 调用 Model 层处理业务逻辑
            val result = adviser.getLanguage(selectedFeature)

            // 3. 将 Model 返回的结果更新到 View 层
            binding.textView.text = getString(R.string.result_prefix) + result
        }
    }
}