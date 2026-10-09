package cn.edu.sicnu.cs.hln.secondhomework

class ProgramAdviser {
    fun getLanguage(feature: String): String {
        // 这里的字符串如果严格要求，也可以抽到 strings.xml，但作为 Model 层逻辑通常可以放在这里
        return when (feature) {
            "快速" -> "C"
            "容易" -> "Python"
            "新语言" -> "Kotlin"
            "面向对象" -> "Java"
            else -> "Unknown"
        }
    }
}