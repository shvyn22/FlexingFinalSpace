# FlexingFinalSpace
FlexingFinalSpace is an Android MVVM sample application created for learning purposes only.\
This application is based on [FinalSpaceAPI](https://finalspaceapi.com/) and provides information about characters, episodes and quotes of [Final Space](https://www.imdb.com/title/tt6317068/) series.

## Screenshots
### Light mode
<p float="left">
  <img src="screenshots/screen1.png" width=200/>
  <img src="screenshots/screen2.png" width=200/> 
  <img src="screenshots/screen3.png" width=200/>
  <img src="screenshots/screen4.png" width=200/>
</p>

### Dark mode
<p float="left">
  <img src="screenshots/screen1-dm.png" width=200/>
  <img src="screenshots/screen2-dm.png" width=200/> 
  <img src="screenshots/screen3-dm.png" width=200/>
  <img src="screenshots/screen4-dm.png" width=200/>
</p>

## Tech stack and concepts
* **[Kotlin](https://kotlinlang.org/)** as programming language.
* **[Kotlin coroutines](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/)** as framework for asynchronous jobs.
  * **Flow**(*StateFlow*) as dataholder for UI layer.  
* Single activity support.
  * **[Fragments](https://developer.android.com/jetpack/androidx/releases/fragment)**.
  * **[Navigation components](https://developer.android.com/jetpack/androidx/releases/navigation)**.
* Modern UI toolkit.
  * **[Material components](https://material.io/develop/android)**.
  * **ViewBinding** for binding .xml views to objects in Activities and Fragments.
* Api-based remote data layer.
  * **[Retrofit](https://square.github.io/retrofit/)** for network queries.
  * **[GSON](https://github.com/google/gson)** for parsing JSON.
  NOTE: considering switch to **Moshi**.
* **[DataStore](https://developer.android.com/jetpack/androidx/releases/datastore)** for working with user preferences (e.g. light/dark mode).
* **[Room](https://developer.android.com/jetpack/androidx/releases/room)** for local data layer.
* Caching remote data using **NetworkBoundResource** pattern (by utilizing Room, Retrofit and Flow).
* **[Lifecycle components](https://developer.android.com/jetpack/androidx/releases/lifecycle)**.
  * **ViewModel** for implementing MVVM pattern.
* **[Glide](https://github.com/bumptech/glide)** for working with images.
* Testing.
  * **JUnit**.
  * **[Android testing library](https://developer.android.com/jetpack/androidx/releases/test)**.
  * **Espresso** for UI-testing.
  * **Mockito** for mocking third-party classes (e.g. *NavController*).
  * Specific testing artifacts for other libraries.
* **[Hilt](https://dagger.dev/hilt/)** for dependency injection.

## Architecture
The application is divided into 3 layers:
1. **UI** (.ui): MainActivity, Fragments and ViewModels associated with them.
2. **Domain** (.repository): Repositories(abstractions and implementations).
3. **Data** (.data, .api): API service, DTOs for remote data; Database, DAOs, Models for local data.

For dependency flow there is .di package with corresponding modules. 

## Project structure
<img src="screenshots/project_structure.png" width=750/>

## Attribution
Icons:
* "Alive" and "Dead" icons made by [Pixel perfect](https://icon54.com/) from [Flaticon](https://www.flaticon.com/).
* "Episode" icon made by [Freepik](https://www.freepik.com) from [Flaticon](https://www.flaticon.com/).

## License
```
MIT License

Copyright (c) 2021 Shvyndia Andrii

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
