/*
 * Copyright (c) 2019 Razeware LLC
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 *  distribute, sublicense, create a derivative work, and/or sell copies of the
 *  Software in any work that is designed, intended, or marketed for pedagogical or
 *  instructional purposes related to programming, coding, application development,
 *  or information technology.  Permission for such use, copying, modification,
 *  merger, publication, distribution, sublicensing, creation of derivative works,
 *  or sale is expressly withheld.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.zainco.library.coroutines.raywenderlich.snowy

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zainco.library.R
import com.zainco.library.coroutines.raywenderlich.snowy.model.Tutorial
import com.zainco.library.coroutines.raywenderlich.snowy.utils.SnowFilter
import kotlinx.android.synthetic.main.fragment_tutorial.*
import kotlinx.coroutines.*
import java.net.URL

class TutorialFragment : Fragment() {

    companion object {

        const val TUTORIAL_KEY = "TUTORIAL"

        fun newInstance(tutorial: Tutorial): TutorialFragment {
            val fragmentHome = TutorialFragment()
            val args = Bundle()
            args.putParcelable(TUTORIAL_KEY, tutorial)
            fragmentHome.arguments = args
            return fragmentHome
        }
    }

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            //2
            coroutineScope.launch(Dispatchers.Main) {
                //3
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = getString(R.string.error_message)
            }

            GlobalScope.launch { println("Caught $throwable") }
        }
    private val parentJob = Job()

    //Both the CoroutineDispatcher and a Job implement CoroutineContext. This allows you to sum them – using plus(), to combine their functionality.
    private val coroutineScope =
        CoroutineScope(Dispatchers.Main + parentJob + coroutineExceptionHandler)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* val tutorial = arguments?.getParcelable(TUTORIAL_KEY) as Tutorial
         val view = inflater.inflate(R.layout.fragment_tutorial, container, false)
         view.findViewById<TextView>(R.id.tutorialName).text = tutorial.name
         view.findViewById<TextView>(R.id.tutorialDesc).text = tutorial.description
 */
        return view
    }

    /* private fun getOriginalBitmapAsync(tutorial: Tutorial): Deferred<Bitmap> =
*//*This emphasizes that the result may not be immediately available.*//*
        coroutineScope.async(Dispatchers.IO) {
            URL(tutorial.url).openStream().use {
                return@async BitmapFactory.decodeStream(it)
            }
        }*/
    /*It is returning a value asynchronously. But async() is better used if you have multiple requests. It’s really useful for parallelism, as you can run a few operations,
     without blocking or suspending, at the same time. Just create multiple async() blocks!
But here, it’s much better to go on the path of using withContext():
Instead of deferring the value, you mark the functions with suspend. This tells the caller it’s using a coroutine, and it might take some time to finish. */
    private suspend fun getOriginalBitmapAsync(tutorial: Tutorial): Bitmap =
        withContext(Dispatchers.IO) {
            URL(tutorial.url).openStream().use {
                return@withContext BitmapFactory.decodeStream(it)
            }
        }

    /*
    Applying a filter is a heavy task because it has to work pixel-by-pixel, for the entire image. This is usually CPU intensive work, so you can use the Default dispatcher to use a worker thread.
     */
    private fun loadSnowFilterAsync(originalBitmap: Bitmap): Deferred<Bitmap> =
        coroutineScope.async(Dispatchers.Default) {
            SnowFilter.applySnowEffect(originalBitmap)
        }

    private fun loadImage(snowFilterBitmap: Bitmap) {
        progressBar.visibility = View.GONE
        snowFilterImage?.setImageBitmap(snowFilterBitmap)
    }

    override fun onDestroy() {
        super.onDestroy()
        parentJob.cancel()
    }
}
