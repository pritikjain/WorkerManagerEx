# WorkerManagerEx
WorkManager library in Android

What is a WorkManager?

* WorkManager library is a part of Android JetPack and Architecture Components. 
  It is used to run deferrable background task (work). 
  Basically, it is meant for tasks that requires guaranteed execution even 
  if the app is in background. You can also specify some constraints a device 
  needs to fulfill before the work can run. For example, 
  suppose there is a task that requires intensive battery usage, 
  then that task can be run when the device is charging. 

In this code?
  * two examples one with OneTimeWorkRequest and another with PeriodicWorkRequest. 
