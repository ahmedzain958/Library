package com.zainco.library.accessibility


import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.accessibilityservice.GestureDescription.StrokeDescription
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Path
import android.graphics.PixelFormat
import android.media.AudioManager
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.zainco.library.R
import java.util.*


class GlobalActionBarService : AccessibilityService() {
    var mLayout: FrameLayout? = null

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onServiceConnected() {
        super.onServiceConnected()
        //The code inflates the layout and adds the action bar towards the top of the screen.
        val wm =
            getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mLayout = FrameLayout(this)
        val lp = WindowManager.LayoutParams()
        //TYPE_ACCESSIBILITY_OVERLAY This permission lets you draw directly on the screen above existing content without having to go through a complicated permissions flow.
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
        lp.format = PixelFormat.TRANSLUCENT
        lp.flags = lp.flags or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.TOP
        val inflater = LayoutInflater.from(this)
        inflater.inflate(R.layout.action_bar, mLayout)
        wm.addView(mLayout, lp)
        val power = mLayout?.findViewById<Button>(R.id.power)
        val volume = mLayout?.findViewById<Button>(R.id.volume_up)
        val record = mLayout?.findViewById<Button>(R.id.record)
        val swipe = mLayout?.findViewById<Button>(R.id.swipe)
        power?.setOnClickListener {
            performGlobalAction(GLOBAL_ACTION_POWER_DIALOG)
        }
        volume?.setOnClickListener {
            val audioManager =
                getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioManager.adjustStreamVolume(
                AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI
            )
        }
        record?.setOnClickListener {
            /*val recorder: CustomMediaRecorder = CustomMediaRecorder.getInstance()
            var audiofile: File? = null
            val out: String = SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(Date())
            val sampleDir = File(getExternalFilesDir(null), "/TestRecordingDasa1")
            if (!sampleDir.exists()) {
                sampleDir.mkdirs()
            }
            val file_name = "Record"
            try {
                audiofile = File.createTempFile(file_name, ".amr", sampleDir)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            recorder.getRecorder().setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
            recorder.getRecorder().setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
            recorder.getRecorder().setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            recorder.getRecorder().setOutputFile(audiofile.getAbsolutePath())
            try {
                recorder.getRecorder().prepare()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            recorder.start(applicationContext)
*/
        }
        swipe?.setOnClickListener {
            val swipePath = Path()
            swipePath.moveTo(1000f, 1000f)
            swipePath.lineTo(100f, 1000f)
            val gestureBuilder = GestureDescription.Builder()
            gestureBuilder.addStroke(StrokeDescription(swipePath, 0, 500))
            dispatchGesture(gestureBuilder.build(), null, null)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun findScrollableNode(root: AccessibilityNodeInfo): AccessibilityNodeInfo? {
        val deque: Deque<AccessibilityNodeInfo> = ArrayDeque()
        deque.add(root)
        while (!deque.isEmpty()) {
            val node: AccessibilityNodeInfo = deque.removeFirst()
            if (node.actionList.contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD)
            ) {
                return node
            }
            for (i in 0 until node.childCount) {
                deque.addLast(node.getChild(i))
            }
        }
        return null
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        TODO("Not yet implemented")
    }
}
