import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.parsehashmap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appLink = referrer?.toString()
        val linkParams = appLink?.let { parseLinkParams(it) }
        linkParams?.let { params ->
            params.forEach { (key, value) ->
                println("$key: $value")
            }
        }
    }

    private fun parseLinkParams(link: String): HashMap<String, String> {
        val params = hashMapOf<String, String>()
        val uri = Uri.parse(link)
        uri.queryParameterNames.forEach { paramName ->
            uri.getQueryParameter(paramName)?.let { paramValue ->
                params[paramName] = paramValue
            }
        }
        return params
    }
}
