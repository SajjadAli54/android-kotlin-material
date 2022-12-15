# Work Manager in Android

## Add the following depedency into the build.gradle file

```gradle
depedency{
	implementation 'androidx.work:work-runtime:2.7.1'
}
```

## Worker Code

```kotlin
package com.example.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("msg", inputData.getString("msg").toString())
        return Result.success()
    }

}
```

## Main Activity Code

```kotlin
package com.example.workmanagerdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun clickMe(view: View){
        val workManager: WorkManager = WorkManager.getInstance(this)
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()
        val request: OneTimeWorkRequest =
            OneTimeWorkRequest.Builder(MyWorker::class.java)
                .setConstraints(constraints)
                .setInputData(Data.Builder().putString("msg", "Hey dear").build())
                .build()

        workManager.enqueue(request)

    }
}
```
