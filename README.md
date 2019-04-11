# Twitter Client

Twitter Client is a small Twitter clone that is written fully in kotlin and uses the MVVM architecture.  It uses 100% fake data and does not connect to any APIs.

### Features

  - Splash screen (with a small delay added to simulate a network call and show a progress dialog)
  - Login screen with error handling and another intentional delay to simulate a network call.  The correct credentials to log in are:
    `username: user123`
    `password: pass123`
  - List Screen to show a list of fake tweets.  For simplicity, a tweet only contains a user image, username, handle, and the tweet content itself.  This screen act somewhat similar to the normal twitter app in that it adds new tweets to the top of the list and you must scroll up to see them.
  - Add Tweet screen that allows you to enter in a tweet (limited to the 280 chars that twitter limits you to)
  - Ability to logout from the list screen
  - On launch, Twitter Client will show all tweets that are currently stored on the device (in a room database) and will then fetch "new" tweets (simply fetches 3 random hardcoded tweets).  These new tweets get added to the top of the list and you must scroll up to see them.

### Code Structure

 - Twitter client uses the MVVM architecture with databinding, dependency injection, rxjava, and a room database.  The business logic of each screen can be found in the appropriate ViewModel.  The activity itself essentially just binds the data and handles the appropriate lifecycle methods that are needed.  The activity has direct reference to its ViewModel via injection (the custom ViewModel factory is injected into the activity and that is used to grab the view model).  The ViewModel does not have any knowledge about the activity.  The ViewModels simply set livedata values.

 - The data layer is made up of a data source (an interface is used so that a real data source could be swapped in very easily), a repository, and a room database.  The repository is the only piece that is should be accessed by screens.  The repository can then decide to pull data from the database or from the data source.

 - The last piece is the shared preference helper (datamanager).  This is essentially just a delegate/manager combo that allows storing/retrieving data to and from shared preferences.

 - Also, there are a few unit tests written.  The ListViewModel and LoginViewModel have been fully tested.  For times sake, I have not added tests for all classes.


### Tech

Twitter Client uses a few different libraries:

* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Specifically LiveData, ViewModels, and the Room database
* [DataBinding](https://developer.android.com/topic/libraries/data-binding) - Super useful libary that allows you to bind data directly in your xml layouts
* [Dagger 2](https://github.com/google/dagger) - Dagger 2/Dagger Android is used for dependency injection
* [RxJava](https://github.com/ReactiveX/RxJava) - Rx is used mainly for threading/asynchronus tasks (ie. fetching items from the room database)
* [Glide](https://github.com/bumptech/glide) - Glide is an image loading library that Twitter Client uses to load user images
* [Circular Image View](https://github.com/hdodenhof/CircleImageView) - A handy library that creates a circular image view
* [LiveData Testing](https://github.com/jraska/livedata-testing) - This helps a lot with writing unit tests that involve livedata and asserting values on livedata
