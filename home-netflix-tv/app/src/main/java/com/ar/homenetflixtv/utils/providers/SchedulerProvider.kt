package com.ar.homenetflixtv.utils.providers

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun computation(): Scheduler

    fun ui(): Scheduler
}