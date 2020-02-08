package com.example.rxandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxandroid.R
import com.example.rxandroid.models.TasksModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observable: Observable<TasksModel> = Observable
            .fromIterable(createTaskList())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        observable.subscribe(object : Observer<TasksModel> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: TasksModel) {
                println(t.description)
            }

            override fun onError(e: Throwable) {

            }

        })
    }

    private fun createTaskList(): List<TasksModel> {
        val taskList: ArrayList<TasksModel> = ArrayList();

        taskList.add(TasksModel("Take out the trash", true))
        taskList.add(TasksModel("Walk the dog", false))
        taskList.add(TasksModel("Make my bed", true))
        taskList.add(TasksModel("Unload the dishwasher", false))
        taskList.add(TasksModel("Make dinner", true))

        return taskList;
    }
}
