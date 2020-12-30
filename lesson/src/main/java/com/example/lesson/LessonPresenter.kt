package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient.get
import com.example.core.utils.Utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.util.*

class LessonPresenter(private var activity: LessonActivity? = null) {
    companion object {
        private const val LESSON_PATH = "lessons"
    }

    private var lessons = mutableListOf<Lesson>()
    private val type = object : TypeToken<MutableList<Lesson>>() {}.type

    fun fetchData() {
        get(LessonPresenter.LESSON_PATH, type, object : EntityCallback<MutableList<Lesson>> {
            override fun onSuccess(entity: MutableList<Lesson>?) {
                if (entity != null) lessons = entity
                activity?.runOnUiThread { activity?.showResult(lessons) }
            }

            override fun onFailure(message: String) {
                activity?.runOnUiThread { toast(message) }
            }
        })
    }

    fun showPlayback() {
        val playbackLessons: MutableList<Lesson> = ArrayList()
        for (lesson in lessons) {
            if (lesson.state === Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity?.showResult(playbackLessons)
    }
}