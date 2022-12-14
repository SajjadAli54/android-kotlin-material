# Image rotation in Android

Create the following application using Thread / Service. When the user hits TOGGLE ROTATE button, the
image begin a rotation animation. This button works as a toggle mechanism, which means the image stops
a rotation animation by hitting the button again. The rotation will begin where it leaves off in the previous
animation.

Sample Output

![image](https://user-images.githubusercontent.com/58862894/202628712-b7538e88-cd62-44ea-8216-425f8bc6c499.png)

## Code

```kotlin
package com.example.imagerotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)

        Thread{
            while (true){
                if(flag){
                    imageView.post{
                        imageView.rotation = (imageView.rotation + 10)%360
                    }
                }
                Thread.sleep(100)
            }
        }.start()
    }

    fun handleToggle(view: View){
        flag = !flag
    }
}
```
